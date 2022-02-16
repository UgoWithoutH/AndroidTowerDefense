package com.androidtowerdefense.model.gamelogic.action.tower;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IAttacker;
import com.androidtowerdefense.model.gamelogic.action.states.Updater;

import java.util.List;

public class AttackerTower implements IAttacker {
   private final GameState gameState;

    public AttackerTower(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void attack(Character target, Tower tower) {
        target.takeDamage(tower.getAttackDamage());
        if(target.isDead()){
            gameState.getCharactersAlive().remove(target);
            Updater.updateStates(target, gameState);
        }
    }
}
