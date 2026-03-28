package com.TaoTDM.onelifemc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SharedDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        for (Player p : deadPlayer.getServer().getOnlinePlayers()) {
            if (!p.equals(deadPlayer) && p.getGameMode() != org.bukkit.GameMode.SPECTATOR) {
                p.setHealth(0);
            }
        }
    }
}
