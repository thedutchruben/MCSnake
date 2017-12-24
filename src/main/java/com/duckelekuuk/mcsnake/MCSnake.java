package com.duckelekuuk.mcsnake;

import com.duckelekuuk.mcsnake.listeners.InventoryClickListeners;
import com.duckelekuuk.mcsnake.listeners.InventoryCloseListener;
import com.duckelekuuk.mcsnake.managers.ConsoleManager;
import com.duckelekuuk.mcsnake.models.Console;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCSnake extends JavaPlugin implements Listener {

    @Getter
    private static MCSnake plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new InventoryClickListeners(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Console console = new Console(event.getPlayer());
        ConsoleManager.registerConsole(console);

        console.open();
    }
}
