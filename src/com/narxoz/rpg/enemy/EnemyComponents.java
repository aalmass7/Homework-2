package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.*;

public final class EnemyComponents {
    private final List<Ability> abilities;
    private final LootTable lootTable;
    private final Map<Integer, Integer> phases;

    private EnemyComponents(List<Ability> abilities, LootTable lootTable, Map<Integer, Integer> phases) {
        this.abilities = abilities;
        this.lootTable = lootTable;
        this.phases = phases;
    }

    public static EnemyComponents of(List<Ability> abilities, LootTable lootTable, Map<Integer, Integer> phases) {
        List<Ability> abilitiesCopy = new ArrayList<>();
        if (abilities != null) {
            for (Ability a : abilities) {
                if (a != null) abilitiesCopy.add(a.clone());
            }
        }

        Map<Integer, Integer> phasesCopy = (phases == null) ? new LinkedHashMap<>() : new LinkedHashMap<>(phases);
        LootTable lootCopy = (lootTable == null) ? null : lootTable.clone();

        return new EnemyComponents(abilitiesCopy, lootCopy, phasesCopy);
    }

    public List<Ability> getAbilities() {
        return Collections.unmodifiableList(abilities);
    }

    public LootTable getLootTable() {
        return (lootTable == null) ? null : lootTable.clone();
    }

    public Map<Integer, Integer> getPhases() {
        return Collections.unmodifiableMap(phases);
    }
}
