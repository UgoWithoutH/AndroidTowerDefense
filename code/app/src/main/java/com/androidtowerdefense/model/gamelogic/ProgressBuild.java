package com.androidtowerdefense.model.gamelogic;

public class ProgressBuild {
    int maxProgress;
    int currentProgress;
    int incrementProgress;

    public ProgressBuild(int maxProgress, int currentProgress, int incrementProgress) {
        this.maxProgress = maxProgress;
        this.currentProgress = currentProgress;
        this.incrementProgress = incrementProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public int getIncrementProgress() {
        return incrementProgress;
    }

    public void increment(){
        if(currentProgress < maxProgress){
            currentProgress += incrementProgress;
        }
    }

    public boolean isFinished(){
        return maxProgress == currentProgress;
    }
}
