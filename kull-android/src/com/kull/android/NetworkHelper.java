package com.kull.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;





import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;

import com.kull.StringHelper;
import com.kull.douban.BaseEntity;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;


@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class NetworkHelper{

	public enum NetworkType{
		none,wifi,cnnet,cnwap,unknow
	}
	
	
	
	public static int BUFFER_SIZE = 2048;  
	
	public static String streamToString(InputStream is) throws IOException{
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = is.read(data,0,data.length)) != -1)  {
            outStream.write(data, 0, count); 
            outStream.flush();
        }
        data = null; 
        String str=outStream.toString();
	    outStream.close();
	    outStream=null;
	   
	    //System.runFinalization();
	    return str;
	}
	
	
	
	public static String doGet(String url,HttpGet httpGet,HttpResponse httpResponse) throws Exception{
		
		   //httpGet=httpGet==null?new HttpGet(url):httpGet;
		   //httpResponse=new DefaultHttpClient().execute(httpGet);
		   InputStream is= doGetStream(url, httpGet, httpResponse);
	       String context= streamToString(is);
	     
	       is.close();
	       is=null;
	      
	       System.gc();
	       return context; 
	}
	
	public static InputStream doGetStream(String url,HttpGet httpGet,HttpResponse httpResponse) throws Exception{
		
		   httpGet=httpGet==null?new HttpGet(url):httpGet;
		   httpResponse=new DefaultHttpClient().execute(httpGet);
		   InputStream is= httpResponse.getEntity().getContent();
		  
	      return is; 
	}
	
	public static String doPost(String url,Map<String, Object> params,HttpPost httpPost,HttpResponse httpResponse)throws Exception{
	      //HttpParams httpparams=new BasicHttpParams();
	      List <NameValuePair> nvps=new ArrayList<NameValuePair>();
	      for(String key :params.keySet()){
	    	  //httpparams.setParameter(key, params.get(key));
	         nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
	      }
	      httpPost=httpPost==null?new HttpPost(url):httpPost;
		  httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		  httpResponse=new DefaultHttpClient().execute(httpPost);
		  InputStream is=httpResponse.getEntity().getContent();
		  String context=streamToString(is);
		  is.close();
		  is=null;
	       System.gc();
		  return context;
	}
	
	public static String doPost(String url,HttpParams params,HttpPost httpPost,HttpResponse httpResponse)throws Exception{
		 
		  httpPost=httpPost==null?new HttpPost(url):httpPost;
		  //httpPost.setParams(params);
		  DefaultedHttpParams dhp=new DefaultedHttpParams(params, params);
		  httpResponse=new DefaultHttpClient(dhp).execute(httpPost);
		  InputStream is=httpResponse.getEntity().getContent();
		  String context=streamToString(is);
		  is.close();
		  is=null;
	       System.gc();
		  return context;
	}
	
	
	public static NetworkType checkNetwork(Context context){
		NetworkType netType = NetworkType.none;
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return NetworkType.none;
		}		
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if(StringHelper.isNotBlank(extraInfo)){
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NetworkType.cnnet;
				} else {
					netType = NetworkType.cnwap;
				}
			}else{
				return NetworkType.unknow;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NetworkType.wifi;
		}
		return netType;
	}
	
	public static void enableNetwrokOnMainThread(){
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads()  
        .detectDiskWrites()  
        .detectNetwork()   // or .detectAll() for all detectable problems  
        .penaltyLog()  
        .build());  
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects()  
        
        .penaltyLog()  
        .penaltyDeath()  
        .build());  
	}
}
