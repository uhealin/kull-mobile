package org.pccpa;

import java.text.MessageFormat;

import org.pccpa.api.Client;
import org.pccpa.api.Contact;
import org.pccpa.api.LoginLog;
import org.pccpa.api.SiteSynRunnable;
import org.pccpa.api.SiteSynTask;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.kull.android.SQLiteOrmHelper;

public class SettingActivity extends BaseFragmentActivity{

	Button btnSiteSyn,btnLogout;
	TextView txvLoginInfo,txvSynInfo;
	Thread synThread;
	SettingSiteSynTask settingSiteSynTask;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.setContentView(R.layout.activity_setting);
		btnSiteSyn=(Button)this.findViewById(R.id.btnSiteSyn);
		btnLogout=(Button)this.findViewById(R.id.btnDoLogin);
		txvLoginInfo=(TextView)this.findViewById(R.id.txvLoginInfo);
		txvSynInfo=(TextView)this.findViewById(R.id.txvSynInfo);
		if(synThread==null){
    		SiteSynRunnable siteSynRunnable=new SiteSynRunnable(this);
    		synThread= new Thread(siteSynRunnable);
    		//synThread.run();
    	}
		btnSiteSyn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// if(!synThread.isAlive()){
		    	//	synThread.start();
		    	//}
				settingSiteSynTask=new SettingSiteSynTask(getApplicationContext());
				settingSiteSynTask.execute((Void)null);
			}
		});
	
        btnLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Client.CURR_CLIENT=null;
				 DB.local.createSqLiteOrmHelper(getApplicationContext()).truncateTable(LoginLog.class);
				 contextHelper.to(LoginActivity.class);
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
		refreshInfo();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
		super.onStart();
		refreshInfo();
	}

	private void refreshInfo(){
		Contact contact=Client.CURR_CLIENT.getContact();
		txvLoginInfo.setText(MessageFormat.format("当前登录用户： {0} {1} {2}",
				 contact.getAreaName()
				 ,contact.getDepartName()
				 ,contact.getEUserName()
				));
		SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(this);
		int contactCount=0;
        try {
			contactCount=sqLiteOrmHelper.count(Contact.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txvSynInfo.setText(MessageFormat.format("通信录记录数 :{0}", contactCount));
	}
	
	private class SettingSiteSynTask extends SiteSynTask{

		public SettingSiteSynTask(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(_context, "开始同步数据...", 3000).show();
		}
		
	}
}
