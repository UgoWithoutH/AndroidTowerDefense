package com.androidtowerdefense.model.gamelogic;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * States relatifs à la partie
 */
public class GameState implements Comparable<GameState>{

    private final List<Tower> playerTowers;
    private final List<Character> charactersAlive;
    private boolean speed = false;
    private boolean removeCharacter = false;
    private String pseudo;
    private int timeSeconds;
    private boolean gameOver;
    private boolean victory;
    private int level;
    private int lives;
    private int coins;
    private int score;

    /**
     * Ressources du joueur et states de la Partie
     * @param pseudo    String Pseudo
     */
    public GameState(String pseudo){
        this.pseudo = pseudo;
        playerTowers = new ArrayList<>();
        charactersAlive = new ArrayList<>();
        timeSeconds = 0;
        coins = 50;
        level = 1;
        score = 0;
        lives = 2;
        victory = false;
    }

    public boolean isRemoveCharacter() {return removeCharacter;}
    public void setRemoveCharacter(boolean value){
        removeCharacter = value;}

    public boolean isSpeed() {return speed;}
    public void setSpeed(boolean speed) {this.speed = speed;}

    public List<Tower> getPlayerTowers() {return playerTowers;}

    public List<Character> getCharactersAlive() {return charactersAlive;}

    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    public int getTimeSeconds() {return timeSeconds;}
    public void setTimeSeconds(int timeSeconds) {this.timeSeconds = timeSeconds;}

    public boolean isGameOver() {return gameOver;}
    public void setGameOver(boolean gameOver) {this.gameOver = gameOver;}

    public boolean isVictory() {return victory;}
    public void setVictory(boolean victory) {this.victory = victory;}

    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}

    public int getLives() {return lives;}
    public void setLives(int lives) {this.lives = lives;}

    public int getCoins() {return coins;}
    public void setCoins(int coins) {this.coins = coins;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public void addTower(Tower tower){playerTowers.add(tower);}

    @Override
    public int compareTo(GameState g) {
        int coeff = g.isGameOver() ? 100 : 1;

        if(this.getPseudo().compareTo(g.getPseudo()) == 0) {
            return ((this.getLevel() - g.getLevel()*coeff) +
                    (this.getScore() - g.getScore()) +
                    (this.getTimeSeconds()) - g.getTimeSeconds());
        }
        else{
            return ((this.getLevel() - g.getLevel()*coeff) +
                    (this.getScore() - g.getScore()) +
                    (this.getTimeSeconds()) - g.getTimeSeconds() +
                    this.getPseudo().compareTo(g.getPseudo()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return pseudo.equals(gameState.pseudo) && timeSeconds == gameState.timeSeconds && victory == gameState.victory && level == gameState.level && score == gameState.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, timeSeconds, victory, level, score);
    }
}
