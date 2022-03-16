package com.androidtowerdefense.model.characters.monster;

import android.util.Log;

import com.androidtowerdefense.model.characters.Character;

/**
 * Définit un Monster à partir d'un Character
 */
public abstract class Monster extends Character {
    public static final int DEFAULT_BASIC_LIFE = 10;
    public static final int DEFAULT_SPEED_LIFE = 3;

    /**
     * Variable qui définit si le Monstre est visible ou non
     */
    private boolean visible;

    /**
     * Constructeur de Monstre
     * @param healthPoints  int Nombre Point de Vie
     * @param movementSpeed int Vitesse de Mouvement
     */
    public Monster(int healthPoints, float movementSpeed) {
        super(healthPoints,movementSpeed);
        visible = true;
    }


    public boolean isVisible() {return visible;}
    public void setVisible(boolean visible) {this.visible = visible;}

    /**
     *  Dommages reçus
     * @param damage int Valeur des dégats
     */
    @Override
    public void takeDamage(int damage) {
        Log.d("attaque","dégats");
        setHealthPoints(getHealthPoints() - damage);
        if (getHealthPoints() <= 0) {
            setDead(true);
            setPathFinished(false);
        }
    }
}
