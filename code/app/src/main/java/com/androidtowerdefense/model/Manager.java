package com.androidtowerdefense.model;

import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.model.serialization.AdministratorPersistence;
import com.androidtowerdefense.model.serialization.AdministratorPersistenceBinary;

/**
 * Gestion de l'application
 */
public class Manager {
    private GameManager gameManager;
    private ScoreRanking scoreRanking;
    private AdministratorPersistence administratorPersistence;
    private String pseudo;


    public Manager(ScoreRanking scoreRanking){
        this.scoreRanking=scoreRanking;
        administratorPersistence = new AdministratorPersistenceBinary();
        //ScreenController.getStage().setOnCloseRequest(event -> saveStates());
        //administratorPersistence.load(scoreRanking);
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
}
