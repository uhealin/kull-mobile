package org.pccpa;

import org.pccpa.api.Contact;
import org.pccpa.api.SiteSynRunnable;

import android.content.Context;

import com.kull.android.SQLiteOrmHelper;

public enum DB {

	
	backup,local; 
	
	public SQLiteOrmHelper createSqLiteOrmHelper(Context context){
		return new SQLiteOrmHelper(context,this.name());
	}
	
	
	public void initDB(Context context) throws Exception{
		SQLiteOrmHelper sqLiteOrmHelper=createSqLiteOrmHelper(context);
		try{
			sqLiteOrmHelper.select(Contact.class,0,1);
		}catch(Exception ex){
			//sqLiteOrmHelper.createTable(Contact.class);
			SiteSynRunnable siteSynRunnable=new SiteSynRunnable(context);
			new Thread(siteSynRunnable).start();
		}
		
		
		
	}
}
