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
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;

/**
 *
 * @author ikmal
 */
public class LihatSuratMahasiswa extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public LihatSuratMahasiswa() {
        initComponents();
        setLocationRelativeTo(null);
        comboboxpilihjenissurat.removeAllItems();
        comboboxpilihjenissurat.addItem("Semua");
        comboboxpilihjenissurat.addItem("Surat Maaf");
        comboboxpilihjenissurat.addItem("Surat Konsul");
        comboboxpilihjenissurat.addItem("Surat Pertemuan");
        tablem((String) comboboxpilihjenissurat.getSelectedItem());
        fieldfilesurat.setEditable(false); 
        fieldfilesurat.setFocusable(false);
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
        tableModel.addColumn("Tag");
        tableModel.addColumn("File Surat");
        tableModel.addColumn("Tanggal Kirim");

        Connection conn = DatabaseConnection.getInstance().getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Database connection gagal.");
            return;
        }

        try {
            String query = "SELECT surat.ID_Surat, mahasiswa.Nama_Mahasiswa, surat.Isi_Surat, surat.Jenis_Surat, surat.Status_Surat, "
                    + "surat.Tag, surat.FileSurat, surat.Tgl_Kirim "
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

            while (resultSet.next()) {
                String idSurat = resultSet.getString("ID_Surat");
                String namaMahasiswa = resultSet.getString("Nama_Mahasiswa");
                String isiSurat = resultSet.getString("Isi_Surat");
                String jenisSurat = resultSet.getString("Jenis_Surat");
                String statusSurat = resultSet.getString("Status_Surat");
                String tag = resultSet.getString("Tag");
                String tglKirim = resultSet.getString("Tgl_Kirim");

                byte[] fileBlob = resultSet.getBytes("FileSurat");
                String outputFilePath = null;
                if (fileBlob != null) {
                    outputFilePath = "path/to/save/" + idSurat + "_surat.pdf";
                    File outputFile = new File(outputFilePath);
                    File outputDir = new File("path/to/save");
                    if (!outputDir.exists()) {
                        outputDir.mkdirs();
                    }
                    try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                        fileOutputStream.write(fileBlob);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Gagal menyimpan file: " + e.getMessage());
                        outputFilePath = "File tidak tersedia";
                    }
                }

                tableModel.addRow(new Object[]{
                    idSurat,
                    namaMahasiswa,
                    isiSurat,
                    jenisSurat,
                    statusSurat,
                    tag,
                    outputFilePath != null ? outputFilePath : "Tidak ada file",
                    tglKirim
                });
            }
            tablem.setModel(tableModel);
            tablem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tablem.getTableHeader().setResizingAllowed(false);
            tablem.getTableHeader().setReorderingAllowed(false);

            System.out.println("Tabel berhasil diperbarui.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }
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
                System.out.println("Error opening file: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "File tidak ditemukan di path: " + filePath);
            System.out.println("File not found: " + filePath);
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
        logo1 = new javax.swing.JLabel();
        sidebarkirimsuratpribadi = new javax.swing.JLabel();
        sidebarlihatsurat = new javax.swing.JLabel();
        sidebarlogout = new javax.swing.JLabel();
        garispembataslogo = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        isisurattext = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        buttonlihatsurat = new javax.swing.JButton();
        comboboxpilihjenissurat = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        buttonpilihfile = new javax.swing.JButton();
        fieldfilesurat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buttonubah = new javax.swing.JButton();
        logorefresh = new javax.swing.JLabel();
        buttonhapussurat = new javax.swing.JButton();
        buttontidakpenting = new javax.swing.JButton();
        buttonpenting = new javax.swing.JButton();
        buttonubahisisurat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1035, 610));

        jPanel1.setBackground(new java.awt.Color(219, 244, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1035, 610));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        logo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\logoesurat150x150 (1).png")); // NOI18N
        logo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logo1MouseClicked(evt);
            }
        });

        sidebarkirimsuratpribadi.setBackground(new java.awt.Color(0, 0, 0));
        sidebarkirimsuratpribadi.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        sidebarkirimsuratpribadi.setForeground(new java.awt.Color(0, 0, 0));
        sidebarkirimsuratpribadi.setText("KIRIM SURAT ");
        sidebarkirimsuratpribadi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarkirimsuratpribadiMouseClicked(evt);
            }
        });

        sidebarlihatsurat.setBackground(new java.awt.Color(0, 0, 0));
        sidebarlihatsurat.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        sidebarlihatsurat.setForeground(new java.awt.Color(0, 0, 0));
        sidebarlihatsurat.setText("LIHAT SURAT");
        sidebarlihatsurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarlihatsuratMouseClicked(evt);
            }
        });

        sidebarlogout.setBackground(new java.awt.Color(0, 0, 0));
        sidebarlogout.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        sidebarlogout.setForeground(new java.awt.Color(0, 0, 0));
        sidebarlogout.setText("LOG OUT");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(garispembataslogo, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sidebarkirimsuratpribadi)
                            .addComponent(sidebarlihatsurat)
                            .addComponent(sidebarlogout)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(logo1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(logo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(garispembataslogo, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidebarkirimsuratpribadi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidebarlihatsurat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sidebarlogout)
                .addGap(0, 483, Short.MAX_VALUE))
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
        tablem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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
        tablem.setGridColor(new java.awt.Color(0, 0, 0));
        tablem.setMaximumSize(new java.awt.Dimension(300, 80));
        tablem.setMinimumSize(new java.awt.Dimension(300, 80));
        tablem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablem);

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Isi Surat");

        isisurattext.setBackground(new java.awt.Color(255, 255, 255));
        isisurattext.setColumns(20);
        isisurattext.setRows(5);
        isisurattext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane2.setViewportView(isisurattext);

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

        jLabel5.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Beri Tag");

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

        comboboxpilihjenissurat.setBackground(new java.awt.Color(255, 255, 255));
        comboboxpilihjenissurat.setForeground(new java.awt.Color(0, 0, 0));
        comboboxpilihjenissurat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxpilihjenissurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxpilihjenissuratActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        buttonpilihfile.setBackground(new java.awt.Color(60, 179, 255));
        buttonpilihfile.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        buttonpilihfile.setForeground(new java.awt.Color(255, 255, 255));
        buttonpilihfile.setText("Pilih File");
        buttonpilihfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonpilihfileMouseClicked(evt);
            }
        });
        buttonpilihfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonpilihfileActionPerformed(evt);
            }
        });

        fieldfilesurat.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(buttonpilihfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldfilesurat, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonpilihfile)
                    .addComponent(fieldfilesurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 255));
        jLabel9.setText("File Harus Berbentuk PDF");

        jLabel10.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("File Skripsi");

        buttonubah.setBackground(new java.awt.Color(0, 204, 255));
        buttonubah.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        buttonubah.setForeground(new java.awt.Color(255, 255, 255));
        buttonubah.setText("Ubah File");
        buttonubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonubahActionPerformed(evt);
            }
        });

        logorefresh.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\Downloads\\logorefresh30x30 (1).png")); // NOI18N
        logorefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logorefreshMouseClicked(evt);
            }
        });

        buttonhapussurat.setBackground(new java.awt.Color(0, 204, 255));
        buttonhapussurat.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        buttonhapussurat.setForeground(new java.awt.Color(255, 255, 255));
        buttonhapussurat.setText("Hapus Surat");
        buttonhapussurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonhapussuratActionPerformed(evt);
            }
        });

        buttontidakpenting.setBackground(new java.awt.Color(255, 153, 0));
        buttontidakpenting.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        buttontidakpenting.setForeground(new java.awt.Color(255, 255, 255));
        buttontidakpenting.setText("Tidak Penting");
        buttontidakpenting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttontidakpentingActionPerformed(evt);
            }
        });

        buttonpenting.setBackground(new java.awt.Color(51, 255, 0));
        buttonpenting.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        buttonpenting.setForeground(new java.awt.Color(255, 255, 255));
        buttonpenting.setText("Penting");
        buttonpenting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonpentingActionPerformed(evt);
            }
        });

        buttonubahisisurat.setBackground(new java.awt.Color(0, 204, 255));
        buttonubahisisurat.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        buttonubahisisurat.setForeground(new java.awt.Color(255, 255, 255));
        buttonubahisisurat.setText("Ubah Isi Surat");
        buttonubahisisurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonubahisisuratActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logorefresh))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxpilihjenissurat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(buttonubahisisurat))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(buttonpenting, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttontidakpenting))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonubah)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonhapussurat)
                                .addGap(18, 18, 18)
                                .addComponent(buttonlihatsurat)))
                        .addGap(38, 38, 38))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logorefresh)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboboxpilihjenissurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonlihatsurat)
                        .addComponent(buttonhapussurat))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonubahisisurat))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttontidakpenting)
                            .addComponent(buttonpenting))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buttonubah)
                                .addGap(190, 190, 190)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 810, 570));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\10515253.png")); // NOI18N
        jLabel2.setDisabledIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\360_F_325073621_7AkPhyNfndERS4kb6oQNIQMyOFbtHA4F.jpg")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 550));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablemMouseClicked

    private void buttonlihatsuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonlihatsuratActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            bukaFileSurat(row);
        } else {
            JOptionPane.showMessageDialog(null, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonlihatsuratActionPerformed

    private void comboboxpilihjenissuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxpilihjenissuratActionPerformed
        String selectedJenisSurat = (String) comboboxpilihjenissurat.getSelectedItem();
        tablem(selectedJenisSurat);
    }//GEN-LAST:event_comboboxpilihjenissuratActionPerformed

    private void buttonpilihfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonpilihfileMouseClicked
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        if (f != null) {
            if (f.getName().toLowerCase().endsWith(".pdf")) {
                fieldfilesurat.setText(f.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "File harus berformat PDF!", "Tipe File Salah", JOptionPane.ERROR_MESSAGE);
                fieldfilesurat.setText("File Kosong");
            }
        } else {
            fieldfilesurat.setText("File Kosong");
        }
    }//GEN-LAST:event_buttonpilihfileMouseClicked

    private void buttonpilihfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonpilihfileActionPerformed

    }//GEN-LAST:event_buttonpilihfileActionPerformed

    private void buttonubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonubahActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            System.out.println("ID_Surat yang dipilih: " + ID_Surat);

            String FileSuratPath = fieldfilesurat.getText();
            if (FileSuratPath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Silakan pilih file terlebih dahulu.");
                return;
            }

            File FileSurat = new File(FileSuratPath);
            if (!FileSurat.exists() || !FileSurat.isFile()) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan. Harap periksa ulang.");
                System.out.println("File tidak ditemukan: " + FileSurat.getAbsolutePath());
                return;
            }
            System.out.println("Path FileSurat: " + FileSurat.getAbsolutePath());

            Surat surat1 = new Surat(null, null, null, FileSurat);
            surat1.setID_Surat(ID_Surat);

            try {
                boolean updated = surat1.updateFileSurat();
                if (updated) {
                    JOptionPane.showMessageDialog(this, "File Surat berhasil diperbarui.");
                    tablem("Semua");
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(LihatSuratMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonubahActionPerformed

    private void logorefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logorefreshMouseClicked
        LihatSuratMahasiswa lihatSuratMahasiswaForm = new LihatSuratMahasiswa();
        lihatSuratMahasiswaForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logorefreshMouseClicked

    private void buttonhapussuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonhapussuratActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Kamu yakin mau menghapus surat ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = Surat.deleteSurat(ID_Surat);
                if (deleted) {
                    ((DefaultTableModel) tablem.getModel()).removeRow(row);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih barisnya terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonhapussuratActionPerformed

    private void buttonpentingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonpentingActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            String currentTag = (String) tablem.getValueAt(row, 5);

            String newTag = "Penting";
            if ("Penting".equals(currentTag)) {
                JOptionPane.showMessageDialog(this, "Tag sudah bernilai 'Penting'.");
                return;
            }

            boolean isUpdated = Surat.setTagSurat(ID_Surat, newTag);
            if (isUpdated) {
                tablem.setValueAt(newTag, row, 5);  // Update tag di tabel
                JOptionPane.showMessageDialog(this, "Tag berhasil diubah menjadi " + newTag + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah tag surat.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonpentingActionPerformed

    private void buttontidakpentingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttontidakpentingActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            String currentTag = (String) tablem.getValueAt(row, 5);

            String newTag = "Tidak Penting";
            if ("Tidak Penting".equals(currentTag)) {
                JOptionPane.showMessageDialog(this, "Tag sudah bernilai 'Tidak Penting'.");
                return;
            }

            boolean isUpdated = Surat.setTagSurat(ID_Surat, newTag);
            if (isUpdated) {
                tablem.setValueAt(newTag, row, 5);  // Update tag di tabel
                JOptionPane.showMessageDialog(this, "Tag berhasil diubah menjadi " + newTag + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah tag surat.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }

    }//GEN-LAST:event_buttontidakpentingActionPerformed

    private void buttonubahisisuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonubahisisuratActionPerformed
        int row = tablem.getSelectedRow();
        if (row != -1) {
            String ID_Surat = (String) tablem.getValueAt(row, 0);
            String newIsiSurat = isisurattext.getText();

            if (newIsiSurat.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi surat tidak boleh kosong.");
                return;
            }

            boolean isUpdated = Surat.updateIsiSurat(ID_Surat, newIsiSurat);
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Isi surat berhasil diperbarui.");
                tablem("Semua");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui isi surat.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
        }
    }//GEN-LAST:event_buttonubahisisuratActionPerformed

    private void sidebarlihatsuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarlihatsuratMouseClicked
        LihatSuratMahasiswa LihatSUratMahasiswaForm = new LihatSuratMahasiswa();
        LihatSUratMahasiswaForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sidebarlihatsuratMouseClicked

    private void sidebarkirimsuratpribadiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarkirimsuratpribadiMouseClicked
        KirimSurat kirimSuratForm = new KirimSurat();
        kirimSuratForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sidebarkirimsuratpribadiMouseClicked

    private void logo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logo1MouseClicked
        MenuMahasiswa menuMahasiswaForm = new MenuMahasiswa();
        menuMahasiswaForm.setVisible(true);
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
            java.util.logging.Logger.getLogger(LihatSuratMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LihatSuratMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LihatSuratMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LihatSuratMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new LihatSuratMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonhapussurat;
    private javax.swing.JButton buttonlihatsurat;
    private javax.swing.JButton buttonpenting;
    private javax.swing.JButton buttonpilihfile;
    private javax.swing.JButton buttontidakpenting;
    private javax.swing.JButton buttonubah;
    private javax.swing.JButton buttonubahisisurat;
    private javax.swing.JComboBox<String> comboboxpilihjenissurat;
    private javax.swing.JTextField fieldfilesurat;
    private javax.swing.JPanel garispembataslogo;
    private javax.swing.JTextArea isisurattext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logorefresh;
    private javax.swing.JLabel sidebarkirimsuratpribadi;
    private javax.swing.JLabel sidebarlihatsurat;
    private javax.swing.JLabel sidebarlogout;
    private javax.swing.JTable tablem;
    // End of variables declaration//GEN-END:variables
}
