/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rhema
 */
public class koneksi {

    public static Connection koneksi;

    public static void bukaKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/posdb";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
            } catch (SQLException t) {
                System.out.println("Error Koneksi!");
            }
        }
    }

    public static ResultSet getDataBarang(String id) {
        bukaKoneksi();
        ResultSet rs = null;

        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM databarang WHERE idBarang = '" + id + "'";
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
        }

        return rs;
    }

    public static void insertTransaksi(String namabarang, int idbarang, int kuantiti, String hargabarang, String totalHarga) {
        bukaKoneksi();
        int num = 0;
        int result = -1;

        String query = "INSERT INTO riwayattransaksi (namaBarang, idBarang, kuantiti, hargaBarang, totalHarga"
                + ") VALUES ("
                + " '" + namabarang + "',"
                + " '" + idbarang + "',"
                + " '" + kuantiti + "',"
                + " '" + hargabarang + "',"
                + " '" + totalHarga + "'"
                + " )";

        try {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            result = -1;
        }
    }
}
