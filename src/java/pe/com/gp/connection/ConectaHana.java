package pe.com.gp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @version 1.1
 * @author Acabello - Grupo Pana S.A.
 */
public final class ConectaHana {

    private final String dbUser = "SYSTEM";
    private final String dbPassword = "Passw0rd";
    private final String dbIP = "192.168.198.103";
    private final String dbPort = "30015";
    private final String dbSchema = "B1H_PANA_COMPRAS";

    public Connection connection() {
        Connection cn = null;
        try {
            Class.forName("com.sap.db.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection("jdbc:sap://" + dbIP + ":" + dbPort + "/?currentschema=" + dbSchema, dbUser, dbPassword);
        } catch (SQLException e) {
        } catch (Exception e) {
        }
        return cn;
    }

    public ConectaHana() {
    }
}
