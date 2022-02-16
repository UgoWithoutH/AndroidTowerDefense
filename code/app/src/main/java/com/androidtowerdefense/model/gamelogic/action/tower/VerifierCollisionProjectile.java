package com.androidtowerdefense.model.gamelogic.action.tower;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.Projectile;

public class VerifierCollisionProjectile {

    public static boolean verifyCollision(Character character, Projectile projectile){
        return character.getCoordinate().getX() == projectile.getCurrentX() && character.getCoordinate().getY() == projectile.getCurrentY();
    }

}
