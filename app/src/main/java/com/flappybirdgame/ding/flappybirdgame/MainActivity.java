package com.flappybirdgame.ding.flappybirdgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.flappybirdgame.ding.flappybirdgame.game.GameSurfaceView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(new GameSurfaceView(this));

    }
}
