package org.pccpa;

import java.util.List;

import org.pccpa.api.Client;
import org.pccpa.api.EmployeeItem;

import org.pccpa.dummy.DummyContent;
import org.pccpa.dummy.DummyContent.DummyItem;

import com.kull.android.SQLiteOrmHelper;
import com.kull.lucene.FsRamDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}
        //progressBar=(ProgressBar)this.findViewById(R.id.pb_wating);
		
		
		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		DummyItem dummyItem=DummyContent.ITEM_MAP.get(id);
		 Fragment fragment=null;
			try {
				fragment = dummyItem.fragmentCls.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			//ItemDetailFragment fragment = new ItemDetailFragment();
			//fragment.setArguments(arguments);
			
		   
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, fragment.getClass());
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.menuitem_main_logout:
			Intent intent=new Intent(this,LoginActivity.class);
			this.startActivity(intent);
			break;
		case R.id.menuitem_main_reloadContact:
			//progressBar.setVisibility(View.VISIBLE);
			
			try {
				List<EmployeeItem> ems= Client.CURR_CLIENT.getEms(0,Integer.MAX_VALUE).getRows();
				SQLiteOrmHelper ormHelper=DB.local.createSqLiteOrmHelper(this);
				ormHelper.replaceTable(EmployeeItem.class);
				int i=0;
				//progressBar.setProgress(i);
				//progressBar.setMax(ems.size());
				for(EmployeeItem em:ems){
					ormHelper.insert(em);
					//progressBar.setProgress(i++);
				}
				//progressBar.setVisibility(View.GONE);
				Toast.makeText(this, "成功更新了"+ems.size()+"条记录", 300).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.menuitem_main_about:
			Toast.makeText(this, "关于", 300).show();
			break;
		default:
			break;
		}
		return true;
	}
	
	
}
