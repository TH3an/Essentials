package me.thunthean.Events;

import me.thunthean.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;


public class JoinAndLeave implements Listener {
   @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
       Player player = e.getPlayer();
       if (main.getInstance().vanish) {
           player.setInvisible(true);
       }
       if(player.isInvisible()) {
           Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + player.getName() + " has join silently (Vanish mode: ON)");
           e.setJoinMessage("");
       }
       if(main.getInstance().getConfig().getBoolean("JoinAndLeave")) {
           if (player.hasPlayedBefore()) {
               e.setJoinMessage(ChatColor.BLUE + "[+] " + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + " has joined the server");
           } else {
               e.setJoinMessage(ChatColor.BLUE + "[+]" + " [New-Player] " + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + " has joined the server");
           }
       }
   }
   @EventHandler
    public void OnLeave(@NotNull PlayerQuitEvent e) {
       Player player = e.getPlayer();
       if(player.isInvisible()) {
           Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + player.getName() + " has left silently (Vanish mode: ON)");
           e.setQuitMessage("");
       }
       if(main.getInstance().getConfig().getBoolean("JoinAndLeave")) {
           e.setQuitMessage(ChatColor.RED + "[-] " + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + " has left the server");
       }
   }
}
