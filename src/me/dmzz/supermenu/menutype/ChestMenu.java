package me.dmzz.supermenu.menutype;

import me.dmzz.supermenu.yml.ReadYml;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChestMenu {
    public static Map<String, Inventory> ChestMenus = new HashMap<>();
    public static void chestMenu(String Name, FileConfiguration MenuConfig){
        Inventory MenuInventory = Bukkit.createInventory(null, MenuConfig.getInt("Integer"), Objects.requireNonNull(MenuConfig.getString("MenuName")));
        List<String> SecondaryList = ReadYml.getSecondaryList(MenuConfig);
        for (String Path:SecondaryList) {
            MenuInventory.setItem(MenuConfig.getInt("Items." + Path +".Index"), Item.ParseItems(MenuConfig, "Items." + Path));
        }
        ChestMenus.put(Name, MenuInventory);
    }
    public static Inventory getMenu(String MenuName) {
        return ChestMenus.get(MenuName);
    }
}

