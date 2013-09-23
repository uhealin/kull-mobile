package org.pccpa;

import java.io.File;

import com.kull.android.ContextHelper;
import com.kull.android.IOHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class InitActivity extends Activity {

	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_init);
		String path=Environment.getExternalStorageDirectory()+"/pccpa";
		File file=new File(path);
	
		
		try {
			if(!file.exists())
				file.createNewFile();
			//path+="/contact_photo";
			//file=new File(path);
			//if(!file.exists())
			//	file.createNewFile();
			DB.local.initDB(this);
			ContextHelper contextHelper=new ContextHelper(this);
			contextHelper.to(LoginActivity.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "≥ı ºªØ ß∞‹:"+e.getLocalizedMessage(), 5000).show();
		}
	}

	
	
	
	
	
}
