package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.shadow.DarkNova;
import com.narxoz.rpg.combat.shadow.ShadowStrike;
import com.narxoz.rpg.combat.shadow.Vanish;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.shadow.ShadowLootTable;

import java.util.Arrays;
import java.util.List;

public class ShadowComponentFactory implements ThemedEnemyComponentFactory {

    @Override public List<Ability> createAbilities() {
        return Arrays.asList(new ShadowStrike(), new Vanish(), new DarkNova());
    }

    @Override public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override public String createAIBehavior() {
        return "TACTICAL";
    }

    @Override public Theme getTheme() {
        return Theme.SHADOW;
    }
}
