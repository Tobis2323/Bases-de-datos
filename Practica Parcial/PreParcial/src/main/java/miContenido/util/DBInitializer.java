package miContenido.util;


import org.h2.tools.RunScript;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Inicializa/recrea el esquema ejecutando sql/ddl.sql con el runner oficial de H2.
 * Evita problemas de SPLIT manual por ';' (CRLF, espacios, comentarios, etc.).
 */
public final class DBInitializer {

    private static final String URL  = "jdbc:h2:mem:backdb;DB_CLOSE_DELAY=-1;MODE=LEGACY";
    private static final String USER = "sa";
    private static final String PASS = "";

    private static final String DDL_CLASSPATH = "/sql/ddl.sql";

    private DBInitializer() {}

    public static void recreateSchemaFromDdl() {
        try (Connection cn = DriverManager.getConnection(URL, USER, PASS)) {
            InputStream in = DBInitializer.class.getResourceAsStream(DDL_CLASSPATH);
            if (in == null) {
                throw new IllegalStateException("No se encontró el recurso " + DDL_CLASSPATH +
                        " en el classpath (¿está en src/main/resources/sql/ddl.sql?).");
            }

            try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                RunScript.execute(cn, reader);
            }

            try (PreparedStatement ps = cn.prepareStatement("SELECT COUNT(*) FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_COUNTRY_ID'")) {
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) == 0) {
                        throw new IllegalStateException("La secuencia SEQ_COUNTRY_ID no existe tras correr el DDL. " +
                                "Revisá el contenido del ddl.sql.");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando DDL con RunScript", e);
        }
    }
}
