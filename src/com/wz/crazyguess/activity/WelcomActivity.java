package com.wz.crazyguess.activity;

import com.wz.crazyguess.R;
import com.wz.crazyguess.R.layout;
import com.wz.crazyguess.R.menu;
import com.wz.crazyguess.util.Globals;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;

public class WelcomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcom);
		
		Thread t = new Thread(){
			public void run() {
				Globals.init(WelcomActivity.this);
				SharedPreferences sp = getSharedPreferences("game", MODE_PRIVATE);
				if(sp.getInt("nowStage", 0) == 0) {
					SharedPreferences.Editor editor = sp.edit();
					editor.putInt("nowStage", 1);
					editor.putInt("coin", 50);
					editor.commit();
				}
				try {
					sleep(2000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(WelcomActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		};
		t.start();
	}
}
