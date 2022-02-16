package com.androidtowerdefense.model.gamelogic.action;

import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;

public interface IAttacker {

    void attack(Character target, Tower tower);
}
