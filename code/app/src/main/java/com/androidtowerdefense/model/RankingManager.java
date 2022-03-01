package com.androidtowerdefense.model;

import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.serialization.AdministratorPersistence;
import com.androidtowerdefense.model.serialization.AdministratorPersistenceBinary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestion de l'application
 */
public class RankingManager implements Serializable {

    private final List<GameState> ranking;
    private int numberScores;
    private String pseudo;

    public RankingManager(){
        ranking = new ArrayList<>();
        numberScores = 10;
        stubTest();
    }

    private void stubTest(){
        ranking.add(new GameState("TOTO"));
    }

    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    public List<GameState> getRanking() {
        return ranking;
    }

    public void setNumberScores(int numberScores) {this.numberScores = numberScores;}

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
