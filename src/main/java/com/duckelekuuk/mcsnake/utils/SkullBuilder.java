package com.duckelekuuk.mcsnake.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@Getter()
public class SkullBuilder {

    private ItemStack skull;
    private SkullMeta meta;

    public SkullBuilder() {
        this.skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        this.meta = (SkullMeta) this.skull.getItemMeta();
    }

    public SkullBuilder setDisplayName(String string) {
        meta.setDisplayName(string);
        return this;
    }

    public SkullBuilder setBase64(String url) {

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        return this;
    }

    public ItemStack build() {
        skull.setItemMeta(meta);
        return skull;
    }
}
