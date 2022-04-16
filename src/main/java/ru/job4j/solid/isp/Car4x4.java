package ru.job4j.solid.isp;

/**
 * Интерфейс полноприводных авто, в который добавлен функционал включения/отключения полного привода
 */
public interface Car4x4 extends Car {
    void enableAllWheelDrive();
    void disableAllWheelDrive();
}
