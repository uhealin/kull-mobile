package org.pccpa.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class ToastHandler extends Handler {

	private Context context;
	
	public ToastHandler(Context context){
		this.context=context;
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		Toast.makeText(context, msg.obj.toString(), 2000);
	}

}
