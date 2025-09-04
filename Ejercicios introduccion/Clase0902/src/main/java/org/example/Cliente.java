package org.example;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Genera getters, setters, toString, equals, hashCode
@AllArgsConstructor     // Constructor con todos los atributos
@NoArgsConstructor      // Constructor vacío

public class Cliente {
    private String nombre;
    private int edad;
    private short dni;
    private String ocupacion;
    private int cantidadPosteos;
    private float horasEnPlataforma;
    private boolean verificado;
}
