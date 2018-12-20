package gamecentre.battlegame;


/**
 * Representation of the Shaman Cat character.
 */
public class ShamanCat extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 7;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 22;

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
     * Perform the Shaman Cat's special move by reducing this character's MP, reduce the opponent's
     * HP and heal this character by the amount of damage it's opponent takes.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        reduceMp(SPECIAL_MOVE_COST);
        getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        increaseHp(SPECIAL_MOVE_DAMAGE);
        getBattleQueue().add(this);

    }


    @Override
    String getSprite() {
        return "shaman_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
