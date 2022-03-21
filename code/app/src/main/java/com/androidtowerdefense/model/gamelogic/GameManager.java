package com.androidtowerdefense.model.gamelogic;

import com.androidtowerdefense.model.characters.Coordinate;
import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.gamelogic.action.IGenerator;
import com.androidtowerdefense.model.gamelogic.action.IDisplacer;
import com.androidtowerdefense.model.gamelogic.action.ILevel;
import com.androidtowerdefense.model.gamelogic.action.ISpawner;
import com.androidtowerdefense.model.gamelogic.action.character.DisplacerCharacters;
import com.androidtowerdefense.model.gamelogic.action.character.SpawnerCharacter;
import com.androidtowerdefense.model.gamelogic.action.states.Updater;
import com.androidtowerdefense.model.gamelogic.action.tower.GeneratorProjectiles;
import com.androidtowerdefense.model.gamelogic.action.tower.DisplacerProjectiles;
import com.androidtowerdefense.model.gamelogic.action.tower.WaitingTowers;
import com.androidtowerdefense.model.gamelogic.map.Map;
import com.androidtowerdefense.model.gamelogic.gameloop.IObserverLoop;
import com.androidtowerdefense.model.gamelogic.gameloop.Loop;

/**
 * Classe qui gère la partie
 */
public class GameManager extends ObservableGame implements IObserverLoop {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private IDisplacer displacerCharacters;
    private IDisplacer displacerProjectiles;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IGenerator attacker;
    private ILevel levelNext;

    /**
     * Creation d'un gameManager et de tout ses éléments
     * @param pseudo    String Pseudo representant le Player
     * @param map   Map a dessiner
     */
    public GameManager(String pseudo, Map map){
        this.gameMap = map;
        game = new GameState(pseudo);
        Character.setPath(gameMap.getPath());
        loop = new Loop();
        loop.subscribe(this);
        displacerCharacters = new DisplacerCharacters(game);
        displacerProjectiles = new DisplacerProjectiles(game);
        levelNext = new AdministratorLevel(game);
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game, levelNext, loop);
        spawner = new SpawnerCharacter(game, levelNext);
        attacker = new GeneratorProjectiles(game.getPlayerTowers(), game.getCharactersAlive());
    }

    public GameState getGame() {
        return game;
    }

    public Map getGameMap() {return gameMap;}

    public void setTileWidth(int tileWidth) {
        Coordinate.setTileWidth(tileWidth);
    }

    public void setTileHeight(int tileHeight) {
        Coordinate.setTileHeight(tileHeight);
    }

    public void stop(){
        loop.setRunning(false);
    }

    public void restart(){
        loop.setRunning(true);
        loop.restart();
    }

    public long getSpeedMillis(){
        return loop.getMillis();
    }

    public void setSpeedMillis(long millis){
        loop.setMillis(millis);
    }

    public boolean isRunning(){
        return loop.isRunning();
    }

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
    public void updateLoop(int timer) {
        if (loop.isRunning()) {
            Updater.updateTimerSeconds(timer, loop.DEFAULT_MILLIS, game);

            administratorVictoryGameOver.verifyVictory();

            spawner.spawn(timer);

            WaitingTowers.run(game.getPlayerTowers());

            administratorVictoryGameOver.verifyGameOver(!displacerCharacters.updateLocations());

            displacerProjectiles.updateLocations();

            attacker.generate();

            notifyObserverGame();
        }
    }
}
