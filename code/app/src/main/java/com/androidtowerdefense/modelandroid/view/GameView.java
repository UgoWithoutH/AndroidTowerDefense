package com.androidtowerdefense.modelandroid.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.action.IBuyerTower;
import com.androidtowerdefense.model.gamelogic.action.tower.BuyerTower;
import com.androidtowerdefense.model.gamelogic.map.GenerationMap;
import com.androidtowerdefense.model.observer.IObserver;
import com.androidtowerdefense.modelandroid.view.draw.DrawMap;
import com.androidtowerdefense.modelandroid.view.draw.DrawCharacters;
import com.androidtowerdefense.modelandroid.view.draw.DrawProgressBar;
import com.androidtowerdefense.modelandroid.view.draw.DrawProjectiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameView extends View implements IObserver {
    private Bitmap bitmap;
    private GameManager gameManager;
    private DrawMap drawMap;
    private DrawCharacters drawMonsters;
    private DrawProjectiles drawProjectiles;
    private DrawProgressBar drawProgressBar;
    private UpdaterTextStates updaterTextStates;
    private IVerifier verifier;
    private ConstraintLayout endLayout;
    private Activity gameActivity;
    private TextView textEndGame;
    private Paint paint;
    private boolean constructTowers = true;
    private Map<ProgressBar,Integer> progressBars = new HashMap<>();

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        GenerationMap generationMap = new GenerationMap(20*64, 13*64);
        drawMap = new DrawMap(generationMap,bitmap);
        drawProgressBar = new DrawProgressBar();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
        drawMonsters = new DrawCharacters(gameManager.getGame().getCharactersAlive());
        drawProjectiles = new DrawProjectiles(gameManager.getGame().getPlayerTowers());
        verifier = new VerifierGame(gameManager.getGame());
        gameManager.subscribe(this);
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
        endLayout = gameActivity.findViewById(R.id.gameEndView);
        updaterTextStates = new UpdaterTextStates(gameActivity,gameManager.getGame());
        textEndGame = gameActivity.findViewById(R.id.textEndGame);
    }

    public DrawMap getDrawMap() {
        return drawMap;
    }

    public void setConstructTowers(boolean constructTowers) {
        this.constructTowers = constructTowers;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap.draw(canvas);
        gameManager.setTileWidth(drawMap.getWidthResize());
        gameManager.setTileHeight(drawMap.getHeightResize());
        drawProgressBar.draw(canvas, progressBars);
        drawMonsters.draw(canvas);
        drawProjectiles.draw(canvas);
        updaterTextStates.update();
        int returnCode = verifier.verify();
        if(returnCode != VerifierGame.DEFAULT_RESULT_CODE){
            if(returnCode == VerifierGame.VICTORY_RESULT_CODE) {
                textEndGame.setText(gameActivity.getString(R.string.victory));
                textEndGame.setTextColor(Color.GREEN);
                endLayout.setVisibility(VISIBLE);
            }
            else if(returnCode == VerifierGame.GAMEOVER_RESULT_CODE) {
                textEndGame.setText(gameActivity.getString(R.string.gameOver));
                textEndGame.setTextColor(Color.RED);
                endLayout.setVisibility(VISIBLE);
            }
        }
    }

    //est appelé quand le téléphone tourne içi on viendrait mettre en cache
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        drawMap.caching(w,h);
    }

    @Override
    public void update(int timer) {
        gameActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        });
    }

    public void initializeListener() {
        this.setOnTouchListener((view, event) -> {
            if (constructTowers) {
                Log.d("click","click");
                IBuyerTower buyer = new BuyerTower(gameManager.getGame(), gameManager.getGameMap());
                Tower tower = buyer.buy((int) event.getX()/drawMap.getWidthResize(), (int) event.getY()/drawMap.getHeightResize());
                if(tower != null){
                    progressBars.put(new ProgressBar(tower.getProgressBuild(),(int) event.getX(),(int) event.getY()),0);
                    invalidate();
                } //déléguer tout ça dans le GameManager
                constructTowers = true;
            }
            return true;
        });
    }
}
