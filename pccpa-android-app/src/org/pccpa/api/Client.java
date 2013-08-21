package org.pccpa.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.pccpa.R;
import org.pccpa.R.string;

import android.content.Context;
import android.os.StrictMode;


import com.google.gson.Gson;
import com.kull.android.*;;

public class Client {


	private final static Gson GSON=new Gson();
	final static int BUFFER_SIZE = 4096;  
	
	
	
	static{
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build()); 
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		try {
			RHelper.init(R.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
    private static String path_base="http://oanet.pccpa.cn",
    path_remind_pattern="/SYS/D_Menu/reminds/{0}"
    ,path_grid_pattern="/{0}/{1}/grid",url_home_dologin="/SYS/Home/doLogin"
    ;

    private String eid;
    public static Client CURR_CLIENT;
    
    
    
    
    private Client(String eid){
    
    	this.eid=eid;
  
    }
    
  
    
    
    public List<RemindItem> getReminds() throws Exception{
    	
    	String url=MessageFormat.format(path_base+path_remind_pattern,eid);
    	String context=NetworkHelper.doGet(url, null,null);
    	
    	RemindsAdapter remindsAdapter= GSON.fromJson(context, RemindsAdapter.class);
    	List<RemindItem> reminds=new ArrayList<RemindItem>();
    	reminds.addAll(remindsAdapter.applyList);
    	reminds.addAll(remindsAdapter.remindList);
    	return reminds;
    }
    
    public List<EmployeeItem> getEms(int start,int limit) throws Exception{
    	String url=MessageFormat.format(path_base+path_grid_pattern,"FS","V_Employee");
    	url+=MessageFormat.format("?start={0}&limit={1}",start+"",limit+"");
    	String context=NetworkHelper.doGet(url, null,null);
    	EMGrid grid=GSON.fromJson(context, EMGrid.class);
        return grid.getRows();
    }
    
    
    public static Result doLogin(String ELoginID,String EPassword) throws Exception{
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("ELoginID", ELoginID);
    	param.put("EPassword", EPassword);
    	//HttpParams param=new BasicHttpParams();
    	//param.setParameter("ELoginID", ELoginID);
    	//param.setParameter("EPassword", EPassword);
    	String url=MessageFormat.format(path_base+url_home_dologin, ELoginID,EPassword);
    	
    	String context=NetworkHelper.doPost(url, param, null, null);
    	Result result=GSON.fromJson(context, Result.class);
    	if(result.code==0){
    		CURR_CLIENT=new Client(result.action);
    	}
    	return result;
    }
    
    public class Result {
    	private String msg,action,icon;
    	private int code;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
    	
    }
    
    public class EMGrid extends Grid<EmployeeItem>{}
    
    private class Grid<T>{
    	
    	private int total;
    	private List<T> rows;
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public List<T> getRows() {
			return rows;
		}
		public void setRows(List<T> rows) {
			this.rows = rows;
		}
    	
    	
    }
    
    
    private class RemindsAdapter{
    	private List<RemindItem> remindList,applyList;

		public List<RemindItem> getRemindList() {
			return remindList;
		}

		public void setRemindList(List<RemindItem> remindList) {
			this.remindList = remindList;
		}

		public List<RemindItem> getApplyList() {
			return applyList;
		}

		public void setApplyList(List<RemindItem> applyList) {
			this.applyList = applyList;
		}
    	
    	
    }
}
