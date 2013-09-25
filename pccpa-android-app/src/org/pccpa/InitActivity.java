package org.pccpa;

import java.io.File;

import org.pccpa.api.SiteSynTask;

import com.actionbarsherlock.app.SherlockActivity;
import com.kull.android.ContextHelper;
import com.kull.android.IOHelper;
import com.kull.android.NetworkHelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class InitActivity extends SherlockActivity {

	InitSiteSynTask initSiteSynTask;
	TextView txvInitInfo;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_init);
		txvInitInfo=(TextView)this.findViewById(R.id.txvInitInfo);
		NetworkHelper.enableNetwrokOnMainThread();
		initSiteSynTask=new InitSiteSynTask(this);
		initSiteSynTask.execute((Void)null);
	}

	
	private class InitSiteSynTask extends SiteSynTask{

		
		
		public InitSiteSynTask(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Toast.makeText(_context, "开始初始化...", 2000);
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String path=Environment.getExternalStorageDirectory()+"/pccpa";
			File file=new File(path);
			try {
				if(!file.exists())
					file.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
			boolean re=DB.local.initDB(_context);
			if(re){
			  return super.doInBackground(params);
			}
			
			return re;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			ContextHelper contextHelper=new ContextHelper(_context);
			contextHelper.to(LoginActivity.class);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		
		
	}
	
	
	
}
