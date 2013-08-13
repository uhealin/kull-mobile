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

namespace Pccpa.WP.Util
{
    public class Config
    {
        public const string TEST_EID = "101506";


        //http://localhost:53641/    http://oanet.pccpa.cn
        public const string HOST_PCCPA = "http://localhost:53641";

        public const string URL_PATTERN_REMIND =HOST_PCCPA+"/SYS/D_Menu/reminds/{0}";

        public const string URL_PATTERN_EM_GRID = HOST_PCCPA + "/FS/V_Employee/grid?start={0}&limit={1}";


        public static string CONNSTR(string db)
        {
            return string.Format("Data Source=isostore:/{0}.sdf",db);

        }

        public static string URL_REMIND(string eid)
        {
            return string.Format(URL_PATTERN_REMIND, eid);
        }

        public static string URL_EM_GRID(int start,int limit)
        {
            return string.Format(URL_PATTERN_EM_GRID, start, limit);
        }

    }
}
