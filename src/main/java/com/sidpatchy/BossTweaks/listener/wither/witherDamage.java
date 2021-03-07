package com.sidpatchy.BossTweaks.listener.wither;

import com.sidpatchy.BossTweaks.BossTweaks;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class witherDamage implements Listener {
    private BossTweaks plugin;
    public witherDamage(BossTweaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWitherDamage(EntityDamageByEntityEvent entity) {
        if (entity.getEntity().getType() == EntityType.WITHER ||
                ! (plugin.getConfig().getBoolean("witherEnabled"))) {
            return;
        }

        Double damage = entity.getDamage() / 1.5;
        entity.setDamage(damage);
        Entity e = entity.getEntity();

        Random random = new Random();
        World world = entity.getDamager().getWorld();
        Location witherLocation = e.getLocation();
        Location damagerLocation = entity.getDamager().getLocation();


    }
}
