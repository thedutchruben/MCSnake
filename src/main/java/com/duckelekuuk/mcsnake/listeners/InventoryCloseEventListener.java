package com.duckelekuuk.mcsnake.listeners;

import com.duckelekuuk.mcsnake.managers.ConsoleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseEventListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;
        Player player = (Player) event.getPlayer();

        if (ConsoleManager.getConsole(player) == null) return;

        ConsoleManager.removeConsole(player);

        event.getPlayer().getInventory().clear();
    }
}