package com.androidtowerdefense.model.characters.tower;


import com.androidtowerdefense.model.Coordinate;
import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.Projectile;
import com.androidtowerdefense.model.gamelogic.ProgressBuild;
import com.androidtowerdefense.model.gamelogic.action.tower.WaitingAttacker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tour
 */
public abstract class Tower implements Serializable {
    public static final int DEFAULT_BUILD_TICK = 400; //Temps de construction
    public static final int DEFAULT_PROGRESS_BUILD_TICK = 5;
    public static final int DEFAULT_ATTACK_TICK = 100;
    public static final int DEFAULT_PROGRESS_ATTACK_TICK = 1;
    public static final int DEFAULT_SELL_COST = 25; //Prix de construction
    private final WaitingAttacker waitingAttacker = new WaitingAttacker(DEFAULT_ATTACK_TICK, 0, DEFAULT_PROGRESS_ATTACK_TICK);
    private ProgressBuild progressBuild = new ProgressBuild(DEFAULT_BUILD_TICK,0, DEFAULT_PROGRESS_BUILD_TICK);
    private int attackDamage;
    private int attackRange;
    private List<Projectile> projectiles;
    private Coordinate coordinate;
    private boolean attacker = true;
    private boolean build = false;

    /**
     * Création d'une Tower à une position X et Y sur la map
     * @param x int position X sur la map
     * @param y int position Y sur la map
     */
    public Tower(int x , int y){
        coordinate = new Coordinate(x , y);
        projectiles = new ArrayList<>();
        attackDamage = 5;
        attackRange = 200;
    }

    public boolean isBuild() {
        return build;
    }

    public void setBuild(boolean build) {
        this.build = build;
    }

    public float getX() {return coordinate.getExactX();}

    public float getY() {return coordinate.getExactY();}

    public int getAttackRange(){
        return attackRange;
    }

    public int getAttackDamage(){
        return  attackDamage;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public boolean isAttacker() {
        return attacker;
    }
    public void setAttacker(boolean attacker) {
        this.attacker = attacker;
    }

    public ProgressBuild getProgressBuild() {
        return progressBuild;
    }

    public boolean isAttackerFinished(){
        return waitingAttacker.isFinished();
    }

    public void incrementedWaitingAttack(){
        waitingAttacker.increment();
    }

    public void resetWaitingAttack(){
        waitingAttacker.reset();
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Créé un projetctile et cible un Character
     * @param target Character ciblé
     */
    public void createProjectile(Character target){
        projectiles.add(new Projectile(target , coordinate.getExactX() , coordinate.getExactY()));
    }
}
