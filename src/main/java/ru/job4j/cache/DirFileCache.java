package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        System.out.println("Загружаем файл в кэш");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(String.format("%s/%s", cachingDir, key)))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
                sb.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rsl = sb.toString();
        put(key, rsl);
        System.out.println("Файл загружен в кэш");
        return rsl;
    }
}