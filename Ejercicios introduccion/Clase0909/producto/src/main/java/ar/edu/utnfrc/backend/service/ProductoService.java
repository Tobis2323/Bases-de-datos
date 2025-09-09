package ar.edu.utnfrc.backend.service;
import ar.edu.utnfrc.backend.model.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    public static List<Producto> cargarProductos(String filePath) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // saltar la primera línea (encabezados)
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Producto producto = new Producto();
                producto.setNombre(values[0]);
                producto.setCodigo(Integer.parseInt(values[1]));
                producto.setPrecio(Double.parseDouble(values[2]));
                producto.setStock(Integer.parseInt(values[3]));
                producto.setCategoria(values[4]);
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
}
