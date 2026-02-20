package com.narxoz.rpg.enemy;

public class Skeleton extends AbstractEnemy {

    public Skeleton(String name) {
        super(name, EnemyStats.of(120, 20, 10, 25), "NONE", "DEFENSIVE", EnemyComponents.of(null, null, null));
    }

    Skeleton(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components) {
        super(name, stats, element, aiBehavior, components);
    }

    public static Skeleton create(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components) {
        return new Skeleton(name, stats, element, aiBehavior, components);
    }

    @Override
    public Enemy clone() {
        return new Skeleton(
                this.name,
                EnemyStats.of(this.health, this.damage, this.defense, this.speed),
                this.element,
                this.aiBehavior,
                EnemyComponents.of(deepCopyAbilities(), deepCopyLoot(), copyPhases())
        );
    }
}