package com.duckelekuuk.mcsnake.models.buttons;

import com.duckelekuuk.mcsnake.models.Console;
import com.duckelekuuk.mcsnake.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class QuitButton implements IButton {

    private static ItemStack ITEM = ItemStackBuilder.of(Material.BARRIER).displayName("Quit").build();

    @Override
    public int getX() {
        return 8;
    }

    @Override
    public int getY() {
        return 1;
    }

    @Override
    public ItemStack getItem(boolean active) {
        return ITEM;
    }

    @Override
    public void press(Console console) {

    }

    @Override
    public void unPress(Console console) {

    }
}
