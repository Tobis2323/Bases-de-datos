package Backen.Ejercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/numeros")
public class NumerosController {
    private Set<Integer> numeros = new HashSet<>();
    private Random random = new Random();

    @GetMapping("/aleatorio")
    public Integer agregarAleatorio() {
        int nuevo = random.nextInt(100); // número entre 0 y 99
        numeros.add(nuevo);
        return nuevo;
    };
    @GetMapping("agregar/{nuevo}")
    public void agregar(@PathVariable Integer nuevo){
        numeros.add(nuevo);
    }
}
