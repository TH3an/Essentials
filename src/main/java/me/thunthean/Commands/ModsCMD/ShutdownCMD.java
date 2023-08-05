package me.thunthean.Commands.ModsCMD;

import me.thunthean.main;
import okhttp3.internal.platform.Platform;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.ANNOUNCEMENT;
import static me.thunthean.main.PREFIX;

public class ShutdownCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essentials.commands.shutdown") || player.isOp()) {
                Bukkit.getServer().broadcastMessage(ANNOUNCEMENT + "Server is going to shutdown in 5 second!");
                Bukkit.getScheduler().runTaskLater(main.getInstance(), () -> {
                    Bukkit.getServer().shutdown();
                }, 20 * 5);
            }else{
                player.sendMessage(PREFIX + "You don't have permission!");
            }
        }

        return true;
    }
}
