package de.fi.springconsolenapp.runner;

import de.fi.springconsolenapp.math.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MainRunner implements CommandLineRunner {

    @Qualifier("logger")
    private final Calculator calculator;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(calculator.add(3,5));
    }
}
