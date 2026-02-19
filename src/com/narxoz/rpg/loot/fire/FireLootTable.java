package com.narxoz.rpg.loot.fire;

import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class FireLootTable implements LootTable, Themed {

    private final List<String> items;
    private final int gold;
    private final int xp;

    public FireLootTable() {
        this.items = new ArrayList<>(Arrays.asList("Ember Core", "Charred Fang", "Molten Shard"));
        this.gold = 120;
        this.xp = 300;
    }

    private FireLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }

    @Override public Theme getTheme() { return Theme.FIRE; }

    @Override public LootTable clone() { return new FireLootTable(this.items, this.gold, this.xp); }
}
