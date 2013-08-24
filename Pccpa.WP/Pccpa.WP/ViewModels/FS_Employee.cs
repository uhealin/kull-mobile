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
using System.ComponentModel;
using System.Data.Linq.Mapping;
using Pccpa.WP.Util;

namespace Pccpa.WP.ViewModels
{
   [Table]
    public   class FS_Employee : INotifyPropertyChanging, INotifyPropertyChanged
    {

        private static PropertyChangingEventArgs emptyChangingEventArgs = new PropertyChangingEventArgs(String.Empty);

      

        private string _EID;

      

        private string _EUserName;

     
        private string _EIdentityCard;


        private string _ETelWork;
        private string _ETelWorkShort;
        private string _EMobile;

        public FS_Employee()
        {
           
        }


       [Column(IsPrimaryKey = true, CanBeNull = false, IsDbGenerated = false, AutoSync = AutoSync.OnInsert)]
       public string EID
        {
            get
            {
                return this._EID;
            }
            set
            {
                if ((this._EID != value))
                {
                    //this.OnEIDChanging(value);
                    NotifyPropertyChanging("EID");
                    this._EID = value;
                    NotifyPropertyChanged("EID");
                    //this.OnEIDChanged();
                }
            }
        }

        

        [Column]
        public string EUserName
        {
            get
            {
                return this._EUserName;
            }
            set
            {
                if ((this._EUserName != value))
                {
                    //this.OnEUserNameChanging(value);
                    NotifyPropertyChanging("EUserName");
                    this._EUserName = value;
                    NotifyPropertyChanged("EUserName");
                    //this.OnEUserNameChanged();
                }
            }
        }

       

        [Column]
        public string EIdentityCard
        {
            get
            {
                return this._EIdentityCard;
            }
            set
            {
                if ((this._EIdentityCard != value))
                {
                    //this.OnEIdentityCardChanging(value);
                    NotifyPropertyChanging("EIdentityCard");
                    this._EIdentityCard = value;
                    NotifyPropertyChanged("EIdentityCard");
                    //this.OnEIdentityCardChanged();
                }
            }
        }

        [Column]
        public String ETelWork
        {
            get { return this._ETelWork; }

            set { this._ETelWork = value; }
        }

        [Column]
        public String ETelWorkShort
        {
            get { return this._ETelWorkShort; }

            set { this._ETelWorkShort = value; }
        }

        [Column]
        public String EMobile
        {
            get { return this._EMobile; }

            set { this._EMobile = value; }
        } 

       public string pathPhoto
       {
           get
           {
               return string.Format(Config.URL_PATTERN_EM_PHOTO, this.EID);
           }
       }

       #region INotifyPropertyChanged Members

        public event PropertyChangedEventHandler PropertyChanged;

        //用来通知页面表的字段数据产生了改变
        private void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }

        #endregion

        #region INotifyPropertyChanging Members

        public event PropertyChangingEventHandler PropertyChanging;

        // 用来通知数据上下文表的字段数据将要产生改变
        private void NotifyPropertyChanging(string propertyName)
        {
            if (PropertyChanging != null)
            {
                PropertyChanging(this, new PropertyChangingEventArgs(propertyName));
            }
        }

        #endregion
        
        }

       
    }

