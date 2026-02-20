package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;
import com.narxoz.rpg.enemy.*;
import com.narxoz.rpg.loot.LootTable;

import java.util.*;

public class BasicEnemyBuilder implements EnemyBuilder {

    public enum BasicType { GOBLIN, SKELETON }

    private BasicType type = BasicType.GOBLIN;

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private String element;
    private String ai;

    private final List<Ability> abilities = new ArrayList<>();
    private LootTable loot;
    private final Map<Integer, Integer> phases = new LinkedHashMap<>();

    public BasicEnemyBuilder setType(BasicType type) {
        this.type = (type == null) ? BasicType.GOBLIN : type;
        return this;
    }

    @Override public BasicEnemyBuilder setName(String name) { this.name = name; return this; }
    @Override public BasicEnemyBuilder setHealth(int health) { this.health = health; return this; }
    @Override public BasicEnemyBuilder setDamage(int damage) { this.damage = damage; return this; }
    @Override public BasicEnemyBuilder setDefense(int defense) { this.defense = defense; return this; }
    @Override public BasicEnemyBuilder setSpeed(int speed) { this.speed = speed; return this; }
    @Override public BasicEnemyBuilder setElement(String element) { this.element = element; return this; }

    @Override
    public BasicEnemyBuilder addAbility(Ability ability) {
        if (ability != null) this.abilities.add(ability);
        return this;
    }

    @Override
    public BasicEnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities.clear();
        if (abilities != null) for (Ability a : abilities) if (a != null) this.abilities.add(a);
        return this;
    }

    @Override
    public BasicEnemyBuilder addPhase(int phaseNumber, int healthThreshold) {
        if (phaseNumber <= 0) throw new IllegalArgumentException("Phase number must be positive.");
        if (healthThreshold <= 0) throw new IllegalArgumentException("Health threshold must be positive.");
        this.phases.put(phaseNumber, healthThreshold);
        return this;
    }

    @Override public BasicEnemyBuilder setLootTable(LootTable loot) { this.loot = loot; return this; }
    @Override public BasicEnemyBuilder setAI(String aiBehavior) { this.ai = aiBehavior; return this; }

    @Override
    public Enemy build() {
        validateMandatoryFields();

        Theme inferred = inferThemeFromComponents();
        Theme specified = Theme.fromString(element);
        Theme finalTheme = (specified == Theme.NONE) ? inferred : specified;

        if (inferred != Theme.NONE && finalTheme != Theme.NONE && inferred != finalTheme) {
            throw new IllegalStateException("Theme mismatch: element=" + finalTheme + ", components=" + inferred);
        }

        String finalElement = finalTheme.asElementString();
        String finalAI = (ai == null || ai.isBlank()) ? "NEUTRAL" : ai;

        EnemyStats stats = EnemyStats.of(health, damage, defense, speed);
        EnemyComponents components = EnemyComponents.of(new ArrayList<>(abilities), loot, new LinkedHashMap<>(phases));

        switch (type) {
            case SKELETON:
                return Skeleton.create(name, stats, finalElement, finalAI, components);
            case GOBLIN:
            default:
                return Goblin.create(name, stats, finalElement, finalAI, components);
        }
    }

    private void validateMandatoryFields() {
        if (name == null || name.isBlank()) throw new IllegalStateException("Name is required.");
        if (health <= 0) throw new IllegalStateException("Health must be positive.");
    }

    private Theme inferThemeFromComponents() {
        Theme first = null;

        for (Ability a : abilities) {
            if (a instanceof Themed) {
                Theme t = ((Themed) a).getTheme();
                if (first == null) first = t;
                if (t != first) throw new IllegalStateException("Ability theme mixing detected: " + first + " vs " + t);
            }
        }

        if (loot instanceof Themed) {
            Theme lt = ((Themed) loot).getTheme();
            if (first == null) first = lt;
            if (lt != first) throw new IllegalStateException("Theme mixing detected between abilities and loot: " + first + " vs " + lt);
        }

        return first == null ? Theme.NONE : first;
    }

    @Override
    public BasicEnemyBuilder reset() {
        this.type = BasicType.GOBLIN;
        this.name = null;
        this.health = 0;
        this.damage = 0;
        this.defense = 0;
        this.speed = 0;
        this.element = null;
        this.ai = null;

        this.abilities.clear();
        this.loot = null;
        this.phases.clear();
        return this;
    }
}
