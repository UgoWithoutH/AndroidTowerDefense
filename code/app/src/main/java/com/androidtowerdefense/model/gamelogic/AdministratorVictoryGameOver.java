package com.androidtowerdefense.model.gamelogic;


import com.androidtowerdefense.model.gamelogic.action.ILevel;
import com.androidtowerdefense.model.gamelogic.gameloop.Loop;

/**
 * Classe permettant de vérifier la victoire ou la partie perdu
 */
public class AdministratorVictoryGameOver {

    private GameState gameState;
    private Loop loop;
    private ILevel enemyFile;

    public AdministratorVictoryGameOver(GameState gameState, ILevel level, Loop boucle) {
        this.gameState = gameState;
        this.loop = boucle;
        this.enemyFile=level;
    }

    /**
     * Vérifie si l'état de la partie est une victoire
     */
    public void verifyVictory() {
        if(enemyFile instanceof AdministratorLevel) {
            AdministratorLevel administratorLevel = (AdministratorLevel) enemyFile;
            if (!administratorLevel.getLevelFile().hasNextLine() && gameState.getCharactersAlive().isEmpty() && loop.isRunning()) {
                loop.setRunning(false);
                gameState.setVictory(true);
            }
        }
    }

    /**
     * Verifie si l'Etat de la partie est une Defaite
     * @param value none
     */
    public void verifyGameOver(boolean value){
        if(!value) return;
        gameState.setRemoveCharacter(true);
        gameState.getCharactersAlive().clear();
        loop.setRunning(false);
        gameState.setGameOver(true);
    }
}
