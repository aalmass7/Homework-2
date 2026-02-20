package com.narxoz.rpg.builder;

import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.ThemedEnemyComponentFactory;

public class EnemyDirector {

    private final EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {
        this.builder = builder;
    }

    private static String themeToElement(EnemyComponentFactory factory) {
        if (factory instanceof ThemedEnemyComponentFactory) {
            Theme t = ((ThemedEnemyComponentFactory) factory).getTheme();
            return t.asElementString();
        }
        return Theme.NONE.asElementString();
    }

    public Enemy createMinion(EnemyComponentFactory factory) {
        return builder.reset()
                .setName("Minion")
                .setHealth(60)
                .setDamage(10)
                .setDefense(3)
                .setSpeed(30)
                .setElement(themeToElement(factory))
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .build();
    }

    public Enemy createElite(EnemyComponentFactory factory) {
        return builder.reset()
                .setName("Elite")
                .setHealth(200)
                .setDamage(35)
                .setDefense(20)
                .setSpeed(25)
                .setElement(themeToElement(factory))
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .build();
    }

    public Enemy createMiniBoss(EnemyComponentFactory factory) {
        return builder.reset()
                .setName("Mini Boss")
                .setHealth(5000)
                .setDamage(120)
                .setDefense(70)
                .setSpeed(35)
                .setElement(themeToElement(factory))
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .addPhase(1, 5000)
                .addPhase(2, 2500)
                .build();
    }

    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        return builder.reset()
                .setName("Raid Boss")
                .setHealth(100000)
                .setDamage(1000)
                .setDefense(500)
                .setSpeed(60)
                .setElement(themeToElement(factory))
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .setAI(factory.createAIBehavior())
                .addPhase(1, 100000)
                .addPhase(2, 50000)
                .addPhase(3, 25000)
                .build();
    }
}