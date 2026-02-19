package com.narxoz.rpg.loot.ice;

import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class IceLootTable implements LootTable, Themed {

    private final List<String> items;
    private final int gold;
    private final int xp;

    public IceLootTable() {
        this.items = new ArrayList<>(Arrays.asList("Frost Crystal", "Ice Scale", "Glacier Dust"));
        this.gold = 100;
        this.xp = 280;
    }

    private IceLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }

    @Override public Theme getTheme() { return Theme.ICE; }

    @Override public LootTable clone() { return new IceLootTable(this.items, this.gold, this.xp); }
}
