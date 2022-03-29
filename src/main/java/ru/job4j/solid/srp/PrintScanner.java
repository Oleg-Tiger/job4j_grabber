package ru.job4j.solid.srp;

import java.nio.file.Path;

public interface PrintScanner {
    String scan(Path input);
    void print(String str);
}
