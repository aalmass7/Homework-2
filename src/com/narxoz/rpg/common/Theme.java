package com.narxoz.rpg.common;

import java.util.Locale;

public enum Theme {
    FIRE,
    ICE,
    SHADOW,
    NONE;

    public static Theme fromString(String raw){
        if(raw==null || raw.isBlank()){
            return NONE;
        }
        String normalized = raw.trim().toUpperCase(Locale.ROOT);
        for (Theme t : values()) {
            if (t.name().equals(normalized)) return t;
        }
        return NONE;
    }
    public String asElementString() {
        return this.name();
    }


}
