package me.dmzz.supermenu.command;

import me.dmzz.supermenu.yml.ReadYml;
import me.dmzz.supermenu.menutype.Menus;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuperMenu implements CommandExecutor, TabExecutor {

    public FileConfiguration FileConfig = me.dmzz.supermenu.SuperMenu.FileConfig;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.RED + FileConfig.getString("PleaseEnterASubcommand"));
        }
        else {
            if (args[0].equals("reload")) {
                me.dmzz.supermenu.SuperMenu.FileConfig = me.dmzz.supermenu.SuperMenu.getPlugin(me.dmzz.supermenu.SuperMenu.class).getConfig();
                FileConfig = me.dmzz.supermenu.SuperMenu.getPlugin(me.dmzz.supermenu.SuperMenu.class).getConfig();
                ReadYml.readAllFile();
                commandSender.sendMessage(ChatColor.GREEN + FileConfig.getString("ConfigurationFileReloaded"));
            }
            if (args[0].equals("menu")) {
                ReadYml.readAllFile();
                if (args.length == 2) {
                    if (Menus.getMenu(args[1] + ".yml") != null) {
                        Player player = (Player) commandSender;
                        if (Menus.getMenu(args[1] + ".yml")  instanceof net.wesjd.anvilgui.AnvilGUI.Builder) {
                            ((AnvilGUI.Builder) Objects.requireNonNull(Menus.getMenu(args[1] + ".yml"))).open(player);
                        }
                        else if (Menus.getMenu(args[1] + ".yml") instanceof Inventory) {
                            player.openInventory(((Inventory) Objects.requireNonNull(Menus.getMenu(args[1] + ".yml"))));
                        }
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + FileConfig.getString("SorryTheMenuYouEnteredDoesNotExist"));
                    }
                }
                else {
                    commandSender.sendMessage(ChatColor.RED + FileConfig.getString("SorryTheMenuYouEnteredDoesNotExist"));
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            list.add("reload");
            list.add("menu");
            return list;
        }
        if (args.length == 2) {
            if (args[0].equals("menu")) {
                File DataFolder = new File(me.dmzz.supermenu.SuperMenu.getPlugin(me.dmzz.supermenu.SuperMenu.class).getDataFolder().getPath() + "/menus");
                File[] DataFolderFileList = DataFolder.listFiles();
                assert DataFolderFileList != null;
                List<String> list = new ArrayList<>();
                for (File file:DataFolderFileList) {
                    list.add(file.getName().replace(".yml", ""));
                }
                return list;
            }
        }
        return null;
    }
}
