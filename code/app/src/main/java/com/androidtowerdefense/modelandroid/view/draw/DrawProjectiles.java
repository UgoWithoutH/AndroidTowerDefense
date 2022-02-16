package com.androidtowerdefense.modelandroid.view.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.androidtowerdefense.model.characters.Projectile;
import com.androidtowerdefense.model.characters.tower.Tower;

import java.util.List;

public class DrawProjectiles {
    private List<Tower> towers;
    private Paint paint;

    public DrawProjectiles(List<Tower> towers) {
        this.towers = towers;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas){
        for(Tower tower : towers){
            for(Projectile projectile : tower.getProjectiles()){
                canvas.drawCircle(projectile.getCurrentX(),projectile.getCurrentY(),5,paint);
            }
        }
    }
}
