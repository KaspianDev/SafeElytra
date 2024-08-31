package com.github.kaspiandev.safeelytra;

import com.github.kaspiandev.safeelytra.algorithm.AlgorithmManager;
import com.github.kaspiandev.safeelytra.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeElytra extends JavaPlugin {

    private Config config;

    @Override
    public void onEnable() {
        config = new Config(this);

        AlgorithmManager algorithmManager = new AlgorithmManager(this);
        getServer().getPluginManager().registerEvents(algorithmManager, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Config getConf() {
        return config;
    }

}
