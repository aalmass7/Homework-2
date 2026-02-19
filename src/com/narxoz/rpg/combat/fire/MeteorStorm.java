package com.narxoz.rpg.combat.fire;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.common.Themed;
import com.narxoz.rpg.common.Theme;

public final class MeteorStorm implements Ability, Themed {

    @Override public String getName() { return "Meteor Storm"; }
    @Override public int getDamage() { return 70; }
    @Override public String getDescription() { return "Calls down meteors, dealing massive fire damage."; }

    @Override public Theme getTheme() { return Theme.FIRE; }

    @Override public Ability clone() { return new MeteorStorm(); }
}

