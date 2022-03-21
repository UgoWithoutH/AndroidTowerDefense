package com.androidtowerdefense.model.characters;


import java.io.Serializable;

/**
 * Coordonnées
 */
public class Coordinate implements Serializable {
    private float x;
    private float y;
    private static int tileWidth;
    private static int tileHeight;

    /**
     * Coordonée sur la map
     * @param x int Position X
     * @param y int Position Y
     */
    public Coordinate(float x , float y){
        this.x = x;
        this.y = y;
    }

    public static void setTileWidth(int tileWidth) {
        Coordinate.tileWidth = tileWidth;
    }

    public static void setTileHeight(int tileHeight) {
        Coordinate.tileHeight = tileHeight;
    }

    /**
     * Accède a la position X exacte sur la fenêtre
     * @return int Position X
     */
    public float getExactX() {
        return x * tileWidth + (tileWidth/2);
    }

    /**
     * Accède a la position Y exacte sur la fenêtre
     * @return  int Position Y
     */
    public float getExactY() { return y * tileHeight + (tileHeight/2);}

    public float getX() {return x;}
    public void setX(float x) {this.x = x;}

    public float getY() {return y;}
    public void setY(float y) {this.y = y;}

    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
