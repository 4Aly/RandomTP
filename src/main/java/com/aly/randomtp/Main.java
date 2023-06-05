package com.aly.randomtp;

import com.aly.randomtp.Command.RandomTP;
import com.aly.randomtp.Events.InventoryClick;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("rtp").setExecutor(new RandomTP());
        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);

    }
}
