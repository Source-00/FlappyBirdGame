package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.R;
import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;
import com.flappybirdgame.ding.flappybirdgame.utils.Assist;

/**
 * BackGround
 *
 * @author: DING
 * @time: 2016/1/24 10:02
 */
public class BackGround extends BaseLayer{




    public BackGround(GameSurfaceView surface) {
        super(surface);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        res=Resources.getSystem();

       // paint.setColor(Assist.getColor(res, R.color.Cyan));
        paint.setColor(Color.BLUE);

        canvas.drawRect(0,0,screenW,screenH,paint);


    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown() {

    }
}
