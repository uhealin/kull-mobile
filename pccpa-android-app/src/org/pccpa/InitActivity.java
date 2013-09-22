package org.pccpa;

import com.kull.android.ContextHelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class InitActivity extends Activity {

	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_init);
		
		try {
			DB.local.initDB(this);
			ContextHelper contextHelper=new ContextHelper(this);
			contextHelper.to(LoginActivity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "≥ı ºªØ ß∞‹:"+e.getLocalizedMessage(), 5000).show();
		}
	}

	
	
	
	
	
}
