package com.androidtowerdefense.modelandroid.view.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.characters.monster.Speed;

import java.util.List;

public class DrawCharacters {
    private List<Character> characters;
    private Paint paint;

    public DrawCharacters(List<Character> characters) {
        this.characters = characters;
        paint = new Paint();
    }

    public void draw(Canvas canvas){
        for(Character character : characters){
            if(character instanceof Monster){
                drawMonster(canvas, (Monster) character);
            }
        }
    }

    private void drawMonster(Canvas canvas, Monster monster){
        if(monster instanceof Speed){
            paint.setColor(Color.GREEN);
        }
        else{
            paint.setColor(Color.RED);
        }
        canvas.drawCircle(monster.getCoordinate().getX(), monster.getCoordinate().getY(), 10, paint);
    }
}
