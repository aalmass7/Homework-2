package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.fire.FireShield;
import com.narxoz.rpg.combat.fire.FlameBreath;
import com.narxoz.rpg.combat.fire.MeteorStorm;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.fire.FireLootTable;

import java.util.Arrays;
import java.util.List;

public class FireComponentFactory implements ThemedEnemyComponentFactory {

    @Override public List<Ability> createAbilities() {
        return Arrays.asList(new FlameBreath(), new FireShield(), new MeteorStorm());
    }

    @Override public LootTable createLootTable() {
        return new FireLootTable();
    }

    @Override public String createAIBehavior() {
        return "AGGRESSIVE";
    }

    @Override public Theme getTheme() {
        return Theme.FIRE;
    }
}
