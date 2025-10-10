package main.java.utnfc.isi.back.infra;

import org.h2.jdbcx.JdbcDataSource; // H2 DataSource para pruebas
import javax.sql.DataSource; // Interfaz estándar para DataSource

public final class DataSourceProvider {
    private static final DataSource DS;

    static {
        JdbcDataSource h2 = new JdbcDataSource();
        h2.setURL("jdbc:h2:mem:legos;DB_CLOSE_DELAY=-1;MODE=LEGACY");
        h2.setUser("sa");
        h2.setPassword("");
        DS = h2;
    }
    private DataSourceProvider() {}

    public static DataSource get() {
        return DS;
    }
}
