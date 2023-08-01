package me.thunthean.Commands;

import me.thunthean.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class EssentialsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.help") || player.hasPermission("Essentials.*")) {
                if (args.length == 1) {
                    player.sendMessage(PREFIX + "version is on: " + main.getInstance().getDescription().getVersion());
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(PREFIX + "");
                }
                if (args[0].equalsIgnoreCase("discord")) {
                    player.sendMessage(PREFIX + "Discord Link: https://discord.gg/example");
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    main.getInstance().reloadConfig();
                    player.sendMessage(PREFIX + "config.yml has been reloaded");
                }
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }
        return true;
    }
}
