package org.pccpa;

import org.pccpa.remind.RemindListActivity;

import android.content.Intent;
import android.net.Uri;
import greendroid.app.GDApplication;

public class PccpaApplication extends GDApplication {

	@Override
	public Class<?> getHomeActivityClass() {
		// TODO Auto-generated method stub
		return RemindListActivity.class;
	}

	@Override
	public Intent getMainApplicationIntent() {
		// TODO Auto-generated method stub
		 return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_url)));
	}

	
	
	
}
