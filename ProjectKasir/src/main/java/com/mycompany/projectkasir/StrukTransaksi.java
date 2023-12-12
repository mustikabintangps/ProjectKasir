/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectkasir;

import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class StrukTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form StrukTransaksi
     */
    String[][] datas;
    DecimalFormat df = new DecimalFormat("#,###");

    public StrukTransaksi(String[][] datas) {
        initComponents();
        this.datas = datas;
        tampilkanData();
        insertData();

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String formattedDate = myDateObj.format(myFormatObj);
        kolomtanggal.setText(formattedDate);
    }

    private void insertData() {
        String namabarang;
        String idbarang;
        int kuantiti;
        String hargabarang;
        String totalHarga;
        for (int i = 0; i < datas.length; i++) {
            idbarang = datas[i][0];
            namabarang = datas[i][1];
            hargabarang = datas[i][2] ;
            kuantiti = Integer.parseInt(datas[i][3]);
            totalHarga = datas[i][4];
            
//            koneksi.insertTransaksi(namabarang, kuantiti, hargabarang, totalHarga);
            koneksi.insertTransaksi(namabarang, ABORT, kuantiti, hargabarang, totalHarga);
        }
    }

    private void tampilkanData() {
        DefaultTableModel model = (DefaultTableModel) tabeltransaksi2.getModel();

        for (int i = 0; i < datas.length; i++) {
            String rowData[] = new String[6];
            for (int j = 1; j <= datas[i].length; j++) {
                rowData[j] = datas[i][j - 1];
            }
            model.addRow(rowData);
            model.setValueAt(i + 1 + ".", i, 0);
        }

        jumlahTotal();
    }

    private void jumlahTotal() {
        int sub_total = 0;
        double diskon = 0;
        double total = 0;

        for (int i = 0; i < tabeltransaksi2.getRowCount(); i++) {
            sub_total += Integer.parseInt((String) tabeltransaksi2.getValueAt(i, 5).toString());
        }

        diskon = sub_total * 0.1;
        total = sub_total - diskon;

        kolomtotalharga2.setText("Rp." + df.format(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelstruk = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeltransaksi2 = new javax.swing.JTable();
        labelstruk1 = new javax.swing.JLabel();
        labeltotalharga = new javax.swing.JLabel();
        kolomtotalharga2 = new javax.swing.JTextField();
        kolomtanggal = new javax.swing.JTextField();
        labelstruk2 = new javax.swing.JLabel();
        labelstruk3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        labelstruk.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        labelstruk.setText("STRUK TRANSAKSI");

        tabeltransaksi2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Harga", "QTY", "Total Harga"
            }
        ));
        jScrollPane1.setViewportView(tabeltransaksi2);

        labelstruk1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelstruk1.setText("Tanggal :");

        labeltotalharga.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labeltotalharga.setText("Total Harga");

        kolomtotalharga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolomtotalharga2ActionPerformed(evt);
            }
        });

        kolomtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolomtanggalActionPerformed(evt);
            }
        });

        labelstruk2.setText("Terimakasih sudah berbelanja di ARGOMART");

        labelstruk3.setText("Barang yang sudah dibeli tidak dapat dikembalikan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelstruk1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kolomtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(labeltotalharga)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(kolomtotalharga2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(labelstruk, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelstruk2)
                        .addGap(19, 19, 19))
                    .addComponent(labelstruk3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelstruk)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelstruk1)
                    .addComponent(kolomtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeltotalharga)
                    .addComponent(kolomtotalharga2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(labelstruk2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelstruk3)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kolomtotalharga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolomtotalharga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kolomtotalharga2ActionPerformed

    private void kolomtanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolomtanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kolomtanggalActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StrukTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StrukTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StrukTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StrukTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new StrukTransaksi().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kolomtanggal;
    private javax.swing.JTextField kolomtotalharga2;
    private javax.swing.JLabel labelstruk;
    private javax.swing.JLabel labelstruk1;
    private javax.swing.JLabel labelstruk2;
    private javax.swing.JLabel labelstruk3;
    private javax.swing.JLabel labeltotalharga;
    private javax.swing.JTable tabeltransaksi2;
    // End of variables declaration//GEN-END:variables
}
