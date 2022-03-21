package com.androidtowerdefense.model.ranking;

import com.androidtowerdefense.model.gamelogic.GameState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Score et Ranking permettant de classer le resultat en fonction du score obtenu
 */
public class ScoreRanking {
    private String pseudo;
    private int level;
    private int score;
    private int time;

    public ScoreRanking(String pseudo, int level, int score, int time) {
        this.pseudo = pseudo;
        this.level = level;
        this.score = score;
        this.time = time;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
