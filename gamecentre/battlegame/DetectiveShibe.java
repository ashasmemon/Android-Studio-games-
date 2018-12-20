package gamecentre.battlegame;

/**
 * Representation of the Detective Shibe character.
 */
public class DetectiveShibe extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 7;
    private static final int SPECIAL_MOVE_DAMAGE = 12;
    private static final int SPECIAL_MOVE_COST = 15;

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
     * Reduce this character's magic points and reduce the enemy's Health points by
     * SPECIAL_MOVE_DAMAGE. Add this character into the battle queue twice so it can attack twice in
     * the next round.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        reduceMp(SPECIAL_MOVE_COST);
        getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return "detective_shibe";
    }

    public String getType() {
        return "dog";
    }
}
