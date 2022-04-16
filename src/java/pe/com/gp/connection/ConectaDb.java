package pe.com.gp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @version 1.1
 * @author Acabello - Grupo Pana S.A.
 */
public final class ConectaDb {

    //private final String server = "desa";
    private final String server = "gp";

    public Connection connection() {
        Connection cn = null;
        String ip = ("gp".equals(server)) ? "192.168.253.180" : "192.168.253.108";
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + ip + ":1521:gp",
                    "comercial", "gp");

            // Oracle Cloud
            // cn = DriverManager.getConnection(
            // "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(HOST=129.156.113.106)(PORT=1521)(PROTOCOL=tcp))(CONNECT_DATA=(SERVICE_NAME=gp.590528563.oraclecloud.internal)))",
            // "comercial", "gp");
        } catch (SQLException e) {
        } catch (Exception e) {
        }
        return cn;
    }

    public ConectaDb() {
    }

    public String getServer() {
        return server;
    }
}
