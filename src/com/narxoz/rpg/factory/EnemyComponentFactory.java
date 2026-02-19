package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

public interface EnemyComponentFactory {

    List<Ability> createAbilities();

    LootTable createLootTable();

    String createAIBehavior();

}
