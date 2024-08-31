package com.github.kaspiandev.safeelytra.algorithm;

import com.github.kaspiandev.safeelytra.SafeElytra;
import com.github.kaspiandev.safeelytra.algorithm.context.Context;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Algorithm<C extends Context> {

    protected final SafeElytra plugin;
    protected final Map<UUID, C> contexts;

    protected Algorithm(SafeElytra plugin) {
        this.plugin = plugin;
        this.contexts = new HashMap<>();
    }

    public abstract void check(PlayerMoveEvent event);

    public void clean(Player player) {
        contexts.remove(player.getUniqueId());
    }

}
