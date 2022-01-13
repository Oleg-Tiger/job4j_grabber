package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> val = new SoftReference<>(value);
        cache.put(key, val);
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            V strong = cache.get(key).get();
            if (strong != null) {
                System.out.println("Файл есть в кэше, получаем его:");
                return strong;
            }
        }
        System.out.println("Файла нет в кэше");
       return load(key);
    }

    protected abstract V load(K key);
}