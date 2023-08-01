package me.thunthean.Events;

import me.thunthean.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import static me.thunthean.main.PREFIX;


public class JoinAndLeave implements Listener {
   @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
       Player player = e.getPlayer();
       if (main.getInstance().vanish) {
           player.setInvisible(true);
       }
       //A Layer protection whitelist system
       //can be spoofed with private client
       if(!player.isWhitelisted() && Bukkit.getServer().hasWhitelist()) {
           player.sendTitle(ChatColor.RED + "[ALERTS] UNKNOWN PLAYER [ALERTS]","", 1, 20 * 10, 1);
           player.sendMessage(PREFIX + ChatColor.RED + "Don't spy on us! :)");
           player.setInvulnerable(true);
           player.addPotionEffect((new PotionEffect(PotionEffectType.SATURATION, 10000, 1)));
           player.addPotionEffect((new PotionEffect(PotionEffectType.WEAKNESS, 10000, 1)));
           player.addPotionEffect((new PotionEffect(PotionEffectType.BLINDNESS, 10000, 1)));
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

   //A Layer protection whitelist system
   //can be spoofed with private client
   @EventHandler
   public void OnMove(PlayerMoveEvent e) {
       Player player = e.getPlayer();
       if(!player.isWhitelisted()) {
           e.setCancelled(true);
       }
   }
    //A Layer protection whitelist system
    //can be spoofed with private client
    @EventHandler
    public void OnCommand(PlayerCommandPreprocessEvent e) {
       Player player = e.getPlayer();
       if(!player.isWhitelisted()) {
           e.setCancelled(true);
       }
    }

   @EventHandler
    public void OnLeave(@NotNull PlayerQuitEvent e) {
       Player player = e.getPlayer();
       if(player.isInvisible()) {
           Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + player.getName() + " has left silently (Vanish mode: ON)");
           e.setQuitMessage("");
       }
       //A Layer protection whitelist system
       //can be spoofed with private client
       if(Bukkit.getServer().hasWhitelist() && !player.isWhitelisted() && player.hasPotionEffect(PotionEffectType.BLINDNESS) && player.hasPotionEffect(PotionEffectType.WEAKNESS) && player.hasPotionEffect(PotionEffectType.SATURATION)) {
           player.setInvulnerable(false);
           player.removePotionEffect(PotionEffectType.BLINDNESS);
           player.removePotionEffect(PotionEffectType.WEAKNESS);
           player.removePotionEffect(PotionEffectType.SATURATION);
       }
       if(main.getInstance().getConfig().getBoolean("JoinAndLeave")) {
           e.setQuitMessage(ChatColor.RED + "[-] " + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + " has left the server");
       }
   }
}
