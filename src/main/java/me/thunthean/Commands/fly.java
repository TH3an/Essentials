package me.thunthean.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static me.thunthean.main.PREFIX;

public class fly implements CommandExecutor {
    private ArrayList<Player> list_of_flying_players = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.fly") || player.hasPermission("Essentials.commands.flight") || player.hasPermission("Essentials.*")) {
                if (list_of_flying_players.contains(player)) {
                    list_of_flying_players.remove(player);
                    player.setAllowFlight(false);
                    player.sendMessage(PREFIX + "Flight mode: " + ChatColor.RED + "OFF");
                } else if (!list_of_flying_players.contains(player)) {
                    list_of_flying_players.add(player);
                    player.setAllowFlight(true);
                    player.sendMessage(PREFIX + "Flight mode: " + ChatColor.GREEN + "ON");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(PREFIX + "Console is not allowed");
        }
        return true;
    }
}
