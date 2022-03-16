package com.androidtowerdefense.modelandroid.view;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidtowerdefense.model.gamelogic.GameState;

public class VerifierGame implements IVerifier {
    private GameState gameState;
    public static final int DEFAULT_RESULT_CODE = 0;
    public static final int VICTORY_RESULT_CODE = 1;
    public static final int GAMEOVER_RESULT_CODE = 2;

    public VerifierGame(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public int verify() {
        if(gameState.isVictory()){
            return VICTORY_RESULT_CODE;
        }
        else if(gameState.isGameOver()){
            return GAMEOVER_RESULT_CODE;
        }
        else{
            return DEFAULT_RESULT_CODE;
        }
    }
}
