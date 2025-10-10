package miContenido.util;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import miContenido.Service.AgeGroupService;
import miContenido.Service.CountryService;
import miContenido.Service.ThemeService;


import miContenido.model.Theme;
import miContenido.model.AgeGroup;
import miContenido.model.Country;
import miContenido.model.LegoSet;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public List<LegoSet> parsearLegoSets(Path path) {

        System.out.println("Leyendo archivo CSV desde: " + path.toString());
        ThemeService themeService = new ThemeService();
        AgeGroupService ageGroupService = new AgeGroupService();
        CountryService countryService = new CountryService();

        List<LegoSet> vecLegoSets = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new java.io.FileReader(path.toFile()));) {
            reader.readNext(); // Saltar la cabecera
            String[] fila;
            while ((fila = reader.readNext()) != null) {

                // --- Ahora fila[] tiene todos los valores separados por ; ---

                System.out.println("Aqui");
                System.out.println(fila[1]);
                Integer prodId = Integer.parseInt(fila[0]);
                String setName = fila[1];
                String prodDesc = fila[2];
                String themeName = fila[3]; // theme
                String ageCode = fila[4]; // age group
                String reviewDifficulty = fila[5];
                int pieceCount = Integer.parseInt(fila[6]);
                double starRating = Double.parseDouble(fila[7]);
                double listPrice = Double.parseDouble(fila[8]);
                String countryName = fila[9]; // country



                Theme theme = themeService.findOrCreateByName(themeName);
                AgeGroup ageGroup = ageGroupService.findOrCreateByCode(ageCode);
                Country country = countryService.findOrCreateByName(countryName);

                // Crear objeto principal
                LegoSet objLegoSet = new LegoSet();
                objLegoSet.setProdId(prodId);
                objLegoSet.setSetName(setName);
                objLegoSet.setProdDesc(prodDesc);
                objLegoSet.setTheme(theme);
                objLegoSet.setAgeGroup(ageGroup);
                objLegoSet.setReviewDifficulty(reviewDifficulty);
                objLegoSet.setPieceCount(pieceCount);
                objLegoSet.setStarRating(BigDecimal.valueOf(starRating));
                objLegoSet.setListPrice(BigDecimal.valueOf(listPrice));
                objLegoSet.setCountry(country);

                vecLegoSets.add(objLegoSet);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error leyendo CSV", e);
        }

        return vecLegoSets;
    }
}
