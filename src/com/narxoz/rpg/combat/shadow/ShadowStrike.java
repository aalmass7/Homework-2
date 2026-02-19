package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class ShadowStrike implements Ability, Themed {

    @Override public String getName() { return "Shadow Strike"; }
    @Override public int getDamage() { return 30; }
    @Override public String getDescription() { return "Strikes from darkness with precision."; }

    @Override public Theme getTheme() { return Theme.SHADOW; }

    @Override public Ability clone() { return new ShadowStrike(); }
}
