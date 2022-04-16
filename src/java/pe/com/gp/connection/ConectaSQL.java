/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.connection;

import java.sql.Connection;

/**
 *
 * @author Jpalomino
 */
public class ConectaSQL {
    private final String server = "produccion";
    private final String URL = "jdbc:sqlserver://";
    private static final String USER = "golden";
    private static final String PASS = "golden";
    //private static final String PASS = "TrinetCorp2008$$";
    private static final String PORT = "1433";    
    private static final String DB = "Golden";

    private static final String IP = "192.168.198.103";
 //   private static final String IP = "192.168.253.111";
    //private static final String IP = "192.168.1.23";

    private String getConnectionUrl() {
        return URL + IP + ":" + PORT + ";databaseName=" + DB;
    }

    public Connection connection() {
        Connection connection = null;
        try {
                   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2005 Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            //2012 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            connection = java.sql.DriverManager.getConnection(getConnectionUrl(), USER, PASS);
            if (connection != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : "
                    + e.getMessage());
        }
        return connection;
    }
    
    public String getServer() {
        return server;
    }
}
