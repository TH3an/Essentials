package me.thunthean.Commands.GameModes;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class GameModeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.gamemode.all") || player.hasPermission("Essentials.*")) {
                if(args.length == 1) {
                    player.sendMessage(PREFIX + "Please do /gamemode <gamemode>!");
                    return true;
                }
                if(args[0].equalsIgnoreCase("creative")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(PREFIX + "You're now on creative mode.");
                }
                if(args[0].equalsIgnoreCase("survival")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(PREFIX + "You're now on survival mode.");
                }
                if(args[0].equalsIgnoreCase("adventure")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(PREFIX + "You're now on adventure mode.");
                }
                if(args[0].equalsIgnoreCase("spectator")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(PREFIX + "You're now on spectator mode.");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
