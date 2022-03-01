package com.androidtowerdefense.model;

import com.androidtowerdefense.model.gamelogic.GameState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Score et Ranking permettant de classer le resultat en fonction du score obtenu
 */
public class ScoreRanking implements Serializable {
    private final List<GameState> ranking;
    private int numberScores;

    /**
     * Set le score de base au lancement de la Partie
     */
    public ScoreRanking() {
        ranking = new ArrayList<>();
        numberScores = 10;
    }


}
