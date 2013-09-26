package org.pccpa.frage;



import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.RemindItem;
import org.pccpa.api.Client.RemindsAdapter;
import org.pccpa.api.RemindSynTask;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.kull.android.ContextHelper;
import com.kull.android.widget.ItemAdapter;
import com.kull.android.widget.item.DescriptionItem;
import com.kull.android.widget.item.Item;
import com.kull.android.widget.item.SeparatorItem;

public abstract class RemindListFragment extends SherlockListFragment {

	public abstract String getRtype();
	
	ListSynTask remindListSynTask;
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
	}


	public void loadList(Context context){
		remindListSynTask=new ListSynTask(context);
		
		remindListSynTask.execute(Client.CURR_CLIENT.getContact().getEID(),getRtype());	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		loadList(getActivity());
		
	}



	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}



	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}



	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	
	private class ListSynTask extends RemindSynTask{

private Context context;
		
		public ListSynTask(Context context){
			this.context=context;
		}
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(context, "开始读取...", 3000).show();
		}

		@Override
		protected void onPostExecute(List<RemindItem> result) {
			// TODO Auto-generated method stub
			if(result.isEmpty()){
				Toast.makeText(context, "当前没有待办事项", 3000).show();
			}
			List<Item> items=new ArrayList<Item>();
			super.onPostExecute(result);
			for(RemindItem remind : result){
				items.add(new SeparatorItem(remind.getTitle()));
				items.add(new DescriptionItem(remind.getText()));
			}
			ItemAdapter adapter = new ItemAdapter(getActivity(),items);
			setListAdapter(adapter);
		}
		
		
		
		
	}
	
	
	
	
	

	
}
