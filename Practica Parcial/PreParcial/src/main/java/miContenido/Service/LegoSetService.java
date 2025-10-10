package miContenido.Service;

import miContenido.Repository.LegoSetRepository;
import miContenido.model.Country;
import miContenido.model.LegoSet;
import miContenido.model.Theme;

import java.util.List;

public class LegoSetService {
    public final LegoSetRepository legoSetRepository;

    public LegoSetService() {
        this.legoSetRepository = new LegoSetRepository();
    }

    // Métodos para gestionar LegoSet

    public List<LegoSet> getAllCountries() {
        return legoSetRepository.findAll();
    }
    public LegoSet getCountryById(Integer id) {
        return legoSetRepository.findById(id);
    }
    public void saveCountry(LegoSet legoSet) {
        legoSetRepository.save(legoSet);
    }
    public void updateCountry(LegoSet legoSet) {
        legoSetRepository.update(legoSet);
    }
    public void deleteCountry(LegoSet legoSet) {
        legoSetRepository.delete(legoSet);
    }
     public List<LegoSet> getAllLegoSets() {
         return legoSetRepository.findAll();
     }


     public void agregarArrayList(List<LegoSet> legoSets) {
         for (LegoSet legoSet : legoSets) {
             legoSetRepository.save(legoSet);
         }
     }


}
