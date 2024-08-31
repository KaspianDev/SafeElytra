package com.github.kaspiandev.safeelytra.algorithm.context;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerContext {

    private final Player player;
    private Location lastLocation;
    private double blocksFallen;

    public PlayerContext(Player player) {
        this.player = player;
        this.lastLocation = player.getLocation();
        this.blocksFallen = 0;
    }

    public void fall(Location newLocation) {
        System.out.println("1");
        if (player.isFlying()) reset();
        System.out.println("2");

        World lastWorld = lastLocation.getWorld();
        assert lastWorld != null;
        World newWorld = newLocation.getWorld();
        assert newWorld != null;

        if (!lastWorld.equals(newWorld)) {
            System.out.println("4");
            reset();
            return;
        }

        if (!newLocation.getBlock().getType().isAir()) {
            System.out.println("5");
            reset();
            return;
        }

        double yDifference = lastLocation.getY() - newLocation.getY();
        lastLocation = newLocation;
        System.out.println("diff " + yDifference);
        if (yDifference < 0) {
            System.out.println("6");
            reset();
            return;
        }

        System.out.println("7");
        blocksFallen += yDifference;
    }

    public boolean isFalling() {
        return blocksFallen > 0;
    }

    public double getBlocksFallen() {
        return blocksFallen;
    }

    public void reset() {
        blocksFallen = 0;
    }

}
