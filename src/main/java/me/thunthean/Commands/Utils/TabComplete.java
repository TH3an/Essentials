package me.thunthean.Commands.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {

    private static final List<String> gamemode = Arrays.asList("creative", "survival", "adventure", "spectator");
    private static final List<String> whitelist = Arrays.asList("on", "off", "add", "remove");
    private static final List<String> essentials = Arrays.asList("reload", "help", "discord");
    private static final List<String> switches = Arrays.asList("off", "on");
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player) {
            List<String> response = new ArrayList<String>();
            if(command.getName().equalsIgnoreCase("essentials")) {
                if(args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], essentials, response);
                    return response;
                }
            }
            if(command.getName().equalsIgnoreCase("gamemode")) {
                if(args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], gamemode, response);
                    return response;
                }
            }
            if(command.getName().equalsIgnoreCase("whitelist")) {
                if(args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], whitelist, response);
                    return response;
                }
            }
        }
        return null;
    }
}
