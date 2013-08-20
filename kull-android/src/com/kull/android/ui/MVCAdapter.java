package com.kull.android.ui;

import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MVCAdapter<M,V extends View,C extends Context> extends BaseAdapter {

	private List<M> data;

	protected C context;
	
	
	
	public abstract V getView(M model,int position, View view, ViewGroup viewGroup);
	
	
	public MVCAdapter(C context,List<M> data){
		this.data=data;
		this.context=context;
		
	}
	
	@Override
	public  int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public M getItem(List<M> data,int position){
		return data.get(position);
	}
	
	@Override
	public final Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(this.data,position);
	}

	

	@Override
	public final View getView(int position, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		M model=getItem(this.data,position);
		return getView(model, position, view, viewGroup);
	}

	
	
	
}
