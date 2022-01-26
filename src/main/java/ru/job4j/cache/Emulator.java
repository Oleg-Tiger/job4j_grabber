package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Emulator {

    private String fileName;
    private AbstractCache<String, String> dirFileCache;

    public void setDirectory(String directory) {
        dirFileCache = new DirFileCache(directory);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return dirFileCache.get(fileName);
    }

    public void loadFile() {
        String strong = dirFileCache.cache.getOrDefault(fileName, new SoftReference<>(null)).get();
        if (strong == null) {
            System.out.println("файл не загружен в кэш");
            dirFileCache.put(fileName, dirFileCache.load(fileName));
        }
        System.out.println("файл загружен в кэш");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Выберите действие, введите число от 1 до 5:");
        System.out.println("1. Указать кэшируемую директорию");
        System.out.println("2. Указать имя файла");
        System.out.println("3. Загрузить содержимое файла в кэш");
        System.out.println("4. Получить содержимое файла из кэша");
        System.out.println("5. Выйти");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Emulator emulator = new Emulator();
        emulator.showMenu();
        String answer = sc.nextLine();
        while (!answer.equals("5")) {
            switch (answer) {
                case "1":
                    System.out.println("Укажите кэшируемую директорию");
                    emulator.setDirectory(sc.nextLine());
                    break;
                case "2":
                    System.out.println("Укажите имя файла");
                    emulator.setFileName(sc.nextLine());
                    break;
                case "3":
                    emulator.loadFile();
                case "4":
                    System.out.println(emulator.getFile());
                default:
                    System.out.println("Введено некорректное значение, повторите ввод");
            }
            emulator.showMenu();
            answer = sc.nextLine();
        }
    }
}



