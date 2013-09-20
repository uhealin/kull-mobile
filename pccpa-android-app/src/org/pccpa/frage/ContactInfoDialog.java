package org.pccpa.frage;

import java.text.MessageFormat;

import org.pccpa.R;
import org.pccpa.api.Contact;

import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.kull.StringHelper;
import com.kull.android.ContextHelper;

public class ContactInfoDialog extends SherlockDialogFragment {

	private Contact _contact;
	
	
	
	
	
	public Contact get_contact() {
		return _contact;
	}





	public void set_contact(Contact _contact) {
		this._contact = _contact;
	}





	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final ContextHelper contextHelper=new ContextHelper(getActivity());
		View view=inflater.inflate(R.layout.dialog_contact_info, container,false);
		TextView txvEmName=(TextView)view.findViewById(R.id.txvEmName)
		 ,txvDepName=(TextView)view.findViewById(R.id.txvDepName)
		 ,txvMobile=(TextView)view.findViewById(R.id.txvMobile)
		 ,txvTel=(TextView)view.findViewById(R.id.txvTel)
		 ,txvEmail=(TextView)view.findViewById(R.id.txvEmail)
         ,txvTelShort=(TextView)view.findViewById(R.id.txvTelShort)
	     ,txvMobileShort=(TextView)view.findViewById(R.id.txvMobileShort)
	     ,txvRankName=(TextView)view.findViewById(R.id.txvRankName)
		 ;
		ImageButton imbCallMobile=(ImageButton)view.findViewById(R.id.imbCallMobile)
				,imbCallMobileShort=(ImageButton)view.findViewById(R.id.imbCallMobileShort)
			    ,imbSendEmail=(ImageButton)view.findViewById(R.id.imbSendMail)
			   ,imbSendSMSMobile=(ImageButton)view.findViewById(R.id.imbSendSMSMobile)
		;
		Button btnSaveToPhone=(Button)view.findViewById(R.id.btnSaveToPhone);
        txvEmName.setText(_contact.getEUserName());
        txvDepName.setText(StringHelper.concat(_contact.getAreaName()," ",_contact.getDepartName()));
        txvMobile.setText(_contact.getEMobile());
        txvTel.setText(_contact.getETelWork());
        txvMobileShort.setText(_contact.getEMobileShort());
        txvTelShort.setText(_contact.getETelWorkShort());
        txvEmail.setText(_contact.getEMail());
        txvRankName.setText(_contact.getRankName());
        if(StringHelper.isBlank(_contact.getEMobile() ) ){
           imbCallMobile.setVisibility(View.INVISIBLE);
           imbSendSMSMobile.setVisibility(View.INVISIBLE);
        }else{
        imbCallMobile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			      Intent intent=	contextHelper.toCallTel(_contact.getEMobile());
				  getActivity().startActivity(intent);
			}
			
			
		});
        
        
         imbSendSMSMobile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    	Intent intent= contextHelper.toSendSms(_contact.getEMobile(),"OA短信：");
			    	getActivity().startActivity(intent);
			}
		});
        
        }
        if(StringHelper.isBlank(_contact.getEMobileShort() ) ){
            imbCallMobileShort.setVisibility(View.INVISIBLE);
         }else{
           imbCallMobileShort.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    	Intent intent= contextHelper.toCallTel(_contact.getEMobileShort());
			    	getActivity().startActivity(intent);
				
			}
		  });
         }
        
        if(StringHelper.isBlank(_contact.getEMail() ) ){
        	imbSendEmail.setVisibility(View.INVISIBLE);
         }else{
        	 imbSendEmail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    	//contextHelper.toTel(_contact.getEMobileShort());
				 Intent intent= contextHelper.toSendMail(_contact.getEMail());
				 getActivity().startActivity(intent);
			}
		  });
         }
        
        btnSaveToPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    try {
					contextHelper.doSaveContact(_contact.getEUserName(),
							StringHelper.firstNotBlank(_contact.getEMobile(),_contact.getEMobileShort())
							, _contact.getEMail());
					 Toast.makeText(getActivity(), 
						      MessageFormat.format("{0} 的信息已成功保存到手机", _contact.getEUserName())
						    		, 3000).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(), 
						      MessageFormat.format("添加失败:{0}", e.getMessage())
						    		, 5000).show();
					e.printStackTrace();
				} 
			   
			}
		});
        
		return view;
	}

}
