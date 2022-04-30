package me.dmzz.supermenu.menutype;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AnvilMenu {
    public static Map<String, net.wesjd.anvilgui.AnvilGUI.Builder> AnvilMenus = new HashMap<>();
    public static void anvilMenu(String Name, FileConfiguration MenuConfig){
        net.wesjd.anvilgui.AnvilGUI.Builder builder = new net.wesjd.anvilgui.AnvilGUI.Builder();
        builder.plugin(Bukkit.getPluginManager().getPlugin("MyPlugin"));
        ItemStack LeftItemStack = Item.ParseItems(MenuConfig, "LeftItem");
        builder.itemLeft(LeftItemStack);
        builder.onLeftInputClick(player -> {
            for (String Command:MenuConfig.getStringList("LeftItem.Command"))
                player.chat("/" + Command);
        });

        ItemStack RightItemStack = Item.ParseItems(MenuConfig, "RightItem");
        builder.itemRight(RightItemStack);
        builder.onRightInputClick(player -> {
            for (String Command:MenuConfig.getStringList("RightItem.Command"))
                player.chat("/" + Command);
        });

        if (MenuConfig.getBoolean("PreventClose")){builder.preventClose();}
        builder.title(MenuConfig.getString("MenuName", "Repair & Name"));
        builder.text(MenuConfig.getString("Text", "Please enter"));

        builder.onClose(player -> {
            for (String Command:MenuConfig.getStringList("Command.OnClose"))
                player.chat("/" + Command);
        });
        builder.onComplete((player, text) -> {
            for (String Command:MenuConfig.getStringList("Command.OnComplete"))
            player.chat("/" + Command.replace("{text}", text));
            return net.wesjd.anvilgui.AnvilGUI.Response.close();
        });

        AnvilMenus.put(Name, builder);
    }
    public static net.wesjd.anvilgui.AnvilGUI.Builder getMenu(String MenuName) {
        return AnvilMenus.get(MenuName);
    }
}
