package id.ac.unpas.modul10.controller;

import id.ac.unpas.modul10.model.Mahasiswa;
import id.ac.unpas.modul10.KoneksiDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaController {
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa");
            while (rs.next()) {
                list.add(new Mahasiswa(
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("jurusan")
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Load Data: " + e.getMessage());
        }
        return list;
    }

    public boolean tambahMahasiswa(Mahasiswa m) {
        try {
            Connection conn = KoneksiDB.configDB();
            // Cek NIM
            String cekSql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
            PreparedStatement cekPst = conn.prepareStatement(cekSql);
            cekPst.setString(1, m.getNim());
            ResultSet rs = cekPst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "NIM sudah terdaftar! Input ditolak.");
                return false;
            }
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getNama());
            pst.setString(2, m.getNim());
            pst.setString(3, m.getJurusan());
            pst.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Simpan Data: " + e.getMessage());
            return false;
        }
    }

    public boolean ubahMahasiswa(Mahasiswa m) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, m.getNama());
            pst.setString(2, m.getJurusan());
            pst.setString(3, m.getNim());
            pst.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Ubah Data: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusMahasiswa(String nim) {
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            pst.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Hapus Data: " + e.getMessage());
            return false;
        }
    }

    public List<Mahasiswa> cariMahasiswa(String keyword) {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Mahasiswa(
                        rs.getString("nama"),
                        rs.getString("nim"),
                        rs.getString("jurusan")
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Cari Data: " + e.getMessage());
        }
        return list;
    }
}