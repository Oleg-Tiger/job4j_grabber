package ru.job4j.solid.srp;

import java.util.Collection;

public interface Calculator<T> {
    T calculate(Collection<T> collection);
}
