﻿using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace Kull.WP
{
    public class StringHelper
    {

        public static  string findFirstNotEmpty(params string[] vals)
        {
            foreach (string val in vals)
            {
                if (!string.IsNullOrEmpty(val)) return val;
            }
            return string.Empty;
        }
    }
}