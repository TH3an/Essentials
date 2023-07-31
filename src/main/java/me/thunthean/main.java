package me.thunthean;

import me.thunthean.Commands.*;
import me.thunthean.Commands.GameModes.GameModeCMD;
import me.thunthean.Events.BedListener;
import me.thunthean.Events.JoinAndLeave;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    public static main instance;
    public boolean vanish = false;

    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Essentials" + ChatColor.GRAY + "] ► "  + ChatColor.GRAY;


    @Override
    public void onLoad() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register Events
        getServer().getConsoleSender().sendMessage(PREFIX + "[EVENTS]: Registering JoinAndLeave");
        getServer().getPluginManager().registerEvents(new JoinAndLeave(), this);
        getServer().getConsoleSender().sendMessage(PREFIX + "[EVENTS]: Registering BedListener");
        getServer().getPluginManager().registerEvents(new BedListener(), this);

        //Register Commands
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading gamemode");
        getCommand("gamemode").setExecutor(new GameModeCMD());
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading fly");
        getCommand("fly").setExecutor(new fly());
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading heal");
        getCommand("heal").setExecutor(new HealCMD());
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading vanish");
        getCommand("vanish").setExecutor(new VanishCMD());
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading tps");
        getCommand("tps").setExecutor(new TpsCMD());
        getServer().getConsoleSender().sendMessage(PREFIX + "[COMMANDS]: loading reload command");
        getCommand("Essentialsreload").setExecutor(new ReloadCMD());
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        if(getConfig().getBoolean("Discord-Bot")) {
            // Using Discord JDA
            // Create a Discord Bot
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static main getInstance() {
        return instance;
    }
}
