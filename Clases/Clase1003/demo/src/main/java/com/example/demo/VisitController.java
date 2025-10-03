package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {

    @Autowired
    private VisitCounterSingleton visitCounterSingleton;

    @Autowired
    private VisitCounterPrototype visitCounterPrototype;

    @Autowired
    private VisitCounterRequest visitCounterRequest;

    @Autowired
    private VisitCounterSession visitCounterSession;

    @GetMapping("/health")
    public String healthCheck() {
        return "API is running";
    }

    @GetMapping("/visit/singleton")
    public int getSingletonVisitCount() {
        return visitCounterSingleton.increment();
    }

    @GetMapping("/visit/prototype")
    public int getPrototypeVisitCount() {
        return visitCounterPrototype.increment();
    }

    @GetMapping("/visit/request")
    public int getRequestVisitCount() {
        return visitCounterRequest.increment();
    }

    @GetMapping("/visit/session")
    public int getSessionVisitCount() {
        return visitCounterSession.increment();
    }
    @GetMapping("/contadores")
    public int [] obtenerContadores(){
        int g = visitCounterSingleton.increment();
        int s = visitCounterSession.increment();
        int r = visitCounterRequest.increment();

        return new int [] {g, s, r};
    }
}
