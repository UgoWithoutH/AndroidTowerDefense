package com.androidtowerdefense.model.gamelogic.action.character.monster;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IRemover;

/**
 * Classe pour supprimer des monstres
 */
public class RemoverMonster implements IRemover {
    private GameState game;

    public RemoverMonster(GameState game) {
        this.game = game;
    }

    /**
     * Supprime un Character dans l'ObservableList<Characters> de la GameState
     * @param character Character (Monster)
     */
    @Override
    public void remove(Character character) {
        Monster monster = (Monster) character;
        game.setRemoveCharacter(true);
        game.getCharactersAlive().remove(monster);
        monster.setVisible(false);
        game.setRemoveCharacter(false);
    }
}
