package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.Map;
public interface Enemy {

    String getName();
    int getHealth();
    int getDamage();
    int getDefense();
    int getSpeed();

    String getElement();
    String getAIBehavior();

    List<Ability> getAbilities();
    LootTable getLootTable();
    Map<Integer, Integer> getPhases();

    void displayInfo();

    Enemy clone();
}
