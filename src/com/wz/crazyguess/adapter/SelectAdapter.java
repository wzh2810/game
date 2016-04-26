package com.wz.crazyguess.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SelectAdapter extends BaseAdapter {
	private List<View> allViews;
	private Context content;
	
	public SelectAdapter(List<View> allViews,Context content) {
		this.allViews = allViews;
		this.content = content;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allViews.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return allViews.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return allViews.get(position);
	}

}
