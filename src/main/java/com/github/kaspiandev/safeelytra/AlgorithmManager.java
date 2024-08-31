package com.github.kaspiandev.safeelytra;

import com.github.kaspiandev.safeelytra.algorithm.Algorithm;
import com.github.kaspiandev.safeelytra.algorithm.DistanceAlgorithm;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlgorithmManager implements Listener {

    private final Map<String, Algorithm<?>> algorithms;
    private final Map<UUID, Algorithm<?>> playerAlgorithms;

    public AlgorithmManager() {
        this.algorithms = new HashMap<>();
        algorithms.put("distance", new DistanceAlgorithm());
        this.playerAlgorithms = new HashMap<>();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Algorithm<?> algorithm = playerAlgorithms.get(uuid);
        if (algorithm != null) {
            algorithm.clean(player);
            playerAlgorithms.remove(uuid);
        }
    }

}
