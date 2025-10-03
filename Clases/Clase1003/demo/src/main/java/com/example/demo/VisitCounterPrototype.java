package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class VisitCounterPrototype {
    private int count = 0;

    public int increment() {
        return ++count;
    }
}
