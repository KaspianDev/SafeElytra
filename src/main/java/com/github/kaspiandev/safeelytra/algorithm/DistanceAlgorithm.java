package com.github.kaspiandev.safeelytra.algorithm;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DistanceAlgorithm implements Listener {

    private static final int DEFAULT_DISTANCE = 4;


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location to = event.getTo();
        if (to == null) return;

    }

}
