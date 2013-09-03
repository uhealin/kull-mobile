package org.pccpa;

import org.pccpa.api.Client;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockExpandableListActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.kull.android.ContextHelper;

public abstract class BaseListFragmentActivity extends SherlockListActivity implements OnNavigationListener{

	
	protected ContextHelper contextHelper;
	protected static int NAV_POSITION=0;
	protected static boolean IS_POSTBACK=false;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		IS_POSTBACK=false;
		super.onCreate(arg0);
		setTheme(R.style.Theme_Sherlock_Light);
		BaseFragmentActivity.initActionBar(this, getSupportActionBar());
		contextHelper=new ContextHelper(this);
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
