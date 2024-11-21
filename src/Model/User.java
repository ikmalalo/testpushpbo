/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ikmal
 */
public abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract boolean login();

    protected boolean authenticate(String tableName, String userColumn, String passColumn) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        try {
            String query = "SELECT * FROM " + tableName + " WHERE " + userColumn + " = ? AND " + passColumn + " = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login Gagal! " + e.getMessage());
            return false;
        }
    }
}
