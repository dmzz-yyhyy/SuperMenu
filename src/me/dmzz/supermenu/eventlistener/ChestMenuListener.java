package me.dmzz.supermenu.eventlistener;

import me.dmzz.supermenu.SuperMenu;
import me.dmzz.supermenu.menutype.Item;
import me.dmzz.supermenu.yml.ReadYml;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class ChestMenuListener implements Listener {
    @EventHandler
    public void PreventRemovalItems(InventoryClickEvent MenuClickEvent) {
        Player player = (Player) MenuClickEvent.getWhoClicked();
        File DataFolder = new File(SuperMenu.getPlugin(SuperMenu.class).getDataFolder().getPath() + "/menus");
        File[] DataFolderFileList = DataFolder.listFiles();
        assert DataFolderFileList != null;
        for (File file:DataFolderFileList) {
            File MenuFile = new File(SuperMenu.getPlugin(SuperMenu.class).getDataFolder(), "menus/" + file.getName());
            YamlConfiguration MenuConfig = YamlConfiguration.loadConfiguration(MenuFile);
            if (MenuClickEvent.getWhoClicked().getOpenInventory().getTitle().equals(MenuConfig.getString("MenuName")) && Objects.requireNonNull(MenuConfig.getString("MenuType")).equals("ChestMenu")) {
                if (MenuConfig.getBoolean("TakeItems") && MenuClickEvent.getRawSlot() <= MenuClickEvent.getInventory().getSize()) {
                    MenuClickEvent.setCancelled(true);
                }
                List<String> SecondaryList = ReadYml.getSecondaryList(MenuConfig);
                for (String Path:SecondaryList) {
                    if (MenuClickEvent.getRawSlot() == MenuConfig.getInt("Items." + Path +".Index")) {
                        for (String Command:MenuConfig.getStringList("Items." + Path +".Command")){
                            player.chat("/" + Command);
                        }
                    }
                }
            }
        }
    }
}
