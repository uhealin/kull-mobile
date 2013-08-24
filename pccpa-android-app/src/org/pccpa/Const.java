package org.pccpa;

import com.kull.android.SQLiteOrmHelper;

import android.content.Context;
import android.os.StrictMode;

public class Const {

	
	static{
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
	}
	
	
	
	
	
	
	
	
}
