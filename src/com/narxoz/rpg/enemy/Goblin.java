package com.narxoz.rpg.enemy;


public class Goblin extends AbstractEnemy{

    public Goblin(String name) {
        super(name, EnemyStats.of(100, 15, 5, 35), "NONE", "AGGRESSIVE", EnemyComponents.of(null, null, null));
    }

    Goblin(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components) {
        super(name, stats, element, aiBehavior, components);
    }

    public static Goblin create(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components) {
        return new Goblin(name, stats, element, aiBehavior, components);
    }

    @Override
    public Enemy clone() {
        return new Goblin(
                this.name,
                EnemyStats.of(this.health, this.damage, this.defense, this.speed),
                this.element,
                this.aiBehavior,
                EnemyComponents.of(deepCopyAbilities(), deepCopyLoot(), copyPhases())
        );
    }
}
