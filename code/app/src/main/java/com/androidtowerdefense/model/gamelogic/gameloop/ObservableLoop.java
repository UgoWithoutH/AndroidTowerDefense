package com.androidtowerdefense.model.gamelogic.gameloop;

import java.util.LinkedList;

/**
 * Classe Observable
 */
public abstract class ObservableLoop {
    private LinkedList<IObserverLoop> observatory = new LinkedList<>();

    public void subscribe(IObserverLoop listener){
        observatory.add(listener);
    }

    public void unsubscribe(IObserverLoop listener){
        observatory.remove(listener);
    }

    /**
     * Notification de tout Observer sur la boucle
     * @param timer
     */
    protected void notifyObserverLoop(int timer){
        //Log.d("Totot","---" + String.valueOf(observatory.size()));
        for(IObserverLoop observer : observatory){
            observer.updateLoop(timer);
        }
    }
}