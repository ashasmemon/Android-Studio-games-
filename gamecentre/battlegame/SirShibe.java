package gamecentre.battlegame;

/**
 * Class representing the Sir Shibe character.
 */
public class SirShibe extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 10;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 12;

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
     * Perform the special move of Sir Shibe with reduces this character's MP, reduces the
     * opponent's HP and resets the battle queue so that there is only one copy of each character.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        Character ch1 = getBattleQueue().getNextCharacter();
        BattleQueue bq = ch1.getBattleQueue();

        reduceMp(SPECIAL_MOVE_COST);
        this.getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        while (!bq.isEmpty()) {
            bq.removeCharacter();
        }
        bq.add(ch1);
        bq.add(ch1.getOpponent());

    }

    @Override
    String getSprite() {
        return "sir_shibe";
    }

    @Override
    public String getType() {
        return "dog";
    }

}
