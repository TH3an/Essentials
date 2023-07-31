package me.thunthean.Events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;

public class ReadyListener implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(event instanceof ReadyEvent) {
            Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "Bot is ready");
        }
    }
}
