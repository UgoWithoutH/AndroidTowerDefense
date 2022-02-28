package com.androidtowerdefense.model;

import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.serialization.AdministratorPersistence;
import com.androidtowerdefense.model.serialization.AdministratorPersistenceBinary;

import java.io.Serializable;

/**
 * Gestion de l'application
 */
public class Manager implements Serializable {
    private GameManager gameManager;
    private ScoreRanking scoreRanking;
    private AdministratorPersistence administratorPersistence;
    private String pseudo;

    public Manager(){
        this.scoreRanking = new ScoreRanking();
        stubTest();
        administratorPersistence = new AdministratorPersistenceBinary();
        //ScreenController.getStage().setOnCloseRequest(event -> saveStates());
        //administratorPersistence.load(scoreRanking);
    }

    private void stubTest(){
        scoreRanking.getRanking().add(new GameState("TOTO"));
    }

    /**
     * Sauvegarde le Score de la partie
     */
    public void saveStates(){
        administratorPersistence.save(scoreRanking);
    }

    public GameManager getGameManager() {return gameManager;}
    public void setGameManager(GameManager gameManager) {this.gameManager = gameManager;}

    public ScoreRanking getScoreRanking() {return scoreRanking;}

    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}
}
