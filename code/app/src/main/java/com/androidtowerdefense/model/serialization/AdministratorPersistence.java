package com.androidtowerdefense.model.serialization;

import com.androidtowerdefense.model.ScoreRanking;

/**
 * Gère la persistance des données
 */
public abstract class AdministratorPersistence implements ISaveStates, ILoadStates {
    @Override
    public abstract void save(ScoreRanking scoreRanking);

    @Override
    public abstract void load(ScoreRanking scoreRanking);
}
