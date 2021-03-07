package com.sidpatchy.BossTweaks.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Class which houses the info command
 * (/bt info)
 */

public class infoCommand {
    public static void getInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.WHITE + "---------- " + ChatColor.AQUA + ChatColor.BOLD + "BossTweaks" + ChatColor.WHITE + " ----------");
        sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.WHITE + "v1.0");
        sender.sendMessage(ChatColor.AQUA + "Author: " + ChatColor.WHITE + "Sidpatchy");
    }
}
