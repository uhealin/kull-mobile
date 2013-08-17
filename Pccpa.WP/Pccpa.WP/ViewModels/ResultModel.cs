using System;
using System.Collections.Generic;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace Pccpa.WP.ViewModels
{
    public class ResultModel
    {
        public string msg
        {
            get; set;
        }

        public string action
        {
            get;
            set;
        }

        public int code
        {
            get; set;

        }

       

        public bool isSuccess
        {
            get
            {
                return this.code == 0;
            }
        }


    }
}
