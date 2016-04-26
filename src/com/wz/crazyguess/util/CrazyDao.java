package com.wz.crazyguess.util;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/23.
 */
public class CrazyDao {

    public static List<Map<String, Object>> getStageData(int nowStage) {
        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        String sql = "select id from crazy";
        Cursor c = Globals.util.getReadableDatabase().rawQuery(sql,null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("stage", c.getInt(0));
            if(nowStage>=c.getInt(0)) {
                map.put("overFlag", true);
            }else {
                map.put("overFlag", false);
            }
            values.add(map);
            c.moveToNext();
        }
        c.close();
        return values;
    }

    public static Map<String, Object> getDataById(int id) {
        String sql = "select answer, question_type, question_img, select_txt from crazy where id=?";
        Map<String, Object> map = new HashMap<String, Object>();
        Cursor c = Globals.util.getReadableDatabase().rawQuery(sql, new String[]{id+""});
        c.moveToFirst();
        map.put("answer", c.getString(0));
        map.put("questionType", c.getString(1));
        map.put("questionImg", c.getString(2));
        map.put("selectTxt", c.getString(3));
        c.close();
        return map;
    }
}
