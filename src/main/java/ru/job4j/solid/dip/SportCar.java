package ru.job4j.solid.dip;

/**
 *  В данном случае мы видим зависимость класса SportCar от конкретной реализации двигателя.
 *  В случае необходимости создания спорткара с двигателем V10, придётся создавать другую реализацию
 *  интерфейса Car уже с двигателем V10. Так как, если мы изменим уже существующий код, нарушим принцип OCP.
 *  Лучше создать интерфейс Engine и использовать его в качестве поля, чтобы иметь возможность выбирать его
 *  реализации. Если мы будем работать не только со спорткарами, то можно разделить интерфейс на двигатели
 *  для спорткаров и обычных авто добавив характерный только для них функционал.
 */


public class SportCar implements Car {
    private String vin;
    private String model;
    private String color;
    private EngineV8 engine;

    public SportCar(String vin, String model, String color, EngineV8 engine) {
        this.vin = vin;
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    @Override
    public void drive() {
        engine.startEngine();

    }

    @Override
    public void brake() {

    }
}
