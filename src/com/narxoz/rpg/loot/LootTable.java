package com.narxoz.rpg.loot;

import java.util.List;

public interface LootTable {

    List<String> getItems();
    int getGoldDrop();
    int getExperienceDrop();

    LootTable clone();

    default String getLootInfo() {
        return "Items=" + getItems() + ", Gold=" + getGoldDrop() + ", XP=" + getExperienceDrop();
    }

}
