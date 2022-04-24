package ru.job4j.design.isp.menu;

import org.junit.Test;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
    }

    @Test
    public void whenChildAlreadyExistThenNotAdded() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Съездить в гипермаркет", STUB_ACTION);
        assertTrue(menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION));
        assertFalse(menu.add("Съездить в гипермаркет", "Купить продукты", STUB_ACTION));
    }

    @Test
    public void whenAddAndCheckDelegate() {
        Menu menu = new SimpleMenu();
        StubActionDelegate actionDelegate = new StubActionDelegate();
        menu.add(Menu.ROOT, "Сходить в магазин", actionDelegate);
        menu.add(Menu.ROOT, "Покормить собаку", actionDelegate);
        menu.select("Сходить в магазин").get().getActionDelegate().delegate();
        assertThat(actionDelegate.getCount(), is(1));
        menu.select("Покормить собаку").get().getActionDelegate().delegate();
        assertThat(actionDelegate.getCount(), is(2));

    }

    @Test
    public void whenItemNotExistThenNotFound() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        Optional<Menu.MenuItemInfo> rsl = (menu.select("Покормить собаку"));
        assertTrue(rsl.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParentNotExist() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Покормить собаку", "Купить продукты", STUB_ACTION);
    }


    @Test
    public void whenPrintMenu() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить хлеб", "Чёрный хлеб", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add("Купить хлеб", "Половинка белого", STUB_ACTION);
        String expected = "1.Сходить в магазин" + System.lineSeparator()
                + "   1.1.Купить продукты" + System.lineSeparator()
                + "      1.1.1.Купить хлеб" + System.lineSeparator()
                + "         1.1.1.1.Чёрный хлеб" + System.lineSeparator()
                + "         1.1.1.2.Половинка белого" + System.lineSeparator()
                + "      1.1.2.Купить молоко" + System.lineSeparator()
                + "2.Покормить собаку" + System.lineSeparator();
        MenuPrinter printer = new StubMenuPrinter();
        printer.print(menu);
        assertThat(printer.toString(), is(expected));
    }
}