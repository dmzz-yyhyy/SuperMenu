package me.dmzz.supermenu.yml;

import me.dmzz.supermenu.SuperMenu;
import me.dmzz.supermenu.menutype.AnvilMenu;
import me.dmzz.supermenu.menutype.ChestMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadYml {
    public static void readAllFile() {
        File DataFolder = new File(SuperMenu.getPlugin(SuperMenu.class).getDataFolder().getPath() + "/menus");
        File[] DataFolderFileList = DataFolder.listFiles();
        assert DataFolderFileList != null;
        for (File file:DataFolderFileList) {
            readOneFile("menus/" + file.getName());
        }
    }
    public static void readOneFile(String FilePath) {
        File MenuFile = new File(SuperMenu.getPlugin(SuperMenu.class).getDataFolder(), FilePath);
        String MenuName = MenuFile.getName();
        FileConfiguration Menu = YamlConfiguration.loadConfiguration(MenuFile);
        String MenuType = Menu.getString("MenuType");
        if (MenuType != null) {
            if (MenuType.equals("AnvilMenu")) {
                AnvilMenu.anvilMenu(MenuName, Menu);
            }
            if (MenuType.equals("ChestMenu")) {
                ChestMenu.chestMenu(MenuName, Menu);
            }
        }
    }
    public static List<String> getSecondaryList(FileConfiguration MenuConfig) {
        List<String> list = new ArrayList<>();
        String MenuConfigText = MenuConfig.saveToString();
        Pattern pattern = Pattern.compile("(?<=\\n|^)( *)");
        String Indentation = "";
        for (String OneText:MenuConfigText.split("\n")) {
            Matcher OneTextMatcher = pattern.matcher(OneText);
            if (OneTextMatcher.find()) {
                String collegeId = OneTextMatcher.group(1);
                if (!collegeId.equals("")) {
                    Indentation = collegeId;
                    break;
                }
            }
        }
        pattern = Pattern.compile("(?<=^|\\n)(indentation)(.[^ ]*)?(?=:)".replace("indentation", Indentation));
        for (String OneText:MenuConfigText.split("\n")) {
            Matcher OneTextMatcher = pattern.matcher(OneText);
            if (OneTextMatcher.find()) {
                String collegeId = OneTextMatcher.group(2);
                if (!collegeId.equals("  ")) {
                    list.add(collegeId);
                }
            }
        }
        return list;
    }
}
