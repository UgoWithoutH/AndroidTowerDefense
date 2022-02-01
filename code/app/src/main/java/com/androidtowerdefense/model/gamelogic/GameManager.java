package com.androidtowerdefense.model.gamelogic;

import com.androidtowerdefense.model.gamelogic.action.IAttacker;
import com.androidtowerdefense.model.gamelogic.action.IDisplacer;
import com.androidtowerdefense.model.gamelogic.action.ILevel;
import com.androidtowerdefense.model.gamelogic.action.ISpawner;
import com.androidtowerdefense.model.gamelogic.action.character.DisplacerCharacters;
import com.androidtowerdefense.model.gamelogic.action.character.SpawnerCharacter;
import com.androidtowerdefense.model.gamelogic.action.states.Updater;
import com.androidtowerdefense.model.gamelogic.action.tower.AttackerTower;
import com.androidtowerdefense.model.gamelogic.map.Map;
import com.androidtowerdefense.model.gameloop.IObserver;
import com.androidtowerdefense.model.gameloop.Loop;

/**
 * Classe qui gère la partie
 */
public class GameManager implements IObserver {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private IDisplacer displacer;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IAttacker attacker;
    private ILevel levelNext;

    /**
     * Creation d'un gameManager et de tout ses éléments
     * @param pseudo    String Pseudo representant le Player
     * @param map   Map a dessiner
     */
    public GameManager(String pseudo, Map map){
        this.gameMap = map;
        game = new GameState(pseudo);
        //Character.setPath(gameMap.getPath());
        loop = new Loop();
        loop.subscribe(this);
        displacer = new DisplacerCharacters(game);
        levelNext = new AdministratorLevel(game);
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game, levelNext, loop);
        spawner = new SpawnerCharacter(game, levelNext);
        //attacker = new AttackerTower(game.getPlayerTowers(), game.getCharactersAlive());
    }

    public Loop getLoop(){ return loop; }

    public GameState getGame() {
        return game;
    }

    public Map getGameMap() {return gameMap;}

    /**
     * Démarre la boucle de jeu
     */
    public void start() {
        loop.setRunning(true);
        Thread boucleThread = new Thread(loop);
        boucleThread.start();
    }

    /**
     * Update à chaque tour de boucle
     * @param timer int Timer de la boucle de Jeu
     */
    @Override
    public void update(int timer) {
        if (loop.isRunning()) {
            Updater.updateTimerSeconds(timer, loop.getDefaultMillis(), game);

            administratorVictoryGameOver.verifyVictory();

            spawner.spawn(timer);

            administratorVictoryGameOver.verifyGameOver(!displacer.updateLocations());

            attacker.attack();
        }
    }
}
