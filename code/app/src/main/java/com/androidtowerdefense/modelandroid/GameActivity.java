package com.androidtowerdefense.modelandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.modelandroid.view.GameView;
import com.androidtowerdefense.modelandroid.view.draw.DrawMap;

public class GameActivity extends AppCompatActivity{

    private GameManager gameManager;
    private RankingManager rankingManager;
    private Button pauseRestartButton;
    private Button speedButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getIntent().getExtras();
        String pseudo = data.getString("pseudo");
        setContentView(R.layout.game_view);
        GameView gameView = findViewById(R.id.myView);
        pauseRestartButton = findViewById(R.id.buttonStopRestart);
        speedButton = findViewById(R.id.speedButton);
        DrawMap drawMap = gameView.getDrawMap();
        rankingManager = new RankingManager(getApplicationContext());
        gameManager = new GameManager(pseudo, drawMap.getMap());
        gameView.setGameManager(gameManager);
        gameView.initializeListener();
        if(!gameManager.isRunning()){
            gameManager.start();
        }
    }

    public void returnHome(View view) {
        rankingManager.saveGameState(gameManager.getGame());
        finish();
    }

    public void giveUp(View view){
        finish();
    }

    public void stopOrRestart(View view) {
        if (gameManager.isRunning()) {
            pauseRestartButton.setText(getString(R.string.restart));
            gameManager.stop();
        } else {
            pauseRestartButton.setText(R.string.stop);
            gameManager.restart();
        }
    }

    public void speed(View view) {
        if (!gameManager.getGame().isSpeed()) {
            speedButton.setText(getString(R.string.x2));
            gameManager.getGame().setSpeed(true);
            gameManager.setSpeedMillis(gameManager.getSpeedMillis() / 2);
        } else {
            speedButton.setText(getString(R.string.x1));
            gameManager.getGame().setSpeed(false);
            gameManager.setSpeedMillis(gameManager.getSpeedMillis() * 2);
        }
    }
}
