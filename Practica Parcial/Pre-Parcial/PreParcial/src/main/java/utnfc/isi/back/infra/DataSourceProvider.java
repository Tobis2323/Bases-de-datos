package utnfc.isi.back.infra;

import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

/**
 * Provee un DataSource H2 en memoria, compartido por JDBC y JPA.
 * URL sugerida en el enunciado: jdbc:h2:mem:legos;DB_CLOSE_DELAY=-1
 */
public final class DataSourceProvider {

    // 1) Hacemos singleton simple para compartir el mismo DataSource
    private static DataSource dataSource;

    // 2) Constructor privado para que no se instancie
    private DataSourceProvider() {}

    // 3) Devuelve siempre la misma instancia (lazy init)
    public static DataSource get() {
        if (dataSource == null) {
            JdbcDataSource ds = new JdbcDataSource();
            // 4) BD en memoria llamada "legos", que no se cierra al terminar la conexión
            ds.setURL("jdbc:h2:mem:legos;DB_CLOSE_DELAY=-1");
            // 5) Usuario/clave no obligatorios para H2 en memoria; se pueden dejar vacíos
            ds.setUser("sa");
            ds.setPassword("");
            dataSource = ds;
        }
        return dataSource;
    }
}
