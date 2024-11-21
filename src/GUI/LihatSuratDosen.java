package GUI;

import Model.Surat;
import Database.DatabaseConnection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Connection;
import java.awt.Desktop;
import java.io.File;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.FileOutputStream;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ikmal
 */
public class LihatSuratDosen extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public LihatSuratDosen() {
        initComponents();
        setLocationRelativeTo(null);
        comboboxpilihjenissurat.removeAllItems();
        comboboxpilihjenissurat.addItem("Semua");
        comboboxpilihjenissurat.addItem("Surat Maaf");
        comboboxpilihjenissurat.addItem("Surat Konsul");
        comboboxpilihjenissurat.addItem("Surat Pertemuan");
        tablem((String) comboboxpilihjenissurat.getSelectedItem());
    }

    public void tablem(String jenisSuratFilter) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableModel.addColumn("ID Surat");
        tableModel.addColumn("Nama Mahasiswa");
        tableModel.addColumn("Isi Surat");
        tableModel.addColumn("Jenis Surat");
        tableModel.addColumn("Status Surat");
        tableModel.addColumn("Tgl Kirim");
        tableModel.addColumn("File Surat");

        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            System.out.println("Database connection is null.");
            return;
        }
        System.out.println("Database connection established.");

        try {
            String query = "SELECT surat.ID_Surat, surat.ID_Mahasiswa, surat.Isi_Surat, surat.Jenis_Surat, surat.Status_Surat, "
                    + "surat.Tgl_kirim, surat.FileSurat, mahasiswa.Nama_Mahasiswa "
                    + "FROM surat "
                    + "JOIN mahasiswa ON surat.ID_Mahasiswa = mahasiswa.ID_Mahasiswa";

            if (!"Semua".equals(jenisSuratFilter)) {
                query += " WHERE surat.Jenis_Surat = ?";
            }
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            if (!"Semua".equals(jenisSuratFilter)) {
                preparedStatement.setString(1, jenisSuratFilter);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Query executed successfully, fetching data");

            while (resultSet.next()) {
                String idSurat = resultSet.getString("ID_Surat");
                String namaMahasiswa = resultSet.getString("Nama_Mahasiswa");
                String isiSurat = resultSet.getString("Isi_Surat");
                String jenisSurat = resultSet.getString("Jenis_Surat");
                String statusSurat = resultSet.getString("Status_Surat");
                String tglKirim = resultSet.getString("Tgl_kirim"); 
                byte[] fileBlob = resultSet.getBytes("FileSurat");
                String outputFilePath = "path/to/save/" + idSurat + "_surat.pdf";
                File outputFile = new File(outputFilePath);
                File outputDir = new File("path/to/save");

                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }

                if (fileBlob != null) {
                    try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                        fileOutputStream.write(fileBlob);
                        System.out.println("File saved: " + outputFilePath);
                        tableModel.addRow(new Object[]{
                            idSurat,
                            namaMahasiswa,
                            isiSurat,
                            jenisSurat,
                            statusSurat,
                            tglKirim,
                            outputFilePath
                        });
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error menyimpan file " + e.getMessage());
                        System.out.println("Error menyimpan file " + e.getMessage());
                    }
                }
            }
            tablem.setModel(tableModel);
            System.out.println("Data loaded into table successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal! " + e.getMessage());
            System.out.println("SQL Exception: " + e.getMessage());
        }

        tablem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablem.getTableHeader().setResizingAllowed(false);
        tablem.getTableHeader().setReorderingAllowed(false);
    }

    private void bukaFileSurat(int row) {
        String idSurat = (String) tablem.getValueAt(row, 0);
        String filePath = (String) tablem.getValueAt(row, 6);

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Tidak dapat membuka file: " + e.getMessage());
                System.out.println("Error buka file: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "File tidak ditemukan di path: " + filePath);
            System.out.println("File tidak ketemu: " + filePath);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        logoutlabel = new javax.swing.JLabel();
        labellihatkirimansurat = new javax.swing.JLabel();
        garispembataslogo = new javax.swing.JPanel();
        logo1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablem = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        buttonlihatsurat = new javax.swing.JButton();
        buttonsetuju = new javax.swing.JButton();
        buttontolak = new javax.swing.JButton();
        comboboxpilihjenissurat = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1035, 610));

        jPanel1.setBackground(new java.awt.Color(219, 244, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1035, 610));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        logoutlabel.setBackground(new java.awt.Color(0, 0, 0));
        logoutlabel.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        logoutlabel.setForeground(new java.awt.Color(0, 0, 0));
        logoutlabel.setText("LOG OUT");
        logoutlabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutlabelMouseClicked(evt);
            }
        });

        labellihatkirimansurat.setBackground(new java.awt.Color(0, 0, 0));
        labellihatkirimansurat.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        labellihatkirimansurat.setForeground(new java.awt.Color(0, 0, 0));
        labellihatkirimansurat.setText("LIHAT KIRIMAN SURAT");
        labellihatkirimansurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labellihatkirimansuratMouseClicked(evt);
            }
        });

        garispembataslogo.setBackground(new java.awt.Color(204, 204, 204));
        garispembataslogo.setPreferredSize(new java.awt.Dimension(100, 4));

        javax.swing.GroupLayout garispembataslogoLayout = new javax.swing.GroupLayout(garispembataslogo);
        garispembataslogo.setLayout(garispembataslogoLayout);
        garispembataslogoLayout.setHorizontalGroup(
            garispembataslogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        garispembataslogoLayout.setVerticalGroup(
            garispembataslogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        logo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\logoesurat150x150 (1).png")); // NOI18N
        logo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logo1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(garispembataslogo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(logoutlabel)
                                    .addComponent(labellihatkirimansurat)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(logo1)))
                        .addGap(28, 28, 28)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(logo1)
                .addGap(12, 12, 12)
                .addComponent(garispembataslogo, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labellihatkirimansurat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutlabel)
                .addGap(0, 494, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 660));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        tablem.setBackground(new java.awt.Color(255, 255, 255));
        tablem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablem.setGridColor(new java.awt.Color(255, 255, 255));
        tablem.setMaximumSize(new java.awt.Dimension(300, 80));
        tablem.setMinimumSize(new java.awt.Dimension(300, 80));
        tablem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablem);

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Persetujuan");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Surat");

        buttonlihatsurat.setBackground(new java.awt.Color(0, 204, 255));
        buttonlihatsurat.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        buttonlihatsurat.setForeground(new java.awt.Color(255, 255, 255));
        buttonlihatsurat.setText("Lihat Surat");
        buttonlihatsurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonlihatsuratActionPerformed(evt);
            }
        });

        buttonsetuju.setBackground(new java.awt.Color(51, 255, 51));
        buttonsetuju.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        buttonsetuju.setForeground(new java.awt.Color(0, 0, 0));
        buttonsetuju.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\iconcentang.png")); // NOI18N
        buttonsetuju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsetujuActionPerformed(evt);
            }
        });

        buttontolak.setBackground(new java.awt.Color(255, 51, 51));
        buttontolak.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        buttontolak.setForeground(new java.awt.Color(0, 0, 0));
        buttontolak.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\iconmarkX.png")); // NOI18N
        buttontolak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontolakActionPerformed(evt);
            }
        });

        comboboxpilihjenissurat.setBackground(new java.awt.Color(255, 255, 255));
        comboboxpilihjenissurat.setForeground(new java.awt.Color(0, 0, 0));
        comboboxpilihjenissurat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxpilihjenissurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxpilihjenissuratActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxpilihjenissurat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonlihatsurat)
                .addGap(38, 38, 38))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(buttonsetuju, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttontolak, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxpilihjenissurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonlihatsurat))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttontolak, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonsetuju, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(213, 213, 213)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 810, 360));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\10515253.png")); // NOI18N
        jLabel2.setDisabledIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\360_F_325073621_7AkPhyNfndERS4kb6oQNIQMyOFbtHA4F.jpg")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 550));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonlihatsuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonlihatsuratActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            bukaFileSurat(row);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonlihatsuratActionPerformed

    private void buttonsetujuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsetujuActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            String Status1 = (String) tablem.getValueAt(row, 4);

            String newStatus = "";
            if (Status1.equals("Pending")) {
                newStatus = "Diterima";
            } else if (Status1.equals("Ditolak")) {
                newStatus = "Diterima";
            }

            boolean isUpdated = Surat.setStatusSurat(ID_Surat, newStatus);
            if (isUpdated) {

                tablem.setValueAt(newStatus, row, 4);  
                JOptionPane.showMessageDialog(this, "Status surat berhasil diubah menjadi " + newStatus + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah status surat.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonsetujuActionPerformed

    private void buttontolakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontolakActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            String Status1 = (String) tablem.getValueAt(row, 4);

            String newStatus = "";
            if (Status1.equals("Pending")) {
                newStatus = "Ditolak";
            } else if (Status1.equals("Diterima")) {
                newStatus = "Ditolak";
            }

            boolean isUpdated = Surat.setStatusSurat(ID_Surat, newStatus);
            if (isUpdated) {

                tablem.setValueAt(newStatus, row, 4);  
                JOptionPane.showMessageDialog(this, "Status surat berhasil diubah menjadi " + newStatus + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah status surat.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttontolakActionPerformed

    private void tablemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablemMouseClicked

    private void comboboxpilihjenissuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxpilihjenissuratActionPerformed
        String selectedJenisSurat = (String) comboboxpilihjenissurat.getSelectedItem();
        tablem(selectedJenisSurat);
    }//GEN-LAST:event_comboboxpilihjenissuratActionPerformed

    private void logoutlabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutlabelMouseClicked
        Dashboard dashboardForm = new Dashboard();
        dashboardForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutlabelMouseClicked

    private void labellihatkirimansuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labellihatkirimansuratMouseClicked
        LihatSuratDosen lihatsuratdosenForm = new LihatSuratDosen();
        lihatsuratdosenForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_labellihatkirimansuratMouseClicked

    private void logo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logo1MouseClicked
        MenuDosen menuDosenForm = new MenuDosen();
        menuDosenForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logo1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LihatSuratDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LihatSuratDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LihatSuratDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatSuratDosen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LihatSuratDosen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonlihatsurat;
    private javax.swing.JButton buttonsetuju;
    private javax.swing.JButton buttontolak;
    private javax.swing.JComboBox<String> comboboxpilihjenissurat;
    private javax.swing.JPanel garispembataslogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labellihatkirimansurat;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logoutlabel;
    private javax.swing.JTable tablem;
    // End of variables declaration//GEN-END:variables
}
