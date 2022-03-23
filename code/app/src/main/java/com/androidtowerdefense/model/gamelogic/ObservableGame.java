package com.androidtowerdefense.model.gamelogic;

import java.util.LinkedList;

public abstract class ObservableGame {
    private LinkedList<IObserverGame> observatory = new LinkedList<>();

    public void subscribe(IObserverGame listener){
        observatory.add(listener);
    }

    public void unsubscribe(IObserverGame listener){
        observatory.remove(listener);
    }

    /**
     * Notification de tout Observer sur la boucle
     */
    protected void notifyObserverGame(){
        for(IObserverGame observer : observatory){
            observer.update();
        }
    }
}
