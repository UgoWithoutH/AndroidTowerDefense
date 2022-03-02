package com.androidtowerdefense.modelandroid.view.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.androidtowerdefense.model.gamelogic.map.Map;

import java.util.HashMap;

public class DrawMap {
    private static final int tileSize = 64;
    private Bitmap bitmap;
    private Map map;
    private int widthResize;
    private int heightResize;
    private Paint paint;
    private HashMap<String,Bitmap> bitmapsCache;

    public DrawMap(Map map, Bitmap bitmap) {
        this.map = map;
        this.bitmap = bitmap;
        paint = new Paint();
        bitmapsCache = new HashMap<>();
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

    public void caching(int width, int height){
        widthResize = width / 20;
        heightResize = height / 13;
        bitmapsCache.put("herbe", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, 6*tileSize, tileSize, tileSize, tileSize),widthResize,heightResize,false));
        bitmapsCache.put("horizontal", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,6*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("vertical", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,7*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("coinEstNord", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,4*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("coinSudEst", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,3*tileSize,3*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("coinNordEst", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,3*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("coinEstSud", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,4*tileSize,2*tileSize,tileSize,tileSize),widthResize,heightResize,false));
        bitmapsCache.put("Tower", Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap,6*tileSize,8*tileSize,tileSize,tileSize),widthResize,heightResize,false));
    }

    public void draw(Canvas canvas){
        Bitmap tile = null;
        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                switch (map.getMap()[y][x]) {
                    case 0 : //peindre l'herbe (OPEN NODE)
                            tile = bitmapsCache.get("herbe");
                            break;
                    case 1 : //peindre le chemin horizontal
                            tile = bitmapsCache.get("horizontal");
                            break;
                    case 2 : //peindre le chemin vertical
                            tile = bitmapsCache.get("vertical");
                            break;
                    case 3 : //peindre coin EST jusqu'à NORD
                            tile = bitmapsCache.get("coinEstNord");
                            break;
                    case 4 : //peindre coin SUD jusqu'à EST
                            tile = bitmapsCache.get("coinSudEst");
                            break;
                    case 5 : //peindre coin NORD jusqu'à EST
                            tile = bitmapsCache.get("coinNordEst");
                            break;
                    case 6 : //peindre coin EST jusqu'à SUD
                            tile = bitmapsCache.get("coinEstSud");
                            break;
                    case 7 : //peindre l'herbe et tour
                            tile = bitmapsCache.get("Tower");
                            break;
                }
                if(tile != null){
                    canvas.drawBitmap(tile,x*tile.getWidth(),y*tile.getHeight(),paint);
                }
            }
        }
    }
}
