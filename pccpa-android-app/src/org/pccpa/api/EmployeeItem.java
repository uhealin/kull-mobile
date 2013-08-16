package org.pccpa.api;

import com.kull.annotation.SimpleOrmTable;



@SimpleOrmTable(name="fs_employee",pk="EID")
public class EmployeeItem {
      
	private String EID,EUserName;

	public String getEID() {
		return EID;
	}

	public void setEID(String eID) {
		EID = eID;
	}

	public String getEUserName() {
		return EUserName;
	}

	public void setEUserName(String eUserName) {
		EUserName = eUserName;
	}
	
	
	
	
}
