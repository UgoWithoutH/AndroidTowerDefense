package com.androidtowerdefense.model.gamelogic.action.tower;

import android.util.Log;

import com.androidtowerdefense.model.Coordinate;
import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.Projectile;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IDisplacer;

import java.util.ArrayList;
import java.util.List;

public class DisplacerProjectiles implements IDisplacer {
    private static final int DEFAULT_SPEED_PROJECTILES = 10;
    private GameState gameState;
    private AttackerTower attackerTower;

    public DisplacerProjectiles(GameState gameState) {
        this.gameState = gameState;
        attackerTower = new AttackerTower(gameState);
    }

    @Override
    public boolean updateLocations() {
        ArrayList<Integer> positions;
        ArrayList<Projectile> removeProjectiles = new ArrayList<>();
        for(Tower tower : gameState.getPlayerTowers()){
            for(Projectile projectile : tower.getProjectiles()){
                positions = generatePosition(projectile);
                projectile.setCurrentX(positions.get(0));
                projectile.setCurrentY(positions.get(1));
                if((projectile.getTarget().getCoordinate().getX() == projectile.getCurrentX()
                        &&
                   projectile.getTarget().getCoordinate().getY() == projectile.getCurrentY())
                        ||
                   projectile.getTarget().isDead()){
                    removeProjectiles.add(projectile);
                }
            }
            for(Projectile projectile : removeProjectiles){
                Character target = projectile.getTarget();
                tower.getProjectiles().remove(projectile);
                attackerTower.attack(target, tower);
            }
        }
        return true;
    }

    private ArrayList<Integer> generatePosition(Projectile projectile){
        ArrayList<Integer> positions = new ArrayList<>();
        Character target = projectile.getTarget();

        int posX;
        int posY;

        if(projectile.getCurrentX() > target.getCoordinate().getX()){
            posX = projectile.getCurrentX() - DEFAULT_SPEED_PROJECTILES;
            if(posX < target.getCoordinate().getX()){
                posX = target.getCoordinate().getX();
            }
        }
        else{
            posX = projectile.getCurrentX() + DEFAULT_SPEED_PROJECTILES;
            if(posX > target.getCoordinate().getX()){
                posX = target.getCoordinate().getX();
            }
        }
        positions.add(posX);

        if(projectile.getCurrentY() > target.getCoordinate().getY()){
            posY = projectile.getCurrentY() - DEFAULT_SPEED_PROJECTILES;
            if(posY < target.getCoordinate().getY()){
                posY = target.getCoordinate().getY();
            }
        }
        else{
            posY = projectile.getCurrentY() + DEFAULT_SPEED_PROJECTILES;
            if(posY > target.getCoordinate().getY()){
                posY =  target.getCoordinate().getY();
            }
        }
        positions.add(posY);

        return positions;
    }
}
