package com.androidtowerdefense.model.gamelogic.action.tower;

public class WaitingAttacker {
    int maxProgress;
    int currentProgress;
    int incrementProgress;

    public WaitingAttacker(int maxProgress, int currentProgress, int incrementProgress) {
        this.maxProgress = maxProgress;
        this.currentProgress = currentProgress;
        this.incrementProgress = incrementProgress;
    }

    public void increment(){
        if(currentProgress < maxProgress){
            currentProgress += incrementProgress;
        }
    }

    public boolean isFinished(){
        return maxProgress == currentProgress;
    }

    public void reset(){
        currentProgress = 0;
    }
}
