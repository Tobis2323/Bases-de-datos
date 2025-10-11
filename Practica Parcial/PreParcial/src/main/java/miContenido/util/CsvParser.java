package miContenido.util;

import com.opencsv.CSVReader;
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

        try (CSVReader reader = new CSVReader(new java.io.FileReader(path.toFile()))) {
            reader.readNext(); // Saltar la cabecera
            String[] fila;
            int rowNum = 1; // contando desde después del header
            while ((fila = reader.readNext()) != null) {
                rowNum++;
                if (fila.length == 0) {
                    // línea vacía
                    continue;
                }
                // Saltar filas incompletas
                if (fila.length < 10) {
                    System.out.println("Fila " + rowNum + " ignorada: columnas insuficientes (" + fila.length + ")");
                    continue;
                }
                // Validar primera columna (PROD_ID)
                if (fila[0] == null || fila[0].trim().isEmpty()) {
                    System.out.println("Fila " + rowNum + " ignorada: PROD_ID vacío");
                    continue;
                }

                Integer prodId = Integer.parseInt(fila[0].trim());
                String setName = safeTrim(fila[1]);
                String prodDesc = safeTrim(fila[2]);
                String themeName = safeTrim(fila[3]);
                String ageCode = safeTrim(fila[4]);
                String reviewDifficulty = safeTrim(fila[5]);
                int pieceCount = Integer.parseInt(fila[6].trim());
                double starRating = Double.parseDouble(fila[7].trim());
                double listPrice = Double.parseDouble(fila[8].trim());
                String countryCode = safeTrim(fila[9]);

                Theme theme = themeService.findOrCreateByName(themeName);
                AgeGroup ageGroup = ageGroupService.findOrCreateByCode(ageCode);
                Country country = countryService.findOrCreateByCode(countryCode);

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

    private static String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
