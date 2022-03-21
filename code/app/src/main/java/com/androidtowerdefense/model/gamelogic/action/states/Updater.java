package com.androidtowerdefense.model.gamelogic.action.states;


import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.gamelogic.GameState;

/**
 * Classe mettant à jour des informations de la partie
 */
public abstract class Updater {

    /**
     * Met a Jour le score en fonction de la position des Characters
     * @param character Character
     * @param gameState  GameState
     */
    public static void updateStates(Character character, GameState gameState) {
        if (character.isPathFinished()) {
            gameState.setLives((gameState.getLives()) - 1);
        } else {
            gameState.setCoins((gameState.getCoins()) + character.getReward());
            gameState.setScore(gameState.getScore() + (character.getReward() * (gameState.getLevel() + 1)));
        }
    }

    /**
     * Met a jour le timer du jeu par rapport à un timer (boucle de jeu)
     * @param timer int
     * @param millis    long
     * @param gameState  GameState
     */
    public static void updateTimerSeconds(int timer, long millis, GameState gameState){
        long timeMillis = timer * millis;
        int timeSeconds = (int) (timeMillis / 1000);

        if (timeSeconds != gameState.getTimeSeconds()) {
            gameState.setTimeSeconds(gameState.getTimeSeconds() + 1);
        }
    }
}
