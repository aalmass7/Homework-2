package com.narxoz.rpg.combat.ice;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class IceShield implements Ability, Themed {

    @Override public String getName() { return "Ice Shield"; }
    @Override public int getDamage() { return 10; }
    @Override public String getDescription() { return "A shield of ice that hardens defenses."; }

    @Override public Theme getTheme() { return Theme.ICE; }

    @Override public Ability clone() { return new IceShield(); }
}
