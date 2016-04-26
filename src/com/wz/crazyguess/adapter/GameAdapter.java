package com.wz.crazyguess.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wz.crazyguess.R;
import com.wz.crazyguess.util.Globals;
import com.wz.crazyguess.util.ImageUtil;

public class GameAdapter extends BaseAdapter {
	private List<Map<String, Object>> values;
	private Context context;

	public GameAdapter(List<Map<String, Object>> values, Context context) {
		this.values = values;
		this.context = context;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Object getItem(int position) {
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.grid_list, null);
			convertView.setLayoutParams(new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					Globals.SCREEN_HEIGHT / 9));
		}
		Map<String,Object> map = values.get(position);
		TextView stage = (TextView) convertView.findViewById(R.id.stage);
		stage.setText(map.get("stage").toString());
		if((Boolean) map.get("overFlag")) {
			stage.setBackgroundDrawable(new BitmapDrawable(context.getResources(),ImageUtil.getCanSelect()));
		} else {
			stage.setBackgroundDrawable(new BitmapDrawable(context.getResources(),ImageUtil.getNoSelect()));
		}
		return convertView;
	}

	public List<Map<String, Object>> getValues() {
		return values;
	}

	public void setValues(List<Map<String, Object>> values) {
		this.values = values;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
