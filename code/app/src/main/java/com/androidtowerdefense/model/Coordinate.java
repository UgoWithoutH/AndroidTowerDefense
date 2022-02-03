package com.androidtowerdefense.model;


import java.io.Serializable;

/**
 * Coordonnées
 */
public class Coordinate implements Serializable {
    private int x;
    private int y;

    /**
     * Coordonée sur la map
     * @param x int Position X
     * @param y int Position Y
     */
    public Coordinate(int x , int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Accède a la position X exacte sur la fenêtre
     * @return int Position X
     */
    public int getExactX() {
        return x * 64 + 32;
    }

    /**
     * Accède a la position Y exacte sur la fenêtre
     * @return  int Position Y
     */
    public int getExactY() { return y * 64 + 32;}

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

    public int getY() {return y;}
    public void setY(int y) {this.y = y;}

    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
