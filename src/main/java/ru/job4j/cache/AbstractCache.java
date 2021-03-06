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
            V strong = cache.getOrDefault(key, new SoftReference<>(null)).get();
            if (strong == null) {
                System.out.println("файл не загружен в кэш");
                strong = load(key);
                put(key, strong);
            }
            System.out.println("файл загружен в кэш:");
            return strong;
    }

    protected abstract V load(K key);
}