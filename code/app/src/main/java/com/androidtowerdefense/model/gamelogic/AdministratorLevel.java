package com.androidtowerdefense.model.gamelogic;

import android.util.Log;

import com.androidtowerdefense.model.gamelogic.action.ILevel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;

/**
 * Gère les niveaux de la partie
 */
public class AdministratorLevel implements ILevel {
    private GameState game;
    private int level;
    private Scanner levelFile;

    public AdministratorLevel(GameState game) {
        this.game = game;
        this.level = game.getLevel();
        setLevelFile(level);
    }

    public Scanner getLevelFile() {
        return levelFile;
    }

    /**
     * Set le Scanner du fichier d'ennemie si disponible
     * Sinon il n'y a pas plus de manche
     * @param level int Level a charger
     * @return  boolean true si disponible
     */
    public boolean setLevelFile(int level) {
        Scanner scannerMonster = null;
        String currentLine;
        String afterLine;
        StringBuilder sb = new StringBuilder();

        InputStream is = getClass().getResourceAsStream("/res/raw/level" + level + ".txt");
        if (is != null) {
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
                currentLine = bf.readLine();
                while (currentLine != null) {
                    if ((afterLine = bf.readLine()) == null) {
                        sb.append(currentLine);
                        currentLine = null;
                    } else {
                        sb.append(currentLine + "\n");
                        currentLine = afterLine;
                    }
                }
            } catch (IOException e) {
                return false;
            }
            scannerMonster = new Scanner(sb.toString());
            this.levelFile = scannerMonster;
            return true;
        }
        return false;
    }

    /**
     *  Charge le prochain Niveau (Vague) si disponible
     * @return  boolean si Level trouvé
     */
    @Override
    public boolean nextLevel() {
        this.level++;
        if(setLevelFile(this.level)){
            this.game.setLevel(this.level);
            return true;
        }
        return false;
    }
}
