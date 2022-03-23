package com.androidtowerdefense.model.gamelogic.gameloop;

import static java.lang.Thread.sleep;

import android.util.Log;

/**
 * Boucle de jeu
 */
public class Loop extends ObservableLoop implements Runnable {
    public static final long DEFAULT_MILLIS = 16;
    private long millis = DEFAULT_MILLIS;
    private int timer = 0;
    private boolean running = false;

    public long getMillis(){return millis;}
    public void setMillis(long millis){this.millis = millis;}

    public boolean isRunning(){return running;}
    public void setRunning(boolean run){running = run;}

    public void restart(){
        synchronized (this) {
            notify();
        }
    }

    /**
     * Procédure de la boucle avec sa fréquence
     */
    @Override
    public void run() {
        while(running) {
            try {
                sleep(millis);
                timer++;
                beep(timer);
                if(!running){
                    synchronized (this) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void beep(int timer) {
        notifyObserverLoop(timer);
    }
}
