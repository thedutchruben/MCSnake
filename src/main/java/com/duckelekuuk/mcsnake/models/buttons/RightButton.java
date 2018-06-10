package com.duckelekuuk.mcsnake.models.buttons;

import com.duckelekuuk.mcsnake.models.Button;
import com.duckelekuuk.mcsnake.models.Console;
import com.duckelekuuk.mcsnake.models.Direction;
import com.duckelekuuk.mcsnake.utils.Properties;
import com.duckelekuuk.mcsnake.utils.SkullBuilder;
import org.bukkit.inventory.ItemStack;

public class RightButton implements IButton {

    private static final ItemStack PASSIVE_ITEM = new SkullBuilder().setBase64(Properties.SKULL_RIGHT).setDisplayName("Right").build();
    private static final ItemStack ACTIVE_ITEM = new SkullBuilder().setBase64(Properties.SKULL_RIGHT_ACTIVE).setDisplayName("Right").build();

    @Override
    public int getX() {
        return 5;
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
        return Button.RIGHT;
    }

    @Override
    public boolean canBePressed(Console console) {
        return console.getSnake().canGo(Direction.RIGHT);
    }

    @Override
    public void press(Console console) {
        console.getSnake().setDirection(Direction.RIGHT);
        console.setItemInController(getX(), getY(), getItem(true));
    }

    @Override
    public void unPress(Console console) {
        console.setItemInController(getX(), getY(), getItem(false));
    }
}
