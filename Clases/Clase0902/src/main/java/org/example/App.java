package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;

public class App {
    public static void main(String[] args) {
        String ruta = "clientes.csv";

        List<Cliente> clientes = leerClientes(ruta);
    }
    static List<Cliente> leerClientes(String ruta){
        List<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] campos = linea.split(",");
                if (campos.length != 7){
                    System.out.println("Me estas dando cualquier cosa en " + linea);
                    continue;
                }
                try{
                    String nombre = campos[0].trim();
                    int dni = Integer.parseInt(campos[1].trim());
                    short edad = Short.parseShort(campos[2].trim());
                    String ocupacion = campos[3].trim();
                    int cantidadPosteos = Integer.parseInt(campos[4].trim());
                    float horas = Float.parseFloat(campos[5].trim().replace(",", "."));
                    boolean verificado = Boolean.parseBoolean(campos[6].trim());

                    Cliente c = new Cliente(nombre, dni, edad, ocupacion, cantidadPosteos, horas, verificado);
                } catch (NumberFormatException e) {
                    System.out.println("Error parseando números en línea: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }

        return lista;
    }
}
