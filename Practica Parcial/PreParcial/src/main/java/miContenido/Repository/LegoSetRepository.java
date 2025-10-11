package miContenido.Repository;


import miContenido.model.LegoSet;
import miContenido.util.LocalEntityManagerProvider;

import java.util.List;

public class LegoSetRepository extends BaseRepository<LegoSet, Integer> {

    public LegoSetRepository() {
        super(LocalEntityManagerProvider.getEntityManager(), LegoSet.class);
    }

    public List<LegoSet> findByThemeName(String themeName) {
        String jpql = "SELECT ls FROM LegoSet ls WHERE ls.theme.name = :name";
        return em.createQuery(jpql, LegoSet.class)
                .setParameter("name", themeName)
                .getResultList();
    }

    // ✅ Nuevo: buscar por prefijo del nombre del set (case-insensitive)
    public List<LegoSet> findBySetNameStartingWithIgnoreCase(String prefix) {
        String jpql = "SELECT ls FROM LegoSet ls WHERE LOWER(ls.setName) LIKE :p";
        return em.createQuery(jpql, LegoSet.class)
                .setParameter("p", prefix.toLowerCase() + "%")
                .getResultList();
    }

    // ✅ Nuevo: traer todos los sets con sus relaciones (JOIN FETCH)
    public List<LegoSet> findAllWithRelations() {
        String jpql = "SELECT ls FROM LegoSet ls " +
                "JOIN FETCH ls.theme " +
                "JOIN FETCH ls.ageGroup " +
                "JOIN FETCH ls.country";
        return em.createQuery(jpql, LegoSet.class).getResultList();
    }
}
