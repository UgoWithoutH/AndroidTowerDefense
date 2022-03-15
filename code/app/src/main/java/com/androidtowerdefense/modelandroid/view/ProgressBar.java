package com.androidtowerdefense.modelandroid.view;

import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.ProgressBuild;

public class ProgressBar {
    private ProgressBuild progressBuild;
    private int xStart;
    private int yStart;

    public ProgressBar(ProgressBuild progressBuild, int xStart, int yStart) {
        this.progressBuild = progressBuild;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    public ProgressBuild getProgressBuild() {
        return progressBuild;
    }

    public int getxStart() {
        return xStart;
    }

    public int getyStart() {
        return yStart;
    }
}
