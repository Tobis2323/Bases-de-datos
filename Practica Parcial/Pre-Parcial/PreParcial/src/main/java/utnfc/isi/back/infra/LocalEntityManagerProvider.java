package utnfc.isi.back.infra;

import javax.sql.DataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Construye un EntityManagerFactory/EntityManager usando el MISMO DataSource
 * que usamos para JDBC, según pide el enunciado.
 * - hibernate.hbm2ddl.auto = none (el DDL ya lo ejecuta DbInitializer)
 * - dialect H2
 * - show_sql opcional
 */
public final class LocalEntityManagerProvider {

    private static EntityManagerFactory emf;

    private LocalEntityManagerProvider() {}

    public static synchronized EntityManager getEntityManager() {
        if (emf == null) {
            DataSource ds = DataSourceProvider.get();

            Map<String, Object> props = new HashMap<>();
            // 1) Conectar JPA/Hibernate al mismo DataSource (no-JTA)
            props.put("jakarta.persistence.nonJtaDataSource", ds);
            // 2) Respetamos configuración del persistence.xml (hbm2ddl=none, dialect, etc.)
            //    Podrías sobreescribir propiedades aquí si necesitás

            // 3) Creamos el EMF con el nombre de unidad del persistence.xml
            emf = Persistence.createEntityManagerFactory("legosPU", props);
        }
        // 4) Devolvemos un EM nuevo en cada llamada (ligero; se cierra con .close())
        return emf.createEntityManager();
    }
}
