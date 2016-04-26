package com.wz.crazyguess.util;

import android.app.Activity;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/23.
 */
public class Globals {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static float GRID_WIDTH;
    public static float GRID_SEP;

    public static DBUtil util;
    public static void init(Activity a) {
        util = new DBUtil(a);
        SCREEN_WIDTH = a.getWindowManager().getDefaultDisplay().getWidth();
        SCREEN_HEIGHT = a.getWindowManager().getDefaultDisplay().getHeight();
        GRID_WIDTH = SCREEN_WIDTH / 9;
        GRID_SEP = 2;
        try {
            ImageUtil.init(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
