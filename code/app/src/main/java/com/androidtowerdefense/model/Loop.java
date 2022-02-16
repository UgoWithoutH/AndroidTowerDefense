package com.androidtowerdefense.model;

import static java.lang.Thread.sleep;

import com.androidtowerdefense.model.observer.Observable;

/**
 * Boucle de jeu
 */
public class Loop extends Observable implements Runnable {
    private static final long DEFAULT_MILLIS = 50;
    private long millis = DEFAULT_MILLIS;
    private int timer = 0;
    private boolean running = false;

    public static long getDefaultMillis() {return DEFAULT_MILLIS;}

    public long getMillis(){return millis;}
    public void setMillis(long millis){this.millis = millis;}

    public boolean isRunning(){return running;}
    public void setRunning(boolean run){running = run;}


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
        while(isRunning()) {
            try {
                sleep(millis);
                timer++;
                beep(timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void beep(int timer) {
        notify(timer);
    }
}
