package org.pccpa;

import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public  class MainFrameActivity  extends SherlockActivity{
	public static int THEME = R.style.Theme_Sherlock;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 menu.add("Search")
         .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

     menu.add("Refresh")
         .setIcon( R.drawable.ic_refresh)
         .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(THEME); //Used for theme switching in samples
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
     
        setContent((TextView)findViewById(R.id.text));
    }
	
	   protected void setContent(TextView view) {
	        view.setText(R.string.action_items_content);
	    }
}
