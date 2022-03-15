package com.androidtowerdefense.modelandroid.view.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.ProgressBuild;
import com.androidtowerdefense.modelandroid.view.ProgressBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DrawProgressBar {
    private static final int DEFAULT_WIDTH_DRAW = 80;
    private static final int DEFAULT_HEIGHT_DRAW = 20;
    private List<ProgressBar> progressBarToRemove = new ArrayList<>();

    private Paint paint;

    public DrawProgressBar() {
        paint = new Paint();
    }

    public void draw(Canvas canvas, Map<ProgressBar,Integer> progressBars) {

        if(!progressBarToRemove.isEmpty()) {
            for (ProgressBar progressBar : progressBarToRemove) {
                progressBars.remove(progressBar);
            }
            progressBarToRemove.clear();
        }

        Iterator<Map.Entry<ProgressBar,Integer>> it = progressBars.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<ProgressBar,Integer> map = it.next();
            ProgressBar progressBar = map.getKey();
            if(progressBar.getProgressBuild().isFinished()){
                if(map.getValue() == 0){
                    progressBars.put(progressBar,1);
                }
                else if(map.getValue() == 1){
                    progressBarToRemove.add(progressBar);
                }
            }
            else{
                paint.setColor(Color.WHITE);
                ProgressBuild progressBuild = progressBar.getProgressBuild();
                canvas.drawRect(new Rect(progressBar.getxStart(),
                                         progressBar.getyStart(),
                                         progressBar.getxStart()+DEFAULT_WIDTH_DRAW,
                                         progressBar.getyStart()+DEFAULT_HEIGHT_DRAW
                                        ),paint);

                paint.setColor(Color.BLUE);
                int numberTotalBlock = progressBuild.getMaxProgress()/progressBuild.getIncrementProgress();
                int widthProgress = DEFAULT_WIDTH_DRAW / numberTotalBlock;
                int numberBlock = progressBuild.getCurrentProgress()/progressBuild.getIncrementProgress();
                int widthToDraw = widthProgress * numberBlock;
                Log.d("dessin", String.valueOf(widthToDraw));
                canvas.drawRect(new Rect(progressBar.getxStart(),
                        progressBar.getyStart(),
                        progressBar.getxStart()+widthToDraw,
                        progressBar.getyStart()+DEFAULT_HEIGHT_DRAW-10
                ),paint);
            }
        }
    }
}
