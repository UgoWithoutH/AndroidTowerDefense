package com.androidtowerdefense.model;

import static java.lang.Thread.sleep;

import android.util.Log;

import com.androidtowerdefense.model.observer.Observable;

/**
 * Boucle de jeu
 */
public class Loop extends Observable implements Runnable {
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
     * Démarrage de la boucle
     */
    public void start() {
        running = true;
        run();
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
                Log.d("Totot","---");
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
        notify(timer);
    }
}
