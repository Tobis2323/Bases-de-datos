package miContenido.Repository;


import miContenido.model.LegoSet;
import miContenido.util.EntityManagerProvider;

import java.util.List;

public class LegoSetRepository extends BaseRepository<LegoSet, Integer> {

    public LegoSetRepository() {
        super(EntityManagerProvider.getEntityManager(), LegoSet.class);
    }

    public List<LegoSet> findByTheme(String theme) {
        String jpql = "SELECT ls FROM LegoSet ls WHERE ls.theme = :theme";
        return em.createQuery(jpql, LegoSet.class)
                .setParameter("theme", theme)
                .getResultList();
    }
}
