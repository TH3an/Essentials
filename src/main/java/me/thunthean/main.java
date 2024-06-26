package me.thunthean;

import me.thunthean.Commands.*;
import me.thunthean.Commands.GameModes.*;
import me.thunthean.Commands.ModsCMD.AnnounceCMD;
import me.thunthean.Commands.ModsCMD.ShutdownCMD;
import me.thunthean.Commands.Utils.TabComplete;
import me.thunthean.Events.BedListener;
import me.thunthean.Events.JoinAndLeave;
import me.thunthean.Events.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {

    public static main instance;

    public boolean vanish = false;

    public static final String ANNOUNCEMENT = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "ANNOUNCEMENT" + ChatColor.GRAY + "] ► "  + ChatColor.GRAY;

    public static final String PUNISHMENTPREFIX = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "PUNISHMENT" + ChatColor.GRAY + "] ► "  + ChatColor.GRAY;

    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Essentials" + ChatColor.GRAY + "] ► "  + ChatColor.GRAY;


    @Override
    public void onLoad() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Register Events
        getServer().getConsoleSender().sendMessage(PREFIX + "[EVENTS]: Registering Events");

        //Register Commands
        getServer().getConsoleSender().sendMessage(PREFIX + "Loading commands");

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getServer().getConsoleSender().sendMessage(PREFIX + "is enabled");

        // Register Events
        getServer().getPluginManager().registerEvents(new JoinAndLeave(), this);
        getServer().getPluginManager().registerEvents(new BedListener(), this);

        //Register Commands
        getCommand("gamemode").setExecutor(new GameModeCMD());
        getCommand("creative").setExecutor(new CreativeMode());
        getCommand("survival").setExecutor(new SurvivalMode());
        getCommand("spectator").setExecutor(new SpectatorMode());
        getCommand("adventure").setExecutor(new AdventureMode());
        getCommand("whitelist").setExecutor(new WhitelistCMD());
        getCommand("tell").setExecutor(new TellCMD());
        getCommand("fly").setExecutor(new fly());
        getCommand("god").setExecutor(new GodCMD());
        getCommand("heal").setExecutor(new HealCMD());
        getCommand("vanish").setExecutor(new VanishCMD());
        getCommand("tps").setExecutor(new TpsCMD());
        getCommand("kill").setExecutor(new KillCMD());
        getCommand("ping").setExecutor(new PingCMD());
        getCommand("announce").setExecutor(new AnnounceCMD());
        getCommand("essentials").setExecutor(new EssentialsCMD());
        getCommand("essentials").setTabCompleter(new TabComplete());
        getCommand("shutdown").setExecutor(new ShutdownCMD());

        if(getConfig().getBoolean("Discord-Bot")) {
            JDABuilder builder = JDABuilder.createDefault(getConfig().getString("Discord-Bot-Token"));

            // Disable parts of the cache
            builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
            // Enable the bulk delete event
            builder.setBulkDeleteSplittingEnabled(false);
            builder.addEventListeners(new ReadyListener());

            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            builder.setActivity(Activity.watching(Objects.requireNonNull(getConfig().getString("Discord-Status-Message"))));

            builder.build();
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
