package com.wz.crazyguess.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wz.crazyguess.R;
import com.wz.crazyguess.adapter.GameAdapter;
import com.wz.crazyguess.util.CrazyDao;
import com.wz.crazyguess.util.ImageUtil;

public class MainActivity extends Activity {
	 private GridView grid;
	    private LinearLayout topBar;
	    private TextView topStar;
	    private List<Map<String, Object>> values;
	    private GameAdapter adapter;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);

	        grid = (GridView) findViewById(R.id.grid);
	        topBar = (LinearLayout) findViewById(R.id.top_bar);
	        topStar = (TextView) findViewById(R.id.top_star);

	        topBar.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getTopBar()));
	        topStar.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getTopStar()));
	        int nowStage = getSharedPreferences("game", MODE_PRIVATE).getInt("nowStage",0);
	        topStar.setText(String.valueOf(nowStage));
	        values = CrazyDao.getStageData(nowStage);
	        adapter = new GameAdapter(values, this);
	        grid.setAdapter(adapter);

	        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	                Map<String, Object> map = values.get(position);
	                if((Boolean)map.get("overFlag")) {
	                    Intent in = new Intent(MainActivity.this, GameActivity.class);
	                    in.putExtra("id", (Integer)map.get("stage"));
	                  //  in.putExtra("id",2);
	                    startActivity(in);
	                }else {
	                    Toast.makeText(MainActivity.this, "»¹²»ÄÜÍæ", Toast.LENGTH_SHORT).show();
	                }
	            }
	        });
	    }
	}
