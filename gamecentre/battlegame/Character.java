package gamecentre.battlegame;

abstract class Character {
    // character's special attack deflects opponent's move back at them

    private int hp = 100;
    private int mp = 100;
    private Character opponent;
    private BattleQueue battleQueue;

    /**
     * Return whether this character has enough Health points to perform a special attack.
     * @return True if this character has enough HP to perform an attack.
     */
    abstract boolean hasAttackMp();

    /**
     * Return the Magic points for this character.
     * @return The amount of magic points.
     */
    int getMp() {
        return mp;
    }

    /**
     * Return the number of Health points this character has.
     * @return The amount of magic points.
     */
    int getHp() { return hp;
    }

    /**
     * Set the Mp of this character to newMp.
     * @param newMp
     */
    void setMp(int newMp) {
        mp = newMp;
    }

    /**
     * Set the Hp of this character to newHp.
     * @param newHp
     */
    void setHp(int newHp) {
        hp = newHp;
    }

    /**
     * Get an Array of the actions available for this character.
     *
     * @return Array of attacks
     */
    String[] getActions() {
        return new String[]{"A"};
    }

    /**
     * Add the opponent for this character.
     *
     * @param character the opponent for this character
     */
    void setOpponent(Character character) {
        opponent = character;
    }

    /**
     * Perform this character's regular attack on its enemy and add the Character at the end of the
     * Battle Queue.
     */
    abstract void regularMove();

    /**
     * Perform this character's special move on its opponent.
     */
    abstract void specialMove();


    /**
     * Get the next sprite for the character to display.
     * @return The name of the next sprite.
     */
    abstract String getSprite();

    /**
     * Reduce this character's MP by damage if they have enough MP, else 0.
     * @param amount
     */
    void reduceMp(int amount) {
        if (mp >= amount) { mp = mp - amount; }
        else { mp = 0; }
    }

    /**
     * Reduce the Hp of this character by damage.
     * @param damage Amount to reduce HP
     */
    void reduceHp(int damage) {

        if (hp >= damage) {
            hp -= damage;
        } else {
            hp = 0;
        }
    }

    /**
     * Return this character's BattleQueue.
     * @return Character's Battle Queue
     */
    public BattleQueue getBattleQueue() {
        return battleQueue;
    }

    /**
     * Set this character's Battle Queue.
     * @param battleQueue The Battle Queue this character and its Opponent will use.
     */
    public void setBattleQueue(BattleQueue battleQueue) {
        this.battleQueue = battleQueue;
    }

    /**
     * Return this Character's opponent
     * @return Opponent
     */
    public Character getOpponent() {
        return opponent;
    }

    /**
     * Increase the character's Hp.
     * @param amountHp Amount by which to increase the Hp.
     */
    public void increaseHp(int amountHp) {
        hp += amountHp;
    }

    public abstract String getType();

}
