package me.thunthean.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class KillCMD implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            String GetPlayerName = args[0];
            if(player.hasPermission("Essentials.commands.kill")) {
                if (args.length == 1) {
                    player.setHealth(0);
                    player.sendMessage(PREFIX + "You're got killed");
                }
                if (args[0].equalsIgnoreCase(GetPlayerName)) {

                   //String GetPlayerName = args[0]
                   Player target = Bukkit.getServer().getPlayer(GetPlayerName);

                    if (target == null || !target.isOnline()) {
                        player.sendMessage(PREFIX + "Player is not found!");
                        return true;
                    }
                    //Kill the target
                    target.setHealth(0);
                    player.sendMessage(PREFIX + target + " got killed");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
