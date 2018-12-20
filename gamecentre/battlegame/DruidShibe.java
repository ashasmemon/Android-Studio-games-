package gamecentre.battlegame;


/**
 * DruidShibe character class. Able to perform Regular Move costing 5 Magic points to dealing 8
 * health points damage to its opponent, adds  and a Special Move costing 10 Magic points to
 * dealing 13 Health points damage to its opponent.
 */
class DruidShibe extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 8;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 10;


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
     * Perform the special move of the Druid Shibe. Reduce this character's MP adn reduce the
     * opponent's HP. Add this character into the battle queue twice so it can attack twice next
     * round.
     */
    @Override
    void specialMove() {
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        reduceMp(SPECIAL_MOVE_COST);
        this.getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return "druid_shibe";
    }

    @Override
    public String getType() {
        return "dog";
    }

}
