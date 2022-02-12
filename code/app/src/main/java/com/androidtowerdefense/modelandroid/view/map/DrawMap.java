package com.androidtowerdefense.modelandroid.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;

import com.androidtowerdefense.model.gamelogic.map.Map;

public class DrawMap {
    private static final int DEFAULT_MATCH_WIDTH = 512;
    //private static final int DEFAULT_MATCH_HEIGHT = 576;
    private Bitmap bitmap;
    private Map map;
    private Context context;

    public DrawMap(Map map, Bitmap bitmap, Context context) {
        this.map = map;
        this.bitmap = bitmap;
        this.context = context;
    }

    //TODO : correspondance de l'écran avec la Bitmap pour la position et les tailles
    public void draw(Canvas canvas, Paint paint){
        Bitmap tile = null;
        //int dim = (int) (64 * context.getResources().getDisplayMetrics().density);
        int width = bitmap.getWidth();
        int dim = width * 64 / DEFAULT_MATCH_WIDTH;
        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                switch (map.getMap()[y][x]) {
                    case 0 : //peindre l'herbe (OPEN NODE)
                            tile = Bitmap.createBitmap(bitmap, 6*dim, dim, dim, dim);
                            break;
                    case 1 : //peindre le chemin horizontal
                            tile = Bitmap.createBitmap(bitmap,6*dim,3*dim,dim,dim);
                            break;
                    case 2 : //peindre le chemin vertical
                            tile = Bitmap.createBitmap(bitmap,7*dim,2*dim,dim,dim);
                            break;
                    case 3 : //peindre coin EST jusqu'à NORD
                            tile = Bitmap.createBitmap(bitmap,4*dim,3*dim,dim,dim);
                            break;
                    case 4 : //peindre coin SUD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,3*dim,3*dim,dim,dim);
                            break;
                    case 5 : //peindre coin NORD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,3*dim,2*dim,dim,dim);
                            break;
                    case 6 : //peindre coin EST jusqu'à SUD
                            tile = Bitmap.createBitmap(bitmap,4*dim,2*dim,dim,dim);
                            break;
                    case 7 : //peindre l'herbe et tour
                            tile = Bitmap.createBitmap(bitmap,6*dim,8*dim,dim,dim);
                            break;
                }
                if(tile != null){
                    canvas.drawBitmap(tile,x*64,y*64,paint);
                }
            }
        }
    }
}
