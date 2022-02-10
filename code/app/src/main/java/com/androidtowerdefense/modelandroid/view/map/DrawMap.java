package com.androidtowerdefense.modelandroid.view.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;

import com.androidtowerdefense.model.gamelogic.map.Map;

public class DrawMap {
    private static final int DEFAULT_DIM_PIXELS = 64;
    private static final int DEFAULT_MATCH_WIDTH = 512;
    private static final int DEFAULT_MATCH_HEIGHT = 576;
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
        Bitmap tile;
        int dim = (int) (64 * context.getResources().getDisplayMetrics().density);
        int width = bitmap.getWidth();
        int height = bitmap.getWidth();
        Log.d("dim", String.valueOf((int) (64* context.getResources().getDisplayMetrics().density)));
        for(int x = 0; x < map.getTileLengthX(); x++){
            for(int y = 0; y < map.getTileLengthY(); y++ ){
                Log.d("map", String.valueOf(map.getMap()[y][x]));
                switch (map.getMap()[y][x]) {
                    case 0 : //peindre l'herbe (OPEN NODE)
                            Log.d("test", String.valueOf(bitmap.getWidth() + " " + bitmap.getWidth()));
                            tile = Bitmap.createBitmap(bitmap, DEFAULT_MATCH_WIDTH*384/width, DEFAULT_MATCH_HEIGHT*64/height, dim, dim);
                            canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 1 : //peindre le chemin horizontal
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*384/width,DEFAULT_MATCH_HEIGHT*192/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 2 : //peindre le chemin vertical
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*448/width,DEFAULT_MATCH_HEIGHT*128/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 3 : //peindre coin EST jusqu'à NORD
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*256/width,DEFAULT_MATCH_HEIGHT*192/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 4 : //peindre coin SUD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*192/width,DEFAULT_MATCH_HEIGHT*192/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 5 : //peindre coin NORD jusqu'à EST
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*192/width,DEFAULT_MATCH_HEIGHT*128/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 6 : //peindre coin EST jusqu'à SUD
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*256/width,DEFAULT_MATCH_HEIGHT*128/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                    case 7 : //peindre l'herbe et tour
                            tile = Bitmap.createBitmap(bitmap,DEFAULT_MATCH_WIDTH*384/width,DEFAULT_MATCH_HEIGHT*512/height,dim,dim);
                        canvas.drawBitmap(tile,x*dim,y*dim,paint);
                            break;
                }
            }
        }
    }
}
