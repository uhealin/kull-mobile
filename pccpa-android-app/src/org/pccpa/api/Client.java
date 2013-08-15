package org.pccpa.api;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.pccpa.R;

import android.content.Context;
import android.os.StrictMode;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

public class Client {

	private final static HttpTransport TRANSPORT;
	private final static HttpRequestFactory FACTORY;
	final static int BUFFER_SIZE = 4096;  
	
	static{
		TRANSPORT=new NetHttpTransport();
		FACTORY=TRANSPORT.createRequestFactory();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
	}
	
	public static String doGet(String url,HttpGet httpGet,HttpResponse httpResponse) throws Exception{
		
	   httpGet=new HttpGet(url);
	   httpResponse=new DefaultHttpClient().execute(httpGet);
	   InputStream is= httpResponse.getEntity().getContent();
	   ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
       byte[] data = new byte[BUFFER_SIZE];  
       int count = -1;  
       while((count = is.read(data,0,BUFFER_SIZE)) != -1)  
           outStream.write(data, 0, count);  
         
       data = null;  
       String context= new String(outStream.toByteArray()); 
       return context; 
	}
	
	public static String doGet2(String url,com.google.api.client.http.HttpRequest request,com.google.api.client.http.HttpResponse	httpResponse) throws IOException{
		String context="";
		
		GenericUrl genericUrl=new GenericUrl(url);
		request=FACTORY.buildGetRequest(genericUrl);
	   	httpResponse=request.execute();
		InputStream is= httpResponse.getContent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = is.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        context= new String(outStream.toByteArray()); 
		return context;
	}
	
	
	
    private String eid,path_base,path_remind_pattern;
    private Context context;
    
    public Client(Context context){
    	this.context=context;
    	eid=context.getString(R.string.cfg_testeid);
    	init();
    }
    
    
    public Client(Context context,String eid){
    	this.context=context;
    	this.eid=eid;
    	init();
    }
    
    private void init(){
    	path_base=context.getString(R.string.cfg_path_base);
    	path_remind_pattern=context.getString(R.string.cfg_path_reminds_pattern);
    
    }
    
    
    public List<RemindItem> getReminds() throws Exception{
    	
    	String url=MessageFormat.format(path_base+path_remind_pattern,eid);
    	String context=doGet2(url, null,null);
    	Gson gson=new Gson();
    	RemindsAdapter remindsAdapter= gson.fromJson(context, RemindsAdapter.class);
    	List<RemindItem> reminds=new ArrayList<RemindItem>();
    	reminds.addAll(remindsAdapter.applyList);
    	reminds.addAll(remindsAdapter.remindList);
    	return reminds;
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
