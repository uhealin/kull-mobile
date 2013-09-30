package org.pccpa.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.pccpa.api.Client.InnerMsgGrid;

import android.os.AsyncTask;

public class InnerMsgSynTask extends AsyncTask<String, Double, List<InnerMsg>>{

	@Override
	protected List<InnerMsg> doInBackground(String... params) {
		// TODO Auto-generated method stub
		String eid=params[0];
		String isRead=params[1];
		List<InnerMsg> ims=new ArrayList<InnerMsg>();
		try {
			
			InnerMsgGrid grid=Client.getInnerMsgGrid("eid="+eid+"&mode="+isRead+URLEncoder.encode("|", "UTF-8"), 0, 200);
			ims=grid.getRows();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ims;
	}

}
