package com.androidtowerdefense.model.gamelogic.action.tower;


import com.androidtowerdefense.model.characters.tower.ClassicTower;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IBuyer;
import com.androidtowerdefense.model.gamelogic.map.Map;

/**
 * Class permettant d'acheter des tours
 */
public class BuyerTower implements IBuyer {
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
     * @param xCords    double Position X sur la Fenetre
     * @param yCords    double Position Y sur la Fenetre
     */
    @Override
    public boolean buy(double xCords, double yCords) {
        int xTile = (int) (xCords / 64);
        int yTile = (int) (yCords / 64);

        if (gameMap.nodeOpen(xTile, yTile)) {
            Tower tower = new ClassicTower(xTile, yTile);
            if (game.getCoins() >= Tower.getDefaultSellCost()) {
                game.addTower(tower);
                game.setCoins(game.getCoins() - Tower.getDefaultSellCost());
                gameMap.setMapNode(((int) (xCords / 64)), ((int) (yCords / 64)), 7);
                return true;
            }
        }
        return false;
    }
}
