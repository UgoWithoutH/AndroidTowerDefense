package com.androidtowerdefense.model.gamelogic.action.tower;


import static java.lang.Thread.sleep;

import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.ProgressBuild;

import java.util.List;

/**
 * Classe permettant de faire attendre la tour durant le temps de construction
 */
public class WaitingTowers {

    /**
     * Vérifie si la Tower est en construction et définit si elle est libre pour attaquer
     */
    public static void run(List<Tower> towers) {
        for (Tower tower : towers) {
            if (!tower.isBuild()) {
                ProgressBuild progressBuild = tower.getProgressBuild();
                if (progressBuild.isFinished()) {
                    tower.setBuild(true);
                } else {
                    progressBuild.increment();
                }
            } else {
                if (!tower.isAttacker()) {
                    if (tower.isAttackerFinished()) {
                        tower.setAttacker(true);
                        tower.resetWaitingAttack();
                    } else {
                        tower.incrementedWaitingAttack();
                    }
                }
            }
        }
    }
}
