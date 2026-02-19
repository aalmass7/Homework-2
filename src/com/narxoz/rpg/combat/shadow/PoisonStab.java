package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

/**
 * Доп. способность для демонстрации Prototype (добавляем к клону).
 */
public final class PoisonStab implements Ability, Themed {

    @Override public String getName() { return "Poison Stab"; }
    @Override public int getDamage() { return 20; }
    @Override public String getDescription() { return "A quick stab that poisons the target."; }

    @Override public Theme getTheme() { return Theme.SHADOW; }

    @Override public Ability clone() { return new PoisonStab(); }
}
