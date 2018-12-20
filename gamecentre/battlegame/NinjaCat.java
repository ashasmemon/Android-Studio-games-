package gamecentre.battlegame;

/**
 * The Ninja Cat character class. Represents the Ninja Cat character.
 */
class NinjaCat extends Character {

    private static final int SPECIAL_MOVE_COST = 9;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int REGULAR_MOVE_DAMAGE = 5;

    @Override
    boolean hasAttackMp() {
        return getMp() >= SPECIAL_MOVE_COST;
    }

    @Override
    void regularMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        this.getOpponent().reduceHp(REGULAR_MOVE_DAMAGE);
        getBattleQueue().add(this);

    }

    /**
     * Reduce this character's magic points and the opponents health points. Then add in this
     * charcter into the battle queue twice.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        reduceMp(SPECIAL_MOVE_COST);
        reduceHp(SPECIAL_MOVE_DAMAGE);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return "ninja_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
