package ru.job4j.design.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {

    private final MenuPrinter stubPrinter = new StubMenuPrinter();

    @Override
    public void print(Menu menu) {
        stubPrinter.print(menu);
        System.out.print(stubPrinter.toString());
    }
}

