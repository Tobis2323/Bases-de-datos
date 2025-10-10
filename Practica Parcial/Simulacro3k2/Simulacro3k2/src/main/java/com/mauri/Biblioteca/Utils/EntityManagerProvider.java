package com.mauri.Biblioteca.Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static final String PERSISTENCE_UNIT = "simulacro";
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
