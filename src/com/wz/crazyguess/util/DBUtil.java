package com.wz.crazyguess.util;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class DBUtil extends SQLiteOpenHelper {

    private Activity a;
    private Random random = new Random();

    public DBUtil(Context ctx) {
        super(ctx, "crazy.db", null, 1);
        a = (Activity) ctx;
    }

    public DBUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table crazy(" +
                "       id          integer     primary key," +
                "       question_img    text," +
                "       answer          text," +
                "       question_type   text," +
                "       select_txt      text)";
        db.execSQL(sql);
        insertCrazyData(db);
    }

    public void insertCrazyData(SQLiteDatabase db) {
        String sql = "insert into crazy (question_img, answer, question_type, select_txt) values (?,?,?,?)";
        try {
            String[] allValues = a.getAssets().list("question_answer");
            for (int i = 0; i < allValues.length; i++) {
                String fileName = allValues[i];
                String path = "question_answer/" + fileName;
                String questionImg = "question_img/" + fileName.substring(0, fileName.lastIndexOf(".")) + ".png";
                BufferedReader br = new BufferedReader(new InputStreamReader(a.getAssets().open(path), "GBK"));
                String line = null;
                int index = 0;
                String answer = null;
                String questionType = null;
                while ((line = br.readLine()) != null) {
                    index++;
                    switch (index) {
                        case 1:
                            answer = line;
                            break;
                        case 2:
                            questionType = line;
                            break;
                    }
                }
                String selectTxt = getSelectTxt(answer);
                db.execSQL(sql,new Object[]{questionImg, answer, questionType, selectTxt});
                br.close();
                Log.d("提示", selectTxt + "------------------->");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSelectTxt(String answer) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(answer);
        while (sb.length() < 24) {
            sb.append(create());
        }
        return sb.toString();
    }

    //随机生成一个汉字
    public String create() throws Exception {

        String str = null;

        int hightPos, lowPos; // 定义高低位


        hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值

        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值

        byte[] b = new byte[2];

        b[0] = (new Integer(hightPos).byteValue());

        b[1] = (new Integer(lowPos).byteValue());

        str = new String(b, "GBk");//转成中文

        return str;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
