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
using System.Data.Linq;
using Pccpa.WP.ViewModels;

namespace Pccpa.WP.Util
{
    public class DataContextFactory
    {


        public static DataContext dataContextByName(string db)
        {
            string conn = Config.CONNSTR(db);
           
            DataContext dc =new DataContext(conn);
            if (!dc.DatabaseExists()) { 
                dc.CreateDatabase(); 
            
            }
           
            return dc;
        }

        public static DefaultDataContext defaultDataContext()
        {

            DefaultDataContext dc= new DefaultDataContext(Config.CONNSTR("pccpa"));
            if (!dc.DatabaseExists()) {
                dc.CreateDatabase();
                FS_Employee em = new FS_Employee();
                em.EID = "test";
                dc.table_em.InsertOnSubmit(em);
                dc.SubmitChanges();
                dc.table_em.DeleteOnSubmit(em);
            }
            
            return dc;
        }
    }
}
