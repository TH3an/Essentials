package me.thunthean.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class TpsCMD implements CommandExecutor {

    private final double TPS = Bukkit.getTPS().length;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.tps") || player.hasPermission("Essentials.*")) {
                    sender.sendMessage(PREFIX + "TPS: " + TPS);
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
