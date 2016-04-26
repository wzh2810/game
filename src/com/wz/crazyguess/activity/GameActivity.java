package com.wz.crazyguess.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wz.crazyguess.R;
import com.wz.crazyguess.adapter.SelectAdapter;
import com.wz.crazyguess.util.CrazyDao;
import com.wz.crazyguess.util.ImageUtil;
import com.wz.crazyguess.view.MyView;

public class GameActivity extends Activity {
    private Button returnBtn;
    private TextView gameTopStar;
    private LinearLayout gameTopBar;
    private TextView coin;
    private TextView coinCount;
    private TextView delete;
    private TextView hint;
    private TextView quetsionType;
    private TextView questionImg;
    private GridView selectGrid;
    private List<View> allViews = new ArrayList<View>();
    private SelectAdapter selectAdapter;
    private MyView answerView;
    private int id;
    private Map<String, Object> crazyMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        returnBtn = (Button) findViewById(R.id.return_btn);
        gameTopBar = (LinearLayout) findViewById(R.id.game_top_bar);
        gameTopStar = (TextView) findViewById(R.id.game_top_star);
        coin = (TextView) findViewById(R.id.coin);
        coinCount = (TextView) findViewById(R.id.coin_count);
        delete = (TextView) findViewById(R.id.delete);
        hint = (TextView) findViewById(R.id.hint);
        quetsionType = (TextView) findViewById(R.id.question_type);
        questionImg = (TextView) findViewById(R.id.question_img);
        selectGrid = (GridView) findViewById(R.id.select_grid);
        answerView = (MyView) findViewById(R.id.answer_view);

        returnBtn.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getReturnBtn()));
        gameTopBar.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getTopBar()));
        gameTopStar.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getTopStar()));
        coin.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getCoin()));
        delete.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getDelete()));
        hint.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getHint()));
        quetsionType.setBackgroundDrawable(new BitmapDrawable(getResources(), ImageUtil.getQuestionType()));

        //从上一个界面传过来的id
        id = getIntent().getIntExtra("id", 1);
        //根据id查询本题的内容
        crazyMap = CrazyDao.getDataById(id);
        gameTopStar.setText(id+"");
        coinCount.setText(String.valueOf(getSharedPreferences("game", MODE_PRIVATE).getInt("coin", 50)));
        quetsionType.setText(crazyMap.get("questionType").toString());
        try {
            questionImg.setBackgroundDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeStream(getAssets().open(crazyMap.get("questionImg").toString()))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String select = crazyMap.get("selectTxt").toString();
        List<Character> charList = new ArrayList<Character>();
        for(int i=0;i<select.length();i++) {
            charList.add(select.charAt(i));
        }
        //打乱顺序
        Collections.shuffle(charList);
        LayoutInflater l = LayoutInflater.from(this);
        for(int i=0;i<charList.size();i++) {
            View v = l.inflate(R.layout.select_list,null);
            TextView t = (TextView) v.findViewById(R.id.select_txt);
            t.setText(charList.get(i).toString());
            t.setBackgroundDrawable(new BitmapDrawable(getResources(),ImageUtil.getCanSelect()));
            allViews.add(v);
        }
        selectAdapter = new SelectAdapter(allViews,this);
        selectGrid.setAdapter(selectAdapter);

        //根据答案来初始格
        answerView.initAnswer(crazyMap.get("answer").toString());

        selectGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = allViews.get(position);
                TextView t = (TextView) v.findViewById(R.id.select_txt);
                answerView.initUserAnswer(t.getText().toString(),position);
                v.setVisibility(View.INVISIBLE);
            }
        });
        
        returnBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
    }
    

    public List<View> getAllViews() {
        return allViews;
    }
}

