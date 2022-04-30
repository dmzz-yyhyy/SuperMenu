package me.dmzz.supermenu.menutype;

public class Menus {
    public static Object getMenu(String MenuName) {
        if (AnvilMenu.getMenu(MenuName) == null) {
            if (ChestMenu.getMenu(MenuName) == null) {
                return null;
            }
            else {
                return ChestMenu.getMenu(MenuName);
            }
        }
        else if (ChestMenu.getMenu(MenuName) == null) {
            if (AnvilMenu.getMenu(MenuName) == null) {
                return null;
            }
            else {
                return AnvilMenu.getMenu(MenuName);
            }
        }
        return null;
    }
}
