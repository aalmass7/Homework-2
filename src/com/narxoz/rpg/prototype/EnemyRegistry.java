package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.*;

public class EnemyRegistry {

    private final Map<String, Enemy> templates = new LinkedHashMap<>();

    public void registerTemplate(String key, Enemy template){
        if(key == null || key.isBlank()){
            throw new IllegalArgumentException("Key is required");
        }
        if(template == null){
            throw new IllegalArgumentException("Template is required");
        }
        templates.put(key, template);
    }

    public Enemy createFromTemplate(String key){
        Enemy template = templates.get(key);
        if(template == null){
            throw new IllegalArgumentException("Unknown template: " + key);
        }
        return template.clone();
    }

    public Set<String> ListTemplates(){
        return Collections.unmodifiableSet(templates.keySet());
    }
}
