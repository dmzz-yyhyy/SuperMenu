package me.dmzz.supermenu.nbt.version;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class Wrapper1_17R1 {
    public void getNmsItem(int Item) {}
    ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
    net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
}
