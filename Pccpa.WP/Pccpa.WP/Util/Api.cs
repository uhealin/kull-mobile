using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Newtonsoft.Json;
using Pccpa.WP.ViewModels;
using System.Collections.Generic;
using WP7_WebLib.HttpGet;
using System.Text;
using System.IO;
using System.Threading;

using System.Data.Linq;

namespace Pccpa.WP.Util
{
    public class Api
    {

       

        public static string doGet(string url)
        {
            string context = "";
            WebClient client = new WebClient();
            
            client.DownloadStringCompleted += (s, e) =>
            {
                context = e.Result;
               
            };
            client.DownloadStringAsync(new Uri(url));
          
            
            return context;
        }

        public static List<RemindViewModel> getReminds(DownloadStringCompletedEventArgs args) {
            List<RemindViewModel> rs = new List<RemindViewModel>();

            string context = args.Result;
            Reminds reminds = JsonConvert.DeserializeObject<Reminds>(context);
            rs.AddRange(reminds.applyList);
            rs.AddRange(reminds.remindList);
            return rs;

        }

        public static Grid<FS_Employee> loadEms(){
            Grid<FS_Employee> rs = new Grid<FS_Employee>();
            IDictionary<string, FS_Employee> dict_ems_local = new Dictionary<string, FS_Employee>();
            using ( DefaultDataContext dc= DataContextFactory.defaultDataContext())
            {
                
                IEnumerator<FS_Employee> ite = dc.table_em.GetEnumerator();
                
                while (ite.MoveNext())
                {
                    dict_ems_local[ite.Current.EID] = ite.Current;
                }
            }
            rs.total = dict_ems_local.Count;
            rs.rows = dict_ems_local.Values;
            return rs;
        }

        public static Grid<FS_Employee> getEms(DownloadStringCompletedEventArgs args,bool synDB)
        {
            Grid<FS_Employee> rs = new Grid<FS_Employee>();

            string context = args.Result;
            rs = JsonConvert.DeserializeObject<Grid<FS_Employee>>(context);

            if (synDB) {
                using (DataContext dc = DataContextFactory.defaultDataContext()) {
                    IDictionary<string, FS_Employee> dict_ems_remote = new Dictionary<string, FS_Employee>()
                        ,dict_ems_local = new Dictionary<string, FS_Employee>();
                    Table<FS_Employee> table_em = dc.GetTable<FS_Employee>();

                    ICollection<FS_Employee> list_ems_local = loadEms().rows;

                    foreach (FS_Employee em in list_ems_local) {
                        dict_ems_local[em.EID] = em;
                    }

                    foreach (FS_Employee em in rs.rows)
                    {
                        if (dict_ems_local.ContainsKey(em.EID))
                        {
                            dict_ems_local[em.EID] = em;
                        }
                        else {
                            table_em.InsertOnSubmit(em);
                        }
                    }
                    dc.SubmitChanges();
                } 
            }

            return rs;

        }

        public static ResultModel getResult(WebClient client, DownloadStringCompletedEventArgs args)
        {
            
            ResultModel rs=new ResultModel();
            
            try
            {
                string context = args.Result;
                rs = JsonConvert.DeserializeObject<ResultModel>(context);
            }
            catch (Exception ex)
            {
                rs.code = 3;

                rs.msg = string.Format("error:{0},reason:{1}",client.BaseAddress, ex.Message);
            }
            return rs;
        }

    }
}
