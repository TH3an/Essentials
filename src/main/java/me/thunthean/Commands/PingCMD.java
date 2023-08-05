package me.thunthean.Commands;

import me.thunthean.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class PingCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                int ping = player.getPing();
                player.sendMessage(PREFIX + "Checking ping.....");
                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    if (ping <= 120) {
                        player.sendMessage(PREFIX + "Pong! your ping: " + ChatColor.GREEN + player.getPing() + ChatColor.GRAY + "ms");
                    } else {
                        player.sendMessage(PREFIX + "Pong! your ping: " + ChatColor.RED + player.getPing() + ChatColor.GRAY + "ms");
                    }
                }, 20 * 5);
            }
            if(player.hasPermission("Essentials.command.ping.other")) {
                if(args.length == 2) {
                    String getname = args[0];
                    Player target = Bukkit.getPlayer(getname);
                    int ping = target.getPing();

                    if(!target.isOnline()) {
                       player.sendMessage(PREFIX + "Player is not found");
                       return true;
                    }

                    player.sendMessage(PREFIX + "Checking " + target.getName() + " ping.....");
                    Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                        if (ping <= 120) {
                            player.sendMessage(PREFIX + "Pong! " + target.getName() + " ping: " + ChatColor.GREEN + player.getPing() + ChatColor.GRAY + "ms");
                        } else {
                            player.sendMessage(PREFIX + "Pong! " + target.getName() + " ping: " + ChatColor.RED + player.getPing() + ChatColor.GRAY + "ms");
                        }
                    }, 20 * 5);
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
