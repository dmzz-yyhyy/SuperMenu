package me.dmzz.supermenu;

import me.dmzz.supermenu.eventlistener.ChestMenuListener;
import me.dmzz.supermenu.yml.ReadYml;
import me.dmzz.supermenu.yml.SaveYml;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SuperMenu extends JavaPlugin {

    public static SuperMenu Plugin;
    public static FileConfiguration FileConfig;

    @Override
    public void onEnable() {
        Plugin = this;
        // 保存文件
        SaveYml.saveYml();
        //载入配置
        FileConfig = this.getConfig();
        ReadYml.readAllFile();

        // 加载指令
        Objects.requireNonNull(getCommand("SuperMenu")).setExecutor(new me.dmzz.supermenu.command.SuperMenu());
        Objects.requireNonNull(getCommand("SuperMenu")).setTabCompleter(new me.dmzz.supermenu.command.SuperMenu());

        // 侦听事件
        getServer().getPluginManager().registerEvents(new ChestMenuListener(), this);

        System.out.println("[SuperMenu]插件已加载");
    }

    @Override
    public void onDisable() {

        System.out.println("[SuperMenu]插件已卸载");
    }

}
