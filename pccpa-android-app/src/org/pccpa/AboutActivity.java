package org.pccpa;

import org.pccpa.widget.MenuActionGrid;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;



import greendroid.app.GDActivity;

public class AboutActivity extends GDActivity {

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBarContentView(R.layout.about);
        
        final TextView aboutText = (TextView) findViewById(R.id.about);
        aboutText.setMovementMethod(LinkMovementMethod.getInstance());
        MenuActionGrid.renderTo(this);
    }
}
