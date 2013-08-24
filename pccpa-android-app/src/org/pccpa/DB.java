package org.pccpa;

import android.content.Context;

import com.kull.android.SQLiteOrmHelper;

public enum DB {

	
	backup,local; 
	
	public SQLiteOrmHelper createSqLiteOrmHelper(Context context){
		return new SQLiteOrmHelper(context,this.name());
	}
}
