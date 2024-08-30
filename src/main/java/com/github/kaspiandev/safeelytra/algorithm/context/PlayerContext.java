package com.github.kaspiandev.safeelytra.algorithm.context;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerContext {

    private final Player player;
    private Location lastLocation;
    private boolean isFalling;
    private int blocksFallen;

    public PlayerContext(Player player) {
        this.player = player;
        this.lastLocation = player.getLocation();
        this.isFalling = false;
        this.blocksFallen = 0;
    }

    public void fall(Location newLocation) {
        World lastWorld = lastLocation.getWorld();
        assert lastWorld != null;
        World newWorld = newLocation.getWorld();
        assert newWorld != null;

        lastLocation = newLocation;
        if (!lastWorld.equals(newWorld)) {
            blocksFallen = 0;
            isFalling = false;
            return;
        }
    }

}
