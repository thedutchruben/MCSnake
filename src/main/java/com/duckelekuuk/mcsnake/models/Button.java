package com.duckelekuuk.mcsnake.models;

import com.duckelekuuk.mcsnake.models.buttons.*;
import com.duckelekuuk.mcsnake.utils.ItemStackBuilder;
import com.duckelekuuk.mcsnake.utils.Properties;
import com.duckelekuuk.mcsnake.utils.SkullBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Button {

    UP(new UpButton()),
    DOWN(new DownButton()),
    LEFT(new LeftButton()),
    RIGHT(new RightButton()),
    QUIT(new QuitButton());

    private IButton info;

    public static Button getButton(ItemStack itemStack) {
        if (itemStack == null) return null;
        if (itemStack.hasItemMeta()) return null;

        return Arrays.stream(values())
                .filter(button ->
                        itemStack.isSimilar(button.getInfo().getItem(true)) ||
                        itemStack.isSimilar(button.getInfo().getItem(false)))
                .findFirst()
                .orElse(null);
    }

    public static boolean isButton(ItemStack itemStack) {
        return Arrays.stream(values()).anyMatch(button -> itemStack.isSimilar(button.getInfo().getItem(true)) || itemStack.isSimilar(button.getInfo().getItem(false)));
    }
}
