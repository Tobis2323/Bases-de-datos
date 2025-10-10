package miContenido;

import jakarta.persistence.EntityManager;
import miContenido.Repository.CountryRepository;
import miContenido.Repository.ThemeRepository;
import miContenido.Service.CountryService;
import miContenido.Service.LegoSetService;
import miContenido.Service.ThemeService;
import miContenido.model.Country;
import miContenido.model.LegoSet;
import miContenido.model.Theme;
import miContenido.util.CsvParser;
import miContenido.util.EntityManagerProvider;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        // 1️⃣ Obtener EntityManager
        EntityManager em = EntityManagerProvider.getEntityManager();
        System.out.println("✅ Conexión establecida correctamente.");

        // 2️⃣ Parsear CSV
        URL fileUrl = App.class.getResource("/sql/legosets.csv");
        if (fileUrl == null) {
            System.err.println("No se encontró archivo.csv en resources");
            return;
        }
//        Path path = Paths.get(fileUrl.toURI());
//        CsvParser parser = new CsvParser();
//        List<LegoSet> legoSet = parser.parsearLegoSets(path);

        CountryService brai = new CountryService();
        List<Country> braian2 = brai.getAllCountries();

//        LegoSetService legoSetService = new LegoSetService();
//        legoSetService.agregarArrayList(legoSet);
//
//        List<LegoSet> var1 = legoSetService.getAllCountries();
//
//        System.out.println(legoSetService.getCountryById(1));



        // 4. Mostrar los temas en consola
        if (braian2.isEmpty()) {
            System.out.println("No hay temas guardados en la base de datos.");
        } else {
            for (Country var : braian2) {
                System.out.println("Tema: " + var.getName());
            }
        }








        // 5️⃣ Cerrar recursos
            em.close();
        EntityManagerProvider.close();

    }
}
