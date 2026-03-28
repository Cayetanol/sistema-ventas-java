package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {

private static final String URL = "jdbc:sqlite:C:/Users/pikah/Desktop/consola retro/consoleRetro/ventas.db";

public static Connection conectar() throws SQLException {

    try {
        // Carga manual del driver
        Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
        throw new SQLException("No se pudo cargar el driver SQLite", e);
    }

    return DriverManager.getConnection(URL);
}

}
