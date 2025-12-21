/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul07.tugas;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField inputNama;
    private JTextField inputNilai;
    private JComboBox<String> comboMatkul;

    private JTable tableData;
    private DefaultTableModel tableModel;

    private JTabbedPane tabPane;

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Nama Siswa:"));
        inputNama = new JTextField();
        panel.add(inputNama);

        panel.add(new JLabel("Nilai:"));
        inputNilai = new JTextField();
        panel.add(inputNilai);

        panel.add(new JLabel("Mata Kuliah:"));
        comboMatkul = new JComboBox<>(new String[]{
                "Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemrograman I", "Praktikum Pemograman II"
        });
        panel.add(comboMatkul);

        JButton btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(e -> prosesSimpan());
        panel.add(btnSimpan);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> prosesReset());
        panel.add(btnReset);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(
                new Object[]{"Nama", "Nilai", "Mata Kuliah", "Grade"}, 0
        );

        tableData = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(tableData);
        scroll.setPreferredSize(new Dimension(450, 250));

        panel.add(scroll, BorderLayout.CENTER);

        JButton btnHapus = new JButton("Hapus Data");
        btnHapus.addActionListener(e -> prosesHapus());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHapus);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void prosesSimpan() {
        String nama = inputNama.getText().trim();
        String strNilai = inputNilai.getText().trim();
        String matkul = comboMatkul.getSelectedItem().toString();

        // Validasi nama tidak kosong
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }

        // Validasi nama minimal 3 karakter
        if (nama.length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal 3 karakter!");
            return;
        }

        int nilai = 0;

        try {
            nilai = Integer.parseInt(strNilai);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nilai harus angka!");
            return;
        }

        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!");
            return;
        }

        // SWITCH CASE UNTUK GRADE
        String grade;
        switch (nilai / 10) {
            case 10:
            case 9:
                grade = "A";
                break;
            case 8:
                grade = "B";
                break;
            case 7:
                grade = "C";
                break;
            case 6:
                grade = "D";
                break;
            default:
                grade = "E";
        }

        tableModel.addRow(new Object[]{nama, nilai, matkul, grade});
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
    }

    private void prosesReset() {
        inputNama.setText("");
        inputNilai.setText("");
        comboMatkul.setSelectedIndex(0);
    }

    private void prosesHapus() {
        int index = tableData.getSelectedRow();
        if (index >= 0) {
            tableModel.removeRow(index);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
        }
    }

    public ManajemenNilaiSiswaApp() {
        setTitle("Tugas - Manajemen Nilai Siswa");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabPane = new JTabbedPane();
        tabPane.addTab("Input Data", createInputPanel());
        tabPane.addTab("Daftar Nilai", createTablePanel());

        add(tabPane);
    }

    public static void main(String[] args) {
        new ManajemenNilaiSiswaApp().setVisible(true);
    }
}
