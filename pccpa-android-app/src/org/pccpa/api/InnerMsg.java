package org.pccpa.api;

import com.kull.android.OrmTable;

@OrmTable(name="inner_msg",pk="MRID")
public class InnerMsg {
	 protected Integer ID;
	 protected String MRID;
	 protected String MRProcessID;
	 protected String MRPostEID;
	 protected String MRReceiveEID;
	 protected String MRTitle;
	 protected String MRContent;
	 protected Integer MRIsRead;
	 protected Integer MRIsPop;
	 protected String MRUrl;
	 protected String MRPostDateTime;


	 public Integer getID () { 	 return this.ID;  	} 
 	 public void setID (Integer ID){  	 this.ID=ID ;  	} 

	 public String getMRID () { 	 return this.MRID;  	} 
 	 public void setMRID (String MRID){  	 this.MRID=MRID ;  	} 

	 public String getMRProcessID () { 	 return this.MRProcessID;  	} 
 	 public void setMRProcessID (String MRProcessID){  	 this.MRProcessID=MRProcessID ;  	} 

	 public String getMRPostEID () { 	 return this.MRPostEID;  	} 
 	 public void setMRPostEID (String MRPostEID){  	 this.MRPostEID=MRPostEID ;  	} 

	 public String getMRReceiveEID () { 	 return this.MRReceiveEID;  	} 
 	 public void setMRReceiveEID (String MRReceiveEID){  	 this.MRReceiveEID=MRReceiveEID ;  	} 

	 public String getMRTitle () { 	 return this.MRTitle;  	} 
 	 public void setMRTitle (String MRTitle){  	 this.MRTitle=MRTitle ;  	} 

	 public String getMRContent () { 	 return this.MRContent;  	} 
 	 public void setMRContent (String MRContent){  	 this.MRContent=MRContent ;  	} 

	 public Integer getMRIsRead () { 	 return this.MRIsRead;  	} 
 	 public void setMRIsRead (Integer MRIsRead){  	 this.MRIsRead=MRIsRead ;  	} 

	 public Integer getMRIsPop () { 	 return this.MRIsPop;  	} 
 	 public void setMRIsPop (Integer MRIsPop){  	 this.MRIsPop=MRIsPop ;  	} 

	 public String getMRUrl () { 	 return this.MRUrl;  	} 
 	 public void setMRUrl (String MRUrl){  	 this.MRUrl=MRUrl ;  	} 

	 public String getMRPostDateTime () { 	 return this.MRPostDateTime;  	} 
 	 public void setMRPostDateTime (String MRPostDateTime){  	 this.MRPostDateTime=MRPostDateTime ;  	} 
}
