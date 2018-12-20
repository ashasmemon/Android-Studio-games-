package gamecentre.battlegame;

/**
 * AttackManager class to handle attacks for the Battle Game.
 */
class AttackManager {

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int cost;
    private int damage;

    /**
     * Instantiate this Attack with no parameters.
     */
    AttackManager() {
        this.cost = 0;
        this.damage = 0;
    }

    /**
     * Instantiate this Attack with a cost and amount of damage it deals the opponent.
     *
     * @param cost Amount of MP used for this attack
     * @param damage Amount of damage this attack deals.
     */
    AttackManager(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * Perform this attack used by character on the opponent.
     * @param character The character performing the attack
     * @param opponent  Character taking damage.
     */
    void performAttack(Character character, Character opponent) {
        character.reduceMp(cost);
        opponent.reduceHp(damage);
        character.getBattleQueue().add(character);
    }



}
