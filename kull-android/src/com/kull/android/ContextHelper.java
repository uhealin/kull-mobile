package com.kull.android;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
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
	
	public static Intent toCallTel(String telNumber){
		Uri uri=Uri.parse("tel:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_DIAL,uri);
		//_context.startActivity(intent);
		 return intent;
	}
	
	public static Intent doCallTel(String telNumber){
		Uri uri=Uri.parse("tel:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_CALL,uri);
		//_context.startActivity(intent);
		 return intent;
	}
	
	public static Intent toSendSms(String telNumber,String context){
		Uri uri=Uri.parse("smsto:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_VIEW,uri);
		//intent.putExtra("smsto", telNumber);
		intent.putExtra("sms_body", context);
		intent.setType("vnd.android-dir/mms-sms");
		 return intent;
		//_context.startActivity(intent);
	}
	
	public static Intent doSendSms(String telNumber,String context){
		Uri uri=Uri.parse("smsto:"+telNumber);
		Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
		intent.putExtra("sms_body", context);
		 return intent;
		//_context.startActivity(intent);
	}
	
	public static Intent toSendMail(String email){
		Uri uri=Uri.parse("mailto:"+email);
		Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
		 return intent;
		//_context.startActivity(intent);
	}
	
	public static Intent doSendMail(String[] toAddrs,String copyAddrs,String text,String subject){
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_EMAIL, toAddrs);
		intent.putExtra(Intent.EXTRA_CC, copyAddrs);
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.setType("message/rfc882");
		Intent.createChooser(intent, "Choose Email Client");
		//_context.startActivity(intent);
	    return intent;
	}
	
	public ContentProviderResult[] doSaveContact(String displayName,String mobile,String email) throws RemoteException, OperationApplicationException{
		
        ContentResolver cr=_context.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        Uri uri = ContactsContract.RawContacts.CONTENT_URI;
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
            .withValue("account_name", null)
            .build();
        operations.add(op1);
        
       
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/name")
            .withValue("data2", displayName)
            .build();
        operations.add(op2);
        
        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
            .withValue("data1", mobile)            
            .withValue("data2", "2")
            .build();
        operations.add(op3);
        
        ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
        .withValueBackReference("raw_contact_id", 0)
        .withValue("mimetype", "vnd.android.cursor.item/email_v2")
        .withValue("data1", email)            
        .withValue("data2", "2")
        .build();
         operations.add(op4);
        
        ContentProviderResult[] res= cr.applyBatch("com.android.contacts", operations);
        return res;
	}
}
