package com.androidtowerdefense.model.gamelogic;

import com.androidtowerdefense.model.Coordinate;
import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.gamelogic.action.IAttacker;
import com.androidtowerdefense.model.gamelogic.action.IDisplacer;
import com.androidtowerdefense.model.gamelogic.action.ILevel;
import com.androidtowerdefense.model.gamelogic.action.ISpawner;
import com.androidtowerdefense.model.gamelogic.action.character.DisplacerCharacters;
import com.androidtowerdefense.model.gamelogic.action.character.SpawnerCharacter;
import com.androidtowerdefense.model.gamelogic.action.states.Updater;
import com.androidtowerdefense.model.gamelogic.action.tower.AttackerTower;
import com.androidtowerdefense.model.gamelogic.map.Map;
import com.androidtowerdefense.model.observer.IObserver;
import com.androidtowerdefense.model.Loop;
import com.androidtowerdefense.model.observer.Observable;

/**
 * Classe qui gère la partie
 */
public class GameManager extends Observable implements IObserver {

    private Map gameMap;
    private GameState game;
    private Loop loop;
    private IDisplacer displacer;
    private AdministratorVictoryGameOver administratorVictoryGameOver;
    private ISpawner spawner;
    private IAttacker attacker;
    private ILevel levelNext;
    private int tileWidth;
    private int tileHeight;

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
        displacer = new DisplacerCharacters(game);
        levelNext = new AdministratorLevel(game);
        administratorVictoryGameOver = new AdministratorVictoryGameOver(game, levelNext, loop);
        spawner = new SpawnerCharacter(game, levelNext);
        attacker = new AttackerTower(game.getPlayerTowers(), game.getCharactersAlive());
    }

    public Loop getLoop(){ return loop; }

    public GameState getGame() {
        return game;
    }

    public Map getGameMap() {return gameMap;}

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
        Coordinate.setTileWidth(tileWidth);
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
        Coordinate.setTileHeight(tileHeight);
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
    public void update(int timer) {
        if (loop.isRunning()) {
            Updater.updateTimerSeconds(timer, loop.getDefaultMillis(), game);

            administratorVictoryGameOver.verifyVictory();

            spawner.spawn(timer);

            administratorVictoryGameOver.verifyGameOver(!displacer.updateLocations());

            attacker.attack();

            notify(0);
        }
    }
}
