package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class VisitCounterSingleton {
    private int count = 0;

    public int increment() {
        return ++count;
    }
}
