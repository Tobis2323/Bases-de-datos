package utnfc.isi.back.infra;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * Ejecuta el script resources/sql/ddl_legos.sql desde el classpath.
 * - Abre conexión (H2 memoria)
 * - Lee el archivo completo
 * - Lo parte por ';' y ejecuta cada sentencia
 * - Cierra recursos con try-with-resources
 */
public class DbInitializer {

    public static void run() throws Exception {
        // 1) Obtenemos el DataSource compartido
        DataSource ds = DataSourceProvider.get();

        // 2) Abrimos conexión y creamos un Statement (try-with-resources cierra todo)
        try (Connection cn = ds.getConnection();
             Statement st = cn.createStatement()) {

            // 3) Leemos el recurso ddl_legos.sql desde /resources/sql/
            String resourcePath = "/sql/ddl_legos.sql"; // ruta dentro del classpath
            String sqlText = readClasspath(resourcePath); // archivo completo en un String

            // 4) Partimos por ';' respetando bloques simples
            for (String raw : sqlText.split(";")) {
                String sql = raw.trim();
                if (sql.isEmpty()) continue; // 5) saltamos líneas vacías
                st.execute(sql);             // 6) ejecutamos la sentencia
            }
        }
    }

    // 7) Utilitario para leer un archivo del classpath como String
    private static String readClasspath(String path) throws Exception {
        try (InputStream is = DbInitializer.class.getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
}
