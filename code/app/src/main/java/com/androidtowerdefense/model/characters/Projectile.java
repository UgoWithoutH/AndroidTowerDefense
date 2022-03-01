package com.androidtowerdefense.model.characters;

/**
 * Projectile d'une tour
 */
public class Projectile{
    private static final float DEFAULT_SPEED_PROJECTILES = 4.5f;
    private final Character target;
    private float currentX;
    private float currentY;

    /**
     * Créé un Projectile de Tour avec une cible Character
     * @param target    Character Cible
     * @param towerX    int Position x de la Tour (Point de départ du Projectile)
     * @param towerY    int Position y de la Tour (Point de départ du Projectile)
     *
     */
    public Projectile(Character target, float towerX, float towerY){
        this.target = target;
        currentX = towerX;
        currentY = towerY;
    }

    public static float getDefaultSpeedProjectiles() {
        return DEFAULT_SPEED_PROJECTILES;
    }

    public Character getTarget(){
        return target;
    }

    public float getCurrentX(){return currentX;}
    public float getCurrentY(){return currentY;}

    public void setCurrentX(float currentX) {this.currentX = currentX;}
    public void setCurrentY(float currentY) {this.currentY = currentY;}
}

