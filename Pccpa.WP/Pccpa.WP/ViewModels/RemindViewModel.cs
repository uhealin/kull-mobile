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
using System.Collections.Generic;

namespace Pccpa.WP.ViewModels
{
    public class RemindViewModel
    {
        public string menuID { get; set; }

        public string title
        {
            get;
            set;
        }

        public string text
        {
            get;
            set;
        }

        public int total
        {
            get;
            set;
        }
    }

    public class Reminds
    {
        public List<RemindViewModel> remindList { get; set; }
        public List<RemindViewModel> applyList { get; set; }
    }
}
