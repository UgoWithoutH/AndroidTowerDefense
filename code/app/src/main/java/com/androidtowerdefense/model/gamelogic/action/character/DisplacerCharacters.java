package com.androidtowerdefense.model.gamelogic.action.character;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.IDisplacer;
import com.androidtowerdefense.model.gamelogic.action.IRemover;
import com.androidtowerdefense.model.gamelogic.action.character.monster.RemoverMonster;
import com.androidtowerdefense.model.gamelogic.action.states.Updater;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Déplaceur de caractères
 */
public class DisplacerCharacters implements IDisplacer {

    private GameState gameState;
    private IRemover remover;

    public DisplacerCharacters(GameState gameState) {this.gameState = gameState;}

    /**
     * Modifie la position de tous les Character dans Observable List -> GameState
     * @return
     */
    @Override
    public boolean updateLocations() {
        ArrayList<Character> charactersEnd = new ArrayList<>();
        List listCharacters = gameState.getCharactersAlive();
        if (!listCharacters.isEmpty()) {
            Iterator<Character> characterIterator = listCharacters.iterator();
            Character character;
            while (characterIterator.hasNext()) {
                character = characterIterator.next();
                character.updateLocation();
                if (character.isPathFinished()) {
                    Updater.updateStates(character, gameState);
                    charactersEnd.add(character);
                    if (gameState.getLives() == 0) {
                        return false;
                    }
                }
            }
            for (Character characterDelete : charactersEnd) {
                if(characterDelete instanceof Monster){
                    remover = new RemoverMonster(gameState);
                }
                remover.remove(characterDelete);
            }
        }
        return true;
    }
}
