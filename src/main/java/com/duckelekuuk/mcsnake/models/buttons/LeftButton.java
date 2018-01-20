package com.duckelekuuk.mcsnake.models.buttons;

import com.duckelekuuk.mcsnake.models.Button;
import com.duckelekuuk.mcsnake.models.Console;
import com.duckelekuuk.mcsnake.models.Direction;
import com.duckelekuuk.mcsnake.utils.Properties;
import com.duckelekuuk.mcsnake.utils.SkullBuilder;
import org.bukkit.inventory.ItemStack;

public class LeftButton implements IButton {

    private static final ItemStack PASSIVE_ITEM = new SkullBuilder().setBase64(Properties.SKULL_LEFT).setDisplayName("Left").build();
    private static final ItemStack ACTIVE_ITEM = new SkullBuilder().setBase64(Properties.SKULL_LEFT_ACTIVE).setDisplayName("Left").build();

    @Override
    public int getX() {
        return 3;
    }

    @Override
    public int getY() {
        return 2;
    }

    @Override
    public ItemStack getItem(boolean active) {
        return active ? ACTIVE_ITEM : PASSIVE_ITEM;
    }

    @Override
    public Button getButton() {
        return Button.LEFT;
    }

    @Override
    public boolean canBePressed(Console console) {
        return console.getSnake().canGo(Direction.LEFT);
    }

    @Override
    public void press(Console console) {
        console.getSnake().setDirection(Direction.LEFT);
        console.setItemInController(getX(), getY(), getItem(true));
    }

    @Override
    public void unPress(Console console) {
        console.setItemInController(getX(), getY(), getItem(false));
    }
}
