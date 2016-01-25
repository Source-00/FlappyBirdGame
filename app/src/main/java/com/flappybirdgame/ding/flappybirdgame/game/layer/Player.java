package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;
import com.flappybirdgame.ding.flappybirdgame.utils.Constants;

/**
 * Player
 *
 * @author: DING
 * @time: 2016/1/24 10:00
 */
public class Player extends BaseLayer{
    private float playerX;
    private float playerY;
    private float speed;
    private float speedH;

    private float radius;

    public Player(GameSurfaceView surface) {
        super(surface);
        playerX =screenW/2;
        playerY =screenH/2-200;
        speed=10;
        speedH=50;
        radius=40;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        //paint.setColor(Assist.getColor(res, R.color.Red));


        switch (surface.getGameState())
        {
            case Constants.GAME_START:
                paint.setColor(Color.RED);
                canvas.drawCircle(screenW/2, screenH/2-200,radius,paint);
                break;

            case Constants.GAMEING:
                paint.setColor(Color.RED);
                canvas.drawCircle(playerX, playerY,radius,paint);
                break;
            case Constants.GAME_STOP:

                paint.setColor(Color.RED);
                canvas.drawCircle(screenW/2, screenH/2-200,radius,paint);

                break;
            default:
                break;

        }


    }

    @Override
    public void logic() {


        playerY +=speed;
        speed+=2.5;
        if(playerY-radius<0||playerY+radius>screenH){
            surface.setGameState(Constants.GAME_STOP);
        }

        if(playerY >=screenH)
        {
            speed=0;
        }


    }

    @Override
    public void onTouchEvent(MotionEvent event) {

        switch (surface.getGameState())
        {
            case Constants.GAME_START:
                break;

            case Constants.GAMEING:

//                if(playerY<screenH/3){
//                    speed=-Math.abs(speed)/2+10;
//                }
//                else if(playerY<screenH*2/3){
//                    speed=-Math.abs(speed);
//                }
//                else{
//                    speed=-Math.abs(speed)/2-10;
//
//                }

                if(speed>15)
                {
                    speed=-Math.abs(speed)+30;
                }
                else{
                    speed=-Math.abs(speed)-20;
                }
                break;
            case Constants.GAME_STOP:

                break;
            default:
                break;

        }

    }

    @Override
    public void onKeyDown() {

    }
    public float getRadius() {
        return radius;
    }
    public float getPlayerX() {
        return playerX;
    }
    public float getPlayerY() {
        return playerY;
    }




}
