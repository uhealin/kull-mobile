package com.kull.android.widget;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public abstract class ListItemAdapter<I> extends ContextAdapter  {

	public List<I> items=new ArrayList<I>();
	
	public ListItemAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public I getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	

}
