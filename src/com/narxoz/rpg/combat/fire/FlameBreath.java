package com.narxoz.rpg.combat.fire;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class FlameBreath implements Ability, Themed {

    @Override public String getName() { return "Flame Breath"; }
    @Override public int getDamage() { return 40; }
    @Override public String getDescription() { return "Breathes a cone of fire, burning enemies."; }

    @Override public Theme getTheme() { return Theme.FIRE; }

    @Override public Ability clone() { return new FlameBreath(); }
}
