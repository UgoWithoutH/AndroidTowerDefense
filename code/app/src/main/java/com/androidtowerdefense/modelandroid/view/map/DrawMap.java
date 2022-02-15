package com.androidtowerdefense.modelandroid.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;

import com.androidtowerdefense.model.gamelogic.map.Map;

public class DrawMap {
    private static final int tileSize = 64;
    private Bitmap bitmap;
    private Map map;
    private Context context;
    private int widthResize;
    private int heightResize;

    public DrawMap(Map map, Bitmap bitmap, Context context) {
        this.map = map;
        this.bitmap = bitmap;
        this.context = context;
    }

    public Map getMap() {
        return map;
    }

    public int getWidthResize() {
        return widthResize;
    }

    public int getHeightResize() {
        return heightResize;
    }

    //TODO : correspondance de l'écran avec la Bitmap pour la position et les tailles
    public void draw(Canvas canvas, Paint paint){
        Bitmap tile = null;
        widthResize = canvas.getWidth() / 20;
        heightResize = canvas.getHeight() / 13;
        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                switch (map.getMap()[y][x]) {
                    case 0 : //peindre l'herbe (OPEN NODE)
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, 6*tileSize, tileSize, tileSize, tileSize),widthResize,heightResize,false);
                            break;
                    case 1 : //peindre le chemin horizontal
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,6*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 2 : //peindre le chemin vertical
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,7*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 3 : //peindre coin EST jusqu'à NORD
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,4*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 4 : //peindre coin SUD jusqu'à EST
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,3*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 5 : //peindre coin NORD jusqu'à EST
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,3*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 6 : //peindre coin EST jusqu'à SUD
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,4*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                    case 7 : //peindre l'herbe et tour
                            tile = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,6*tileSize,8*tileSize,tileSize,tileSize),widthResize,heightResize,false);
                            break;
                }
                if(tile != null){
                    canvas.drawBitmap(tile,x*tile.getWidth(),y*tile.getHeight(),paint);
                }
            }
        }
    }
}
