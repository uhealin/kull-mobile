package org.pccpa.contact;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.R;
import org.pccpa.api.Client;
import org.pccpa.api.EmployeeItem;
import org.pccpa.api.RemindItem;

import com.kull.android.SQLiteOrmHelper;


import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class IndexFragment extends Fragment {

	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		List<EmployeeItem> ems=new ArrayList<EmployeeItem>();
		try{
			SQLiteOrmHelper sqLiteOrmHelper=new SQLiteOrmHelper(this.getActivity(), "test");
			sqLiteOrmHelper.replaceTable(EmployeeItem.class);
			
			 ems=Client.CURR_CLIENT.getEms(0,100);
			//for(EmployeeItem em:ems){
			//	sqLiteOrmHelper.insert(em);
			//	System.out.print(em.getEUserName()+"  ≤Â»Î≥…π¶");
			//}
			}catch(Exception ex){
				ex.printStackTrace();
			}
        View view=inflater.inflate(R.layout.fragment_contact_index, container,false);
		listView=(ListView)view.findViewById(R.id.listViewContact);
       
		
	
		List<String> testData=new ArrayList<String>();
		
		try {
			
			for(EmployeeItem ri: ems){
				testData.add(ri.getEUserName()+":"+ri.getEID());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ListAdapter listAdapter=new ArrayAdapter<String>(getActivity(), R.layout.adapter_em_list_item,testData);
		listView.setAdapter(listAdapter);
		return view;
	}

	
	
	
}
