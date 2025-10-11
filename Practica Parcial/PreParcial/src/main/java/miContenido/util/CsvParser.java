package miContenido.util;

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

        try (CSVReader reader = new CSVReaderBuilder(new java.io.FileReader(path.toFile()))
                .withCSVParser(new com.opencsv.CSVParserBuilder().withSeparator(';').build())
                .build()) {
            reader.readNext(); // Saltar la cabecera
            String[] fila;
            int rowNum = 1; // contando desde después del header
            while ((fila = reader.readNext()) != null) {
                rowNum++;
                // Ignorar filas con cualquier campo vacío
                boolean tieneCampoVacio = false;
                for (String campo : fila) {
                    if (campo == null || campo.trim().isEmpty()) {
                        tieneCampoVacio = true;
                        break;
                    }
                }
                if (tieneCampoVacio) {
                    //System.out.println("Fila " + rowNum + " ignorada: campo vacío");
                    continue;
                }
                // Saltar filas incompletas
                if (fila.length < 13) { // 13 columnas según el nuevo CSV
                    System.out.println("Fila " + rowNum + " ignorada: columnas insuficientes (" + fila.length + ")");
                    continue;
                }
                // Mapear columnas según el nuevo CSV
                String ageCode = safeTrim(fila[0]);
                double listPrice = Double.parseDouble(fila[1].trim());
                Integer numReviews = parseIntFlexible(fila[2].trim(), "num_reviews", rowNum);
                Integer pieceCount = parseIntFlexible(fila[3].trim(), "piece_count", rowNum);
                String prodDesc = safeTrim(fila[5]);
                Integer prodId = parseIntFlexible(fila[6].trim(), "prod_id", rowNum);
                String reviewDifficulty = safeTrim(fila[7]);
                String setName = safeTrim(fila[8]);
                double starRating = Double.parseDouble(fila[9].trim());
                String themeName = safeTrim(fila[10]);
                String countryCode = safeTrim(fila[12]);

                // Si alguna conversión numérica obligatoria falló, saltar fila
                if (numReviews == null || pieceCount == null || prodId == null) {
                    System.out.println("Fila " + rowNum + " ignorada: conversión numérica inválida");
                    continue;
                }

                Theme theme = themeService.findOrCreateByName(themeName);
                AgeGroup ageGroup = ageGroupService.findOrCreateByCode(ageCode);
                Country country = countryService.getCountryByCode(countryCode); // Solo buscar, no crear

                if (country == null) {
                    System.out.println("Fila " + rowNum + " ignorada: país no encontrado");
                    continue;
                }

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
                objLegoSet.setNumReviews(numReviews);
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

    private static Integer parseIntFlexible(String value, String field, int rowNum) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex1) {
            try {
                double d = Double.parseDouble(value);
                return (int) Math.round(d);
            } catch (NumberFormatException ex2) {
                System.out.println("Fila " + rowNum + " ignorada: valor inválido en '" + field + "' -> '" + value + "'");
                return null;
            }
        }
    }
}
