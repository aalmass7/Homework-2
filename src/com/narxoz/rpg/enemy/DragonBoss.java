package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;


import java.util.Map;

public class DragonBoss extends AbstractEnemy {

    private final boolean canFly;
    private final boolean hasBreathAttack;
    private final int wingspan;

    DragonBoss(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components, DragonBossTraits traits) {
        super(name, stats, element, aiBehavior, components);
        if (traits == null) throw new IllegalArgumentException("Traits cannot be null.");
        if (traits.getWingspan() <= 0) throw new IllegalArgumentException("Wingspan must be positive.");
        this.canFly = traits.canFly();
        this.hasBreathAttack = traits.hasBreathAttack();
        this.wingspan = traits.getWingspan();
    }

    public static DragonBoss create(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components, DragonBossTraits traits) {
        return new DragonBoss(name, stats, element, aiBehavior, components, traits);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (" + getClass().getSimpleName() + ") ===");
        System.out.println("HP=" + health + " DMG=" + damage + " DEF=" + defense + " SPD=" + speed);
        System.out.println("Element=" + element + " AI=" + aiBehavior);
        System.out.println("Fly=" + canFly + " BreathAttack=" + hasBreathAttack + " Wingspan=" + wingspan);

        System.out.println("Abilities:");
        if (abilities.isEmpty()) System.out.println("  (none)");
        else for (Ability a : abilities) System.out.println("  - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());

        if (phases.isEmpty()) System.out.println("Boss Phases: (none)");
        else {
            System.out.println("Boss Phases:");
            for (Map.Entry<Integer, Integer> e : phases.entrySet()) {
                System.out.println("  Phase " + e.getKey() + " triggers at " + e.getValue() + " HP");
            }
        }

        System.out.println("Loot: " + (lootTable == null ? "None" : lootTable.getLootInfo()));
        System.out.println();
    }

    @Override
    public Enemy clone() {
        return new DragonBoss(
                this.name,
                EnemyStats.of(this.health, this.damage, this.defense, this.speed),
                this.element,
                this.aiBehavior,
                EnemyComponents.of(deepCopyAbilities(), deepCopyLoot(), copyPhases()),
                DragonBossTraits.of(this.canFly, this.hasBreathAttack, this.wingspan)
        );
    }
}
