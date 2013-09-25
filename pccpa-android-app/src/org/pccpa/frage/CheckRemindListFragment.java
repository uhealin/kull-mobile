package org.pccpa.frage;

import org.pccpa.api.RemindSynTask;

public class CheckRemindListFragment extends RemindListFragment{

	@Override
	public String getRtype() {
		// TODO Auto-generated method stub
		return RemindSynTask.RType.check.name();
	}
	
}
