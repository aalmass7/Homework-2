package com.narxoz.rpg.enemy;

public final class DragonBossTraits {
    private final boolean canFly;
    private final boolean hasBreathAttack;
    private final int wingspan;

    private DragonBossTraits(boolean canFly, boolean hasBreathAttack, int wingspan) {
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

    public static DragonBossTraits of(boolean canFly, boolean hasBreathAttack, int wingspan) {
        if (wingspan <= 0) throw new IllegalArgumentException("Wingspan must be positive.");
        return new DragonBossTraits(canFly, hasBreathAttack, wingspan);
    }

    public boolean canFly() { return canFly; }
    public boolean hasBreathAttack() { return hasBreathAttack; }
    public int getWingspan() { return wingspan; }
}
