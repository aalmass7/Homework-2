package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public final class EnemyMutator {

    private EnemyMutator() {}

    private static AbstractEnemy asMutable(Enemy enemy) {
        if (enemy == null) throw new IllegalArgumentException("Enemy cannot be null.");
        if (!(enemy instanceof AbstractEnemy)) {
            throw new IllegalArgumentException("Unsupported Enemy implementation: " + enemy.getClass().getName());
        }
        return (AbstractEnemy) enemy;
    }

    public static void rename(Enemy enemy, String newName) {
        asMutable(enemy).rename(newName);
    }

    public static void multiplyStats(Enemy enemy, double multiplier) {
        asMutable(enemy).multiplyStats(multiplier);
    }

    public static void addAbility(Enemy enemy, Ability ability) {
        asMutable(enemy).addAbility(ability);
    }

    public static void addPhase(Enemy enemy, int phaseNumber, int healthThreshold) {
        asMutable(enemy).addPhase(phaseNumber, healthThreshold);
    }

    public static void applyTheme(Enemy enemy, EnemyComponentFactory factory) {
        asMutable(enemy).applyTheme(factory);
    }
}