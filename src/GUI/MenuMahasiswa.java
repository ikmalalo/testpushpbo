/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

/**
 *
 * @author ikmal
 */
import GUI.Dashboard;
import GUI.KirimSurat;
import GUI.LihatSuratMahasiswa;

public class MenuMahasiswa extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public MenuMahasiswa() {
        initComponents();
        setLocationRelativeTo(null);
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
        sidebarlogout = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        sidebarlihatsurat = new javax.swing.JLabel();
        sidebarkirimsuratpribadi = new javax.swing.JLabel();
        garispembataslogo = new javax.swing.JPanel();
        logosayhi = new javax.swing.JLabel();
        sayhitext = new javax.swing.JLabel();
        SelamatDatangText = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1035, 610));
        setMinimumSize(new java.awt.Dimension(1035, 610));

        jPanel1.setBackground(new java.awt.Color(219, 244, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        sidebarlogout.setBackground(new java.awt.Color(0, 0, 0));
        sidebarlogout.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        sidebarlogout.setForeground(new java.awt.Color(0, 0, 0));
        sidebarlogout.setText("LOG OUT");
        sidebarlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarlogoutMouseClicked(evt);
            }
        });

        logo1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\logoesurat150x150 (1).png")); // NOI18N

        sidebarlihatsurat.setBackground(new java.awt.Color(0, 0, 0));
        sidebarlihatsurat.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        sidebarlihatsurat.setForeground(new java.awt.Color(0, 0, 0));
        sidebarlihatsurat.setText("LIHAT SURAT");
        sidebarlihatsurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarlihatsuratMouseClicked(evt);
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

        garispembataslogo.setBackground(new java.awt.Color(204, 204, 204));
        garispembataslogo.setPreferredSize(new java.awt.Dimension(100, 4));

        javax.swing.GroupLayout garispembataslogoLayout = new javax.swing.GroupLayout(garispembataslogo);
        garispembataslogo.setLayout(garispembataslogoLayout);
        garispembataslogoLayout.setHorizontalGroup(
            garispembataslogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
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
                    .addComponent(garispembataslogo, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sidebarkirimsuratpribadi)
                                    .addComponent(sidebarlihatsurat)
                                    .addComponent(sidebarlogout)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(logo1)))
                        .addContainerGap())))
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
                .addContainerGap(433, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 610));

        logosayhi.setBackground(new java.awt.Color(0, 0, 0));
        logosayhi.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 12)); // NOI18N
        logosayhi.setForeground(new java.awt.Color(0, 0, 0));
        logosayhi.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\Sayhi.png")); // NOI18N
        jPanel1.add(logosayhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 350, 350));

        sayhitext.setBackground(new java.awt.Color(153, 153, 153));
        sayhitext.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 36)); // NOI18N
        sayhitext.setForeground(new java.awt.Color(204, 204, 204));
        sayhitext.setText("Di E-Surat Sigma");
        jPanel1.add(sayhitext, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 370, -1));

        SelamatDatangText.setBackground(new java.awt.Color(0, 0, 0));
        SelamatDatangText.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 48)); // NOI18N
        SelamatDatangText.setForeground(new java.awt.Color(0, 150, 234));
        SelamatDatangText.setText("Selamat datang ");
        jPanel1.add(SelamatDatangText, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 520, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\10515253.png")); // NOI18N
        jLabel2.setDisabledIcon(new javax.swing.ImageIcon("C:\\Users\\ikmal\\OneDrive\\Gambar\\360_F_325073621_7AkPhyNfndERS4kb6oQNIQMyOFbtHA4F.jpg")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void sidebarlogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarlogoutMouseClicked
        Dashboard dashboardForm = new Dashboard();
        dashboardForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sidebarlogoutMouseClicked

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
            java.util.logging.Logger.getLogger(MenuMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SelamatDatangText;
    private javax.swing.JPanel garispembataslogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logosayhi;
    private javax.swing.JLabel sayhitext;
    private javax.swing.JLabel sidebarkirimsuratpribadi;
    private javax.swing.JLabel sidebarlihatsurat;
    private javax.swing.JLabel sidebarlogout;
    // End of variables declaration//GEN-END:variables
}
