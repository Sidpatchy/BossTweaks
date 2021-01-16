package com.sidpatchy.BossTweaks.listener.dragon;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class dragonDamage implements Listener {
    @EventHandler
    public void onDragonDamage(EntityDamageByEntityEvent entity) throws InterruptedException {
        if (entity.getEntityType() == EntityType.ENDER_DRAGON) {
            Double damage = entity.getDamage() / 4;
            entity.setDamage(damage);

            Random random = new Random();
            World world = entity.getDamager().getWorld();

            /**
             * Spawn entity at location of damage
             * If arrow damages, the entity will spawn at the location of damage
             * If a player damages it, the entity will spawn at the location of the player
             */
            if (random.nextInt(9) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.BLAZE);
            }
            if (random.nextInt(9) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.ENDER_CRYSTAL);
            }
            if (random.nextInt(3) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.VEX);
            }
            if (random.nextInt(39) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.WITHER_SKELETON);
            }
            if (random.nextInt(99) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.RAVAGER);
            }
            if (random.nextInt(19) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.WITCH);
            }
            if (random.nextInt(7) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.SKELETON);
            }
            if (random.nextInt(49) == 1) {
                world.spawnEntity(entity.getDamager().getLocation(), EntityType.PILLAGER);
            }
            world.spawnEntity(entity.getDamager().getLocation(), EntityType.PRIMED_TNT);
        }
    }
}
