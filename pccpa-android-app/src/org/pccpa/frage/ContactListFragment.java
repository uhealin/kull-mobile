package org.pccpa.frage;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pccpa.ContactActivity;
import org.pccpa.DB;
import org.pccpa.R;

import org.pccpa.adapter.EmployeeItemAdapter;

import org.pccpa.api.Client;
import org.pccpa.api.Contact;
import org.pccpa.api.EmployeeItem;

import greendroid.image.ImageProcessor;
import greendroid.widget.AsyncImageView;
import android.R.string;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView;


import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.internal.widget.IcsAdapterView;


import com.kull.StringHelper;
import com.kull.android.SQLiteOrmHelper;
import com.kull.android.widget.ListItemAdapter;

public class ContactListFragment extends SherlockListFragment 
implements OnScrollListener,OnItemSelectedListener,OnItemClickListener,TextWatcher{
	
	private ContactListAdapter adapter;
	
	SQLiteOrmHelper sqLiteOrmHelper;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	   adapter=new ContactListAdapter(getActivity());
	   adapter.items=ContactActivity.CONTACT_ALL;
	   this.setListAdapter(adapter);
	   sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(getActivity());
	   getListView().setOnScrollListener(this);
       //getListView().setOnItemSelectedListener(this);
       //getListView().setOnItemClickListener(this);
       
	}


	@Override
	public void onScrollStateChanged(AbsListView viewGroup, int scrollState) {
		// TODO Auto-generated method stub
		if (getListView() == viewGroup) {
            searchAsyncImageViews(viewGroup, scrollState == OnScrollListener.SCROLL_STATE_FLING);
        }
	}
	
	
	private void searchAsyncImageViews(ViewGroup viewGroup, boolean pause) {
        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AsyncImageView image = (AsyncImageView) viewGroup.getChildAt(i).findViewById(org.pccpa.R.id.async_image);
            if (image != null) {
                image.setPaused(pause);
            }
        }
    }


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Contact contact= adapter.getItem(position);
		ContactInfoDialog dialog=new ContactInfoDialog();
		dialog.set_contact(contact);
		dialog.show(this.getFragmentManager(), "详细信息");
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	   Toast.makeText(getActivity(), position+"", 5000).show();
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Contact contact= adapter.getItem(position);
		ContactInfoDialog dialog=new ContactInfoDialog();
		dialog.set_contact(contact);
		dialog.show(this.getFragmentManager(), "详细信息");
	}


	

	public class ContactListAdapter extends ListItemAdapter<Contact>  {

		
		private ImageProcessor _imageProcessor;
		
		public ContactListAdapter(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			Contact e=getItem(position);
			return Long.parseLong(e.getEID());
		}

		 class ViewHolder{
			public AsyncImageView imageView;
			public TextView txvEmName,txvEmMobile;
			
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final Contact m=getItem(position);
			ViewHolder holder;
			
			//if(convertView==null){
				convertView=_inflater.inflate(R.layout.listitem_contact, parent,false);
				holder=new ViewHolder();
				holder.imageView=(AsyncImageView)convertView.findViewById(R.id.async_image);
				holder.imageView.setImageProcessor(_imageProcessor);
			    holder.txvEmName=(TextView)convertView.findViewById(R.id.txvEmName);
			    holder.txvEmMobile=(TextView)convertView.findViewById(R.id.txvMobile);
			    //holder.btnTel=(Button)convertView.findViewById(R.id.btnTel);
			    
			    String url=Client.urlEmployeePhoto(m.getEID());
			    final String num=StringHelper.concat(m.getEMobile(),",",m.getEMobileShort(),",",m.getETelWork(),",",m.getETelWorkShort());
			    holder.imageView.setUrl(url);
				holder.txvEmName.setText(MessageFormat.format("{0} {1} {2}", m.getAreaName(),m.getDepartName(),m.getEUserName()));
				holder.txvEmMobile.setText(StringHelper.concat(num));
				//holder.btnTel.setText("拨打");
				//holder.btnTel.setOnClickListener(new View.OnClickListener() {
				//	@Override
				//	public void onClick(View view) {
						//attemptLogin();
				//	    _contextHelper.toTel(num);
				//	}
				//})
			    convertView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				
						ContactInfoCardDialog dialog=new ContactInfoCardDialog();
						dialog.set_contact(m);
						//dialog.setStyle(DialogFragment.STYLE_NO_TITLE, android.R.sty);
						
						dialog.show(getFragmentManager(), "详细信息");
						//Toast.makeText(_context, m.getEUserName(), 2000).show();
					}
				});
				convertView.setTag(holder);
			//}else{
			//	holder=(ViewHolder)convertView.getTag();
			//}
			
			return convertView;
		}

	}


   boolean isSearching=false;

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(),"beforeTextChanged:"+ s, 2000).show();
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		//Toast.makeText(getActivity(),"onTextChanged "+ s+" "+start+" "+before+" "+count, 1000).show();
		
		
	   
	}


	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		 if(!isSearching){
		     isSearching=true;
			 doSearch(s.toString());
			
		 }
		 
	}
	
	private synchronized void doSearch(String keyword){
		
		if(keyword.length()==0){
			 adapter.items=ContactActivity.CONTACT_ALL;
			   this.setListAdapter(adapter);
		}else{
			 String like="%"+keyword+"%";
			 try {
		     List<Contact> cs=sqLiteOrmHelper.select(Contact.class,
		    		 new String[]{"*"},
		    		 "eusername||','||rankname||','||areaname||','||departname||','||email||','||emobile||','||emobileshort||','||etelwork||','||etelworkshort like ? "
		    		 ,new String[]{like});
		     
			   adapter.items=cs;
			   this.setListAdapter(adapter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 isSearching=false;
		;
	}
	
}
