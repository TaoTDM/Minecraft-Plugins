package com.TaoTDM.onelifemc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class OneLifeMC extends JavaPlugin {
    private int respawnDelaySeconds = 30;

    public int getRespawnDelaySeconds() {
        return respawnDelaySeconds;
    }

    public void setRespawnDelaySeconds(int seconds) {
        this.respawnDelaySeconds = seconds;
    }

    @Override
    public void onEnable() {
        getLogger().info("OneLifeMC enabled - shared death is active.");
        getServer().getPluginManager().registerEvents(new SharedDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new InstantRespawnListener(), this);
        getLogger().info("InstantRespawn enabled - doImmediateRespawn applied to all worlds.");
    }

    @Override
    public void onDisable() {
        getLogger().info("OneLifeMC disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setrespawndelay")) {
            if (args.length != 1) {
                sender.sendMessage("§cUsage: /setrespawndelay <seconds>");
                return true;
            }
            try {
                int seconds = Integer.parseInt(args[0]);
                if (seconds < 5 || seconds > 600) {
                    sender.sendMessage("§cPlease specify a value between 5 and 600 seconds.");
                    return true;
                }
                setRespawnDelaySeconds(seconds);
                sender.sendMessage("§aRespawn/reset delay set to " + seconds + " seconds.");
            } catch (NumberFormatException e) {
                sender.sendMessage("§cInvalid number: " + args[0]);
            }
            return true;
        }
        return false;
    }
}
