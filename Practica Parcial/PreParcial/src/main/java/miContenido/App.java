package miContenido;

import jakarta.persistence.EntityManager;
import miContenido.Service.CountryService;
import miContenido.Service.LegoSetService;
import miContenido.model.Country;
import miContenido.model.LegoSet;
import miContenido.util.CsvParser;
import miContenido.util.LocalEntityManagerProvider;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        // 1️⃣ Obtener EntityManager
        EntityManager em = LocalEntityManagerProvider.getEntityManager();
        System.out.println("✅ Conexión establecida correctamente.");

        // 2️⃣ Parsear CSV de ejemplo
        URL fileUrl = App.class.getResource("/sql/legosets_sample.csv");
        if (fileUrl == null) {
            System.err.println("No se encontró legosets_sample.csv en resources/sql");
            em.close();
            LocalEntityManagerProvider.close();
            return;
        }
        Path path = Paths.get(fileUrl.toURI());
        CsvParser parser = new CsvParser();
        List<LegoSet> legoSets = parser.parsearLegoSets(path);

        // 3️⃣ Persistir los LegoSet
        LegoSetService legoSetService = new LegoSetService();
        legoSetService.agregarArrayList(legoSets);

        // 4️⃣ Mostrar resumen
        CountryService countryService = new CountryService();
        List<Country> countries = countryService.getAllCountries();

        System.out.println("Países (seed): " + countries.size());
        for (int i = 0; i < Math.min(5, countries.size()); i++) {
            System.out.println(" - " + countries.get(i).getCode() + " " + countries.get(i).getName());
        }

        List<LegoSet> saved = legoSetService.getAllLegoSets();
        System.out.println("Sets cargados por CSV: " + saved.size());
        for (LegoSet ls : saved) {
            System.out.println(" * [" + ls.getProdId() + "] " + ls.getSetName() + " ($" + ls.getListPrice() + ")");
        }

        // ➕ Listar todos los sets con relaciones (JOIN FETCH)
        List<LegoSet> withRelations = legoSetService.findAllWithRelations();
        System.out.println("\nSets con detalles (Theme, AgeGroup, Country): " + withRelations.size());
        for (LegoSet ls : withRelations) {
            String themeName = (ls.getTheme() != null ? ls.getTheme().getName() : "-");
            String ageCode = (ls.getAgeGroup() != null ? ls.getAgeGroup().getCode() : "-");
            String countryInfo = (ls.getCountry() != null ? (ls.getCountry().getCode() + " " + ls.getCountry().getName()) : "-");
            System.out.println(" - [" + ls.getProdId() + "] " + ls.getSetName() +
                    " | Theme=" + themeName +
                    " | Age=" + ageCode +
                    " | Country=" + countryInfo +
                    " | $" + ls.getListPrice());
        }

        // 7️⃣ Cerrar recursos
        em.close();
        LocalEntityManagerProvider.close();
    }
}
