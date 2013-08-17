using System;
using System.Collections.Generic;
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
using Microsoft.Phone.Controls;
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

                listbox_remind.ItemsSource = reminds;
            };
            Uri uri = new Uri(Config.URL_REMIND(eid));
            wclient.DownloadStringAsync(uri);
            
        }

        private void loadEms() {
            Grid<FS_Employee> local_ems = Api.loadEms();
            if (local_ems.rows.Count <= 1)
            {
                WebClient wclient = new WebClient();
                wclient.DownloadStringCompleted += (s, e) =>
                {
                    Grid<FS_Employee> grid = Api.getEms(e, true);
                    listbox_em.Items.Clear();
                    listbox_em.ItemsSource = grid.rows;
                    panitem_contact.Header += " " + grid.total;
                };
                Uri uri = new Uri(Config.URL_EM_GRID(0, int.MaxValue));
                wclient.DownloadStringAsync(uri);
            }
            else {
                listbox_em.Items.Clear();
                listbox_em.ItemsSource = local_ems.rows;
                panitem_contact.Header += " " + local_ems.total;
            }
        }
    }
}