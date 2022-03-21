package com.androidtowerdefense.model.gamelogic.action.character;

import android.util.Log;

import com.androidtowerdefense.model.characters.monster.Basic;
import com.androidtowerdefense.model.characters.monster.Monster;
import com.androidtowerdefense.model.characters.monster.Speed;
import com.androidtowerdefense.model.gamelogic.AdministratorLevel;
import com.androidtowerdefense.model.gamelogic.GameState;
import com.androidtowerdefense.model.gamelogic.action.ILevel;
import com.androidtowerdefense.model.gamelogic.action.ISpawner;

import java.util.Scanner;

public class SpawnerCharacter implements ISpawner {

    private GameState gameState;
    private ILevel level;

    /**
     *Création du système de génération des ennemies en fonction des level recupérés
     * @param gameState GameState
     * @param level Level pointant sur le fichier des Characters
     */
    public SpawnerCharacter(GameState gameState, ILevel level) {
        this.gameState = gameState;
        this.level = level;
    }

    /**
     * Generation Characters in X timer
     * @param timer int Timer de la Boucle de Jeu
     */
    public void spawn(int timer) {
        if (level instanceof AdministratorLevel) {
            AdministratorLevel administratorLevel = (AdministratorLevel) level;
            Scanner scannerFile = administratorLevel.getLevelFile();
            if (timer % 100 == 0 && scannerFile.hasNextLine()) {
                String spawn = scannerFile.next();
                Log.d("spawn", spawn);
                switch (spawn) {
                    case "Basic" :
                         gameState.getCharactersAlive().add(new Basic(Monster.DEFAULT_BASIC_LIFE));
                         break;
                    case "Speed" :
                        gameState.getCharactersAlive().add(new Speed(Monster.DEFAULT_SPEED_LIFE));
                        break;
                    default :
                        gameState.getCharactersAlive().add(new Basic(Monster.DEFAULT_BASIC_LIFE));
                }
            } else if (!scannerFile.hasNextLine()) {
                level.nextLevel();
            }
        }
    }
}
