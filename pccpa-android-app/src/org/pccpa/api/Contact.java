package org.pccpa.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.pccpa.DB;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.kull.android.OrmTable;
import com.kull.android.SQLiteOrmHelper;
import com.kull.android.widget.AsyncImageView;
import com.kull.bean.JdbcBean.Database;

@OrmTable(name="contact",pk="EID")
public class Contact {

	 protected String AOrderNO;
	 protected String AreaName;
	 protected String DepartName;
	 protected String AreaID;
	 protected String DFatherID;
	 protected String DOrderNO;
	
	 protected String EID;
	 protected String ELoginID;
	 protected Integer EStyle;
	 protected String EUserName;
	 protected Integer ESex;
	 protected String DepartID;
	 protected String EOrderNO;
	 protected String RankID;
	 protected String EMail;
	 protected String ETelWork;
	 protected String ETelWorkShort;
	 protected String EMobile;
	 protected String EMobileShort;

	 protected String RankName;
	 protected String ROrderNO;
	 
	 protected byte[] photo;
     protected Long photo_update_time=new Long(-1);
	 

	 public String getAOrderNO () { 	 return this.AOrderNO;  	} 
 	 public void setAOrderNO (String AOrderNO){  	 this.AOrderNO=AOrderNO ;  	} 

	 public String getAreaName () { 	 return this.AreaName;  	} 
 	 public void setAreaName (String AreaName){  	 this.AreaName=AreaName ;  	} 

	 public String getDepartName () { 	 return this.DepartName;  	} 
 	 public void setDepartName (String DepartName){  	 this.DepartName=DepartName ;  	} 

	 public String getAreaID () { 	 return this.AreaID;  	} 
 	 public void setAreaID (String AreaID){  	 this.AreaID=AreaID ;  	} 

	 public String getDFatherID () { 	 return this.DFatherID;  	} 
 	 public void setDFatherID (String DFatherID){  	 this.DFatherID=DFatherID ;  	} 

	 public String getDOrderNO () { 	 return this.DOrderNO;  	} 
 	 public void setDOrderNO (String DOrderNO){  	 this.DOrderNO=DOrderNO ;  	} 



	 public String getEID () { 	 return this.EID;  	} 
 	 public void setEID (String EID){  	 this.EID=EID ;  	} 

	 public String getELoginID () { 	 return this.ELoginID;  	} 
 	 public void setELoginID (String ELoginID){  	 this.ELoginID=ELoginID ;  	} 

	 

	 public Integer getEStyle () { 	 return this.EStyle;  	} 
 	 public void setEStyle (Integer EStyle){  	 this.EStyle=EStyle ;  	} 

	 public String getEUserName () { 	 return this.EUserName;  	} 
 	 public void setEUserName (String EUserName){  	 this.EUserName=EUserName ;  	} 

	 public Integer getESex () { 	 return this.ESex;  	} 
 	 public void setESex (Integer ESex){  	 this.ESex=ESex ;  	} 

	

	 public String getDepartID () { 	 return this.DepartID;  	} 
 	 public void setDepartID (String DepartID){  	 this.DepartID=DepartID ;  	} 

	 public String getEOrderNO () { 	 return this.EOrderNO;  	} 
 	 public void setEOrderNO (String EOrderNO){  	 this.EOrderNO=EOrderNO ;  	} 

	 public String getRankID () { 	 return this.RankID;  	} 
 	 public void setRankID (String RankID){  	 this.RankID=RankID ;  	} 

	 

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

	 

	 public String getRankName () { 	 return this.RankName;  	} 
 	 public void setRankName (String RankName){  	 this.RankName=RankName ;  	} 

	 public String getROrderNO () { 	 return this.ROrderNO;  	} 
 	 public void setROrderNO (String ROrderNO){  	 this.ROrderNO=ROrderNO ;  	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Long getPhoto_update_time() {
		return photo_update_time;
	}
	public void setPhoto_update_time(Long photo_update_time) {
		this.photo_update_time = photo_update_time;
	} 

	
	public static void setupImageView(final Context context,final Contact m,AsyncImageView imageView,Options options){
		if(m==null)return;
		if(m.getPhoto_update_time()<0||m.getPhoto()==null){
			  String url=Client.urlEmployeePhoto(m.getEID());
		      imageView.setUrl(url);
		      imageView.setOnImageViewLoadListener(new AsyncImageView.OnImageViewLoadListener() {
				
				@Override
				public void onLoadingStarted(AsyncImageView imageView) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLoadingFailed(AsyncImageView imageView, Throwable throwable) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLoadingEnded( AsyncImageView imageView, Bitmap image) {
					// TODO Auto-generated method stub
					
				    	m.setPhoto_update_time(new Date().getTime());
				    	ByteArrayOutputStream bos=new ByteArrayOutputStream();
				    	image.compress(Bitmap.CompressFormat.PNG, 100, bos);
				    	m.setPhoto(bos.toByteArray());
				        SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(context);
				        
				    	try {
				    		sqLiteOrmHelper.update(m);
							bos.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
		    }else{
		      Bitmap bitmap=BitmapFactory.decodeByteArray(m.getPhoto(),0, m.getPhoto().length,options);
		      imageView.setImageBitmap(bitmap);	
		    }
	}
 	 
 	 
}
