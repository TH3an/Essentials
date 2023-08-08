package me.thunthean.Commands;

import me.thunthean.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class VanishCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.vanish") || player.hasPermission("Essentials.*")) {
                main.getInstance().vanish = !main.getInstance().vanish;
                if (args.length == 0) {
                    player.sendMessage(PREFIX + "Vanish mode: " + (main.getInstance().vanish ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));
                }
                if (args[0].equalsIgnoreCase("on")) {
                    if (!player.isInvisible()) {
                        player.setInvisible(true);
                        player.setSilent(true);
                        player.sendMessage(PREFIX + "Vanish mode: " + ChatColor.GREEN + "ON");
                    } else {
                        player.sendMessage(PREFIX + "Vanish mode is already: " + ChatColor.GREEN + "ON");
                    }
                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (player.isInvisible()) {
                        player.setInvulnerable(false);
                        player.setSilent(false);
                        player.sendMessage(PREFIX + "Vanish mode: " + ChatColor.RED + "OFF");
                    } else {
                        player.sendMessage(PREFIX + "Vanish mode is already: " + ChatColor.RED + "OFF");
                    }
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
