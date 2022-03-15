package com.androidtowerdefense.model.gamelogic.action.tower;


import com.androidtowerdefense.model.characters.tower.ClassicTower;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IBuyerTower;
import com.androidtowerdefense.model.gamelogic.map.Map;

/**
 * Class permettant d'acheter des tours
 */
public class BuyerTower implements IBuyerTower {
    private GameState game;
    private Map gameMap;

    /**
     * Créé les services d'achat de tour sur la Map
     * @param game  GamesState
     * @param gameMap   Map
     */
    public BuyerTower(GameState game, Map gameMap) {
        this.game = game;
        this.gameMap = gameMap;
    }

    /**
     * Achat et placement d'une Tower en fonction de la fenêtre
     * @param x    double Position X sur la Fenetre
     * @param y    double Position Y sur la Fenetre
     */
    @Override
    public Tower buy(int x, int y) {

        if (gameMap.nodeOpen(x, y)) {
            Tower tower = new ClassicTower(x, y);
            if (game.getCoins() >= Tower.DEFAULT_SELL_COST) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - Tower.DEFAULT_SELL_COST);
                gameMap.setMapNode(x, y, 7);
                return tower;
            }
        }
        return null;
    }
}
