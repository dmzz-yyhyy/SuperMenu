package me.dmzz.supermenu.yml;

import me.dmzz.supermenu.SuperMenu;

import java.io.File;

public class SaveYml {
    public static void saveYml() {
        SuperMenu.Plugin.saveDefaultConfig();
        File DataFolder = SuperMenu.Plugin.getDataFolder();
        File[] DataFolderFileList = DataFolder.listFiles();
        assert DataFolderFileList != null;
        boolean IsThereMenus = false;
        for (File file:DataFolderFileList) {
            if (file.getName().equals("menus") && file.isDirectory()) {
                IsThereMenus = true;
            }
        }
        if (!IsThereMenus) {
            SuperMenu.Plugin.saveResource("menus/AnvilMenu.yml", false);
            SuperMenu.Plugin.saveResource("menus/ChestMenu.yml", false);
        }
    }
}
