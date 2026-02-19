package com.narxoz.rpg.combat.fire;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class FireShield implements Ability, Themed {

    @Override public String getName() { return "Fire Shield"; }
    @Override public int getDamage() { return 15; }
    @Override public String getDescription() { return "A fiery barrier that scorches attackers."; }

    @Override public Theme getTheme() { return Theme.FIRE; }

    @Override public Ability clone() { return new FireShield(); }
}
