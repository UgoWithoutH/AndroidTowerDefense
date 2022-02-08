package com.androidtowerdefense.modelandroid.view.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;

import com.androidtowerdefense.model.gamelogic.map.Map;

public class DrawMap {
    private Bitmap bitmap;
    private Map map;

    public DrawMap(Map map, Bitmap bitmap) {
        this.map = map;
        this.bitmap = bitmap;
    }

    //TODO : correspondance de l'écran avec la Bitmap pour la position et les tailles
    public void draw(Canvas canvas, Paint paint){
        Bitmap tile;

        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                switch (map.getMap()[y][x]) {
                    case 0 : //peindre l'herbe (OPEN NODE)
                            tile = Bitmap.createBitmap(bitmap,384,64,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 1 : //peindre le chemin horizontal
                            tile = Bitmap.createBitmap(bitmap,384,192,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 2 : //peindre le chemin vertical
                            tile = Bitmap.createBitmap(bitmap,448,128,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 3 : //peindre coin EST jusqu'à NORD
                            tile = Bitmap.createBitmap(bitmap,256,192,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 4 : //peindre coin SUD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,192,192,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 5 : //peindre coin NORD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,192,128,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 6 : //peindre coin EST jusqu'à SUD
                            tile = Bitmap.createBitmap(bitmap,256,128,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                    case 7 : //peindre l'herbe et tour
                            tile = Bitmap.createBitmap(bitmap,384,512,64,64);
                            canvas.drawBitmap(tile,0,0,paint);
                            break;
                }
            }
        }
    }
}
