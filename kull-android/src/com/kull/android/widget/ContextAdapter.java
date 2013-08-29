package com.kull.android.widget;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class ContextAdapter extends BaseAdapter {

	protected Context _context;
	
	public ContextAdapter(Context context){
		this._context=context;
	}
}
