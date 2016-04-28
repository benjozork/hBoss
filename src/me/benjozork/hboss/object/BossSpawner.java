package me.benjozork.hboss.object;

import me.benjozork.hboss.handler.EntityHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjozork on 2016-04-26.
 */
public class BossSpawner implements ConfigurationSerializable {

    String name;
    private EntityType type;
    private int healthMultiplier;
    private Location location;
    private List<BossAttribute> attributes = new ArrayList<>();

    public BossSpawner(String name, Location loc, EntityType type, int healthMultiplier) {
        this.name = name; this.location = loc; this.type = type; this.healthMultiplier = healthMultiplier;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getHealthMultiplier() {
        return healthMultiplier;
    }

    public EntityType getType() {
        return type;
    }

    public List<BossAttribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(BossAttribute a) {
        attributes.add(a);
    }

    public void removeAttribute(BossAttribute a) {
        attributes.remove(a);
    }

    public boolean hasAttribute(BossAttribute a) {
        return attributes.contains(a);
    }

    public BossEntity spawn() {
        LivingEntity entity = (LivingEntity) Bukkit.getWorld(location.getWorld().getName()).spawnEntity(location, type);
        BossEntity boss = new BossEntity(entity, healthMultiplier);
        return boss;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("location", location);
        map.put("type", type);
        map.put("health_multiplier", healthMultiplier);
        map.put("attributes", attributes);
        return map;
    }
}
