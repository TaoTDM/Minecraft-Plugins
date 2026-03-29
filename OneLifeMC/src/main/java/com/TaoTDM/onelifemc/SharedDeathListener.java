package com.TaoTDM.onelifemc;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class SharedDeathListener implements Listener {

    private final OneLifeMC plugin;
    private boolean isProcessing = false;
    private Location deathLocation = null;
    private final HashMap<UUID, Location> respawnTeleports = new HashMap<>();

    public SharedDeathListener(OneLifeMC plugin) {
        this.plugin = plugin;
    }

    private void broadcast(String message) {
        Bukkit.getServer().broadcast(
            LegacyComponentSerializer.legacySection().deserialize(message)
        );
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (isProcessing) return;
        isProcessing = true;

        Player deadPlayer = event.getEntity();
        deathLocation = deadPlayer.getLocation();

        // queue all online players to teleport on respawn
        for (Player p : Bukkit.getOnlinePlayers()) {
            respawnTeleports.put(p.getUniqueId(), deathLocation);
        }

        // 1 second later: everyone spectates at the death location
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.setGameMode(GameMode.SPECTATOR);
                p.teleport(deathLocation);
            }
            isProcessing = false;
        }, 20L);

        int delaySeconds = plugin.getRespawnDelaySeconds();
        long delayTicks = delaySeconds * 20L;

        // initial message
        Bukkit.getScheduler().runTaskLater(plugin, () ->
            broadcast("§c§lThe world will reset in " + delaySeconds + " seconds..."), 20L);

        // 20 sec warning
        if (delaySeconds > 20) {
            Bukkit.getScheduler().runTaskLater(plugin, () ->
                broadcast("§e§l20 seconds remaining..."), (delaySeconds - 20) * 20L);
        }

        // 10 sec warning
        if (delaySeconds > 10) {
            Bukkit.getScheduler().runTaskLater(plugin, () ->
                broadcast("§e§l10 seconds remaining..."), (delaySeconds - 10) * 20L);
        }

        // 5 sec countdown
        for (int i = 5; i >= 1; i--) {
            if (delaySeconds > i) {
                final int sec = i;
                Bukkit.getScheduler().runTaskLater(plugin, () ->
                    broadcast("§c§l" + sec + "..."), (delaySeconds - sec) * 20L);
            }
        }

        // reset world after the specified delay
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            broadcast("§4§lResetting world. See you on the other side.");

            try {
                long newSeed = new Random().nextLong();
                File serverRoot = Bukkit.getWorldContainer();

                // write next seed
                File seedFile = new File(serverRoot, "next-seed.txt");
                Files.writeString(seedFile.toPath(), String.valueOf(newSeed));
                plugin.getLogger().info("Next world seed: " + newSeed + " written to " + seedFile.getAbsolutePath());

                // delete world folders
                File worldFolder = new File(serverRoot, "world");
                File netherFolder = new File(serverRoot, "world_nether");
                File endFolder = new File(serverRoot, "world_the_end");

                plugin.getLogger().info("Deleting world folder: " + worldFolder.getAbsolutePath());
                deleteFolder(worldFolder);
                plugin.getLogger().info("Deleting nether folder: " + netherFolder.getAbsolutePath());
                deleteFolder(netherFolder);
                plugin.getLogger().info("Deleting end folder: " + endFolder.getAbsolutePath());
                deleteFolder(endFolder);

                // delete level.dat files, force PaperMC to use the new seed
                new File(serverRoot, "level.dat").delete();
                new File(serverRoot, "level.dat_old").delete();
                new File(serverRoot, "world/level.dat").delete();
                new File(serverRoot, "world/level.dat_old").delete();
                new File(serverRoot, "world_nether/level.dat").delete();
                new File(serverRoot, "world_nether/level.dat_old").delete();
                new File(serverRoot, "world_the_end/level.dat").delete();
                new File(serverRoot, "world_the_end/level.dat_old").delete();

                plugin.getLogger().info("World deletion complete. Restarting server...");
            } catch (Exception e) {
                plugin.getLogger().warning("Failed during world reset: " + e.getMessage());
                e.printStackTrace();
            }

            // restart the server
            Bukkit.getServer().restart();
        }, delayTicks);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);

        // if this player has a queued teleport, send them to the death location
        if (respawnTeleports.containsKey(player.getUniqueId())) {
            Location loc = respawnTeleports.remove(player.getUniqueId());
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.teleport(loc), 1L);
        }
    }

    private void deleteFolder(File folder) {
        if (!folder.exists()) return;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) deleteFolder(f);
                else f.delete();
            }
        }
        folder.delete();
    }
}
