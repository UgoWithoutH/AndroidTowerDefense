package com.androidtowerdefense.modelandroid.view.map;

import android.graphics.Bitmap;
import android.media.Image;

import com.androidtowerdefense.model.gamelogic.map.Map;

public class DrawMap {
    private Bitmap bitmap;
    private Map map;

    public DrawMap(Map map, Bitmap bitmap) {
        this.map = map;
        this.bitmap = bitmap;
    }

    public void draw(){}
}
