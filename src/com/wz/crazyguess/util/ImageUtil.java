package com.wz.crazyguess.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;


public class ImageUtil {

    private static Bitmap uiMain;
    private static Bitmap topStar;
    private static Bitmap topBar;
    private static Bitmap canSelect;
    private static Bitmap noSelect;
    private static Bitmap returnBtn;
    private static Bitmap coin;
    private static Bitmap delete;
    private static Bitmap hint;
    private static Bitmap questionType;

    public static void init(Activity a) throws IOException {
        uiMain = BitmapFactory.decodeStream(a.getAssets().open("ui_main.png"));
        topStar = getImagePiece(1020, 1939, 108, 108);
        topBar = getImagePiece(975, 893, 640, 96);
        canSelect = getImagePiece(975, 338, 74, 76);
        noSelect = getImagePiece(1602, 155, 66, 63);
        returnBtn = getImagePiece(98,1972,96,72);
        coin = getImagePiece(1602,219,44,33);
        delete = getImagePiece(1503,990,92,123);
        hint = getImagePiece(1579,1371,92,123);
        questionType = getImagePiece(1675,787,310,58);
    }

    //切图
    public static Bitmap getImagePiece(int x, int y, int width, int height) {
        return Bitmap.createBitmap(uiMain, x, y, width, height);
    }

    //缩放
    public static Bitmap getScaleImagePiece(Bitmap source, int overWidth, int overHeight) {
        Matrix m = new Matrix();
        m.postScale(overWidth / source.getWidth(), overHeight / source.getHeight());
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, false);
    }

    //旋转
    public static Bitmap getRotateImagePiece(Bitmap source) {
        Matrix m = new Matrix();
        m.postRotate(25, source.getWidth() / 2, source.getHeight() / 2);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, false);
    }

    public static Bitmap getTopStar() {
        return topStar;
    }

    public static Bitmap getTopBar() {
        return topBar;
    }

    public static Bitmap getCanSelect() {
        return canSelect;
    }

    public static Bitmap getNoSelect() {
        return noSelect;
    }

    public static Bitmap getReturnBtn() {
        return returnBtn;
    }

    public static Bitmap getCoin() {
        return coin;
    }

    public static Bitmap getDelete() {
        return delete;
    }

    public static Bitmap getHint() {
        return hint;
    }

    public static Bitmap getQuestionType() {
        return questionType;
    }
}
