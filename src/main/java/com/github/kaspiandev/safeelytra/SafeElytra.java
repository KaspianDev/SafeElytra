package com.github.kaspiandev.safeelytra;

import com.github.kaspiandev.safeelytra.algorithm.DistanceAlgorithm;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeElytra extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DistanceAlgorithm(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
