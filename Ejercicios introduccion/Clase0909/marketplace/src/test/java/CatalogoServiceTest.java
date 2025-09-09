
import ar.edu.utnfrc.backend.exception.ItemNotFoundException;
import ar.edu.utnfrc.backend.model.*;
import ar.edu.utnfrc.backend.service.CatalogoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoServiceTest {

    static List<ProductoDigital> cargados;
    static Set<ProductoDigital> unicos;
    static CatalogoService service;

    @BeforeAll
    static void init() throws IOException {
        service = new CatalogoService();
        cargados = service.cargarDesdeResource("src\\main\\resources\\productos.csv");
        unicos = service.eliminarDuplicados(cargados);
        assertEquals(100, cargados.size(), "CSV debe tener 9 líneas (incluyendo duplicado)");
        assertEquals(99, unicos.size(), "Debe quedar 1 duplicado menos por SKU");
    }

    @Test
    void cargaPolimorficaYConteoPorTipo() {
        int ebooks = 0, apps = 0, cursos = 0;
        for (ProductoDigital p : unicos) {
            String tipo = p.getTipo();
            if ("EBOOK".equals(tipo)) ebooks++;
            else if ("APP".equals(tipo)) apps++;
            else if ("CURSOONLINE".equals(tipo)) cursos++;
        }
        assertEquals(33, ebooks);
        assertEquals(33, apps);
        assertEquals(33, cursos);
    }

    @Test
    void equalsHashCodePorSku() {
        ProductoDigital a = new Ebook("XYZ", "Test", 10.00, 100, Collections.emptyList());
        ProductoDigital b = new App("xyz", "Otro", 1.00, Plataforma.WEB, Collections.emptyList());
        Set<ProductoDigital> set = new HashSet<>();
        set.add(a);
        set.add(b);
        assertEquals(1, set.size(), "Mismo SKU debe considerarse igual (case-insensitive)");
    }

    @Test
    void top3PorPrecioFinal() {
        List<ProductoDigital> top3 = service.rankingPorPrecioFinal(unicos, 3);
        assertEquals(3, top3.size());
        assertEquals("A044", top3.get(0).getSku());
        assertEquals("A098", top3.get(1).getSku());
        assertEquals("A026", top3.get(2).getSku());
        assertEquals(113.53, top3.get(0).precioFinal(), 1e-2);
        assertEquals(108.37, top3.get(1).precioFinal(), 1e-2);
        assertEquals(105.59, top3.get(2).precioFinal(), 1e-2);
    }

    @Test
    void facturacionPorTipoYTotal() {
        Map<String, Double> f = service.facturacionPorTipo(unicos);
        assertEquals(666.834, f.get("EBOOK"), 1e-2);
        assertEquals(288.2245, f.get("APP"), 1e-2);
        assertEquals(1915.1325, f.get("CURSOONLINE"), 1e-2);

        double total = 0.0;
        for (double v : f.values()) total += v;
        assertEquals(2870.1910, total, 1e-2);
    }

    @Test
    void busquedaPorNombre() {
        List<ProductoDigital> r = service.buscarPorNombre(unicos, "Curso AWS Avanzado");
        assertEquals(1, r.size());
        assertEquals("A059", r.getFirst().getSku());
    }

    @Test
    void carritoValido() {
        double total = service.totalCarrito(unicos, Arrays.asList("A091","A072","A034"));
        assertEquals(22.12, total, 1e-2);
    }

    @Test
    void carritoConSkuInexistenteLanzaExcepcion() {
        assertThrows(ItemNotFoundException.class,
                () -> service.totalCarrito(unicos, Arrays.asList("A1", "A99")));
    }

    @Test
    void parseoDeEtiquetasPorProducto() {
        ProductoDigital pd = null;
        for (ProductoDigital p : unicos) {
            if ("A074".equals(p.getSku())) { pd = p; break; }
        }
        assertNotNull(pd);
        Set<String> nombres = new HashSet<>();
        for (Etiqueta t : pd.getEtiquetas()) nombres.add(t.getNombre());
        assertTrue(nombres.contains("spring"));
        assertTrue(nombres.contains("graphql"));
        assertTrue(nombres.contains("docker"));
    }

    @Test
    void agruparPorTagYBuscarPorTag() {
        Map<String, List<ProductoDigital>> porTag = service.agruparPorEtiquetas(unicos);
        assertTrue(porTag.containsKey("java"));
        assertEquals(5, porTag.get("java").size());

        List<ProductoDigital> devops = service.filtrarProdcutosPorEtiqueta(unicos, "devops");
        assertEquals(5, devops.size());
    }
}

