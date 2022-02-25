package com.androidtowerdefense.model.characters.monster;

/**
 * Monstre basique
 */
public class Basic extends Monster {

    private static final float DEFAULT_MOVEMENT_SPEED = 0.10f;

    /**
     * Creer un Monstre Basic avec un choix du nombre de point de vie
     * @param healthPoints int Nombre de points de vie
     */
    public Basic(int healthPoints) {
        super(healthPoints,DEFAULT_MOVEMENT_SPEED);
    }
}
