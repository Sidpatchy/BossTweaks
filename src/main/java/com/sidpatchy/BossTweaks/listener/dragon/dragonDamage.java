package com.sidpatchy.BossTweaks.listener.dragon;

import com.sidpatchy.BossTweaks.BossTweaks;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class dragonDamage implements Listener {
    private BossTweaks plugin;
    public dragonDamage(BossTweaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDragonDamage(EntityDamageByEntityEvent entity) throws InterruptedException {
        if (entity.getEntityType() == EntityType.ENDER_DRAGON) {
            Double damage = entity.getDamage() / 4;
            entity.setDamage(damage);

            Random random = new Random();
            World world = entity.getDamager().getWorld();
            Location dragonLocation = entity.getEntity().getLocation();
            Location damagerLocation = entity.getDamager().getLocation();

            /**
             * Spawn entity at location of damage
             * If arrow damages, the entity will spawn at the location of damage
             * If a player damages it, the entity will spawn at the location of the player
             *
             * The -100 fall distance is a lazy way to (mostly) disable fall damage on the spawned mobs
             */
            if (random.nextInt(9) == 1) {
                world.spawnEntity(damagerLocation, EntityType.BLAZE).setFallDistance(-100);
            }
            if (random.nextInt(9) == 1) {
                world.spawnEntity(damagerLocation, EntityType.ENDER_CRYSTAL).setFallDistance(-100);
            }
            if (random.nextInt(3) == 1) {
                world.spawnEntity(damagerLocation, EntityType.VEX).setFallDistance(-100);
            }
            if (random.nextInt(39) == 1) {
                world.spawnEntity(damagerLocation, EntityType.WITHER_SKELETON).setFallDistance(-100);
            }
            if (random.nextInt(99) == 1) {
                world.spawnEntity(damagerLocation, EntityType.RAVAGER).setFallDistance(-100);
            }
            if (random.nextInt(19) == 1) {
                world.spawnEntity(damagerLocation, EntityType.WITCH).setFallDistance(-100);
            }
            if (random.nextInt(7) == 1) {
                world.spawnEntity(damagerLocation, EntityType.SKELETON).setFallDistance(-100);
            }
            if (random.nextInt(49) == 1) {
                world.spawnEntity(damagerLocation, EntityType.PILLAGER).setFallDistance(-100);
            }
            if (plugin.getConfig().getBoolean("tntEnabled")) {
                world.spawnEntity(damagerLocation, EntityType.PRIMED_TNT);
            }
        }
    }
}
