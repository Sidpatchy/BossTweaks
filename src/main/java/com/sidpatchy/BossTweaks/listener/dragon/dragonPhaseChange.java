package com.sidpatchy.BossTweaks.listener.dragon;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;

public class dragonPhaseChange implements Listener {
    @EventHandler
    public void onDragonPhaseChange(EnderDragonChangePhaseEvent entity) {
        EnderDragon.Phase phase = entity.getCurrentPhase();
        Location location = entity.getEntity().getLocation();
        World world = entity.getEntity().getWorld();

        /**
         * Check which phase the dragon is in
         */
        if (phase == EnderDragon.Phase.LEAVE_PORTAL) {
            location.setY(location.getBlockY() + 10);
            for (int i = 0; i < 10; i++) {
                world.spawnEntity(location, EntityType.PILLAGER).setFallDistance(-100);
            }
            for (int i = 0; i < 2; i++) {
                world.spawnEntity(location, EntityType.BLAZE);
            }
        }
    }
}
