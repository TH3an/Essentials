package me.thunthean.Commands;

import me.thunthean.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class ReloadCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.reload")) {
                if(args[0].equals("reload")) {
                    main.getInstance().reloadConfig();
                    player.sendMessage(PREFIX + "config.yml has been reloaded");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            main.getInstance().reloadConfig();
            sender.sendMessage(PREFIX + "config.yml has been reloaded");
        }
        return true;
    }
}
