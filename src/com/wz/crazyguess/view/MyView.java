package com.wz.crazyguess.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.wz.crazyguess.activity.GameActivity;
import com.wz.crazyguess.util.Globals;
import com.wz.crazyguess.util.ImageUtil;

public class MyView extends View{
	
	private GameActivity gameActivity;
	private String answer;
	private String[] userAnswer;
	private int[] gridIndex;
	//距离屏幕左边的长度
    private float left;
	
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gameActivity = (GameActivity) context;
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					float x = event.getX();
					if(x > left) {
						int temp = (int) (x - left);
						int result = (int) (temp / (Globals.GRID_WIDTH + Globals.GRID_SEP));
						if(userAnswer[result] != null) {
							userAnswer[result] = null;
							gameActivity.getAllViews().get(gridIndex[result]).setVisibility(View.VISIBLE);
							gridIndex[result] = -1;
							postInvalidate();
						}
					}
				}
				return false;
			}
		});
		
	}

	 public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }

	public void initAnswer(String answer) {
		this.answer = answer;
		userAnswer = new String[answer.length()];
		gridIndex = new int[answer.length()];
		postInvalidate();
		
	}

	public void initUserAnswer(String txt, int position) {
		 for(int i=0;i<userAnswer.length;i++) {
	            if(userAnswer[i]==null) {
	                userAnswer[i] = txt;
	                gridIndex[i] = position;
	                break;
	            }
	        }
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<userAnswer.length;i++) {
	            if(userAnswer[i]==null) {
	                break;
	            }
	            sb.append(userAnswer[i]);
	        }
	        if(sb.length()==answer.length() && sb.toString().equals(answer)) {
	            Toast.makeText(gameActivity,"回答正确，前往下一关",Toast.LENGTH_SHORT).show();
	            SharedPreferences s = gameActivity.getSharedPreferences("game", gameActivity.MODE_PRIVATE);
	            SharedPreferences.Editor e = s.edit();
	            e.putInt("nowStage", s.getInt("nowStage",0) + 1);
	            e.putInt("coin", s.getInt("coin", 50) + 30);
	            e.commit();
	            Intent in = new Intent(gameActivity, GameActivity.class);
	            in.putExtra("id", s.getInt("nowStage",0));
	            gameActivity.startActivity(in);
	            gameActivity.finish();
	        }
	        postInvalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		if(answer != null) {
			int temp = (int) ((answer.length()/2.0f) * (Globals.GRID_WIDTH + Globals.GRID_SEP));
			left = Globals.SCREEN_WIDTH / 2 - temp;
			for(int i = 0; i < answer.length(); i++) {
				canvas.drawBitmap(ImageUtil.getNoSelect(),left + i * (Globals.GRID_WIDTH + Globals.GRID_SEP), 10, paint);
				if(userAnswer[i] != null) {
					canvas.drawText(userAnswer[i],left + i * (Globals.GRID_WIDTH + Globals.GRID_SEP) + (Globals.GRID_WIDTH/2-15), (Globals.GRID_WIDTH/2) + 15,paint);
				}
			}
		}
	}
}
