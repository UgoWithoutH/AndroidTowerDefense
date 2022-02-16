package com.androidtowerdefense.model;


import java.io.Serializable;

/**
 * Coordonnées
 */
public class Coordinate implements Serializable {
    private int x;
    private int y;
    private static int tileWidth;
    private static int tileHeight;

    /**
     * Coordonée sur la map
     * @param x int Position X
     * @param y int Position Y
     */
    public Coordinate(int x , int y){
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
    public int getExactX() {
        return x * tileWidth + (tileWidth/2);
    }

    /**
     * Accède a la position Y exacte sur la fenêtre
     * @return  int Position Y
     */
    public int getExactY() { return y * tileHeight + (tileHeight/2);}

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
