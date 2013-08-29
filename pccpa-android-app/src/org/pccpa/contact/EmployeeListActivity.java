package org.pccpa.contact;



import java.util.ArrayList;
import java.util.List;

import org.pccpa.DB;
import org.pccpa.adapter.EmployeeItemAdapter;
import org.pccpa.api.Client;
import org.pccpa.api.EmployeeItem;
import org.pccpa.widget.MenuActionGrid;

import com.kull.android.SQLiteOrmHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;
import greendroid.app.GDListActivity;
import greendroid.image.ImageProcessor;
import greendroid.widget.AsyncImageView;

public class EmployeeListActivity extends GDListActivity implements OnScrollListener{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MenuActionGrid.renderTo(this);
		List<EmployeeItem> ems=new ArrayList<EmployeeItem>();
		try{
			//SQLiteOrmHelper sqLiteOrmHelper=DB.local.createSqLiteOrmHelper(this);
			//sqLiteOrmHelper.replaceTable(EmployeeItem.class);
			
			 //ems=sqLiteOrmHelper.select(EmployeeItem.class);
			ems=Client.CURR_CLIENT.getEms(0, 50);
			 this.setListAdapter(new EmployeeItemAdapter(this, ems));
			 getListView().setOnScrollListener(this);
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView listView, int scrollState) {
		// TODO Auto-generated method stub
		if (getListView() == listView) {
            searchAsyncImageViews(listView, scrollState == OnScrollListener.SCROLL_STATE_FLING);
        }
	}

	
	private void searchAsyncImageViews(ViewGroup viewGroup, boolean pause) {
        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AsyncImageView image = (AsyncImageView) viewGroup.getChildAt(i).findViewById(org.pccpa.R.id.async_image);
            if (image != null) {
                image.setPaused(pause);
            }
        }
    }
	
}
