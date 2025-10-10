package miContenido.Service;

import miContenido.Repository.CountryRepository;
import miContenido.model.Country;

import java.util.List;

public class CountryService {
    private final CountryRepository countryRepository;
    public CountryService() {
        this.countryRepository = new CountryRepository();
    }
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id);
    }
    public void saveCountry(Country country) {
        countryRepository.save(country);
    }
    public void updateCountry(Country country) {
        countryRepository.update(country);
    }
    public void deleteCountry(Country country) {
        countryRepository.delete(country);
    }

    public Integer getThemeById(Integer id) {
        Country country = countryRepository.findById(id);
        return country != null ? country.getIdCountry() : null;
    }


    // ✅ NUEVO MÉTODO PARA GUARDAR UNA LISTA ENTERA
    public void agregarArrayList(List<Country> countries) {
        for (Country country : countries) {
            countryRepository.save(country);
        }
    }
    public Country findOrCreateByName(String countryName) {
        // Buscar si existe un country con ese nombre
        List<Country> existentes = countryRepository.findAll();
        for (Country country : existentes) {
            if (country.getName().equalsIgnoreCase(countryName)) {
                return country;
            }
        }
        // Si no existe, lo creo, lo guardo y lo retorno
        Country nuevo = new Country();
        nuevo.setName(countryName);
        countryRepository.save(nuevo);
        return nuevo;
    }
}
