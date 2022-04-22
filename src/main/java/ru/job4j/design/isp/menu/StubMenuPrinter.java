package ru.job4j.design.isp.menu;

import java.util.Arrays;

public class StubMenuPrinter implements MenuPrinter {

    private StringBuilder menuFormatted;

    @Override
    public void print(Menu menu) {
        menuFormatted = new StringBuilder();
        int pointsCount;
        String rsl;
        for (Menu.MenuItemInfo info : menu) {
            String number = info.getNumber();
            String name = number.concat(info.getName());
            pointsCount = number.split("\\.").length;
            if (pointsCount == 1) {
                rsl =  name;
            } else {
                char[] indent = new char[(pointsCount - 1) * 3];
                Arrays.fill(indent, ' ');
                rsl = new String(indent).concat(name);
            }
            menuFormatted.append(rsl).append(System.lineSeparator());
        }
    }

    @Override
    public String toString() {
        return menuFormatted.toString();
    }
}
