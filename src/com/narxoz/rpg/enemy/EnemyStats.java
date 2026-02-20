package com.narxoz.rpg.enemy;


public final class EnemyStats {
    private final int health;
    private final int damage;
    private final int defense;
    private final int speed;

    private EnemyStats(int health, int damage, int defense, int speed) {
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
    }

    public static EnemyStats of(int health, int damage, int defense, int speed) {
        if (health <= 0) throw new IllegalArgumentException("Health must be positive.");
        if (damage < 0) throw new IllegalArgumentException("Damage cannot be negative.");
        if (defense < 0) throw new IllegalArgumentException("Defense cannot be negative.");
        if (speed < 0) throw new IllegalArgumentException("Speed cannot be negative.");
        return new EnemyStats(health, damage, defense, speed);
    }

    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
}
