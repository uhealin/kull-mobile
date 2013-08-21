package org.pccpa.api;


import com.kull.android.*;


@OrmTable(name="fs_employee",pk="EID")
public class EmployeeItem {

	 protected Integer ID;
	 protected String EID;
	 protected String ELoginID;
	 protected String EPassword;
	 protected String EModifyLoginIDDateTime;
	 protected String EModifyPasswordDateTime;
	 protected Integer EStyle;
	 protected String EUserName;
	 protected Integer ESex;
	 protected String EBirthday;
	 protected String EIdentityCard;
	 protected String DepartID;
	 protected String EOrderNO;
	 protected String RankID;
	 protected String EBankCard;
	 protected String EMail;
	 protected String ETelWork;
	 protected String ETelWorkShort;
	 protected String EMobile;
	 protected String EMobileShort;
	 protected String RoleIDString;
	 protected String MenuIDString;
	 protected Integer EIsPartner;
	 protected String PICName;
	 protected String EFloor;
	 protected String ERace;
	 protected String EOrigo;
	 protected String EStartWorkDate;
	 protected String EInPccpaDate;
	 protected Integer EIsCollectiveHuKou;
	 protected String ECaption;
	 protected String EOutPccpaDate;
	 protected String EIsCanDinner;
	 protected String EIsHelpKQ;
	 protected String EAddSystemDateTime;
	 protected String EZhuguanEID;
	 protected String EZhongjingliEID;
	 protected String EFenguanEID;
	 protected String EAreaFirstEID;


	 public Integer getID () { 	 return this.ID;  	} 
	 public void setID (Integer ID){  	 this.ID=ID ;  	} 

	 public String getEID () { 	 return this.EID;  	} 
	 public void setEID (String EID){  	 this.EID=EID ;  	} 

	 public String getELoginID () { 	 return this.ELoginID;  	} 
	 public void setELoginID (String ELoginID){  	 this.ELoginID=ELoginID ;  	} 

	 public String getEPassword () { 	 return this.EPassword;  	} 
	 public void setEPassword (String EPassword){  	 this.EPassword=EPassword ;  	} 

	 public String getEModifyLoginIDDateTime () { 	 return this.EModifyLoginIDDateTime;  	} 
	 public void setEModifyLoginIDDateTime (String EModifyLoginIDDateTime){  	 this.EModifyLoginIDDateTime=EModifyLoginIDDateTime ;  	} 

	 public String getEModifyPasswordDateTime () { 	 return this.EModifyPasswordDateTime;  	} 
	 public void setEModifyPasswordDateTime (String EModifyPasswordDateTime){  	 this.EModifyPasswordDateTime=EModifyPasswordDateTime ;  	} 

	 public Integer getEStyle () { 	 return this.EStyle;  	} 
	 public void setEStyle (Integer EStyle){  	 this.EStyle=EStyle ;  	} 

	 public String getEUserName () { 	 return this.EUserName;  	} 
	 public void setEUserName (String EUserName){  	 this.EUserName=EUserName ;  	} 

	 public Integer getESex () { 	 return this.ESex;  	} 
	 public void setESex (Integer ESex){  	 this.ESex=ESex ;  	} 

	 public String getEBirthday () { 	 return this.EBirthday;  	} 
	 public void setEBirthday (String EBirthday){  	 this.EBirthday=EBirthday ;  	} 

	 public String getEIdentityCard () { 	 return this.EIdentityCard;  	} 
	 public void setEIdentityCard (String EIdentityCard){  	 this.EIdentityCard=EIdentityCard ;  	} 

	 public String getDepartID () { 	 return this.DepartID;  	} 
	 public void setDepartID (String DepartID){  	 this.DepartID=DepartID ;  	} 

	 public String getEOrderNO () { 	 return this.EOrderNO;  	} 
	 public void setEOrderNO (String EOrderNO){  	 this.EOrderNO=EOrderNO ;  	} 

	 public String getRankID () { 	 return this.RankID;  	} 
	 public void setRankID (String RankID){  	 this.RankID=RankID ;  	} 

	 public String getEBankCard () { 	 return this.EBankCard;  	} 
	 public void setEBankCard (String EBankCard){  	 this.EBankCard=EBankCard ;  	} 

	 public String getEMail () { 	 return this.EMail;  	} 
	 public void setEMail (String EMail){  	 this.EMail=EMail ;  	} 

	 public String getETelWork () { 	 return this.ETelWork;  	} 
	 public void setETelWork (String ETelWork){  	 this.ETelWork=ETelWork ;  	} 

