package me.thunthean.Commands.ModsCMD;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.thunthean.main.PREFIX;
import static me.thunthean.main.PUNISHMENTPREFIX;

public class KickCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essential.commands.kick")) {
                if (args.length == 1) {
                    player.sendMessage(PREFIX + "Please do /kick <name> <reason>");
                    return true;
                }
                if (args.length == 2) {
                    String getname = args[0];
                    Player target = Bukkit.getPlayer(getname);

                    if (target == null || !target.isOnline()) {
                        player.sendMessage(PREFIX + "Player is not found");
                    } else {
                        target.kickPlayer(PUNISHMENTPREFIX + "Kicked by " + player.getName());
                        player.sendMessage(PREFIX + target.getName() + " got kicked");
                    }
                }
                if (args.length == 3) {
                    List<String> Argslist = new ArrayList<>(Arrays.asList(args));
                    Argslist.remove(0);
                    String getname = args[0];
                    Player target = Bukkit.getPlayer(getname);
                    String reason = String.join(" ", Argslist);

                    if (target == null || !target.isOnline()) {
                        player.sendMessage(PREFIX + "Player is not found");
                    } else {
                        target.kickPlayer(PUNISHMENTPREFIX + "You got kicked reason: " + reason);
                        player.sendMessage(PREFIX + target.getName() + " got kicked reason: (" + reason + ")");
                    }

                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
