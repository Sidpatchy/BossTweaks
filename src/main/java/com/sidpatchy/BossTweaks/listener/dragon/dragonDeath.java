package com.sidpatchy.BossTweaks.listener.dragon;

import com.sidpatchy.BossTweaks.BossTweaks;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class dragonDeath implements Listener {
    private BossTweaks plugin;
    public dragonDeath(BossTweaks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDragonDeath(EntityDeathEvent entity) throws InterruptedException {
        if (entity.getEntityType() == EntityType.ENDER_DRAGON) {
            World world = entity.getEntity().getWorld();
            Location location = new Location(world, 0, 120, 0);

            /**
             * Spawn the dragon egg and/or Elytra if it is enabled
             */
            if (plugin.getConfig().getBoolean("dragonEggDrop")) {
                BlockData block = Material.DRAGON_EGG.createBlockData();
                world.spawnFallingBlock(location, block);
            }
            if (plugin.getConfig().getBoolean("elytraDrop")) {
                world.dropItem(location, new ItemStack(Material.ELYTRA));
            }
        }
    }
}
