package me.thunthean.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static me.thunthean.main.PREFIX;

public class WhitelistCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.whitelist")) {
                if (args.length == 1) {
                    player.sendMessage(PREFIX + "Please do /whitelist <add/remove> <name>");
                    player.sendMessage(PREFIX + "Please do /whitelist <on/off>");
                    return true;
                }
                if(args[0].equalsIgnoreCase("add")) {
                    String getName = args[1];
                    OfflinePlayer target = Bukkit.getOfflinePlayer(getName);

                    if(target.isWhitelisted()) {
                        player.sendMessage(PREFIX + target + "already whitelisted");
                    }
                    if(!target.isWhitelisted()) {
                        target.setWhitelisted(true);
                        player.sendMessage(PREFIX + target + "got whitelisted");
                    }
                }
                if(args[0].equalsIgnoreCase("remove")) {
                    String getName = args[1];
                    OfflinePlayer target = Bukkit.getOfflinePlayer(getName);

                    if(!target.isWhitelisted()) {
                        player.sendMessage(PREFIX + target + "is not whitelist");
                    }
                    if(target.isWhitelisted()) {
                        target.setWhitelisted(false);
                        player.sendMessage(PREFIX + target + "removed from the whitelist");
                    }
                }
                if(args[0].equalsIgnoreCase("list")) {
                    Set<OfflinePlayer> whitelistedPlayers = Bukkit.getWhitelistedPlayers();
                    StringBuilder message = new StringBuilder();
                    message.append(PREFIX + "Whitelisted players: " + ChatColor.WHITE);
                    for (OfflinePlayer player1 : whitelistedPlayers) {
                        message.append(player1.getName()).append(", ");
                    }
                    if (message.toString().endsWith(", ")) {
                        message.delete(message.length() - 2, message.length());
                    }
                    player.sendMessage(message.toString());
                }
                if(args[0].equalsIgnoreCase("on")) {
                    if(!Bukkit.getServer().hasWhitelist()) {
                        Bukkit.getServer().setWhitelist(true);
                        player.sendMessage(PREFIX + "Server whitelist is: " + ChatColor.GREEN + "ON");
                    }else{
                        player.sendMessage(PREFIX + "Server whitelist is already: " + ChatColor.GREEN + "ON");
                    }
                }
                if(args[0].equalsIgnoreCase("off")) {
                    if(Bukkit.getServer().hasWhitelist()) {
                        Bukkit.getServer().setWhitelist(false);
                        player.sendMessage(PREFIX + "Server whitelist is: " + ChatColor.RED + "OFF");
                    }else{
                        player.sendMessage(PREFIX + "Server whitelist is already: " + ChatColor.RED + "OFF");
                    }
                }
            }
        }
        return true;
    }
}
