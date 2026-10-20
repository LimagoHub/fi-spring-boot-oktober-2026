package main;

import java.util.List;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Supplier<Integer> myFunk = this::foo;
    }

    public int foo() {

        List<String> list = List.of("a","b","c","d");

        list.forEach(System.out::println);

        System.out.println("Hier ist foo");
        return 42;

    }
}
