package Backen.Ejercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.stream.Stream;

@RestController
public class HolaController {
    @GetMapping("/")
    public String saludar(){
        return "hola";
    };
    @GetMapping("/junior")
    public String juni(){
        return "hola junior";
    };

    @GetMapping("/stream")
    public Stream<Double> Stream(){
        return Stream.generate(Math::random).limit(100);
    };

}
