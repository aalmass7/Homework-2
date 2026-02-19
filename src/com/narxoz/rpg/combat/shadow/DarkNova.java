package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class DarkNova implements Ability, Themed {

    @Override public String getName() { return "Dark Nova"; }
    @Override public int getDamage() { return 60; }
    @Override public String getDescription() { return "Explodes with dark energy, hitting all around."; }

    @Override public Theme getTheme() { return Theme.SHADOW; }

    @Override public Ability clone() { return new DarkNova(); }
}