	 public String getETelWorkShort () { 	 return this.ETelWorkShort;  	} 
	 public void setETelWorkShort (String ETelWorkShort){  	 this.ETelWorkShort=ETelWorkShort ;  	} 

	 public String getEMobile () { 	 return this.EMobile;  	} 
	 public void setEMobile (String EMobile){  	 this.EMobile=EMobile ;  	} 

	 public String getEMobileShort () { 	 return this.EMobileShort;  	} 
	 public void setEMobileShort (String EMobileShort){  	 this.EMobileShort=EMobileShort ;  	} 

	 public String getRoleIDString () { 	 return this.RoleIDString;  	} 
	 public void setRoleIDString (String RoleIDString){  	 this.RoleIDString=RoleIDString ;  	} 

	 public String getMenuIDString () { 	 return this.MenuIDString;  	} 
	 public void setMenuIDString (String MenuIDString){  	 this.MenuIDString=MenuIDString ;  	} 

	 public Integer getEIsPartner () { 	 return this.EIsPartner;  	} 
	 public void setEIsPartner (Integer EIsPartner){  	 this.EIsPartner=EIsPartner ;  	} 

	 public String getPICName () { 	 return this.PICName;  	} 
	 public void setPICName (String PICName){  	 this.PICName=PICName ;  	} 

	 public String getEFloor () { 	 return this.EFloor;  	} 
	 public void setEFloor (String EFloor){  	 this.EFloor=EFloor ;  	} 

	 public String getERace () { 	 return this.ERace;  	} 
	 public void setERace (String ERace){  	 this.ERace=ERace ;  	} 

	 public String getEOrigo () { 	 return this.EOrigo;  	} 
	 public void setEOrigo (String EOrigo){  	 this.EOrigo=EOrigo ;  	} 

	 public String getEStartWorkDate () { 	 return this.EStartWorkDate;  	} 
	 public void setEStartWorkDate (String EStartWorkDate){  	 this.EStartWorkDate=EStartWorkDate ;  	} 

	 public String getEInPccpaDate () { 	 return this.EInPccpaDate;  	} 
	 public void setEInPccpaDate (String EInPccpaDate){  	 this.EInPccpaDate=EInPccpaDate ;  	} 

	 public Integer getEIsCollectiveHuKou () { 	 return this.EIsCollectiveHuKou;  	} 
	 public void setEIsCollectiveHuKou (Integer EIsCollectiveHuKou){  	 this.EIsCollectiveHuKou=EIsCollectiveHuKou ;  	} 

	 public String getECaption () { 	 return this.ECaption;  	} 
	 public void setECaption (String ECaption){  	 this.ECaption=ECaption ;  	} 

	 public String getEOutPccpaDate () { 	 return this.EOutPccpaDate;  	} 
	 public void setEOutPccpaDate (String EOutPccpaDate){  	 this.EOutPccpaDate=EOutPccpaDate ;  	} 

	 public String getEIsCanDinner () { 	 return this.EIsCanDinner;  	} 
	 public void setEIsCanDinner (String EIsCanDinner){  	 this.EIsCanDinner=EIsCanDinner ;  	} 

	 public String getEIsHelpKQ () { 	 return this.EIsHelpKQ;  	} 
	 public void setEIsHelpKQ (String EIsHelpKQ){  	 this.EIsHelpKQ=EIsHelpKQ ;  	} 

	 public String getEAddSystemDateTime () { 	 return this.EAddSystemDateTime;  	} 
	 public void setEAddSystemDateTime (String EAddSystemDateTime){  	 this.EAddSystemDateTime=EAddSystemDateTime ;  	} 

	 public String getEZhuguanEID () { 	 return this.EZhuguanEID;  	} 
	 public void setEZhuguanEID (String EZhuguanEID){  	 this.EZhuguanEID=EZhuguanEID ;  	} 

	 public String getEZhongjingliEID () { 	 return this.EZhongjingliEID;  	} 
	 public void setEZhongjingliEID (String EZhongjingliEID){  	 this.EZhongjingliEID=EZhongjingliEID ;  	} 

	 public String getEFenguanEID () { 	 return this.EFenguanEID;  	} 
	 public void setEFenguanEID (String EFenguanEID){  	 this.EFenguanEID=EFenguanEID ;  	} 

	 public String getEAreaFirstEID () { 	 return this.EAreaFirstEID;  	} 
	 public void setEAreaFirstEID (String EAreaFirstEID){  	 this.EAreaFirstEID=EAreaFirstEID ;  	} 
	
	
	
}
