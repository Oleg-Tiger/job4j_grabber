package ru.job4j.solid.isp;

/**
 * Интерфейс оружие - описывает поведение оружия в целом, из него можно стрелять, его можно перезарядить.
 */
public interface Weapon {
    void attack();
    void reload();
}
