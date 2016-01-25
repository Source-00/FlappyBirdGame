package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;
import com.flappybirdgame.ding.flappybirdgame.utils.Constants;

import java.util.Random;

/**
 * Barrier
 *
 * @author: DING
 * @time: 2016/1/24 10:03
 */
public class Barrier  extends BaseLayer{

    private float spaceH;//上下两障碍的间距

    private float distance;//左右两障碍之间的间距
    private float barrierW;//障碍的宽

    private float speed;//障碍运动速度

    private float barrier1X,barrier1Y;//第一个障碍物的位置

    private float barrier1H;

    private float barrier2X,barrier2Y;
    private float barrier2H;

    private float playerX,playerY;//主角的位置
    private float radius;//半径




    public Barrier(GameSurfaceView surface) {
        super(surface);
        spaceH=screenH/4;

        barrier1X=screenW+200;
        barrier1Y=0;

        barrierW=100;
        barrier1H=getBarrierH();

        distance=screenW/2+barrierW/2;

        barrier2X=barrier1X+distance;
        barrier2Y=0;

        barrier2H=getBarrierH();

        speed=15;





    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.GREEN);

        //第一个上
        canvas.drawRect(barrier1X,barrier1Y,barrier1X+barrierW,
                barrier1Y+barrier1H,paint);
        canvas.drawRect(barrier1X, barrier1H + spaceH,
                barrier1X + barrierW,screenH,paint);

        //第二个上
        canvas.drawRect(barrier2X,barrier2Y,barrier2X+barrierW,
                barrier2Y+barrier2H,paint);
        canvas.drawRect(barrier2X,barrier2H+spaceH,
                barrier2X+barrierW,screenH,paint);


    }

    @Override
    public void logic() {
        barrier1X-=speed;
        barrier2X-=speed;

        if(barrier1X+barrierW<0)
        {
            barrier1X=screenW;
            barrier1H=getBarrierH();
        }
        if(barrier2X+barrierW<0)
        {
            barrier2X=screenW;
            barrier2H=getBarrierH();
        }

        //主角与障碍的碰撞检测
        boolean isColl1=circleAndRect(playerX,playerY,radius,barrier1X,barrier2Y,barrierW,barrier1H);
        boolean isColl2=circleAndRect(playerX,playerY,radius,barrier1X,barrier1H+spaceH,barrierW,screenH-barrier1H-spaceH);
        boolean isColl3=circleAndRect(playerX,playerY,radius,barrier2X,barrier2Y,barrierW,barrier2H);
        boolean isColl4=circleAndRect(playerX,playerY,radius,barrier2X,barrier2H+spaceH,barrierW,screenH-barrier2H-spaceH);

        if(isColl1||isColl2||isColl3||isColl4)
        {
            surface.setGameState(Constants.GAME_STOP);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown() {

    }

    private float getBarrierH()
    {
        return new Random().nextInt((int)(screenH-spaceH));
    }


    public void setRadius(float radius) {
        this.radius = radius;
    }
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    /**
     * 圆与矩形间的碰撞检测
     *
     * @param circleX
     * @param circleY
     * @param circleR
     * @param rectX
     * @param rectY
     * @param rectW
     * @param rectH
     * @return
     */
    private boolean circleAndRect( float circleX, float circleY, float circleR,float rectX,
                                   float rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }

        return true;
    }
}
