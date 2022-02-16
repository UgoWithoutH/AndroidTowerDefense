package com.androidtowerdefense.model.gamelogic.action.tower;


import com.androidtowerdefense.model.characters.Character;
import com.androidtowerdefense.model.characters.tower.Tower;
import com.androidtowerdefense.model.gamelogic.action.IGenerator;
import java.util.List;

/**
 * Classe permettant aux tours d'attaquer
 */
public class GeneratorProjectiles implements IGenerator {
    private List<Tower> listTower;
    private List<Character> listCharacter;

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
        WaitingBuild attackService;
        for (Tower tower : listTower) {
            if (tower.isAttacker()) {
                int towerMinXRange = tower.getX() - tower.getAttackRange();
                int towerMaxXRange = tower.getX() + tower.getAttackRange();
                int towerMinYRange = tower.getY() - tower.getAttackRange();
                int towerMaxYRange = tower.getY() + tower.getAttackRange();

                for (Character character : listCharacter) {
                    target = character;
                    if (target.getX() < towerMaxXRange & target.getX() > towerMinXRange & target.getY() > towerMinYRange & target.getY() < towerMaxYRange) {
                        attackService = new WaitingBuild(tower);
                        Thread t = new Thread(attackService);
                        t.start();
                        if (tower.isBuild()) {
                            tower.createProjectile(target);
                        }
                        break;
                    }
                }
            }
        }
    }
}
