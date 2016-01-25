package com.flappybirdgame.ding.flappybirdgame.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.flappybirdgame.ding.flappybirdgame.game.layer.BackGround;
import com.flappybirdgame.ding.flappybirdgame.game.layer.Barrier;
import com.flappybirdgame.ding.flappybirdgame.game.layer.Player;
import com.flappybirdgame.ding.flappybirdgame.game.layer.Score;
import com.flappybirdgame.ding.flappybirdgame.game.layer.Start;
import com.flappybirdgame.ding.flappybirdgame.utils.Constants;

/**
 * GameSurfaceView
 *
 * @author: DING
 * @time: 2016/1/24 9:29
 */
public class GameSurfaceView extends SurfaceView
        implements SurfaceHolder.Callback,Runnable {

    private final static String TAG="GameSurfaceView";

    private int gameState;//游戏当前状态

    private SurfaceHolder holder;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    private Thread thread;
    private Barrier barrier;

   private BackGround background;
    private Player player;
    private Start start;
    private Score score;
    private  int scoreMax=0;

    long startTime,endTime;

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /**
     * 初始化
     */
    public  void init(){
        Log.i(TAG, "init");
        holder = getHolder();
        holder.addCallback(this);
        setKeepScreenOn(true);


        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);//抗锯齿

    }

    /**
     * 初始化游戏
     */
    public  void initGame(){
        gameState= Constants.GAME_START;//设置游戏初始状态 开始游戏
        background=new BackGround(this);
        player=new Player(this);
        start=new Start(this);
        barrier=new Barrier(this);
        score=new Score(this);


    }

    public GameSurfaceView(Context context) {
        super(context);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");
        initGame();
        flag=true;

        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        Log.i(TAG, "surfaceDestroyed");
    }

    /**
     * 画图
     */
    public void myDraw(Canvas canvas){
        background.draw(canvas,paint);
        switch (gameState)
        {
            case Constants.GAME_START:
                start.draw(canvas, paint);
                player.draw(canvas, paint);
                break;
            case Constants.GAMEING:
                player.draw(canvas, paint);
                barrier.draw(canvas, paint);
                score.draw(canvas,paint);
                break;
            case Constants.GAME_STOP:

                player.draw(canvas, paint);
                start.draw(canvas, paint);
                score.draw(canvas,paint);
                break;
            default:
                break;

        }

    }

    /**
     * 逻辑
     */
    public void logic(){
        switch (gameState)
        {
            case Constants.GAME_START:
                break;
            case Constants.GAMEING:
                player.logic();
                barrier.setPlayerX((int) player.getPlayerX());
                barrier.setPlayerY((int) player.getPlayerY());
                barrier.setRadius(player.getRadius());

                barrier.logic();
                score.logic();
                break;
            case Constants.GAME_STOP:


                break;
            default:
                break;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gameState)
        {
            case Constants.GAME_START:
                start.onTouchEvent(event);
                break;
            case Constants.GAMEING:
                player.onTouchEvent(event);
                break;
            case Constants.GAME_STOP:

               // gameState=Constants.GAME_START;
                init();
                initGame();
                start.onTouchEvent(event);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override

    public void run() {
        while(flag){
            long start=System.currentTimeMillis();

            canvas=holder.lockCanvas();
            if(null!=canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();
            long end=System.currentTimeMillis();

            if(end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    }

