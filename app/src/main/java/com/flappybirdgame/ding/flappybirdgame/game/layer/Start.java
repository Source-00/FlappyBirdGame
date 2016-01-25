package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;
import com.flappybirdgame.ding.flappybirdgame.utils.Assist;
import com.flappybirdgame.ding.flappybirdgame.utils.Constants;


/**
 * Start
 *
 * @author: DING
 * @time: 2016/1/24 10:03
 */
public class Start extends  BaseLayer{
    public float x,y;
    public float w,h;
    private float triangleW,triangleH;

    public Start(GameSurfaceView surface) {
        super(surface);

        w=200;h=100;
        x=screenW/2-w/2;
        y=screenH/2-h/2;

        triangleW=50;
        triangleH=20;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.YELLOW);
        canvas.drawRect(x, y, x + w, y + h, paint);

        paint.setColor(Color.WHITE);
        Path path=new Path();
        path.moveTo(x+w/2-triangleH/2,y+h/2-triangleW/2);
        path.lineTo(x + w / 2 - triangleH / 2, y + h / 2 + triangleW / 2);
        path.lineTo(x + w / 2 + triangleH / 2, y + h / 2);
        canvas.drawPath(path,paint);

    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

        int touthX= (int) event.getX();
        int touthY= (int) event.getY();

        if(touthX>x && touthX<x+w && touthY>y && touthY<y+h){
            surface.setGameState(Constants.GAMEING);
        }
    }

    @Override
    public void onKeyDown() {

    }
}
