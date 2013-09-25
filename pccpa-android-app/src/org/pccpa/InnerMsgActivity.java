package org.pccpa;

import org.pccpa.ContactActivity.TabsAdapter;
import org.pccpa.api.Client;
import org.pccpa.api.Client.RemindsAdapter;
import org.pccpa.api.RemindItem;
import org.pccpa.frage.HelpFragment;
import org.pccpa.frage.InnerMsgListFragment;
import org.pccpa.frage.ReadedInnerMsgListFragment;
import org.pccpa.frage.RemindListFragment;
import org.pccpa.frage.ApplyRemindListFragment;
import org.pccpa.frage.CheckRemindListFragment;
import org.pccpa.frage.UnReadInnerMsgListFragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class InnerMsgActivity extends BaseFragmentActivity {

	TabsAdapter mTabsAdapter;
	TabHost mTabHost;
	 ViewPager  mViewPager;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.fragment_tabs_pager);
		
		//this.getSupportFragmentManager().beginTransaction().add(android.R.id.content,list).commit();
		 mTabHost = (TabHost)findViewById(android.R.id.tabhost);
	        mTabHost.setup();

	        mViewPager = (ViewPager)findViewById(R.id.pager);

	        
			 try {
				
				
				Bundle b0=new Bundle(),b1=new Bundle();
				b0.putCharSequence("rtype", "check");
				b1.putCharSequence("rtype", "apply");
				
				mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager); 
				
				mTabsAdapter.addTab(mTabHost.newTabSpec("Î´¶Á").setIndicator("Î´¶Á"),
		                UnReadInnerMsgListFragment.class, null);
				mTabsAdapter.addTab(mTabHost.newTabSpec("ÒÑ¶Á").setIndicator("ÒÑ¶Á"),
		                ReadedInnerMsgListFragment.class, null);
				 
				//reloadTabs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				contextHelper.alert(e, Toast.LENGTH_LONG);
			}
			 if (arg0 != null) {
		            mTabHost.setCurrentTabByTag(arg0.getString("tab"));
		        }
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//menu.add("°ïÖú")
		//.setIcon(R.drawable.ic_search)
       // .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

    menu.add("Ë¢ÐÂ")
        .setIcon( R.drawable.ic_refresh_inverse)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	        //This uses the imported MenuItem from ActionBarSherlock
	    if("Ë¢ÐÂ".equals(item.getTitle())){
	    	//RemindListFragment list=new RemindListFragment();
			//this.getSupportFragmentManager().beginTransaction().replace(android.R.id.content,list).commit();
	    	InnerMsgListFragment innerMsgListFragment=(InnerMsgListFragment)mTabsAdapter.getItem(mTabHost.getCurrentTab());
	    	innerMsgListFragment.loadList(this);
	    }else if("°ïÖú".equals(item.getTitle())){
	    	HelpFragment list=new HelpFragment();
	    	list.context="´ý°ì°ïÖú";
			list.show(getSupportFragmentManager(), list.context);
	    }
	    return true;
	}
	
	
	

}
