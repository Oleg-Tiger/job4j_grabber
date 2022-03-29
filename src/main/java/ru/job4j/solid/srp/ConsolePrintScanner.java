package ru.job4j.solid.srp;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Данный класс является реализацией интерфейса PrintScanner c выводом данных в консоль
 * Нарушением принципа SRP является то, что в интерфейсе и в  данном классе соответственно
 * преследуется несколько целей:
 * считывание файла, а также вывод строки, в случае с данным классом - в консоль.
 * В другой раз может потребоваться не выводить в консоль, а, например записать в файл или на диск.
 * Придётся копировать код. То есть, согласно принципу, следует сделать 2 интерфейса,
 * чтобы интиерфейс для вывода файла был независим и можно будет отдельно добавлять требуемые реализации.
 */
public class ConsolePrintScanner implements PrintScanner {

    @Override
    public String scan(Path input) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public void print(String str) {
        System.out.println(str);
    }
}
