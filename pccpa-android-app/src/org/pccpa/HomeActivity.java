package org.pccpa;


import org.pccpa.remind.IndexFragment;



import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.SegmentedAdapter;
import greendroid.widget.SegmentedHost;

public class HomeActivity extends GDActivity {

	   private final Handler mHandler = new Handler();
	    private TitleSegmentedAdapter mAdapter;
	   private FrameLayout frameLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setActionBarContentView(R.layout.segmented_controls);
		//(TextView)this.findViewById(R.id.text);
		//textView.setText("首页");
		this.addActionBarItem(ActionBarItem.Type.Refresh, R.id.action_bar_refresh);
		
		SegmentedHost segmentedHost = (SegmentedHost) findViewById(R.id.segmented_host);
		frameLayout=(FrameLayout)this.findViewById(R.id.gd_segmented_content_view);
        mAdapter = new TitleSegmentedAdapter();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mAdapter.mReverse = true;
                mAdapter.notifyDataSetChanged();
            }
        }, 4000);

        segmentedHost.setAdapter(mAdapter);
        
        
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		// TODO Auto-generated method stub
		return super.onHandleActionBarItemClick(item, position);
	}

	
	
	  private class TitleSegmentedAdapter extends SegmentedAdapter {

	        public boolean mReverse = false;

	        @Override
	        public View getView(int position, ViewGroup parent) {
	            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.text, parent, false);
	            textView.setText(getSegmentTitle(position));
	        	//IndexFragment e=new IndexFragment();
	        	//View v=null;
	            //return v;
	            return textView;
	        }

	        @Override
	        public int getCount() {
	            return 3;
	        }

	        @Override
	        public String getSegmentTitle(int position) {

	            switch (position) {
	                case 0:
	                    return "待办提醒";
	                case 1:
	                    return "通讯录";
	                case 2:
	                    return "系统配置";
	                
	            }

	            return null;
	        }
	    }
}
