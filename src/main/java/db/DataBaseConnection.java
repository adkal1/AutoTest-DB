package db;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instance = null;
    private Connection connection;
    private final ISettingsFile environment = new JsonSettingsFile("testData.json");

    public DataBaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(environment.getValue("/urlMySql").toString(), environment.getValue("/username").toString(), environment.getValue("/password").toString());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static synchronized DataBaseConnection getInstance() {
        if (instance==null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return this.connection;
    }
}
