package ru.job4j.solid.isp;

/**
 * Но мы не учли, что не на всех полноприводных авто можно отключить полный привод
 * Создав класс Niva, которая по сути является полноприводной - реализовали интерфейса
 * Car 4x4 и нарушили isp, т.к. пришлось реализовывать методы, не соотвествующие функционалу.
 * Также нарушен и lsp. Решение - вынести функционал управления полным приводом в отдельный интерфейс.
 */

public class Niva implements Car4x4 {
    @Override
    public void enableAllWheelDrive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void disableAllWheelDrive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void startEngine() {

    }

    @Override
    public void gas() {

    }

    @Override
    public void brake() {

    }
}
