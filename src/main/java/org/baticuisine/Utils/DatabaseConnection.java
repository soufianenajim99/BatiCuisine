package org.baticuisine.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/Baticuisine";
            String user = "baeldung";
            String password = "baeldung";
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return connection;
    }
}
