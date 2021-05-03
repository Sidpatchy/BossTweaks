package com.sidpatchy.BossTweaks;

import com.hm.mcshared.file.CommentedYamlConfiguration;
import com.hm.mcshared.update.UpdateChecker;
import com.sidpatchy.BossTweaks.command.helpCommand;
import com.sidpatchy.BossTweaks.command.infoCommand;
import com.sidpatchy.BossTweaks.listener.dragon.dragonDamage;
import com.sidpatchy.BossTweaks.listener.dragon.dragonDeath;
import com.sidpatchy.BossTweaks.listener.dragon.dragonPhaseChange;
import com.sidpatchy.BossTweaks.listener.wither.witherDamage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * A plugin to tweak vanilla boss battles.
 *
 * BossTweaks is licensed under the GNU General Public License version 3.
 *
 * Github page: https://github.com/Sidpatchy/BossTweaks
 *
 * Spigot page: https://www.spigotmc.org/resources/bosstweaks.89860/
 *
 * @since January 2021
 * @version 1.1
 * @author Sidpatchy
 */
public class BossTweaks extends JavaPlugin {

    // Fields related to file handling.
    private CommentedYamlConfiguration config;

    // Used to check for updates.
    private UpdateChecker updateChecker;

    @Override
    public void onEnable() {
        // Check if config file needs to be updated and load it
        try {
            config = new CommentedYamlConfiguration("config.yml", this);
            config.loadConfiguration();
            configUpdater();
        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().log(Level.SEVERE, "Unable to load config file!");
        }

        PluginManager pm = getServer().getPluginManager();

        // Register events
        pm.registerEvents(new dragonDamage(this), this);
        pm.registerEvents(new dragonPhaseChange(), this);
        pm.registerEvents(new witherDamage(this), this);
        pm.registerEvents(new dragonDeath(this), this);

        // Check for updates
        if (config.getBoolean("checkForUpdate", true)) {
            updateChecker = new UpdateChecker(this, "https://raw.githubusercontent.com/Sidpatchy/BossTweaks/master/pom.xml",
                    "bosstweaks.use", "", "https://www.spigotmc.org/resources/bosstweaks.89860/");
            pm.registerEvents(updateChecker, this);
            updateChecker.launchUpdateCheckerTask();
        }

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
            // Check if config file needs to be updated
            try {
                configUpdater();
            } catch (IOException e) {
                this.getLogger().log(Level.SEVERE, "Unable to reload config file!");
            }

            this.reloadConfig();

            // Send confirmation message
            sender.sendMessage(ChatColor.AQUA + "BossTweaks" + ChatColor.WHITE + " successfully reloaded");
            this.getLogger().info("Configuration reloaded!");
        }
        return true;
    }

    /**
     * Update config file if it is outdated
     */
    private void configUpdater() throws IOException {
        boolean configUpdated = false;

        // Added in v1.1
        if (!config.getKeys(false).contains("dragonEggDrop")) {
            config.set("dragonEggDrop", false,
                    "Controls whether a dragon egg is dropped on dragon death");
            configUpdated = true;
        }
        if (!config.getKeys(false).contains("elytraDrop")) {
            this.getConfig().set("elytraDrop", false);
            config.set("elytraDrop", false,
                    "Controls whether an elytra is dropped on dragon death");
            configUpdated = true;
        }
        if (!config.getKeys(false).contains("checkForUpdates")) {
            this.getConfig().set("checkForUpdates", true);
            config.set("checkForUpdates", false,
                    "Controls whether the plugin should check for updates");
            configUpdated = true;
        }

        if (configUpdated) {
            try {
                config.saveConfiguration();
                config.loadConfiguration();
            }
            catch (InvalidConfigurationException e) {
                this.getLogger().log(Level.SEVERE, "Unable to save changes to the configuration file");
            }
        }
    }
}
