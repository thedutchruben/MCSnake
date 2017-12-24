package com.duckelekuuk.mcsnake.models;

import com.duckelekuuk.mcsnake.utils.ItemStackBuilder;
import com.duckelekuuk.mcsnake.utils.Properties;
import com.duckelekuuk.mcsnake.utils.SkullBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Button {

    UP(new SkullBuilder().setBase64(Properties.SKULL_UP).setDisplayName("Up").build(), new SkullBuilder().setBase64(Properties.SKULL_UP_ACTIVE).setDisplayName("Up").build(), 1, 4),
    DOWN(new SkullBuilder().setBase64(Properties.SKULL_DOWN).setDisplayName("Down").build(), new SkullBuilder().setBase64(Properties.SKULL_DOWN_ACTIVE).setDisplayName("Down").build(), 3, 4),
    LEFT(new SkullBuilder().setBase64(Properties.SKULL_LEFT).setDisplayName("Left").build(), new SkullBuilder().setBase64(Properties.SKULL_LEFT_ACTIVE).setDisplayName("Left").build(), 2, 3),
    RIGHT(new SkullBuilder().setBase64(Properties.SKULL_RIGHT).setDisplayName("Right").build(), new SkullBuilder().setBase64(Properties.SKULL_RIGHT_ACTIVE).setDisplayName("Right").build(), 2, 5),
    QUIT(ItemStackBuilder.of(Material.BARRIER).displayName("Quit").build(), null, 1, 8);

    private ItemStack item;
    @Setter
    private ItemStack item_active;
    private int y;
    private int x;

    public static Button getButton(ItemStack itemStack) {
        return Arrays.stream(values()).filter(button -> itemStack.getItemMeta().getDisplayName().equals(button.getItem().getItemMeta().getDisplayName())).findFirst().orElse(null);
    }
}
