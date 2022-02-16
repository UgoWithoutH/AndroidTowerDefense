package com.androidtowerdefense.model.characters;

/**
 * Projectile d'une tour
 */
public class Projectile{
    private final Character target;
    private int currentX;
    private int currentY;

    /**
     * Créé un Projectile de Tour avec une cible Character
     * @param target    Character Cible
     * @param towerX    int Position x de la Tour (Point de départ du Projectile)
     * @param towerY    int Position y de la Tour (Point de départ du Projectile)
     *
     */
    public Projectile(Character target, int towerX, int towerY){
        this.target = target;
        currentX = towerX;
        currentY = towerY;
    }



    public Character getTarget(){
        return target;
    }

    public int getEndX(){return target.getX();}
    public int getEndY(){return target.getY();}

    public int getCurrentX(){return currentX;}
    public int getCurrentY(){return currentY;}

    public void setCurrentX(int currentX) {this.currentX = currentX;}
    public void setCurrentY(int currentY) {this.currentY = currentY;}
}

