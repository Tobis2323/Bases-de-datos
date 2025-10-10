package utnfc.isi.back;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utnfc.isi.back.infra.DbInitializer;
import utnfc.isi.back.infra.LocalEntityManagerProvider;

/**
 * Orquesta:
 * 1) Ejecuta DbInitializer (crea tablas + seeds del ddl_legos.sql)
 * 2) Crea EntityManager
 * 3) Hace un smoke test: contar países y/o insertar/leer algo simple
 */
public class App {
    public static void main(String[] args) {
        try {
            System.out.println("== Iniciando DB H2 en memoria ==");
            DbInitializer.run(); // 1) ejecutar DDL + seeds

            System.out.println("== Construyendo EntityManager ==");
            EntityManager em = LocalEntityManagerProvider.getEntityManager(); // 2) EM

            // 3) Smoke test: contar registros en COUNTRIES
            Long count;
            em.getTransaction().begin();
            try {
                Query q = em.createQuery("select count(c) from Country c", Long.class);
                count = (Long) q.getSingleResult();
                em.getTransaction().commit();
            } catch (Exception ex) {
                em.getTransaction().rollback();
                throw ex;
            } finally {
                em.close();
            }

            System.out.println("[OK] DB init + JPA mappings verificados. COUNTRIES=" + count);
        } catch (Exception e) {
            System.err.println("[FAIL] " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
