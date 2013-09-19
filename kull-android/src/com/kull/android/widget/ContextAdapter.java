package com.kull.android.widget;

import com.kull.android.ContextHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class ContextAdapter extends BaseAdapter {

	protected Context _context;
	protected final ContextHelper _contextHelper;
	protected LayoutInflater _inflater;
	
	public ContextAdapter(Context context){
		this._context=context;
		_contextHelper=new ContextHelper(_context);
		_inflater=LayoutInflater.from(_context);
	}
}
