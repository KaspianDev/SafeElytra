package com.github.kaspiandev.safeelytra.algorithm.context;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class DamageContext implements Context {

    private final Player player;
    private Location lastLocation;
    private double blocksFallen;
    private int possibleDamage;

    public DamageContext(Player player) {
        this.player = player;
        this.lastLocation = player.getLocation();
        this.blocksFallen = 0;
        this.possibleDamage = 0;
    }

    public void fall(Location newLocation) {
        if (player.isFlying()) reset();

        World lastWorld = lastLocation.getWorld();
        assert lastWorld != null;
        World newWorld = newLocation.getWorld();
        assert newWorld != null;

        if (!lastWorld.equals(newWorld)) {
            reset();
            return;
        }

        if (!newLocation.getBlock().getType().isAir()) {
            reset();
            return;
        }

        double yDifference = lastLocation.getY() - newLocation.getY();
        lastLocation = newLocation;
        if (yDifference < 0) {
            reset();
            return;
        }

        blocksFallen += yDifference;
        possibleDamage = calculateDamage();
    }

    private int calculateDamage() {
        if (blocksFallen <= 3) {
            return 0;
        }

        double damagableDistance = blocksFallen - 3;
        return (int) Math.ceil(damagableDistance);
    }

    public boolean isFalling() {
        return blocksFallen > 0;
    }

    public int getPossibleDamage() {
        return possibleDamage;
    }

    public double getBlocksFallen() {
        return blocksFallen;
    }

    public void reset() {
        blocksFallen = 0;
    }

}
