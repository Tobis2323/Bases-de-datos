package ar.edu.utnfrc.backend.service;

import ar.edu.utnfrc.backend.model.Etiqueta;
import ar.edu.utnfrc.backend.model.ProductoDigital;

import java.io.IOException;
import java.util.*;

public class CatalogoService {

    public List<ProductoDigital> cargarDesdeResource(String filePath) throws IOException {
        return List.of(); // Retorna una lista vacía como placeholder
    }
    private ProductoDigital fabricar(String sku, String tipo, String nombre, double precioBase, String extra, List<Etiqueta> etiquetas){
        return null; // Retorna null como placeholder
    }
    public Set<ProductoDigital> eliminarDuplicados(List<ProductoDigital> lista) {
        return new HashSet<>();
    }
    public Map<String, List<ProductoDigital>> agruparPorTipo(Collection<ProductoDigital> col) {
        return new HashMap<>();
    }
    public Map<String, Double> facturacionPorTipo(Collection<ProductoDigital> col) {
        return new HashMap<>();
    }
    public List<ProductoDigital> rankingPorPrecioFinal(Collection<ProductoDigital> col, int n) {
        return new ArrayList<>();
    }
    public List<ProductoDigital> buscarPorNombre(Collection<ProductoDigital> col, String substring) {
        return new ArrayList<>();
    }
    public Map<String, List<ProductoDigital>> agruparPorEtiquetas(Collection<ProductoDigital> col) {
        return new HashMap<>();
    }

    public List<ProductoDigital> filtrarProdcutosPorEtiqueta(Collection<ProductoDigital> col, String tagName) {
        return new ArrayList<>();
    }
    public double totalCarrito(Collection<ProductoDigital> col, List<String> skus) {
        return 0.0;
    }
    public static double redondear(double value) {
        return 0.0;
    }
}
