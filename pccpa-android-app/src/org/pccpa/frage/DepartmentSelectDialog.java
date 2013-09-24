package org.pccpa.frage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pccpa.DB.Area;
import org.pccpa.DB;
import org.pccpa.DB.Dept;
import org.pccpa.api.Contact;
import org.pccpa.frage.ContactListFragment.ContactListAdapter;
import org.pccpa.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.kull.android.widget.ContextAdapter;
import com.kull.android.widget.ListItemAdapter;

public class DepartmentSelectDialog extends SherlockDialogFragment {

	private ContactListFragment parent;
	
	

	public void setParent(ContactListFragment parent) {
		this.parent = parent;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Dialog dialog= super.onCreateDialog(savedInstanceState);
		dialog.setTitle("区域部门查询");
		
		return dialog;
	}
	
	public static Map<String, Area> CACHE_AREAS=new HashMap<String, DB.Area>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(CACHE_AREAS.isEmpty()){
			try {
			   CACHE_AREAS=DB.local.selectArea(this.getActivity());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		View view=inflater.inflate(R.layout.dialog_department_select, container,false);
		
		ScrollView scrollView=(ScrollView)view;
		LinearLayout layout=(LinearLayout)scrollView.findViewById(R.id.layArea);//.findViewById(R.id.layArea);
		
      //  ArrayList<View> children=new ArrayList<View>();
        for(Area area: CACHE_AREAS.values()){
        	Button areaButton=new Button(getActivity());
        	//areaButton.setWidth(200);
        	areaButton.setText(area.getAreaName());
        	final String areaid=area.getAreaId();
        	areaButton.setOnClickListener(new OnClickListener() {
         
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 try {
						 
					     List<Contact> cs=DB.local.createSqLiteOrmHelper(getActivity()).select(Contact.class,
					    		 new String[]{"*"},
					    		 "AreaID=?"
					    		 ,new String[]{areaid});
					     
					     parent.loadItems(cs);
					     Toast.makeText(getActivity(), "共查出"+cs.size()+"条记录", 3000).show();
					     dismiss();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
        	//children.add(areaButton);
        	layout.addView(areaButton);
        	
        	GridView gridDepts=new GridView(getActivity());
        	gridDepts.setNumColumns(3);
        	gridDepts.setColumnWidth(80);
        	int dsize=area.getDepts().size();
        	if(dsize>3){
        	  int h=((dsize/3)+ (dsize%3==0?0:1) )*48;
        	  LayoutParams layoutParams=new LayoutParams();
        	  layoutParams.height=h;
        	   gridDepts.setLayoutParams(layoutParams);
        	}
        	//gridDepts.setLayoutParams(layparam);
        	DeptItemAdapter deptItemAdapter=new DeptItemAdapter(getActivity());
        	deptItemAdapter.items=new ArrayList<DB.Dept>(area.getDepts().values());
        	gridDepts.setAdapter(deptItemAdapter);
        	layout.addView(gridDepts);
        }
        //view.addTouchables(children);
		return scrollView;
	}
	
	public class DeptItemAdapter extends ListItemAdapter<Dept> {

		public DeptItemAdapter(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		class Hodler{
			Button btnDept;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return Long.parseLong(this.items.get(position).getDeptId());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parentg) {
			// TODO Auto-generated method stub
			final Dept dept=this.getItem(position);
			convertView=_inflater.inflate(R.layout.dialog_department_select_dept, parentg,false);
			Button btnDept=(Button)convertView;
			btnDept.setText(dept.getDeptName());
		
			btnDept.setOnClickListener(new OnClickListener() {
		         
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 try {
						 
					     List<Contact> cs=DB.local.createSqLiteOrmHelper(getActivity()).select(Contact.class,
					    		 new String[]{"*"},
					    		 "DepartID=?"
					    		 ,new String[]{dept.getDeptId()});
					     
					     parent.loadItems(cs);
					     Toast.makeText(getActivity(), "共查出"+cs.size()+"条记录", 3000).show();
					     dismiss();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  
				}
			});
			return btnDept;
		}
		
	} 


}
