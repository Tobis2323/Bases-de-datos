package miContenido.Repository;


import miContenido.model.Country;
import miContenido.util.EntityManagerProvider;

public class CountryRepository extends BaseRepository<Country, Integer> {
    public CountryRepository() {
        super(EntityManagerProvider.getEntityManager(), Country.class);
    }

}
