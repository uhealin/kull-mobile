package org.pccpa.frage;

import org.pccpa.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragment;

public class HelpFragment extends SherlockDialogFragment {

    public String title,context;
	
    
	
	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.help,  container, false);
         View tv = v.findViewById(R.id.text);
         ((TextView)tv).setText(context);
         return v;
     }
}
