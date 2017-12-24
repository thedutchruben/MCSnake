package com.duckelekuuk.mcsnake.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ItemStackBuilder {

    private final ItemStack itemStack;
    private final List<ItemFlag> ALL_FLAGS = ImmutableList.of(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON);

    public static ItemStackBuilder of(Material material) {
        return new ItemStackBuilder(new ItemStack(material));
    }

    public static ItemStackBuilder of(ItemStack itemStack) {
        return new ItemStackBuilder(itemStack);
    }

    private ItemStackBuilder(ItemStack itemStack) {
        this.itemStack = checkNotNull(itemStack, "itemStack is null");
    }

    private ItemStackBuilder editMeta(Consumer<ItemMeta> itemMeta) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            itemMeta.accept(meta);
            itemStack.setItemMeta(meta);
        }
        return this;
    }

    private ItemStackBuilder editItem(Consumer<ItemStack> item) {
        item.accept(itemStack);
        return this;
    }

    public ItemStackBuilder displayName(String displayName) {
        return editMeta(itemMeta -> itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName)));
    }

    public ItemStackBuilder lore(String line) {
        return editMeta(itemMeta -> {
            List<String> theLore = itemMeta.getLore() == null ? Lists.newArrayList() : itemMeta.getLore();
            theLore.add(ChatColor.translateAlternateColorCodes('&', line));
            itemMeta.setLore(theLore);
        });
    }

    public ItemStackBuilder lore(String... lines) {
        return editMeta(itemMeta -> {
            List<String> theLore = itemMeta.getLore() == null ? Lists.newArrayList() : itemMeta.getLore();
            Arrays.stream(lines).forEach(s -> theLore.add(ChatColor.translateAlternateColorCodes('&', s)));
            itemMeta.setLore(theLore);
        });
    }

    public ItemStackBuilder type(Material material) {
        return editItem(itemStack -> itemStack.setType(material));
    }

    public ItemStackBuilder clearLore() {
        return editMeta(itemMeta -> itemMeta.setLore(Collections.emptyList()));
    }

    public ItemStackBuilder amount(int amount) {
        return editItem(itemStack -> itemStack.setAmount(amount));
    }

    public ItemStackBuilder durability(short durability) {
        return editItem(itemStack -> itemStack.setDurability(durability));
    }

    public ItemStackBuilder durability(int durability) {
        return durability((short) durability);
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        return editItem(itemStack -> itemStack.addUnsafeEnchantment(enchantment, level));
    }

    public ItemStackBuilder clearEnchantments() {
        return editItem(itemStack -> itemStack.getEnchantments().keySet().forEach(itemStack::removeEnchantment));
    }

    public ItemStackBuilder breakable(boolean value, boolean attributeHidden) {
        return editMeta(itemMeta -> {
            itemMeta.setUnbreakable(!value);
            if (attributeHidden) {
                hideAttributes();
            }
        });
    }

    public ItemStackBuilder flag(ItemFlag... flags) {
        return editMeta(itemMeta -> itemMeta.addItemFlags(flags));
    }

    public ItemStackBuilder unflag(ItemFlag... flags) {
        return editMeta(itemMeta -> itemMeta.removeItemFlags(flags));
    }

    public ItemStackBuilder hideAttributes() {
        ALL_FLAGS.forEach(this::flag);
        return this;
    }

    public ItemStackBuilder showAttributes() {
        ALL_FLAGS.forEach(this::unflag);
        return this;
    }

    public ItemStackBuilder color(Color color) {
        return editItem(itemStack -> {
            Material type = itemStack.getType();
            if (type == Material.LEATHER_BOOTS || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_HELMET || type == Material.LEATHER_LEGGINGS) {
                LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
                meta.setColor(color);
                itemStack.setItemMeta(meta);
            }
        });
    }

    public ItemStack build() {
        return itemStack;
    }
}

