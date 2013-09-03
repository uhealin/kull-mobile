package org.pccpa.frage;

import greendroid.widget.ItemAdapter;
import greendroid.widget.item.DescriptionItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.RemindItem;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.kull.android.ContextHelper;

public class RemindListFragment extends SherlockListFragment {

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		List<RemindItem> reminds;
		try {
			reminds = Client.CURR_CLIENT.getReminds();
			List<Item> items=new ArrayList<Item>();
			for(RemindItem remind : reminds){
				items.add(new SeparatorItem(remind.getTitle()));
				items.add(new DescriptionItem(remind.getText()));
			}
			ItemAdapter adapter = new ItemAdapter(this.getActivity(),items);
			setListAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(getActivity(), e.getLocalizedMessage(), 5000);
		}
		
	}

	
}
