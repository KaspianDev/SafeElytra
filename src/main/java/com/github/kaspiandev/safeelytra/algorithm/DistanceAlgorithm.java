package com.github.kaspiandev.safeelytra.algorithm;

import com.github.kaspiandev.safeelytra.algorithm.context.PlayerContext;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DistanceAlgorithm implements Listener {

    private static final int DEFAULT_DISTANCE = 4;

    private final Map<UUID, PlayerContext> contexts;

    public DistanceAlgorithm() {
        this.contexts = new HashMap<>();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location to = event.getTo();
        if (to == null) return;

        Player player = event.getPlayer();
        if (player.isFlying()) return;

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;

        UUID uuid = player.getUniqueId();
        PlayerContext context = contexts.computeIfAbsent(uuid, (v) -> new PlayerContext(player));
        System.out.println(context);
        System.out.println(context.getBlocksFallen());
        if (context.getBlocksFallen() >= DEFAULT_DISTANCE) {
            System.out.println("good");
            context.reset();
            player.setFlying(true);
        } else {
            System.out.println("increase");
            context.fall(to);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        contexts.remove(event.getPlayer().getUniqueId());
    }

}
