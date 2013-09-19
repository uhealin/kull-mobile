package com.kull.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ContextHelper {

	
	protected Context _context;

	public ContextHelper(Context context){
		this._context=context;
		
	}
	
	public void to(Class<? extends Context> cls){
		Intent intent=new Intent(_context,cls);
		_context.startActivity(intent);
	}
	
	
	public void alert(Exception ex,int duration){
		Toast.makeText(_context, ex.getLocalizedMessage(),duration).show();
	}
	
	public void toTel(String telNumber){
		Uri uri=Uri.parse("tel:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_CALL,uri);
		_context.startActivity(intent);
	}
	
	public void toSms(String telNumber,String context){
		Uri uri=Uri.parse("smsto:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_VIEW);
		intent.putExtra("smsto", telNumber);
		intent.putExtra("sms_body", context);
		intent.setType("vnd.android-dir/mms-sms");
		_context.startActivity(intent);
	}
	
	public void sendSms(String telNumber,String context){
		Uri uri=Uri.parse("smsto:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
		intent.putExtra("sms_body", context);

		_context.startActivity(intent);
	}
}
