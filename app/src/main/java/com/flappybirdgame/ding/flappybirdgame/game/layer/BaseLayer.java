package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;

/**
 * BaseLayer
 *
 * @author: DING
 * @time: 2016/1/24 10:12
 */
public abstract  class BaseLayer {

    protected GameSurfaceView surface;
    Resources res;

    /**
     * 当前屏幕宽
     */
    protected int screenW;
    /**
     * 当前屏幕高
     */
    protected int screenH;

   public BaseLayer(GameSurfaceView surface){
     this.surface=surface;

       this.screenW=surface.getWidth();
       this.screenH=surface.getHeight();


    }
    /**
     * 画图
     * @param canvas 画布
     * @param paint  画笔
     */
    public abstract  void draw(Canvas canvas,Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();

    /**
     * 触摸事件
     * @param event
     */

    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 按键点击事件
     */
    public abstract void onKeyDown();
}
