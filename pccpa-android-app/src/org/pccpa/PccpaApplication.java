package org.pccpa;

import com.kull.android.app.KullApplication;



public class PccpaApplication extends KullApplication {

	@Override
	public Class<?> getHomeActivityClass() {
		// TODO Auto-generated method stub
		return ContactActivity.class;
	}

	
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

}
