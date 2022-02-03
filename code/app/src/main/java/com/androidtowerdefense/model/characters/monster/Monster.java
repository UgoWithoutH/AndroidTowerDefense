package com.androidtowerdefense.model.characters.monster;

import com.androidtowerdefense.model.characters.Character;

/**
 * Définit un Monster à partir d'un Character
 */
public class Monster extends Character {

    /**
     * Variable qui définit si le Monstre est visible ou non
     */
    private boolean visible;

    /**
     * Constructeur de Monstre
     * @param healthPoints  int Nombre Point de Vie
     * @param movementSpeed int Vitesse de Mouvement
     */
    public Monster(int healthPoints, int movementSpeed) {
        super(healthPoints,movementSpeed);
        visible = true;
    }


    public boolean isVisible() {return visible;}
    public void setVisible(boolean visible) {this.visible = visible;}

    /**
     *  Dommages reçus
     * @param damage int Valeur des dégats
     */
    public void takeDamage(int damage) {
        setHealthPoints(getHealthPoints() - damage);
        if (getHealthPoints() <= 0) {
            setDead(true);
            setPathFinished(false);
        }
    }
}
