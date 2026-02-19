package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.ice.Blizzard;
import com.narxoz.rpg.combat.ice.FrostBreath;
import com.narxoz.rpg.combat.ice.IceShield;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.ice.IceLootTable;

import java.util.Arrays;
import java.util.List;

public class IceComponentFactory implements ThemedEnemyComponentFactory {

    @Override public List<Ability> createAbilities() {
        return Arrays.asList(new FrostBreath(), new IceShield(), new Blizzard());
    }

    @Override public LootTable createLootTable() {
        return new IceLootTable();
    }

    @Override public String createAIBehavior() {
        return "DEFENSIVE";
    }

    @Override public Theme getTheme() {
        return Theme.ICE;
    }
}
