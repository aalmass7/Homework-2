package com.narxoz.rpg.combat.ice;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class FrostBreath implements Ability, Themed {

    @Override public String getName() { return "Frost Breath"; }
    @Override public int getDamage() { return 35; }
    @Override public String getDescription() { return "Breathes freezing air, slowing enemies."; }

    @Override public Theme getTheme() { return Theme.ICE; }

    @Override public Ability clone() { return new FrostBreath(); }
}
