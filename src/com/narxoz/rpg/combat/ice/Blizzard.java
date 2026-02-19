package com.narxoz.rpg.combat.ice;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class Blizzard implements Ability, Themed {

    @Override public String getName() { return "Blizzard"; }
    @Override public int getDamage() { return 55; }
    @Override public String getDescription() { return "Summons a snowstorm, damaging and slowing targets."; }

    @Override public Theme getTheme() { return Theme.ICE; }

    @Override public Ability clone() { return new Blizzard(); }
}
