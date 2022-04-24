package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (findItem(childName).isPresent()) {
            return false;
        }
        MenuItem menuItem = new SimpleMenuItem(childName, actionDelegate);
        if (parentName == Menu.ROOT) {
            return rootElements.add(menuItem);
        }
        Optional<ItemInfo> itemInfo = findItem(parentName);
        if (itemInfo.isEmpty()) {
            throw new IllegalArgumentException("The parent directory does not exist");
        }
        return itemInfo.get().menuItem.getChildren().add(menuItem);
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> itemInfo = findItem(itemName);
        return itemInfo.map(fromOptional -> new MenuItemInfo(fromOptional.menuItem, fromOptional.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {

            private final DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo rsl = iterator.next();
            if (rsl.menuItem.getName().equals(name)) {
                return Optional.of(rsl);
            }
        }
        return Optional.empty();
    }

    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}