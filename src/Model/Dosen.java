/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ikmal
 */
import Database.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;

public class Dosen extends User {

    private String namaDosen;

    public Dosen(String NIP, String password) {
        super(NIP, password);
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    @Override
    public boolean login() {
        if (authenticate("dosen", "NIP", "Password")) {
            JOptionPane.showMessageDialog(null, "Login Berhasil!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login Gagal! NIP atau password salah.");
            return false;
        }
    }

    public static void comboboxdosen(JComboBox<String> comboBoxDosen) {
        comboBoxDosen.removeAllItems(); 

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT nama_dosen FROM dosen");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                comboBoxDosen.addItem(rs.getString("nama_dosen"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data dosen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
