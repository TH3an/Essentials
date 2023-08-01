package me.thunthean.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.thunthean.main.PREFIX;

public class TellCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                player.sendMessage(PREFIX + "Please do /tell <name> <message>");
                return true;
            }
            if(args.length == 3) {
                String name = args[0];
                List<String> Argslist = new ArrayList<>(Arrays.asList(args));
                String message = String.join(" ", Argslist);
                Player getname = Bukkit.getPlayer(name);

                if(getname == null || !getname.isOnline()) {
                    player.sendMessage(PREFIX + "Player is not found");
                }else{
                    getname.sendMessage(PREFIX + "[TELL]: " + player.getName() + " -> You: " + Arrays.toString(message.split(" ")));
                    player.sendMessage(PREFIX + "[TELL]: You -> " + getname.getName() + ": " + Arrays.toString(message.split(" ")));
                }

            }
        }
        return true;
    }
}
