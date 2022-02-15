package com.androidtowerdefense.modelandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.androidtowerdefense.R;
import com.androidtowerdefense.model.gamelogic.map.GenerationMap;
import com.androidtowerdefense.model.gamelogic.map.ImportMap;
import com.androidtowerdefense.model.gamelogic.map.Map;
import com.androidtowerdefense.modelandroid.view.map.DrawMap;

public class GameView extends View {
    private Paint paint = new Paint();
    private Bitmap bitmap;
    private DrawMap drawMap;
    private static final int DEFAULT_MATCH_WIDTH = 512;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        int width = bitmap.getWidth();
        int tileSize = width * 64 / DEFAULT_MATCH_WIDTH;
        GenerationMap generationMap = new GenerationMap(20*tileSize, 13*tileSize);
        drawMap = new DrawMap(generationMap,bitmap,context);
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
        drawMap.draw(canvas,paint);
    }

    //est appelé quand le téléphone tourne içi on viendrait mettre en cache
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
