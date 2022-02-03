package com.androidtowerdefense.model.serialization;

import com.androidtowerdefense.model.ScoreRanking;

import java.io.Serializable;

/**
 * Gère la persistance des données
 */
public abstract class AdministratorPersistence implements ISaveStates, ILoadStates, Serializable {
    @Override
    public abstract void save(ScoreRanking scoreRanking);

    @Override
    public abstract void load(ScoreRanking scoreRanking);
}
