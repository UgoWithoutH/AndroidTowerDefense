package com.androidtowerdefense.model.characters;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Caractère (ennemis)
 */
public abstract class Character implements Serializable {
    private static ArrayList<Coordinate> path;
    private Coordinate coordinate;
    private int healthPoints;
    private int reward;
    private int direction;
    private boolean moveX;
    private boolean dead;
    private boolean pathFinished;
    private float movementSpeed;

    /**
     * Définit les valeurs Basic d'un character
     * @param healthPoints  int Nombre de Point de Vie
     * @param movementSpeed int Vitesse
     */
    public Character(int healthPoints, float movementSpeed) {
        pathFinished = false;
        moveX = true;
        direction = 1;
        this.movementSpeed = movementSpeed;
        this.healthPoints = healthPoints;
        reward = 2;
        coordinate = new Coordinate(path.get(0).getExactX(), path.get(0).getExactY());
    }

    public int getDirection() {return direction;}
    public void setDirection(int direction) {this.direction = direction;}

    public boolean isMoveX() {return moveX;}
    public void setMoveX(boolean moveX) {this.moveX = moveX;}

    public int getHealthPoints() {return healthPoints;}
    public void setHealthPoints(int healthPoints) {this.healthPoints = healthPoints;}

    public Coordinate getCoordinate(){return coordinate;}

    public float getX() {return coordinate.getX();}
    public void setX(float x) {coordinate.setX(x);}

    public float getY() {return coordinate.getY();}
    public void setY(float y) {coordinate.setY(y);}

    public int getReward() {
        return reward;
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean value){dead = value;}

    public boolean isPathFinished() {
        return pathFinished;
    }
    public void setPathFinished(boolean pathFinished) {this.pathFinished = pathFinished;}

    public static void setPath(ArrayList<Coordinate> pathSet) {path = pathSet;}
    public ArrayList<Coordinate> getPath(){return path;}

    public float getMovementSpeed() {return movementSpeed;}
    public void setMovementSpeed(float movementSpeed){this.movementSpeed = movementSpeed;}


    public abstract void takeDamage(int damage);
    /**
     * Modifie la Position, vérifie s'il est arrivé à la fin du chemin.
     * Modifie également la direction qu'il doit prendre en fonction de sa position et du chemin définit
     */
    public void updateLocation() {
        if(pathFinished) return;
        // Déplacement selon l'axe des x
        if (moveX) {
            if((coordinate.getX() + movementSpeed) > path.get(direction).getExactX()){
                setX(path.get(direction).getExactX());
            }
            else {
                setX(coordinate.getX() + movementSpeed);
            }
            // Arrivé à un point de changement dans le chemin, changer de direction
            if (coordinate.getX() == path.get(direction).getExactX()) {
                moveX = false;
                direction++;
                // Traversée de tous les points de changement, fin du chemin
                if (direction == path.size()) {
                    pathFinished = true;
                    dead = true;
                }
            }
        }
        // Déplacement selon l'axe des y
        else {
            if (coordinate.getY() < path.get(direction).getExactY()) {
                if((coordinate.getY() + movementSpeed) > path.get(direction).getExactY()){
                    setY(path.get(direction).getExactY());
                }
                else {
                    setY(coordinate.getY() + movementSpeed);
                }
            } else {
                if((coordinate.getY() - movementSpeed) < path.get(direction).getExactY()){
                    setY(path.get(direction).getExactY());
                }
                else{
                    setY(coordinate.getY() - movementSpeed);
                }
            }
            // Atteindre le point de changement, changer de direction
            if (coordinate.getY() == path.get(direction).getExactY()) {
                moveX = true;
                direction++;
                if (direction == path.size()) {
                    pathFinished = true;
                    dead = true;
                }
            }
        }
    }
}
