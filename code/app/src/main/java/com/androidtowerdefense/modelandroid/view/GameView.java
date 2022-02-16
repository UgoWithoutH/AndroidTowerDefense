package com.androidtowerdefense.modelandroid.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.map.GenerationMap;
import com.androidtowerdefense.model.observer.IObserver;
import com.androidtowerdefense.modelandroid.view.draw.DrawMap;
import com.androidtowerdefense.modelandroid.view.draw.DrawCharacters;
import com.androidtowerdefense.modelandroid.view.draw.DrawProjectiles;

public class GameView extends View  implements IObserver {
    private Bitmap bitmap;
    private GameManager gameManager;
    private DrawMap drawMap;
    private DrawCharacters drawMonsters;
    private DrawProjectiles drawProjectiles;
    private Activity gameActivity;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        GenerationMap generationMap = new GenerationMap(20*64, 13*64);
        drawMap = new DrawMap(generationMap,bitmap,context);
    }

    public void setGameManager(GameManager gameManager){
        this.gameManager = gameManager;
        drawMonsters = new DrawCharacters(gameManager.getGame().getCharactersAlive());
        drawProjectiles = new DrawProjectiles(gameManager.getGame().getPlayerTowers());
        gameManager.subscribe(this);
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawMap getDrawMap() {
        return drawMap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap.draw(canvas);
        gameManager.setTileWidth(drawMap.getWidthResize());
        gameManager.setTileHeight(drawMap.getHeightResize());
        drawMonsters.draw(canvas);
        drawProjectiles.draw(canvas);

    }

    //est appelé quand le téléphone tourne içi on viendrait mettre en cache
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
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
}
