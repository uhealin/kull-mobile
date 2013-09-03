package org.pccpa.frage;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.ContactActivity;
import org.pccpa.DB;
import org.pccpa.adapter.EmployeeItemAdapter;
import org.pccpa.api.EmployeeItem;

import greendroid.widget.AsyncImageView;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.actionbarsherlock.app.SherlockListFragment;
import com.kull.android.SQLiteOrmHelper;

public class ContactListFragment extends SherlockListFragment implements OnScrollListener{
	
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	
	   this.setListAdapter(new EmployeeItemAdapter(getActivity(),ContactActivity.EMP_ALL));
	   getListView().setOnScrollListener(this);
			
	}


	@Override
	public void onScrollStateChanged(AbsListView viewGroup, int scrollState) {
		// TODO Auto-generated method stub
		if (getListView() == viewGroup) {
            searchAsyncImageViews(viewGroup, scrollState == OnScrollListener.SCROLL_STATE_FLING);
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


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}
}
