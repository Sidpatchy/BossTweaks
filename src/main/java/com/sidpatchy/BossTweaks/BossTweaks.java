package com.sidpatchy.BossTweaks;

import com.sidpatchy.BossTweaks.listener.dragon.dragonDamage;
import com.sidpatchy.BossTweaks.listener.dragon.dragonPhaseChange;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BossTweaks extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        // Register events
        pm.registerEvents(new dragonDamage(), this);
        pm.registerEvents(new dragonPhaseChange(), this);

        this.getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin disabled!");
    }
}
