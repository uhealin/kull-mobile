package org.pccpa.widget;




import java.util.List;

import org.pccpa.AboutActivity;
import org.pccpa.DB;
import org.pccpa.LoginActivity;
import org.pccpa.RemindActivity;

import org.pccpa.api.Client;
import org.pccpa.api.EmployeeItem;
import org.pccpa.contact.EmployeeListActivity;


import com.cyrilmottier.android.greendroid.R;
import com.kull.android.ContextHelper;
import com.kull.android.SQLiteOrmHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.Switch;
import android.widget.Toast;
import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionGrid;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;

public class MenuActionGrid extends QuickActionGrid{

	 private Context _context;
	 final protected ContextHelper _contextHelper;
	
	public MenuActionGrid(Context context) {
		super(context);
		_context=context;
		_contextHelper=new ContextHelper(context);
		// TODO Auto-generated constructor stub
	   onInit();
	}

	protected void onInit(){
		  this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_info , org.pccpa.R.string.menu_remind));
	      this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_all_friends,org.pccpa.R.string.menu_contact));
	      this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_refresh, org.pccpa.R.string.menu_syh));
	      this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_settings, org.pccpa.R.string.menu_config));
	      this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_help, org.pccpa.R.string.menu_about));
	      this.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_item_pressed, org.pccpa.R.string.menu_logout));
	      

	      this.setOnQuickActionClickListener(mActionListener);
	}
	
	
	 private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
	        public void onQuickActionClicked(QuickActionWidget widget, int position) {
	        	Intent intent=null;
	           switch (position) {
			case 0:
				_contextHelper.to(RemindActivity.class);
				break;
			case 1:
				_contextHelper.to(EmployeeListActivity.class);
				break;
			case 2:
				int eff=synDB();
				Toast.makeText(_context, "成功更新了"+eff+"条记录", 300).show();
				break;
			case 3:
				Toast.makeText(_context, "功能开发中", 5000).show();break;
				
			case 4:
				_contextHelper.to(AboutActivity.class);
				break;
				
			case 5:
				Client.CURR_CLIENT=null;
				_contextHelper.to(LoginActivity.class);
				
				
		    break;
			default:
				Toast.makeText(_context, "功能开发中", 5000).show();
			}
	        }
	    };
	    
	    private int synDB(){
	    	try {
				List<EmployeeItem> ems= Client.CURR_CLIENT.getEms(0,200).getRows();
				SQLiteOrmHelper ormHelper=DB.local.createSqLiteOrmHelper(_context);
				ormHelper.replaceTable(EmployeeItem.class);
				int i=0;
				//progressBar.setProgress(i);
				//progressBar.setMax(ems.size());
				for(EmployeeItem em:ems){
					ormHelper.insert(em);
					//progressBar.setProgress(i++);
				}
				//progressBar.setVisibility(View.GONE);
				return ems.size();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return 0;
	    }
	    
	    public static <T extends GDActivity> void renderTo(T activity){
	    	MenuActionGrid menuActionGrid=new MenuActionGrid(activity);
	    	activity.setMainQuickActionWidget(menuActionGrid);
	    	
	    }
	
private static class MyQuickAction extends QuickAction {
        
        private static final ColorFilter BLACK_CF = new LightingColorFilter(Color.BLACK, Color.BLACK);

        public MyQuickAction(Context ctx, int drawableId, int titleId) {
            super(ctx, buildDrawable(ctx, drawableId), titleId);
        }
        
        private static Drawable buildDrawable(Context ctx, int drawableId) {
            Drawable d = ctx.getResources().getDrawable(drawableId);
            d.setColorFilter(BLACK_CF);
            return d;
        }
        
    }
}
