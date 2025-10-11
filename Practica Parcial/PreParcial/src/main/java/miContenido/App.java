package miContenido;

import jakarta.persistence.EntityManager;
import miContenido.Service.LegoSetService;
import miContenido.Service.ThemeService;
import miContenido.Service.AgeGroupService;
import miContenido.model.LegoSet;
import miContenido.model.AgeGroup;
import miContenido.model.CountryCostRating;
import miContenido.util.CsvParser;
import miContenido.util.LocalEntityManagerProvider;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        // 1️⃣ Obtener EntityManager
        EntityManager em = LocalEntityManagerProvider.getEntityManager();
        System.out.println("✅ Conexión establecida correctamente.");

        // Localizar CSV en el classpath
        URL fileUrl = App.class.getResource("/sql/lego_sets_data.csv");
        if (fileUrl == null) {
            System.err.println("No se encontró lego_sets_data.csv en resources/sql");
            em.close();
            LocalEntityManagerProvider.close();
            return;
        }
        Path path = Paths.get(fileUrl.toURI());

        // Servicios
        LegoSetService legoSetService = new LegoSetService();
        ThemeService themeService = new ThemeService();
        AgeGroupService ageGroupService = new AgeGroupService();

        // Importar desde CSV
        CsvParser parser = new CsvParser();
        List<LegoSet> aInsertar = parser.parsearLegoSets(path);
        legoSetService.agregarArrayList(aInsertar);
        System.out.println("✅ CSV leido correctamente.");

        // Consultas simples sobre la BD
        List<LegoSet> sets = legoSetService.getAllLegoSets();
        List<AgeGroup> ageGroups = ageGroupService.getAllAgeGroups();
        int cantTematicas = themeService.getAllThemes().size();

        // Salida simple requerida
        System.out.println("\n=== Resultado de la importación ===");
        System.out.println("Importación finalizada usando JPA desde lego_sets_data.csv.");

        System.out.println("\n=== Cantidad de Sets en la base de datos ===");
        System.out.println(sets.size());

        System.out.println("\n=== Cantidad de Rangos de edad distintos ===");
        System.out.print(ageGroups.size() + " rangos");
        // Mostrar los códigos de rango de forma simple, separados por coma
        if (!ageGroups.isEmpty()) {
            String codigos = ageGroups.stream()
                    .map(AgeGroup::getCode)
                    .distinct()
                    .collect(Collectors.joining(", "));
            System.out.println(": " + codigos);
        } else {
            System.out.println();
        }

        System.out.println("\n=== Cantidad de Temáticas distintas ===");
        System.out.println(cantTematicas);

        // Ranking de los 5 países con menor costo/valoración promedio
        List<CountryCostRating> top5 = legoSetService.findTopCountriesByAvgCostPerStar(5);
        System.out.println("\n=== Ranking de los 5 Países con la relación costo/valoración más baja ===");
        if (top5.isEmpty()) {
            System.out.println("(No hay sets disponibles por país)");
        } else {
            int rank = 1;
            for (CountryCostRating r : top5) {
                // Formateo a 2 decimales
                String avg = String.format("%.2f", r.getAvgCostPerStar());
                System.out.println("#" + rank + " " + r.getCode() + " " + r.getName() + " -> $" + avg + "/estrella");
                rank++;
            }
        }

        em.close();
        LocalEntityManagerProvider.close();
    }
}
