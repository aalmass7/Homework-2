package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.ThemedEnemyComponentFactory;
import com.narxoz.rpg.loot.LootTable;

import java.util.*;

public abstract class AbstractEnemy implements Enemy {

    protected String name;
    protected int health;
    protected int damage;
    protected int defense;
    protected int speed;

    protected String element;
    protected String aiBehavior;

    protected final List<Ability> abilities;
    protected LootTable lootTable;

    protected final Map<Integer, Integer> phases;

    protected AbstractEnemy(String name, EnemyStats stats, String element, String aiBehavior, EnemyComponents components) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null/blank.");
        if (stats == null) throw new IllegalArgumentException("Stats cannot be null.");
        if (stats.getHealth() <= 0) throw new IllegalArgumentException("Health must be positive.");

        this.name = name;
        this.health = stats.getHealth();
        this.damage = stats.getDamage();
        this.defense = stats.getDefense();
        this.speed  = stats.getSpeed();

        this.element = (element == null || element.isBlank()) ? Theme.NONE.asElementString() : element;
        this.aiBehavior = (aiBehavior == null || aiBehavior.isBlank()) ? "NEUTRAL" : aiBehavior;

        List<Ability> rawAbilities = (components == null) ? null : components.getAbilities();
        this.abilities = (rawAbilities == null) ? new ArrayList<>() : new ArrayList<>(rawAbilities);

        this.lootTable = (components == null) ? null : components.getLootTable();

        Map<Integer, Integer> rawPhases = (components == null) ? null : components.getPhases();
        this.phases = (rawPhases == null) ? new LinkedHashMap<>() : new LinkedHashMap<>(rawPhases);

        validateThemeConsistencyOrThrow();
    }

    @Override public String getName() { return name; }
    @Override public int getHealth() { return health; }
    @Override public int getDamage() { return damage; }
    @Override public int getDefense() { return defense; }
    @Override public int getSpeed() { return speed; }
    @Override public String getElement() { return element; }
    @Override public String getAIBehavior() { return aiBehavior; }

    @Override public List<Ability> getAbilities() { return Collections.unmodifiableList(abilities); }

    // defensive copy
    @Override
    public LootTable getLootTable() {
        return (lootTable == null) ? null : lootTable.clone();
    }

    @Override public Map<Integer, Integer> getPhases() { return Collections.unmodifiableMap(phases); }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " (" + getClass().getSimpleName() + ") ===");
        System.out.println("HP=" + health + " DMG=" + damage + " DEF=" + defense + " SPD=" + speed);
        System.out.println("Element=" + element + " AI=" + aiBehavior);

        System.out.println("Abilities:");
        if (abilities.isEmpty()) System.out.println("  (none)");
        else for (Ability a : abilities) System.out.println("  - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());

        System.out.println("Loot: " + (lootTable == null ? "None" : lootTable.getLootInfo()));
        System.out.println("Phases: " + phases);
        System.out.println();
    }

    // package-private mutations (only via EnemyMutator in same package)
    void rename(String newName) {
        if (newName == null || newName.isBlank()) throw new IllegalArgumentException("Name cannot be null/blank.");
        this.name = newName;
    }

    void multiplyStats(double multiplier) {
        if (multiplier <= 0) throw new IllegalArgumentException("Multiplier must be positive.");
        this.health  = (int) Math.round(this.health * multiplier);
        this.damage  = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed   = (int) Math.round(this.speed * multiplier);
    }

    void addAbility(Ability ability) {
        if (ability == null) return;
        this.abilities.add(ability.clone());
        validateThemeConsistencyOrThrow();
    }

    void addPhase(int phaseNumber, int healthThreshold) {
        if (phaseNumber <= 0) throw new IllegalArgumentException("Phase number must be positive.");
        if (healthThreshold <= 0) throw new IllegalArgumentException("Health threshold must be positive.");
        this.phases.put(phaseNumber, healthThreshold);
    }

    void applyTheme(EnemyComponentFactory factory) {
        if (factory == null) throw new IllegalArgumentException("Factory cannot be null.");

        List<Ability> newAbilities = factory.createAbilities();
        LootTable newLoot = factory.createLootTable();
        String newAI = factory.createAIBehavior();

        this.abilities.clear();
        if (newAbilities != null) {
            for (Ability a : newAbilities) {
                if (a != null) this.abilities.add(a.clone());
            }
        }

        this.lootTable = (newLoot == null) ? null : newLoot.clone();
        this.aiBehavior = (newAI == null || newAI.isBlank()) ? "NEUTRAL" : newAI;

        Theme t = Theme.NONE;
        if (factory instanceof ThemedEnemyComponentFactory) {
            t = ((ThemedEnemyComponentFactory) factory).getTheme();
        }
        this.element = t.asElementString();

        validateThemeConsistencyOrThrow();
    }

    protected List<Ability> deepCopyAbilities() {
        List<Ability> copy = new ArrayList<>();
        for (Ability a : this.abilities) if (a != null) copy.add(a.clone());
        return copy;
    }

    protected LootTable deepCopyLoot() {
        return (this.lootTable == null) ? null : this.lootTable.clone();
    }

    protected Map<Integer, Integer> copyPhases() {
        return new LinkedHashMap<>(this.phases);
    }

    protected void validateThemeConsistencyOrThrow() {
        Theme expected = Theme.fromString(this.element);
        Theme firstSeen = null;

        for (Ability a : abilities) {
            if (a instanceof Themed) {
                Theme at = ((Themed) a).getTheme();
                if (firstSeen == null) firstSeen = at;
                if (at != firstSeen) throw new IllegalStateException("Theme mixing detected in abilities: " + firstSeen + " vs " + at);
            }
        }

        if (lootTable instanceof Themed) {
            Theme lt = ((Themed) lootTable).getTheme();
            if (firstSeen == null) firstSeen = lt;
            if (lt != firstSeen) {
                throw new IllegalStateException("Theme mixing detected between abilities and loot (" + firstSeen + " vs " + lt + ").");
            }
        }

        if (expected == Theme.NONE && firstSeen != null && firstSeen != Theme.NONE) {
            this.element = firstSeen.asElementString();
            expected = firstSeen;
        }

        if (expected != Theme.NONE && firstSeen != null && expected != firstSeen) {
            throw new IllegalStateException("Element/theme mismatch: element=" + expected + " but components theme=" + firstSeen);
        }
    }

    @Override
    public abstract Enemy clone();
}