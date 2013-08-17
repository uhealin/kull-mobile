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
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using Pccpa.WP.Util;
using Pccpa.WP.ViewModels;

namespace Pccpa.WP
{
    public partial class Login : PhoneApplicationPage
    {
        public Login()
        {
            InitializeComponent();
            if (Config.isDebug)
            {
                tbxLoginID.Text = Config.TEST_LOGINID;
                pbxPwd.Password = Config.TEST_PWD;
            }
        }

        private void btnLogin_Click(object sender, RoutedEventArgs e)
        {
            
            string loginid = tbxLoginID.Text;
            string pwd = pbxPwd.Password;
            if (string.IsNullOrEmpty(loginid))
            {
                MessageBox.Show("用户名不能为空");
                return;
            }else if (string.IsNullOrEmpty(pwd))
            {
                MessageBox.Show("密码不能为空");
                return;
            }
            
            WebClient client=new WebClient();
            Uri uri = Config.Login(loginid, pwd);
            
            
            
            client.DownloadStringCompleted += (s, re) =>
            {
                ResultModel rs = Api.getResult(client,re);
                if (rs.isSuccess)
                {
                    string url = string.Format("/MainPage.xaml?EID={0}",rs.action);
                    Uri mainUri=new Uri(url,UriKind.Relative);
                    NavigationService.Navigate(mainUri);
                }
                else
                {
                    MessageBox.Show(rs.msg);
                }
            };
            client.DownloadStringAsync(uri);
        }
    }
}