package me.thunthean.Commands.ModsCMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.thunthean.main.ANNOUNCEMENT;
import static me.thunthean.main.PREFIX;

public class AnnounceCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                player.sendMessage(PREFIX + "Please do /announce <message>");
                return true;
            }
            if(player.hasPermission("Essentials.commands.announce") || player.isOp()) {
                if(args.length == 2) {
                    List<String> Argslist = new ArrayList<>(Arrays.asList(args));
                    Argslist.remove(0);
                    String message = String.join(" ", Argslist);

                    Bukkit.getServer().broadcastMessage(ANNOUNCEMENT + message);
                }
            }
        }
        return true;
    }
}
