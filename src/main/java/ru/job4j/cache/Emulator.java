package ru.job4j.cache;

public class Emulator {
    private AbstractCache dirFileCache;

    public void setDirectory(String directory) {
        dirFileCache = new DirFileCache(directory);
    }

    public String getFile(String key) {
        return (String) dirFileCache.get(key);
    }

    public void loadFile(String key) {
        if (!dirFileCache.cache.containsKey(key)) {
            dirFileCache.load(key);
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.setDirectory(".");
        System.out.println(emulator.getFile("1.txt"));
        System.out.println(emulator.getFile("1.txt"));
        emulator.loadFile("2.txt");
        System.out.println();
        System.out.println(emulator.getFile("2.txt"));
    }

}
