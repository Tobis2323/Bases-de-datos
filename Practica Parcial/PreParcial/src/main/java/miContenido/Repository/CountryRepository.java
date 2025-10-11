package miContenido.Repository;


import jakarta.persistence.NoResultException;
import miContenido.model.Country;
import miContenido.util.LocalEntityManagerProvider;

public class CountryRepository extends BaseRepository<Country, Integer> {
    public CountryRepository() {
        super(LocalEntityManagerProvider.getEntityManager(), Country.class);
    }

    public Country findByCode(String code) {
        String jpql = "SELECT c FROM Country c WHERE c.code = :code";
        try {
            return em.createQuery(jpql, Country.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
