package gamecentre.battlegame;

public class SamuraiCat extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 6;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int SPECIAL_MOVE_COST = 12;
    //BattleQueue =


    @Override
    boolean hasAttackMp() {
        return getHp() >= SPECIAL_MOVE_COST;
    }

    @Override
    void regularMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        this.getOpponent().reduceHp(REGULAR_MOVE_DAMAGE);
        getBattleQueue().add(this);
    }

    /**
     * Perform the Samurai Cat's special attack, reduce this character's MP and reduce the HP of
     * opponent. Reset the battle queue so that each character appears once.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        Character ch1 = getBattleQueue().getNextCharacter();
        BattleQueue bq = getBattleQueue();

        reduceMp(SPECIAL_MOVE_COST);
        this.getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        while (!bq.isEmpty()) { bq.removeCharacter(); }
        bq.add(ch1);
        bq.add(ch1.getOpponent());

    }

    @Override
    String getSprite() {
        return "samurai_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
