package org.pccpa;

import org.pccpa.api.Client;
import org.pccpa.api.Contact;

import android.R.string;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.kull.android.ContextHelper;

public class BaseFragmentActivity extends SherlockFragmentActivity implements OnNavigationListener {

	protected ContextHelper contextHelper;
	protected static int NAV_POSITION=0;
	protected static boolean IS_POSTBACK=false;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		IS_POSTBACK=false;
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_PROGRESS|Window.FEATURE_CONTEXT_MENU);
		setTheme(R.style.Theme_Sherlock_Light);
		ActionBar actionBar=getSupportActionBar();
		//actionBar.setDisplayShowTitleEnabled(false);
		Contact curContact=Client.CURR_CLIENT.getContact();
	    String title=curContact.getEUserName()
	    		,subtitle=curContact.getAreaName()+" "+curContact.getDepartName();
		actionBar.setTitle(title);
		actionBar.setSubtitle(subtitle);
		actionBar.setDisplayUseLogoEnabled(false);
		initActionBar(this, actionBar);
		contextHelper=new ContextHelper(this);
		
	}

	public static void initActionBar(OnNavigationListener c,ActionBar actionBar){
		
		Context context = actionBar.getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.menus, R.layout.sherlock_spinner_item);
		//ActionBar actionBar=getSupportActionBar();
		list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(list, c);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setSelectedNavigationItem(NAV_POSITION);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.actionbarsherlock.app.ActionBar.OnNavigationListener#onNavigationItemSelected(int, long)
	   <item>首页</item>
        <item>待办提醒</item>
        <item>通讯录</item>
        <item>系统设置</item>
        <item>注销用户</item>
        <item>关于</item>
	 */
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		if(!IS_POSTBACK){
			IS_POSTBACK=true;
			return false;
		}
		NAV_POSITION=itemPosition;
		switch (itemPosition) {
		case 0:
			//contextHelper.to(RemindActivity.class);
			break;
		case 1:
			contextHelper.to(RemindActivity.class);
			break; 
		case 2:
			contextHelper.to(ContactActivity.class);
			break;
		case 3:
			contextHelper.to(SettingActivity.class);
			break;
		case 4:
			Client.CURR_CLIENT=null;
			contextHelper.to(LoginActivity.class);
			break;
		case 5:
			contextHelper.to(AboutActivity.class);
			break;
		default:
			break;
		}
		IS_POSTBACK=true;
		return true;
	}
}
