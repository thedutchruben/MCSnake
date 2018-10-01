package com.duckelekuuk.mcsnake.listeners;

import com.duckelekuuk.mcsnake.managers.ConsoleManager;
import com.duckelekuuk.mcsnake.models.Button;
import com.duckelekuuk.mcsnake.models.Console;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickEventListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Console console = ConsoleManager.getConsole(player);

        if (console == null) return;
        if (!console.getInventory().getViewers().contains(player)) return;

        event.setCancelled(true);
        event.setResult(Event.Result.DENY);

        if (console.isGameOver()) return;

        Button pressed = Button.getButton(event.getCurrentItem());

        if (pressed == null) return;
        console.onClick(pressed);
    }
}
