package org.pccpa.api;

import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client.RemindsAdapter;

import android.os.AsyncTask;

public class RemindSynTask  extends AsyncTask<String, Integer,List<RemindItem>>{

	public enum RType{
		check,apply
	}
	@Override
	protected List<RemindItem> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String eid=params[0];
		String rtype=params[1];
		List<RemindItem> items=new ArrayList<RemindItem>();
		try {
			RemindsAdapter adapter= Client.getReminds(eid);
			if(RType.apply.name().equals(rtype)){
				items=adapter.getApplyList();
			}else if(RType.check.name().equals(rtype)){
				items=adapter.getRemindList();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

}
