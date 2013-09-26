package org.pccpa.frage;



import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.InnerMsg;
import org.pccpa.api.InnerMsgSynTask;
import org.pccpa.api.RemindItem;
import org.pccpa.api.RemindSynTask;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.kull.android.widget.ItemAdapter;
import com.kull.android.widget.item.DescriptionItem;
import com.kull.android.widget.item.Item;
import com.kull.android.widget.item.SeparatorItem;

public abstract class InnerMsgListFragment extends SherlockListFragment {

	
   ListSynTask listSynTask;
	
	public abstract String getIsRead();
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//loadList();
		
	}


	public void loadList(Context context){
		if(listSynTask==null)listSynTask=new ListSynTask(context);
		//listSynTask.cancel(true);
	listSynTask.execute(Client.CURR_CLIENT.getContact().getEID(),getIsRead());
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		loadList(this.getActivity());
		
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

	
	private class ListSynTask extends InnerMsgSynTask{

		
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
		protected void onPostExecute(List<InnerMsg> result) {
			// TODO Auto-generated method stub
			List<Item> items=new ArrayList<Item>();
			super.onPostExecute(result);
			if(result.size()==0){
				Toast.makeText(context, "当前没有  内部短信", 5000).show();
			}
			for(InnerMsg remind : result){
				SeparatorItem siTitle=new SeparatorItem(remind.getMRTitle());
				items.add(siTitle);
				items.add(new DescriptionItem(remind.getMRContent()));
			}
			ItemAdapter adapter = new ItemAdapter(getActivity(),items);
			setListAdapter(adapter);
		}
		
		
		
		
	}
}
