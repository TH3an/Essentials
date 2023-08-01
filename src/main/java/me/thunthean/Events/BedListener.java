package me.thunthean.Events;

import me.thunthean.main;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static me.thunthean.main.PREFIX;

public class BedListener implements Listener {
    private static final int NIGHT_TIME_START = 12541;
    private static final int NIGHT_TIME_END = 23458;

    @EventHandler
    public void OnEnterBed(PlayerBedEnterEvent e) {
      if(main.getInstance().getConfig().getBoolean("OnePlayerSleep")) {
          Player player = e.getPlayer();
          World world = player.getWorld();

          if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_SAFE) {
              // Check if player is sleeping at Near Mob
              // Need permission ( OnePlayerSleep.nearMob.Sleep )
              if (player.hasPermission("OnePlayerSleep.nearMob.Sleep")) {
                  e.setUseBed(Event.Result.ALLOW);
              }
              return;
          }

          if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.TOO_FAR_AWAY) {
              // Check if player is sleeping too far away from the bed
              e.setUseBed(Event.Result.DENY);
              return;
          }

          if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_HERE) {
              // Check if player is possible to sleep
              e.setUseBed(Event.Result.DENY);
              return;
          }

          BukkitTask task =  new BukkitRunnable() {
              @Override
              public void run() {
                  if (player.isSleeping()) {
                      if (world.getTime() >= NIGHT_TIME_END && world.getTime() <= NIGHT_TIME_END) {
                          player.sendMessage(PREFIX + player.getName() + "has been slept");
                          world.setTime(0);
                          world.setThundering(false);
                          world.setStorm(false);
                      }else{
                          e.setUseBed(Event.Result.DENY);
                      }
                  }
              }
          }.runTaskLater(main.getInstance(), 100L);
          player.setMetadata("sleepTask", new FixedMetadataValue(main.getInstance(), task));
      }
    }
    @EventHandler
    public void OnLeaveBd(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (player.hasMetadata("sleepTask")) {
            // Retrieve the task from the player's metadata
            BukkitTask task = (BukkitTask) player.getMetadata("sleepTask").get(0).value();

            // Cancel the task
            task.cancel();

            // Remove the metadata from the player
            player.removeMetadata("sleepTask", main.getInstance());
        }
    }
}
