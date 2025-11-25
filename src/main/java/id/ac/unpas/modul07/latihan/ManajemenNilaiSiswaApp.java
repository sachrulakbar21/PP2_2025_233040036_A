package id.ac.unpas.modul07.latihan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

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
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Nama Siswa:"));
        inputNama = new JTextField();
        panel.add(inputNama);

        panel.add(new JLabel("Nilai:"));
        inputNilai = new JTextField();
        panel.add(inputNilai);

        panel.add(new JLabel("Mata Kuliah:"));
        comboMatkul = new JComboBox<>(new String[]{
                "Matematika Dasar", "Bahasa Indonesia", "Algoritama dan Pemrograman I", "Praktikum Pemograman II"
        });
        panel.add(comboMatkul);

        JButton btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(e -> prosesSimpan());
        panel.add(btnSimpan);

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

        return panel;
    }

    private void prosesSimpan() {
        String nama = inputNama.getText();
        String strNilai = inputNilai.getText();
        String matkul = comboMatkul.getSelectedItem().toString();

        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
            return;
        }

        int nilai = 0;

        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus angka!");
            return;
        }

        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!");
            return;
        }

        String grade = "";
        if (nilai >= 90) grade = "A";
        else if (nilai >= 80) grade = "B";
        else if (nilai >= 70) grade = "C";
        else if (nilai >= 60) grade = "D";
        else grade = "E";

        tableModel.addRow(new Object[]{nama, nilai, matkul, grade});

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
    }

    public ManajemenNilaiSiswaApp() {
        setTitle("Manajemen Nilai Siswa");
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
