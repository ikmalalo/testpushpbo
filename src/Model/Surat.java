package Model;

import Database.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Surat {
    private final String ID_Mahasiswa;
    private final String Isi_Surat;
    private final String Jenis_Surat;
    private final File FileSurat;
    private String Tag;
    private String Status_Surat;
    private String ID_Surat;

    public Surat(String ID_Mahasiswa, String Isi_Surat, String Jenis_Surat, File FileSurat) {
        this.ID_Mahasiswa = ID_Mahasiswa;
        this.Isi_Surat = Isi_Surat;
        this.Jenis_Surat = Jenis_Surat;
        this.FileSurat = FileSurat;
        this.Tag = Tag = "Tag Kosong";
        this.Status_Surat = "Pending";
    }

    public String getID_Mahasiswa() {
        return ID_Mahasiswa;
    }

    public String getIsi_Surat() {
        return Isi_Surat;
    }

    public String getJenis_Surat() {
        return Jenis_Surat;
    }

    public File getFileSurat() {
        return FileSurat;
    }

    public String getTag() {
        return Tag;
    }

    public String getStatus_Surat() {
        return Status_Surat;
    }

    public String getID_Surat() {
        return ID_Surat;
    }

    public void setID_Surat(String ID_Surat) {
        this.ID_Surat = ID_Surat;
    }

    public void mengirimSurat() throws FileNotFoundException, IOException {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return;
        }

        if (!this.getFileSurat().exists() || !this.getFileSurat().isFile()) {
            JOptionPane.showMessageDialog(null, "File surat kosong, Harus isi Suratnya");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(this.getFileSurat())) {
            String query = "INSERT INTO surat (ID_Mahasiswa, Isi_Surat, Jenis_Surat, Status_Surat, FileSurat, Tag, Tgl_kirim) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, this.getID_Mahasiswa());
            preparedStatement.setString(2, this.getIsi_Surat());
            preparedStatement.setString(3, this.getJenis_Surat());
            preparedStatement.setString(4, this.getStatus_Surat());
            preparedStatement.setBinaryStream(5, fileInputStream, (int) this.getFileSurat().length());
            preparedStatement.setString(6, this.getTag());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.setID_Surat(generatedKeys.getString(1));
                }
                JOptionPane.showMessageDialog(null, "Surat berhasil dikirim.");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal mengirim surat. Periksa data Anda.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }
    }

    public boolean updateFileSurat() throws FileNotFoundException {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return false;
        }

        if (this.getID_Surat() == null || this.getID_Surat().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Surat tidak valid.");
            return false;
        }

        String query = "UPDATE surat SET FileSurat = ? WHERE ID_Surat = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            if (this.getFileSurat() != null) {
                // Validasi panjang file
                if (this.getFileSurat().length() == 0) {
                    JOptionPane.showMessageDialog(null, "File kosong atau tidak dapat dibaca.");
                    return false;
                }

                FileInputStream fileInputStream = new FileInputStream(this.getFileSurat());
                preparedStatement.setBinaryStream(1, fileInputStream, (int) this.getFileSurat().length());
            } else {
                preparedStatement.setNull(1, java.sql.Types.BLOB);
            }

            preparedStatement.setString(2, this.getID_Surat());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "File Surat berhasil diperbarui.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Gagal memperbarui file surat. ID Surat tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui file: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteSurat(String ID_Surat) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return false;
        }
        try {
            String deleteQuery = "DELETE FROM surat WHERE ID_Surat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setString(1, ID_Surat);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Surat berhasil dihapus.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus surat. ID Surat tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean setStatusSurat(String ID_Surat, String newStatus) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return false;
        }
        try {
            String selectQuery = "SELECT Status_Surat FROM surat WHERE ID_Surat = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, ID_Surat);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String currentStatus = resultSet.getString("Status_Surat");

                if (currentStatus.equals("Pending") || (currentStatus.equals("Diterima") && newStatus.equals("Ditolak")) || (currentStatus.equals("Ditolak") && newStatus.equals("Diterima"))) {
                    String updateQuery = "UPDATE surat SET Status_Surat = ? WHERE ID_Surat = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newStatus);
                    preparedStatement.setString(2, ID_Surat);

                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                } else {
                    JOptionPane.showMessageDialog(null, "Status surat tidak dapat diubah.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Surat tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean setTagSurat(String ID_Surat, String newTag) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return false;
        }
        try {
            String selectQuery = "SELECT Tag FROM surat WHERE ID_Surat = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, ID_Surat);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String currentTag = resultSet.getString("Tag");

                if (!currentTag.equals(newTag)) {
                    String updateQuery = "UPDATE surat SET Tag = ? WHERE ID_Surat = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newTag);
                    preparedStatement.setString(2, ID_Surat);

                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                } else {
                    JOptionPane.showMessageDialog(null, "Tag sudah bernilai " + newTag + ".");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Surat tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateIsiSurat(String ID_Surat, String newIsiSurat) {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal.");
            return false;
        }
        try {
            String query = "UPDATE surat SET Isi_Surat = ? WHERE ID_Surat = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, newIsiSurat);
            preparedStatement.setString(2, ID_Surat);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Gagal memperbarui isi surat. ID Surat tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }
}
