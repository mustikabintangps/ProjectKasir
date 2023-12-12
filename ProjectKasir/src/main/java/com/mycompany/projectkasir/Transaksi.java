/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projectkasir;

/**
 *
 * @author Rhema
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Transaksi
     */
    DecimalFormat df = new DecimalFormat("#,###");

    public Transaksi() {
        initComponents();
        kosongkanForm();
        KodeBarang();
        Total();
    }

    public String[][] getTableData() {
        DefaultTableModel dtm = (DefaultTableModel) tabeltransaksi.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        String[][] tableData = new String[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                tableData[i][j] = dtm.getValueAt(i, j).toString();
            }
        }
        return tableData;
    }

    private void kosongkanForm() {
        kodebarang.setText("");
        kodebarang.grabFocus();
        namabarang.setText("");
        harga.setText("");
        QTY.setText("");
        QTY.setEditable(false);
        totalharga.setText("");
    }

    public void tampilkanData() {
        DefaultTableModel model = (DefaultTableModel) tabeltransaksi.getModel();

        Object rowData[] = new Object[5];
        rowData[0] = kodebarang.getText();
        rowData[1] = namabarang.getText();
        rowData[2] = harga.getText();
        rowData[3] = QTY.getText();
        rowData[4] = totalharga.getText();

        model.addRow(rowData);

        jumlahTotal();
    }

    private void KodeBarang() {
        kodebarang.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                String id = kodebarang.getText();
                ResultSet rs = koneksi.getDataBarang(id);

                try {
                    while (rs.next()) {
                        namabarang.setText(rs.getString("namaBarang"));
                        harga.setText(rs.getString("hargaBarang"));
                        QTY.setEditable(true);
                        QTY.grabFocus();
                    }
                } catch (Exception err) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                String id = kodebarang.getText();
                ResultSet rs = koneksi.getDataBarang(id);

                try {
                    if (rs.next() == false) {
                        namabarang.setText("");
                        harga.setText("");
                        QTY.setText("");
                        totalharga.setText("");
                        return;
                    }
                    while (rs.next()) {
                        namabarang.setText(rs.getString("namaBarang"));
                        harga.setText(rs.getString("hargaBarang"));
                        QTY.setEditable(true);
                        QTY.grabFocus();
                    }
                } catch (Exception err) {
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    private void Total() {
        QTY.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                try {
                    int hasil = 0;
                    if (QTY.getText().equals("")) {
                        hasil = Integer.parseInt(harga.getText()) * 0;
                    } else {
                        hasil = Integer.parseInt(harga.getText()) * Integer.parseInt(QTY.getText());
                    }
                    totalharga.setText(String.valueOf(hasil));
                } catch (Exception err) {
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                try {
                    int hasil = 0;
                    if (QTY.getText().equals("")) {
                        hasil = Integer.parseInt(harga.getText()) * 0;
                    } else {
                        hasil = Integer.parseInt(harga.getText()) * Integer.parseInt(QTY.getText());
                    }
                    totalharga.setText(String.valueOf(hasil));
                } catch (Exception err) {
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    private void jumlahTotal() {
        int sub_total = 0;
        double diskon = 0;
        double total = 0;

        for (int i = 0; i < tabeltransaksi.getRowCount(); i++) {
            sub_total += Integer.parseInt((String) tabeltransaksi.getValueAt(i, 4).toString());
        }

        if(this.status.getSelectedItem().toString().equals("Member")){
            diskon = sub_total * 0.1;
            total = sub_total - diskon;

            jumlahharga.setText(df.format(sub_total));
            rp.setText("Rp. " + df.format(sub_total));
            diskon10.setText("Rp." + df.format(diskon));
            totalhargabawah.setText("Rp." + df.format(total));
        }else
        {
            jumlahharga.setText(df.format(sub_total));
            rp.setText("Rp. " + df.format(sub_total));
            diskon10.setText("-");
            totalhargabawah.setText("Rp." + df.format(sub_total));
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

        bayarin = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        okdialog = new javax.swing.JButton();
        kolomtotal = new javax.swing.JLabel();
        kolombayar = new javax.swing.JTextField();
        kolomkembalian = new javax.swing.JTextField();
        rp = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kolomnofaktur = new javax.swing.JTextField();
        kodebarang = new javax.swing.JTextField();
        namabarang = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        QTY = new javax.swing.JTextField();
        totalharga = new javax.swing.JTextField();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        bayar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jumlahharga = new javax.swing.JTextField();
        diskon10 = new javax.swing.JTextField();
        totalhargabawah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeltransaksi = new javax.swing.JTable();
        status = new javax.swing.JComboBox<>();
        printstruk = new javax.swing.JButton();

        jLabel1.setText("Total Harga");

        jLabel8.setText("Bayar");

        jLabel9.setText("Kembalian");

        okdialog.setText("OK");
        okdialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okdialogActionPerformed(evt);
            }
        });

        kolomtotal.setText("tes");

        kolombayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolombayarActionPerformed(evt);
            }
        });
        kolombayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kolombayarKeyReleased(evt);
            }
        });

        kolomkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kolomkembalianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bayarinLayout = new javax.swing.GroupLayout(bayarin.getContentPane());
        bayarin.getContentPane().setLayout(bayarinLayout);
        bayarinLayout.setHorizontalGroup(
            bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bayarinLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(okdialog)
                    .addGroup(bayarinLayout.createSequentialGroup()
                        .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kolomtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(kolomkembalian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addComponent(kolombayar, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(28, 28, 28)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        bayarinLayout.setVerticalGroup(
            bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bayarinLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kolomtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(kolombayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bayarinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(kolomkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(okdialog)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        rp.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        rp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rp.setText("Rp.");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("No. Faktur  :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Kode Barang :");

        kodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodebarangActionPerformed(evt);
            }
        });

        namabarang.setEditable(false);

        harga.setEditable(false);

        QTY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QTYActionPerformed(evt);
            }
        });

        totalharga.setEditable(false);

        edit.setText("Edit");

        hapus.setText("Delete");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        bayar.setText("Bayar");
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Total Harga");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Status ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Jumlah Harga");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Diskon 10%");

        jumlahharga.setEditable(false);

        diskon10.setEditable(false);

        tabeltransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga", "QTY", "Total Harga"
            }
        ));
        jScrollPane1.setViewportView(tabeltransaksi);

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Member", "Non Member" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        printstruk.setText("Print Struk");
        printstruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printstrukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kolomnofaktur))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QTY, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(hapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edit)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(314, 314, 314)
                                                .addComponent(jLabel6))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel5)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jumlahharga)
                                    .addComponent(diskon10)
                                    .addComponent(totalhargabawah)
                                    .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(bayar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(printstruk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(rp, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rp)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(kolomnofaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QTY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jumlahharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(diskon10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(totalhargabawah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(printstruk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printstrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printstrukActionPerformed
        // TODO add your handling code here:
        String[][] datas = getTableData();

        this.setVisible(false);
        new StrukTransaksi(datas).setVisible(true);
    }//GEN-LAST:event_printstrukActionPerformed

    private void QTYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QTYActionPerformed
        // TODO add your handling code here:
        
        tampilkanData();
        kosongkanForm();
    }//GEN-LAST:event_QTYActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabeltransaksi.getModel();
        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                if (tabeltransaksi.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(tabeltransaksi.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                    jumlahTotal();
                }
            }
        });
    }//GEN-LAST:event_hapusActionPerformed

    private void kodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodebarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodebarangActionPerformed
    
    private void okdialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okdialogActionPerformed
        // TODO add your handling code here:
        
        this.bayarin.dispose();
    }//GEN-LAST:event_okdialogActionPerformed

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here: 
    bayarin.setSize(390, 250);
    bayarin.setLocationRelativeTo(this);
    String totalBanget = totalhargabawah.getText();
    this.kolomtotal.setText(totalBanget);
    
    // Set visibility to true
    bayarin.setVisible(true);
    
    hitungKembalian();
}

    private void hitungKembalian() {
    String bayarUserStr = kolombayar.getText();
    String totalBangetStr = totalhargabawah.getText();

    // Pastikan nilai kolombayar dan totalhargabawah tidak kosong
    if (!bayarUserStr.isEmpty() && !totalBangetStr.isEmpty()) {
        try {
            double bayarUser = Double.parseDouble(bayarUserStr);
            double totalBanget = Double.parseDouble(totalBangetStr);

            double kembalian = bayarUser - totalBanget;

            // Ubah nilai kembalian menjadi format string dan set ke dalam kolomkembalian
            kolomkembalian.setText("Rp." + df.format(kembalian));
        } catch (NumberFormatException e) {
            kolomkembalian.setText("Format tidak valid");
        }
}
    }//GEN-LAST:event_bayarActionPerformed

    private void kolombayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolombayarActionPerformed
        // TODO add your handling code here

    }//GEN-LAST:event_kolombayarActionPerformed

    private void kolomkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kolomkembalianActionPerformed
        // TODO add your handling code here:
    // String bayarUserStr = kolombayar.getText();
    //String totalBangetStr = totalhargabawah.getText();

    //try {
        //double bayarUser = Double.parseDouble(bayarUserStr);
        //double totalBanget = Double.parseDouble(totalBangetStr);

        //double kembalian = totalBanget - totalBanget;

        //kolomkembalian.setText("Rp." + df.format(kembalian));
    //} catch (NumberFormatException e) {
        // Tangani kesalahan jika input bukan angka
        //kolomkembalian.setText("Format tidak valid");
    //} 
    }//GEN-LAST:event_kolomkembalianActionPerformed

    private void kolombayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kolombayarKeyReleased
        // TODO add your handling code here:
