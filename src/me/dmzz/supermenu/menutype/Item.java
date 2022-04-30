package me.dmzz.supermenu.menutype;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
    public static ItemStack ParseItems(FileConfiguration MenuConfig, String ItemPath) {
        ItemStack MenuItem = null;
        if (MenuConfig.getString("{path}.ItemId".replace("{path}", ItemPath)) != null){
            MenuItem = new ItemStack(Material.valueOf(MenuConfig.getString("{path}.ItemId".replace("{path}", ItemPath))));
            ItemMeta MenuItemMeta = MenuItem.getItemMeta();
            if (MenuItemMeta != null) {
                MenuItemMeta.setDisplayName(MenuConfig.getString("{path}.ItemName".replace("{path}", ItemPath)));
            }
            MenuItem.setItemMeta(MenuItemMeta);
        }
        return MenuItem;
    }
}
