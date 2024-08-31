package com.github.kaspiandev.safeelytra.algorithm;

import com.github.kaspiandev.safeelytra.SafeElytra;
import com.github.kaspiandev.safeelytra.algorithm.context.DistanceContext;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DistanceAlgorithm extends Algorithm<DistanceContext> {

    public DistanceAlgorithm(SafeElytra plugin) {
        super(plugin);
    }

    public void check(PlayerMoveEvent event) {
        Location to = event.getTo();
        if (to == null) return;

        Player player = event.getPlayer();
        if (player.isFlying()) return;

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;

        UUID uuid = player.getUniqueId();
        DistanceContext context = contexts.computeIfAbsent(uuid, (v) -> new DistanceContext(player));
        if (context.getBlocksFallen() >= plugin.getConf().getAlgorithmSection().getDistance().getThreshold()) {
            context.reset();
            player.setGliding(true);
        } else {
            context.fall(to);
        }
    }

}