//        this.kolomkembalian.setText(this.kolombayar.getText());
        String bayarUserStr = kolombayar.getText();
    String totalBangetStr = totalhargabawah.getText();

    // Pastikan nilai kolombayar dan totalhargabawah tidak kosong
    if (!bayarUserStr.isEmpty() && !totalBangetStr.isEmpty()) {
        try {
            double bayarUser = Double.parseDouble(bayarUserStr);
            
            ArrayList<String> hargaCek = new ArrayList<>();
            String[] cekRp = totalhargabawah.getText().split("Rp.");
            String[] cekKoma = cekRp[1].split(",");
            
            String hargaTotalSemua ="";
            for(String apalah: cekKoma){
                hargaTotalSemua += apalah;
            }
            

            double kembalian = bayarUser - Integer.parseInt(hargaTotalSemua);

            // Ubah nilai kembalian menjadi format string dan set ke dalam kolomkembalian
            kolomkembalian.setText("Rp." + df.format(kembalian));
        } catch (NumberFormatException e) {
//            kolomkembalian.setText("Format tidak valid");
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_kolombayarKeyReleased

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField QTY;
    private javax.swing.JButton bayar;
    private javax.swing.JDialog bayarin;
    private javax.swing.JTextField diskon10;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahharga;
    private javax.swing.JTextField kodebarang;
    private javax.swing.JTextField kolombayar;
    private javax.swing.JTextField kolomkembalian;
    private javax.swing.JTextField kolomnofaktur;
    private javax.swing.JLabel kolomtotal;
    private javax.swing.JTextField namabarang;
    private javax.swing.JButton okdialog;
    private javax.swing.JButton printstruk;
    private javax.swing.JLabel rp;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTable tabeltransaksi;
    private javax.swing.JTextField totalharga;
    private javax.swing.JTextField totalhargabawah;
    // End of variables declaration//GEN-END:variables
}
