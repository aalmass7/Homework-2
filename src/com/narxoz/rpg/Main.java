package com.narxoz.rpg;

import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.shadow.PoisonStab;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.EnemyMutator;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Enemy System - Creational Patterns Capstone ===\n");

        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        System.out.println("============================================");
        System.out.println("[Part 1] Abstract Factory: themed components (abilities + loot + AI)\n");
        System.out.println("Fire AI = " + fireFactory.createAIBehavior() + ", Loot = " + fireFactory.createLootTable().getLootInfo());
        System.out.println("Ice  AI = " + iceFactory.createAIBehavior() + ", Loot = " + iceFactory.createLootTable().getLootInfo());
        System.out.println("Shad AI = " + shadowFactory.createAIBehavior() + ", Loot = " + shadowFactory.createLootTable().getLootInfo());
        System.out.println();

        System.out.println("[Part 2] Builder + Factory Method (build())\n");

        BasicEnemyBuilder basicGoblinBuilder = new BasicEnemyBuilder().setType(BasicEnemyBuilder.BasicType.GOBLIN);
        EnemyDirector goblinDirector = new EnemyDirector(basicGoblinBuilder);

        Enemy goblinEliteViaDirector = goblinDirector.createElite(shadowFactory);
        goblinEliteViaDirector.displayInfo();

        BossEnemyBuilder bossBuilder = new BossEnemyBuilder();
        EnemyDirector bossDirector = new EnemyDirector(bossBuilder);

        Enemy raidBossFire = bossDirector.createRaidBoss(fireFactory);
        EnemyMutator.rename(raidBossFire, "Ancient Fire Raid Boss");
        raidBossFire.displayInfo();

        System.out.println("[Safety check] Prevent component mixing bug:");
        try {
            basicGoblinBuilder.reset()
                    .setType(BasicEnemyBuilder.BasicType.GOBLIN)
                    .setName("Broken Goblin")
                    .setHealth(100)
                    .setElement("FIRE")
                    .setAbilities(fireFactory.createAbilities())
                    .setLootTable(iceFactory.createLootTable()) // mismatch
                    .setAI(fireFactory.createAIBehavior())
                    .build();
            System.out.println("ERROR: mismatch was not detected (this should not happen).");
        } catch (IllegalStateException ex) {
            System.out.println("OK (blocked): " + ex.getMessage());
        }
        System.out.println();

        System.out.println("[Part 3] Prototype: templates + deep-copy variants\n");

        EnemyRegistry registry = new EnemyRegistry();

        Enemy goblinTemplate = basicGoblinBuilder.reset()
                .setType(BasicEnemyBuilder.BasicType.GOBLIN)
                .setName("Goblin")
                .setHealth(100)
                .setDamage(15)
                .setDefense(5)
                .setSpeed(35)
                .setElement("NONE")
                .build();

        registry.registerTemplate("goblin", goblinTemplate);

        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        EnemyMutator.rename(eliteGoblin, "Elite Goblin");
        EnemyMutator.multiplyStats(eliteGoblin, 2.0);

        Enemy championGoblin = registry.createFromTemplate("goblin");
        EnemyMutator.rename(championGoblin, "Champion Goblin");
        EnemyMutator.multiplyStats(championGoblin, 5.0);
        EnemyMutator.addAbility(championGoblin, new PoisonStab());

        Enemy goblinKing = registry.createFromTemplate("goblin");
        EnemyMutator.rename(goblinKing, "Goblin King");
        EnemyMutator.multiplyStats(goblinKing, 10.0);
        EnemyMutator.addPhase(goblinKing, 1, goblinKing.getHealth());
        EnemyMutator.addPhase(goblinKing, 2, goblinKing.getHealth() / 2);

        goblinTemplate.displayInfo();
        eliteGoblin.displayInfo();
        championGoblin.displayInfo();
        goblinKing.displayInfo();

        Enemy dragonTemplate = bossBuilder.reset()
                .setName("Dragon")
                .setHealth(50000)
                .setDamage(600)
                .setDefense(300)
                .setSpeed(45)
                .setElement("NONE")
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(35)
                .build();

        registry.registerTemplate("dragon", dragonTemplate);

        Enemy fireDragon = registry.createFromTemplate("dragon");
        EnemyMutator.rename(fireDragon, "Fire Dragon");
        EnemyMutator.applyTheme(fireDragon, fireFactory);

        Enemy iceDragon = registry.createFromTemplate("dragon");
        EnemyMutator.rename(iceDragon, "Ice Dragon");
        EnemyMutator.applyTheme(iceDragon, iceFactory);

        Enemy shadowDragon = registry.createFromTemplate("dragon");
        EnemyMutator.rename(shadowDragon, "Shadow Dragon");
        EnemyMutator.applyTheme(shadowDragon, shadowFactory);

        dragonTemplate.displayInfo();
        fireDragon.displayInfo();
        iceDragon.displayInfo();
        shadowDragon.displayInfo();

        BasicEnemyBuilder skeletonBuilder = new BasicEnemyBuilder().setType(BasicEnemyBuilder.BasicType.SKELETON);
        Enemy skeletonTemplate = skeletonBuilder.reset()
                .setType(BasicEnemyBuilder.BasicType.SKELETON)
                .setName("Skeleton")
                .setHealth(120)
                .setDamage(20)
                .setDefense(10)
                .setSpeed(25)
                .setElement("NONE")
                .build();

        registry.registerTemplate("skeleton", skeletonTemplate);

        Enemy eliteSkeleton = registry.createFromTemplate("skeleton");
        EnemyMutator.rename(eliteSkeleton, "Elite Skeleton");
        EnemyMutator.multiplyStats(eliteSkeleton, 2.0);

        Enemy cursedSkeleton = registry.createFromTemplate("skeleton");
        EnemyMutator.rename(cursedSkeleton, "Cursed Skeleton");
        EnemyMutator.applyTheme(cursedSkeleton, shadowFactory);

        skeletonTemplate.displayInfo();
        eliteSkeleton.displayInfo();
        cursedSkeleton.displayInfo();

        System.out.println("[Deep copy proof] Add ability to clone; template must not change:");
        Enemy cloneForProof = registry.createFromTemplate("goblin");
        int before = goblinTemplate.getAbilities().size();
        EnemyMutator.addAbility(cloneForProof, new PoisonStab());
        int after = goblinTemplate.getAbilities().size();

        System.out.println("Template abilities before = " + before);
        System.out.println("Template abilities after  = " + after);
        System.out.println("Clone abilities count     = " + cloneForProof.getAbilities().size());
        System.out.println("(Expected: template stays same; clone changes)\n");

        System.out.println("=== Demo finished ===");
    }
}


