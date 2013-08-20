package com.kull.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;





import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
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
	
	
	
	public static int BUFFER_SIZE = 4096;  
	
	public static String streamToString(InputStream is) throws IOException{
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = is.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null; 
	    return new String(outStream.toByteArray()); 
	}
	
	
	
	public static String doGet(String url,HttpGet httpGet,HttpResponse httpResponse) throws Exception{
		
		   httpGet=httpGet==null?new HttpGet(url):httpGet;
		   httpResponse=new DefaultHttpClient().execute(httpGet);
		   InputStream is= httpResponse.getEntity().getContent();
	       String context= streamToString(is);
	       return context; 
	}
	
	public static String doPost(String url,Map<String, Object> params,HttpPost httpPost,HttpResponse httpResponse)throws Exception{
	      HttpParams httpparams=new BasicHttpParams();
	      for(String key :params.keySet()){
	    	  httpparams.setParameter(key, params.get(key));
	      }
	      return doPost(url, httpparams, httpPost, httpResponse);
	}
	
	public static String doPost(String url,HttpParams params,HttpPost httpPost,HttpResponse httpResponse)throws Exception{
		 
		  httpPost=httpPost==null?new HttpPost(url):httpPost;
		  httpPost.setParams(params);
		  httpResponse=new DefaultHttpClient().execute(httpPost);
		  InputStream is=httpResponse.getEntity().getContent();
		  String context=streamToString(is);
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
}
