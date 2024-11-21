/*
 0* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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

public class Mahasiswa extends User {

    private String NIM;
    private String Nama_Mahasiswa;
    private static String currentID_Mahasiswa;

    public Mahasiswa(String NIM, String Nama_Mahasiswa, String password) {
        super(NIM, password);
        this.NIM = NIM;
        this.Nama_Mahasiswa = Nama_Mahasiswa;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama_Mahasiswa() {
        return Nama_Mahasiswa;
    }

    public static String getCurrentID_Mahasiswa() {
        return currentID_Mahasiswa;
    }

    @Override
    public boolean login() {
        if (authenticate("mahasiswa", "NIM", "password")) {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            try {
                String query = "SELECT ID_Mahasiswa FROM mahasiswa WHERE NIM = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, this.username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    currentID_Mahasiswa = resultSet.getString("ID_Mahasiswa");
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    return true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Login Gagal! " + e.getMessage());
            }
        }
        return false;
    }

}
