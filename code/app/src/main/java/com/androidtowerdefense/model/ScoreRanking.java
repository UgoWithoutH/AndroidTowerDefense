package com.androidtowerdefense.model;

import com.androidtowerdefense.model.gamelogic.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Score et Ranking permettant de classer le resultat en fonction du score obtenu
 */
public class ScoreRanking {
    private final List<GameState> ranking;
    private int numberScores;

    /**
     * Set le score de base au lancement de la Partie
     */
    public ScoreRanking() {
        ranking = new ArrayList<>();
        numberScores = 10;
    }

    /**
     * Met a jour le Classement une fois que la partie est terminée
     * Prépare aussi la persistence
     * @param gameState GameState
     */
    public void updateRanking(GameState gameState) {

        if(numberScores == 0){
            ranking.clear();
            return;
        }

        if (!ranking.isEmpty()) {
            if (ranking.size() >= numberScores) {
                Collections.sort(ranking);
                GameState lowerState = ranking.get(ranking.size() - 1);
                if (lowerState != gameState) {
                    ranking.remove(lowerState);
                }
            }
        }
        ranking.add(gameState);
        if(ranking.size() > 1){
            Collections.sort(ranking);
        }
    }
}
