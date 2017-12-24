package com.duckelekuuk.mcsnake.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Properties {


    /* Settings */
    public static int WIDTH = 9;
    public static int HEIGHT = 6;
    public static int START_POSITION = InventoryUtils.getLocation(4, 3);
    public static int GAME_SPEED = 10;
    public static int GAMEOVER_SPEED = 2;

    /* Skulls */
    public static final String SKULL_UP = "http://textures.minecraft.net/texture/2d9287616343d833e9e7317159caa2cb3e59745113962c1379052ce478884fa";
    public static final String SKULL_LEFT = "http://textures.minecraft.net/texture/f84f597131bbe25dc058af888cb29831f79599bc67c95c802925ce4afba332fc";
    public static final String SKULL_DOWN = "http://textures.minecraft.net/texture/a3852bf616f31ed67c37de4b0baa2c5f8d8fca82e72dbcafcba66956a81c4";
    public static final String SKULL_RIGHT = "http://textures.minecraft.net/texture/fcfe8845a8d5e635fb87728ccc93895d42b4fc2e6a53f1ba78c845225822";


    public static final String SKULL_UP_ACTIVE = "http://textures.minecraft.net/texture/5da027477197c6fd7ad33014546de392b4a51c634ea68c8b7bcc0131c83e3f";
    public static final String SKULL_LEFT_ACTIVE = "http://textures.minecraft.net/texture/8550b7f74e9ed7633aa274ea30cc3d2e87abb36d4d1f4ca608cd44590cce0b";
    public static final String SKULL_DOWN_ACTIVE = "http://textures.minecraft.net/texture/ff7416ce9e826e4899b284bb0ab94843a8f7586e52b71fc3125e0286f926a";
    public static final String SKULL_RIGHT_ACTIVE = "http://textures.minecraft.net/texture/96339ff2e5342ba18bdc48a99cca65d123ce781d878272f9d964ead3b8ad370";

    /* Items */
    public static final ItemStack SNAKE = ItemStackBuilder.of(Material.STAINED_GLASS_PANE).durability(5).build();
    public static final ItemStack FOOD = ItemStackBuilder.of(Material.STAINED_GLASS_PANE).durability(14).build();
    public static final ItemStack SCREEN_BACKGROUND = ItemStackBuilder.of(Material.STAINED_GLASS_PANE).durability(15).build();
    public static final ItemStack GAMEOVER_TEXT = ItemStackBuilder.of(Material.STAINED_GLASS_PANE).durability(14).build();
    public static final ItemStack CONTROLLER_BACKGROUND = ItemStackBuilder.of(Material.STAINED_GLASS_PANE).durability(7).build();
}
