package com.androidtowerdefense.modelandroid.view;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.gamelogic.GameManager;
import com.androidtowerdefense.modelandroid.view.creators.CreatorMonsters;

import java.util.List;

public class CheckerModelChanges {
    private GameManager gameManager;
    private GameView gameView;
    private int sizeListMonsters;
    private CreatorMonsters creatorMonsters;

    public CheckerModelChanges(GameManager gameManager, GameView gameView) {
        this.gameManager = gameManager;
        this.gameView = gameView;
        sizeListMonsters = gameManager.getGame().getCharactersAlive().size();
        creatorMonsters = new CreatorMonsters();
    }

    public void check(){
        checkMonsters();
    }

    public void checkMonsters(){
        List<Character> list = gameManager.getGame().getCharactersAlive();
        if(sizeListMonsters != list.size()){
            sizeListMonsters = list.size();
            if(!gameManager.getGame().isRemoveCharacter()){
                Character character = list.get(list.size() - 1);
                if (character instanceof Monster) {
                    creatorMonsters.create((Monster) character);
                }
                gameView.invalidate();
            }
        }
    }
}
