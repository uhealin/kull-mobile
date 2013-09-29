package org.pccpa.frage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.pccpa.ContactActivity;
import org.pccpa.DB;
import org.pccpa.R;



import org.pccpa.api.Client;
import org.pccpa.api.Contact;
import org.pccpa.api.EmployeeItem;


import android.R.string;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.StrictMode.VmPolicy;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView;


import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.internal.widget.IcsAdapterView;


import com.kull.StringHelper;
import com.kull.android.ContextHelper;
import com.kull.android.SQLiteOrmHelper;

import com.kull.android.image.ImageProcessor;
import com.kull.android.widget.AsyncImageView;
import com.kull.android.widget.ListItemAdapter;

public class ContactListFragment extends SherlockListFragment 
implements OnScrollListener,OnItemSelectedListener,OnItemClickListener,TextWatcher{
	
	private ContactListAdapter adapter;
	
	

	SQLiteOrmHelper sqLiteOrmHelper;
	private final static int CWJ_HEAP_SIZE = 6* 1024* 1024 ;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	   adapter=new ContactListAdapter(getActivity(),this);
	   adapter.items=ContactActivity.CONTACT_ALL;
	   this.setListAdapter(adapter);
	   sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(getActivity());
	   getListView().setOnScrollListener(this);
       //getListView().setOnItemSelectedListener(this);
       //getListView().setOnItemClickListener(this);
	   
	}

   public void loadItems(List<Contact> items){
	   
	   adapter.items=items;
	   this.setListAdapter(adapter);
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
		//private AsyncImageLoader asyncImageLoader;
		private Options options;
		private ContactListFragment contactListFragment;
		public ContactListAdapter(Context context,ContactListFragment contactListFragment) {
			super(context);
			this.contactListFragment=contactListFragment;
			// TODO Auto-generated constructor stub
			//_imageProcessor= new im
			//asyncImageLoader=new AsyncImageLoader(_context);
			//asyncImageLoader.setCache2File(true);
			options=new Options();
		     //options.inJustDecodeBounds = true;
		    // options.inSampleSize = 4;   //width，hight设为原来的十分一
		     options.inPreferredConfig = Bitmap.Config.RGB_565;   
		     //options.inPurgeable = true;  
		     //options.inInputShareable = true;  
		     options.inDither = true;
		     options.inScaled = true;
		     options.inDensity = DisplayMetrics.DENSITY_MEDIUM;
		     options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
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
		   final ViewHolder holder;
			
			//if(convertView==null){
				convertView=_inflater.inflate(R.layout.listitem_contact, parent,false);
			 holder=new ViewHolder();
				holder.imageView=(AsyncImageView)convertView.findViewById(R.id.async_image);
				//holder.imageView.setImageProcessor(_imageProcessor);
			    holder.txvEmName=(TextView)convertView.findViewById(R.id.txvEmName);
			    holder.txvEmMobile=(TextView)convertView.findViewById(R.id.txvMobile);
			    //holder.btnTel=(Button)convertView.findViewById(R.id.btnTel);
			    
			    Contact.setupImageView(parent.getContext(),m,holder.imageView, options);
			    
			    
			    final String num=StringHelper.concat(m.getEMobile(),",",m.getEMobileShort(),",",m.getETelWork(),",",m.getETelWorkShort());
			   
			    
				holder.txvEmName.setText(
						Html.fromHtml(
						MessageFormat.format("{2} <span style='font-size=0.8em'>{0} {1}</span> ", m.getAreaName(),m.getDepartName(),m.getEUserName())
						)
								);
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
						dialog.setOptions(options);
						dialog.set_contact(m);
						dialog.setParent(contactListFragment);
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
	
	public void loadAreaContacts(String areaname,String areaid){
		try {
			ContactActivity.CONTACT_ALL=DB.local.selectAreaContacts(getActivity(), areaid);
			loadItems(ContactActivity.CONTACT_ALL);
		     //Toast.makeText(getActivity(),area.getAreaName()+ "共有"+ContactActivity.CONTACT_ALL.size()+"条记录", 3000).show();
		     ContextHelper.makeText(getActivity(),3000,"{0} 共有 {1} 条记录", 
		    		 areaname,ContactActivity.CONTACT_ALL.size()
		    		 ).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ContextHelper.makeText(getActivity(),3000,e).show();
		}
	     
	}
	
	
	public void loadAreaRankContacts(String areaname,String rname,String areaid,String rid){
		try {
			ContactActivity.CONTACT_ALL=DB.local.selectAreaRankContacts(getActivity(), areaid,rid);
			loadItems(ContactActivity.CONTACT_ALL);
		     //Toast.makeText(getActivity(),area.getAreaName()+ "共有"+ContactActivity.CONTACT_ALL.size()+"条记录", 3000).show();
		     ContextHelper.makeText(getActivity(),3000,"{0} {1} 共有 {2} 条记录", 
		    		 areaname,rname,ContactActivity.CONTACT_ALL.size()
		    		 ).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ContextHelper.makeText(getActivity(),3000,e).show();
		}
	     
	}
	
	
	public void loadDeptContacts(String aname,String dname,String id){
		try {
			ContactActivity.CONTACT_ALL=DB.local.selectDeptContacts(getActivity(), id);
			loadItems(ContactActivity.CONTACT_ALL);
		     //Toast.makeText(getActivity(),area.getAreaName()+ "共有"+ContactActivity.CONTACT_ALL.size()+"条记录", 3000).show();
		     ContextHelper.makeText(getActivity(),3000,"{0} {1} 共有 {2} 条记录", 
		    		 aname,dname,ContactActivity.CONTACT_ALL.size()
		    		 ).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ContextHelper.makeText(getActivity(),3000,e).show();
		}
	     
	}
	
	
	
}
