package ru.job4j.cache;

import java.lang.ref.SoftReference;

public class Emulator {

    private String fileName;
    private AbstractCache<String, String> dirFileCache;

    public Emulator(String directory, String fileName) {
        this.dirFileCache = new DirFileCache(directory);
        this.fileName = fileName;
    }

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

    public static void main(String[] args) {
        Emulator emulator = new Emulator(".", "1.txt");
        System.out.println(emulator.getFile());
        System.out.println(emulator.getFile());
        emulator.setFileName("2.txt");
        emulator.loadFile();
        System.out.println();
        System.out.println(emulator.getFile());
    }
}
