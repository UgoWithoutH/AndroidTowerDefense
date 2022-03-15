package com.androidtowerdefense.model.gamelogic.action.tower;


import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.action.IGenerator;
import java.util.List;

/**
 * Classe permettant aux tours d'attaquer
 */
public class GeneratorProjectiles implements IGenerator {
    private final List<Tower> listTower;
    private final List<Character> listCharacter;

    /**
     * Créé le système d'attaque des Tours sur les Characters
     * @param listTower
     * @param listCharacter
     */
    public GeneratorProjectiles(List<Tower> listTower, List<Character> listCharacter) {
        this.listTower = listTower;
        this.listCharacter = listCharacter;
    }


    /**
     * Verifie quel monstre est dans la portée de la Tour pour l'attaquer
     * Si plusieurs monstres sont dans la portée, la Tour attaquera le premier Character de la liste
     */
    public void generate() {
        Character target;
        for (Tower tower : listTower) {
                double towerMinXRange = tower.getX() - tower.getAttackRange();
                double towerMaxXRange = tower.getX() + tower.getAttackRange();
                double towerMinYRange = tower.getY() - tower.getAttackRange();
                double towerMaxYRange = tower.getY() + tower.getAttackRange();

                for (Character character : listCharacter) {
                    target = character;
                    if (target.getX() < towerMaxXRange & target.getX() > towerMinXRange & target.getY() > towerMinYRange & target.getY() < towerMaxYRange) {
                        if (tower.isBuild() && tower.isAttacker()) {
                            tower.createProjectile(target);
                            tower.setAttacker(false);
                        }
                        break;
                    }
                }
        }
    }
}
