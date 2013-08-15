package org.pccpa.remind;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pccpa.R;
import org.pccpa.api.Client;
import org.pccpa.api.RemindItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class IndexFragment extends Fragment {

	private ListView listViewRemind;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_remind_index, container,false);
		
		listViewRemind=(ListView)view.findViewById(R.id.listViewRemind);
	
		List<String> testData=new ArrayList<String>();
		Client client=new Client(getActivity());
		try {
			List<RemindItem> reminditems=client.getReminds();
			for(RemindItem ri: reminditems){
				testData.add(ri.getTitle()+":"+ri.getText());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ListAdapter listAdapter=new ArrayAdapter<String>(getActivity(), R.layout.adaper_remind_list_item,testData);
		listViewRemind.setAdapter(listAdapter);
		
		return view;
	}

	
	
	
	
}
