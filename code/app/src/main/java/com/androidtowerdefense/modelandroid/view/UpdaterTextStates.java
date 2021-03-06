package com.androidtowerdefense.modelandroid.view;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.gamelogic.GameState;

public class UpdaterTextStates implements IUpdate{
    private GameState gameState;
    private TextView scoreText;
    private TextView coinsText;
    private TextView levelText;
    private TextView livesText;
    private TextView timeText;

    public UpdaterTextStates(Activity activity, GameState gameState) {
        this.gameState = gameState;
        scoreText = activity.findViewById(R.id.scoreVal);
        coinsText = activity.findViewById(R.id.coinsVal);
        levelText = activity.findViewById(R.id.levelVal);
        livesText = activity.findViewById(R.id.livesVal);
        timeText = activity.findViewById(R.id.timeVal);
    }

    public void update(){
        scoreText.setText(String.valueOf(gameState.getScore()));
        coinsText.setText(String.valueOf(gameState.getCoins()));
        levelText.setText(String.valueOf(gameState.getLevel()));
        livesText.setText(String.valueOf(gameState.getLives()));
        timeText.setText(String.valueOf(gameState.getTimeSeconds()));
    }
}
