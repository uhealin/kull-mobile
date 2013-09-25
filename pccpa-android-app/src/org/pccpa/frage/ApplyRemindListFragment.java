package org.pccpa.frage;

import org.pccpa.api.RemindSynTask;

public class ApplyRemindListFragment extends RemindListFragment{

	@Override
	public String getRtype() {
		// TODO Auto-generated method stub
		return RemindSynTask.RType.apply.name();
	}
	
}