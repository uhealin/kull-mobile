package org.pccpa.remind;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.RemindItem;
import org.pccpa.widget.MenuActionGrid;





import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import greendroid.app.GDListActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.ItemAdapter;
import greendroid.widget.item.DescriptionItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.SeparatorItem;

public class RemindListActivity extends GDListActivity {

	private final Handler mHandler = new Handler();
	private MenuActionGrid menuActionGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		List<Item> items=new ArrayList<Item>();
		
		try {
			List<RemindItem> reminds= Client.CURR_CLIENT.getReminds();
			for(RemindItem remind : reminds){
				items.add(new SeparatorItem(remind.getTitle()));
				items.add(new DescriptionItem(remind.getText()));
			}
			ItemAdapter adapter = new ItemAdapter(this,items);
			this.setListAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), 5000);
		}
		//menuActionGrid=new MenuActionGrid(this);
		//addActionBarItem(Type.List);
          MenuActionGrid.renderTo(this);
	}

	

	
	
}
