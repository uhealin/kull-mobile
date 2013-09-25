package org.pccpa;

import greendroid.app.GDApplication;

public class PccpaApplication extends GDApplication {

	@Override
	public Class<?> getHomeActivityClass() {
		// TODO Auto-generated method stub
		return ContactActivity.class;
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

}
