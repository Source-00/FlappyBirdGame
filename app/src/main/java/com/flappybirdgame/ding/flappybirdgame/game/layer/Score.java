package com.flappybirdgame.ding.flappybirdgame.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;
import com.flappybirdgame.ding.flappybirdgame.utils.Constants;

/**
 * Score
 *
 * @author: DING
 * @time: 2016/1/24 10:04
 */
public class Score extends BaseLayer{
    long startTime,endTime;

    private  int scoreMax;
    int score;
    boolean isStart;


    public Score(GameSurfaceView surface) {
        super(surface);

        score=0;
        isStart=true;
        startTime=0;
        endTime=0;
        scoreMax=surface.getScoreMax();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        switch (surface.getGameState())
        {
            case Constants.GAME_START:
                break;
            case Constants.GAMEING:
                paint.setColor(Color.WHITE);
                paint.setTextSize(100);
                canvas.drawText(score +"", screenW / 2, 300, paint);
                break;
            case Constants.GAME_STOP:
                paint.setColor(Color.WHITE);
                paint.setTextSize(100);
                canvas.drawText("本次游戏成绩  "+score  , 200, 200, paint);
                scoreMax=surface.getScoreMax();
                canvas.drawText("历史最高成绩  "+scoreMax  , 200, 400, paint);
                break;
            default:
                break;

        }



    }

    @Override
    public void logic() {
       if(isStart){
           startTime=System.currentTimeMillis();
           isStart=false;
       }
        endTime=System.currentTimeMillis();
        score=(int)((endTime-startTime)/1000);

        if(score>scoreMax)
        {
            surface.setScoreMax(score);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown() {

    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }
}
