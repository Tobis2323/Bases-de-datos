package org.example;

public class Persona {
    private int documento;
    private String nombre;
    private int edad;

    // Constructor
    public Persona(int documento, String nombre, int edad) {
        this.documento = documento;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters

    public int getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // Para mostrar fácilmente
    @Override
    public String toString() {
        return "Persona{documento='" + documento + "', nombre='" + nombre + "', edad=" + edad + "}";
    }
}
