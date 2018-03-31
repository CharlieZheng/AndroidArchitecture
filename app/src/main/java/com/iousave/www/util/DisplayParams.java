package com.iousave.www.util;


import android.content.Context;
import android.util.DisplayMetrics;


/**
 * 屏幕参数工具
 *
 * @author hayukleung
 */
public class DisplayParams {
    public static final int DEFAULT_SCREEN_HEIGHT = 667;
    /**
     * 表示屏幕朝向垂直
     */
    private final static int SCREEN_ORIENTATION_VERTICAL = 1;
    private final static int SCREEN_ORIENTATION_HORIZONTAL = 2;
    private static final int DEFAULT_SCREEN_WIDTH = 375;
    /**
     * 表示屏幕朝向水平
     */
    private static DisplayParams singleInstance;
    /**
     * 屏幕宽度——px
     */
    private final int screenWidth;
    /**
     * 屏幕高度——px
     */
    private final int screenHeight;
    /**
     * 缩放系数——densityDpi/160
     */
    private final float density;
    /**
     * 文字缩放系数
     */
    private final float fontScale;
    /**
     * 屏幕朝向
     */
    private final int screenOrientation;
    private final float heightRatio;
    private final float widthRatio;

    /**
     * 私有构造方法
     *
     * @param context 上下文
     */
    private DisplayParams(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels; // 1280
        screenHeight = dm.heightPixels; // 720
        /*
      屏幕密度——dpi
     */
        int densityDpi = dm.densityDpi;
        density = dm.density; // 1.5625
        fontScale = dm.scaledDensity; // 1.5625, xdpi=72, ydpi=72
        screenOrientation = screenHeight > screenWidth ? SCREEN_ORIENTATION_VERTICAL : SCREEN_ORIENTATION_HORIZONTAL;
        heightRatio = (screenWidth > screenHeight ? screenWidth : screenHeight) / (float) DEFAULT_SCREEN_HEIGHT;
        widthRatio = (screenWidth > screenHeight ? screenHeight : screenWidth) / (float) DEFAULT_SCREEN_WIDTH;
    }

    /**
     * 获取实例
     *
     * @param context 上下文
     * @return DisplayParams
     */
    public synchronized static DisplayParams getInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new DisplayParams(context);
        }
        return singleInstance;
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param scale （DisplayMetrics类中属性density）
     */
    public static int px2dp(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param scale （DisplayMetrics类中属性density）
     */
    public static int dp2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }

    public float getHeightRatio() {
        return heightRatio;
    }

    public float getWidthRatio() {
        return widthRatio;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public float getDensity() {
        return density;
    }

    public int getScreenOrientation() {
        return screenOrientation;
    }

    public float getFontScale() {
        return fontScale;
    }
}