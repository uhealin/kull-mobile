package org.pccpa;

import greendroid.widget.AsyncImageView;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.adapter.EmployeeItemAdapter;
import org.pccpa.api.Client;
import org.pccpa.api.Contact;
import org.pccpa.api.EmployeeItem;
import org.pccpa.api.SiteSynRunnable;
import org.pccpa.api.Client.ContactGrid;
import org.pccpa.api.Client.EMGrid;
import org.pccpa.frage.ContactListFragment;
import org.pccpa.frage.HelpFragment;
import org.pccpa.frage.RemindListFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.kull.android.SQLiteOrmHelper;

public class ContactActivity extends BaseFragmentActivity {

	TabHost mTabHost;
    ViewPager  mViewPager;
    TabsAdapter mTabsAdapter;
    EditText etSearchKeyword;
    
    ContactListFragment contactListFragment;
    public static List<EmployeeItem> EMP_ALL=new ArrayList<EmployeeItem>();
    
    public static List<Contact> CONTACT_ALL=new ArrayList<Contact>();
    Handler mHandler = new Handler();
    

    private int mProgress = 100;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//setContentView(R.layout.fragment_tabs_pager);
	    contactListFragment=new ContactListFragment();
		this.getSupportFragmentManager().beginTransaction()
		.add(android.R.id.content,contactListFragment)
		.commit();
		
		
		
		
		/*
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager)findViewById(R.id.pager);

        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager); 
        
       
		sqLiteOrmHelper.replaceTable(EmployeeItem.class);
		
		 try {
			 
			mTabsAdapter.addTab(mTabHost.newTabSpec("最近").setIndicator("最近"),
	                ContactListFragment.class, null);
			 //mTabsAdapter.addTab(mTabHost.newTabSpec("按部门").setIndicator("按部门"),
		      //          ContactListFragment.class, null);
		    //    mTabsAdapter.addTab(mTabHost.newTabSpec("按姓氏").setIndicator("按姓氏"),
		    //    		ContactListFragment.class, null);
		    // //   mTabsAdapter.addTab(mTabHost.newTabSpec("按拼音").setIndicator("按拼音"),
		    //    		ContactListFragment.class, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			contextHelper.alert(e, Toast.LENGTH_LONG);
		}

       */
       

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

	 MenuItem miSearch=	menu.add("查找")
		.setIcon(R.drawable.ic_search)
		.setActionView(R.layout.collapsible_edittext);
    miSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
    etSearchKeyword=(EditText)miSearch.getActionView().findViewById(R.id.etxSearchKeyword);
	etSearchKeyword.addTextChangedListener(contactListFragment);
		
		
   // menu.add("同步通讯录")
    //    .setIcon( R.drawable.ic_refresh_inverse)
    //    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
   
    
    
		return true;
	}

	 Thread synThread;
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	        //This uses the imported MenuItem from ActionBarSherlock
	    if("同步通讯录".equals(item.getTitle())){
	    	if(synThread==null){
	    		SiteSynRunnable siteSynRunnable=new SiteSynRunnable(this);
	    		synThread= new Thread(siteSynRunnable);
	    		synThread.run();
	    	}else if(!synThread.isAlive()){
	    		synThread.run();
	    	}
	    }else if("帮助".equals(item.getTitle())){
	    	HelpFragment list=new HelpFragment();
	    	list.context="通讯录帮助";
			list.show(getSupportFragmentManager(), list.context);
	    }
	    return true;
	}
	
	 private int synDB(){
		
		 
	    	try {
			    EMGrid grid= Client.CURR_CLIENT.getEms(0,1);
			    mProgress = grid.getTotal();
				SQLiteOrmHelper ormHelper=DB.local.createSqLiteOrmHelper(this);
				ormHelper.replaceTable(EmployeeItem.class);
				int limit=200,eff=0;
				for(int start=0;start*limit<grid.getTotal();start++){
				List<EmployeeItem> ems=Client.CURR_CLIENT.getEms(start*limit, limit).getRows();
				for(EmployeeItem em:ems){
					ormHelper.insert(em);
					//progressBar.setProgress(i++);
					eff++;
					int progress = (Window.PROGRESS_END - Window.PROGRESS_START) / 100 * eff;;
		            setSupportProgress(progress);
				}
				}
				//progressBar.setVisibility(View.GONE);
				return eff;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return 0;
	    }

	
	public static class TabsAdapter extends FragmentPagerAdapter
    implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
private final Context mContext;
private final TabHost mTabHost;
private final ViewPager mViewPager;
private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

static final class TabInfo {
    private final String tag;
    private final Class<?> clss;
    private final Bundle args;

    TabInfo(String _tag, Class<?> _class, Bundle _args) {
        tag = _tag;
        clss = _class;
        args = _args;
    }
}

static class DummyTabFactory implements TabHost.TabContentFactory {
    private final Context mContext;

    public DummyTabFactory(Context context) {
        mContext = context;
    }

    @Override
    public View createTabContent(String tag) {
        View v = new View(mContext);
        v.setMinimumWidth(0);
        v.setMinimumHeight(0);
        return v;
    }
}

public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager) {
    super(activity.getSupportFragmentManager());
    mContext = activity;
    mTabHost = tabHost;
    mViewPager = pager;
    mTabHost.setOnTabChangedListener(this);
    mViewPager.setAdapter(this);
    mViewPager.setOnPageChangeListener(this);
}

public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
    tabSpec.setContent(new DummyTabFactory(mContext));
    String tag = tabSpec.getTag();

    TabInfo info = new TabInfo(tag, clss, args);
    mTabs.add(info);
    mTabHost.addTab(tabSpec);
    notifyDataSetChanged();
}

@Override
public int getCount() {
    return mTabs.size();
}

@Override
public Fragment getItem(int position) {
    TabInfo info = mTabs.get(position);
    return Fragment.instantiate(mContext, info.clss.getName(), info.args);
}

@Override
public void onTabChanged(String tabId) {
    int position = mTabHost.getCurrentTab();
    mViewPager.setCurrentItem(position);
}

@Override
public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
}

@Override
public void onPageSelected(int position) {
    // Unfortunately when TabHost changes the current tab, it kindly
    // also takes care of putting focus on it when not in touch mode.
    // The jerk.
    // This hack tries to prevent this from pulling focus out of our
    // ViewPager.
    TabWidget widget = mTabHost.getTabWidget();
    int oldFocusability = widget.getDescendantFocusability();
    widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    mTabHost.setCurrentTab(position);
    widget.setDescendantFocusability(oldFocusability);
}

@Override
public void onPageScrollStateChanged(int state) {
}
}
}
