package me.thunthean.Commands.ModsCMD;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class BanIPCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage(PREFIX + "Please do /banip <name> <reason>");
                return true;
            }
            if(args.length == 1) {
                String getname = args[0];
                OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(getname);


                target.banPlayer(PUNISHMENTPREFIX + "You have been banned IP Reason: Hammer has been spoken");
                player.sendMessage(PREFIX + target.getName() + " got banned IP");
            }

            if(args.length == 2) {
                String getname  = args[0];
                List<String> ArrayList = new ArrayList<>(Arrays.asList(args));
                ArrayList.remove(0);
                String reason = String.join(" ", ArrayList);
                OfflinePlayer target = Bukkit.getOfflinePlayer(getname);

                target.banPlayer(PUNISHMENTPREFIX + "You have been banned IP Reason: " + ChatColor.RED + reason);
                player.sendMessage(PREFIX + target.getName() + " got banned IP reason: " + ChatColor.GREEN + "(" + reason + ")");
            }
        }
        return true;
    }
}
