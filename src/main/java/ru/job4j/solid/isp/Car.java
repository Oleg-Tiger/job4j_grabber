package ru.job4j.solid.isp;

/**
 * Интерфейс авто описывающий основной функционал всех авто(не считая игрушечных)
 */

public interface Car {
    void startEngine();
    void gas();
    void brake();
}
