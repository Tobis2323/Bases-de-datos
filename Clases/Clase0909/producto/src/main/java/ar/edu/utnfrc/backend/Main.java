package ar.edu.utnfrc.backend;

import ar.edu.utnfrc.backend.model.Producto;
import ar.edu.utnfrc.backend.service.ProductoService;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {




        // Ruta al archivo
        String PRODUCTOS_FILE_PATH ="src\\main\\resources\\productos.csv" ;

        // Cargo los productos (5 filas del CSV)
        List<Producto> productos = ProductoService.cargarProductos(PRODUCTOS_FILE_PATH);

        // Los muestro por pantalla
        System.out.println("Lista de productos cargados desde CSV:");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}