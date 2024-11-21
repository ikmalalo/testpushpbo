/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author ikmal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance; 
    private Connection connection; 
    private final static String DB_HOST = "localhost";
    private final static String DB_NAME = "e-surat";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "";
    private final static String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    // Constructor privat
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load driver: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
            instance.connect();
        }
        return instance;
    }

    private void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("Database connected!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }

    // Metode untuk memutuskan koneksi
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database disconnected!");
                connection = null; // Reset koneksi setelah diputuskan
            } catch (SQLException e) {
                System.out.println("Failed to disconnect database: " + e.getMessage());
            }
        }
    }

    // Metode untuk mendapatkan koneksi
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Reconnecting to database...");
                connect(); // Reconnect jika koneksi null atau ditutup
            }
        } catch (SQLException e) {
            System.out.println("Error checking connection: " + e.getMessage());
        }
        return connection;
    }
}