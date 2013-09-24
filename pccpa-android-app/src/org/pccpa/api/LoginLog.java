package org.pccpa.api;

import com.kull.android.OrmTable;

@OrmTable(name="login_log",pk="eid")
public class LoginLog {

	
	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	private String loginid,eid,pwd;
	
	private Long last_login_mis;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getLast_login_mis() {
		return last_login_mis;
	}

	public void setLast_login_mis(Long last_login_mis) {
		this.last_login_mis = last_login_mis;
	}
	
	
	
}
