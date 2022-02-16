package com.androidtowerdefense.modelandroid.view.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.characters.monster.Speed;

import java.util.List;

public class DrawCharacters {
    List<Character> list;

    public DrawCharacters(List<Character> list) {
        this.list = list;
    }

    public void draw(Canvas canvas, Paint paint){
        for(Character character : list){
            if(character instanceof Monster){
                drawMonster(canvas, paint, (Monster) character);
            }
        }
    }

    private void drawMonster(Canvas canvas, Paint paint, Monster monster){
        if(monster instanceof Speed){
            paint.setColor(Color.GREEN);
        }
        else{
            paint.setColor(Color.RED);
        }
        canvas.drawCircle(monster.getCoordinate().getX(), monster.getCoordinate().getY(), 10, paint);
    }
}
