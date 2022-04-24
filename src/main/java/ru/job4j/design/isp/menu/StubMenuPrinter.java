package ru.job4j.design.isp.menu;

public class StubMenuPrinter implements MenuPrinter {

    private StringBuilder menuFormatted;

    @Override
    public void print(Menu menu) {
        menuFormatted = new StringBuilder();
        int pointsCount;
        String rsl;
        String indent = " ";
        for (Menu.MenuItemInfo info : menu) {
            String number = info.getNumber();
            String name = number.concat(info.getName());
            pointsCount = number.split("\\.").length;
            rsl = indent.repeat((pointsCount - 1) * 3).concat(name);
            menuFormatted.append(rsl).append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        return menuFormatted.toString();
    }
}
