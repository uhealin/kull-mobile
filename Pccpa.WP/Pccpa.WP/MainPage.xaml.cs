using System;
using System.Collections.Generic;
using System.Data.Linq;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Kull.WP;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Tasks;
using Pccpa.WP.ViewModels;
using Pccpa.WP.Util;

namespace Pccpa.WP
{
    public partial class MainPage : PhoneApplicationPage
    {

        string eid;

        // 构造函数
        public MainPage()
        {
            InitializeComponent();

            // 将 listbox 控件的数据上下文设置为示例数据
            DataContext = App.ViewModel;
           
            this.Loaded += new RoutedEventHandler(MainPage_Loaded);
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            IDictionary<string, string> param = this.NavigationContext.QueryString;
            this.eid = param["EID"];
            this.Title = eid;
        }

        // 为 ViewModel 项加载数据
        private void MainPage_Loaded(object sender, RoutedEventArgs e)
        {
            if (!App.ViewModel.IsDataLoaded)
            {
                App.ViewModel.LoadData();
            }
            loadReminds();
            loadEms();
        }


        private void loadReminds() {
            
            WebClient wclient = new WebClient();

            wclient.DownloadStringCompleted += (s, e) =>
            {
                List<RemindViewModel> reminds = Api.getReminds(e);
                listbox_remind.Items.Clear();
                
                foreach (RemindViewModel remind in reminds)
                {
                    listbox_remind.Items.Add(remind);
                }
            };
            Uri uri = new Uri(Config.URL_REMIND(eid));
            wclient.DownloadStringAsync(uri);
            
        }

        private void loadEms() {
           listbox_em.Items.Clear();
            using (DataContext dc=DataContextFactory.defaultDataContext())
            {
                Table<FS_Employee> table_em = dc.GetTable<FS_Employee>();
                IEnumerator<FS_Employee> ems= table_em.GetEnumerator();
                while (ems.MoveNext())
                {
                    listbox_em.Items.Add(ems.Current);
                }
            }
          
        }

        private void MenuItemReloadRemind_Click(object sender, EventArgs e)
        {
            loadReminds();
        }

        private void MenuItemReloadContact_Click(object sender, EventArgs e)
        {
            WebClient client = new WebClient();
            Uri uri = new Uri(Config.URL_PATTERN_EM_GRID);
            client.DownloadStringCompleted += (s, ee) =>
            {
                Grid<FS_Employee> ems = Api.getEms(ee,true);
                loadEms();
            };
            client.DownloadStringAsync(uri);
        }

        private void MenuItemLogout_Click(object sender, EventArgs e)
        {
            string url = string.Format("/Login.xaml");
            Uri mainUri = new Uri(url, UriKind.Relative);
            NavigationService.Navigate(mainUri);
        }

        private void MenuItemAbout_Click(object sender, EventArgs e)
        {
           
        }

        private void listbox_em_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ListBox listbox = (ListBox)sender;
            FS_Employee em = listbox.SelectedItem as FS_Employee;
            PhoneCallTask t = new PhoneCallTask();
            t.PhoneNumber = StringHelper.firstNotEmpty(em.EMobile, em.ETelWork);
            t.DisplayName =em.EUserName ;
            t.Show();
        }
    }
}