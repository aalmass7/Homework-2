package com.narxoz.rpg.loot.shadow;

import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ShadowLootTable implements LootTable, Themed {

    private final List<String> items;
    private final int gold;
    private final int xp;

    public ShadowLootTable() {
        this.items = new ArrayList<>(Arrays.asList("Shadow Essence", "Night Blade Fragment", "Cursed Coin"));
        this.gold = 130;
        this.xp = 320;
    }

    private ShadowLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }

    @Override public Theme getTheme() { return Theme.SHADOW; }

    @Override public LootTable clone() { return new ShadowLootTable(this.items, this.gold, this.xp); }
}

