package ru.job4j.design.isp.menu;

public class TODOApp {
   private Menu userMenu;
   private MenuPrinter printer;

    public TODOApp(Menu userMenu, MenuPrinter printer) {
        this.userMenu = userMenu;
        this.printer = printer;
    }

    public void setUserMenu(Menu userMenu) {
        this.userMenu = userMenu;
    }

    public void setPrinter(MenuPrinter printer) {
        this.printer = printer;
    }

    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
       return userMenu.add(parentName, childName, actionDelegate);
   }

   public void print() {
       printer.print(userMenu);
   }

}
