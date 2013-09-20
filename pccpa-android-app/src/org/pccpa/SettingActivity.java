package org.pccpa;

import org.pccpa.api.SiteSynRunnable;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class SettingActivity extends BaseFragmentActivity{

	Button btnSiteSyn;
	Thread synThread;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_setting);
		btnSiteSyn=(Button)this.findViewById(R.id.btnSiteSyn);
		if(synThread==null){
    		SiteSynRunnable siteSynRunnable=new SiteSynRunnable(this);
    		synThread= new Thread(siteSynRunnable);
    		//synThread.run();
    	}
		btnSiteSyn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if(!synThread.isAlive()){
		    		synThread.start();
		    	}
			}
		});
	}

}
