package com.sidpatchy.BossTweaks.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Class which houses the info command
 * (/bt info)
 */

public class helpCommand {
    public static void getHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.WHITE + "------------------- " + ChatColor.AQUA + ChatColor.BOLD + "BossTweaks" + ChatColor.WHITE + " -------------------");
        sender.sendMessage(ChatColor.AQUA + "/bt help" + ChatColor.WHITE + " - Lists all commands in BossTweaks.");
        sender.sendMessage(ChatColor.AQUA + "/bt info" + ChatColor.WHITE + " - Lists some info about the plugin.");
        if (sender.hasPermission("bosstweaks.reload")) {
            sender.sendMessage(ChatColor.AQUA + "/bt reload" + ChatColor.WHITE + " - Reloads the config file.");
        }
    }
}
