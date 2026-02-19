package com.narxoz.rpg.combat.shadow;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class Vanish implements Ability, Themed {

    @Override public String getName() { return "Vanish"; }
    @Override public int getDamage() { return 0; }
    @Override public String getDescription() { return "Disappears briefly, becoming harder to hit."; }

    @Override public Theme getTheme() { return Theme.SHADOW; }

    @Override public Ability clone() { return new Vanish(); }
}
