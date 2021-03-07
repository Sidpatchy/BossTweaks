package com.sidpatchy.BossTweaks;

import com.sidpatchy.BossTweaks.command.helpCommand;
import com.sidpatchy.BossTweaks.command.infoCommand;
import com.sidpatchy.BossTweaks.listener.dragon.dragonDamage;
import com.sidpatchy.BossTweaks.listener.dragon.dragonPhaseChange;
import com.sidpatchy.BossTweaks.listener.wither.witherDamage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BossTweaks extends JavaPlugin {
    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();

        // Register events
        pm.registerEvents(new dragonDamage(this), this);
        pm.registerEvents(new dragonPhaseChange(), this);
        pm.registerEvents(new witherDamage(this), this);

        this.getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!"bt".equalsIgnoreCase(cmd.getName()) || !sender.hasPermission("bosstweaks.use")) {
            return false;
        }
        if (args.length == 0 || args.length == 1 && "help".equalsIgnoreCase(args[0])) {
            helpCommand.getHelp(sender);
        } else if ("info".equalsIgnoreCase(args[0])) {
            infoCommand.getInfo(sender);
        } else if ("reload".equalsIgnoreCase(args[0]) && sender.hasPermission("bosstweaks.reload")) {
            this.reloadConfig();
            sender.sendMessage(ChatColor.AQUA + "BossTweaks" + ChatColor.WHITE + " successfully reloaded");
            this.getLogger().info("Configuration reloaded!");
        }
        return true;
    }
}
