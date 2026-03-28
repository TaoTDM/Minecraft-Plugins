package com.TaoTDM.onelifemc;

import org.bukkit.plugin.java.JavaPlugin;

public class OneLifeMC extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("OneLifeMC enabled - shared death is active.");
        getServer().getPluginManager().registerEvents(new SharedDeathListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("OneLifeMC disabled.");
    }
}
