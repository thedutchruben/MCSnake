package com.duckelekuuk.mcsnake;

import com.duckelekuuk.mcsnake.commands.SnakeCommand;
import com.duckelekuuk.mcsnake.listeners.InventoryClickEventListener;
import com.duckelekuuk.mcsnake.listeners.InventoryCloseEventListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCSnake extends JavaPlugin implements Listener {

    @Getter
    private static MCSnake plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseEventListener(), this);
        getCommand("snake").setExecutor(new SnakeCommand());
    }
}
