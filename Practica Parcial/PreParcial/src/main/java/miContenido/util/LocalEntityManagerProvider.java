package miContenido.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LocalEntityManagerProvider {

    private static final String PERSISTENCE_UNIT = "paraElParcial";
    private static final EntityManagerFactory emf;

    static {
        // Primero se crea el esquema
        DBInitializer.recreateSchemaFromDdl();

        // Luego se inicializa la fábrica JPA
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
