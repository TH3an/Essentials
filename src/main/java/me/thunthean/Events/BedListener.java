package me.thunthean.Events;

import me.thunthean.main;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.thunthean.main.PREFIX;

public class BedListener implements Listener {
    @EventHandler
    public void OnEnterBed(PlayerBedEnterEvent e) {
      if(main.getInstance().getConfig().getBoolean("OnePlayerSleep")) {
          Player player = e.getPlayer();
          World world = player.getWorld();
          new BukkitRunnable() {
              @Override
              public void run() {
                  if (player.isSleeping()) {
                      player.sendMessage(PREFIX + player.getName() + "has been slept");
                      world.setTime(0);
                      world.setThundering(false);
                      world.setStorm(false);
                  }
              }
          }.runTaskLater(main.getInstance(), 100L);
      }
    }
    @EventHandler
    public void OnLeaveBd(PlayerQuitEvent e) {
        Player player = e.getPlayer();

    }
}
