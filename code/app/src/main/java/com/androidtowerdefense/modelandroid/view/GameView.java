package com.androidtowerdefense.modelandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

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
        int height = bitmap.getWidth();
        int dim = width * 64 / DEFAULT_MATCH_WIDTH;
        GenerationMap generationMap = new GenerationMap(20*dim, 13*dim);
        drawMap = new DrawMap(generationMap,bitmap,context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMap.draw(canvas,paint);
    }
}
