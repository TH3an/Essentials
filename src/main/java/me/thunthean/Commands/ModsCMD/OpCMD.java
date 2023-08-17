package me.thunthean.Commands.ModsCMD;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class OpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Essential.commands.op")) {
                if (args.length == 0) {
                    player.sendMessage(PREFIX + "Please do /op <name>");
                    return true;
                }
                if (args.length == 1) {
                    String getname = args[0];
                    Player target = Bukkit.getPlayer(getname);

                    if(target == null || !target.isOnline()) {
                        player.sendMessage("");
                        return true;
                    }
                    target.setOp(true);
                    target.sendMessage(PREFIX + " you are an operator now");
                    player.sendMessage(PREFIX + target + " has become a operator");
                }
            }
        }
        return true;
    }
}
