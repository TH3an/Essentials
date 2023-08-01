package me.thunthean.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class GodCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.god") || player.hasPermission("Essentials.commands.jesus") || player.hasPermission("Essentials.*")) {
                if(player.isInvulnerable()) {
                    player.setInvulnerable(false);
                    player.sendMessage(PREFIX + "God mode: " + ChatColor.RED + "OFF");
                }else{
                    player.setInvulnerable(true);
                    player.sendMessage(PREFIX + "Gode mode: " + ChatColor.GREEN + "ON");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
