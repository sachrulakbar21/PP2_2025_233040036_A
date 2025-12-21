package id.ac.unpas.modul10.view;

import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MahasiswaApp extends JFrame {
    JTextField txtNama, txtNim, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel Model;
    MahasiswaController controller;

    public MahasiswaApp() {
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        controller = new MahasiswaController();

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:"));
        txtNim = new JTextField();
        panelForm.add(txtNim);
        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        btnCari = new JButton("Cari");
        txtCari = new JTextField(12);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        panelTombol.add(new JLabel("Cari Nama:"));
        panelTombol.add(txtCari);
        panelTombol.add(btnCari);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        Model = new DefaultTableModel();
        Model.addColumn("No");
        Model.addColumn("Nama");
        Model.addColumn("NIM");
        Model.addColumn("Jurusan");
        tableMahasiswa = new JTable(Model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableMahasiswa.getSelectedRow();
                if (selectedRow >= 0) {
                    txtNama.setText(Model.getValueAt(selectedRow, 1).toString());
                    txtNim.setText(Model.getValueAt(selectedRow, 2).toString());
                    txtJurusan.setText(Model.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        btnCari.addActionListener(e -> cariData());
        loadData();
    }

    private void loadData() {
        Model.setRowCount(0);
        List<Mahasiswa> list = controller.getAllMahasiswa();
        int no = 1;
        for (Mahasiswa m : list) {
            Model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
        }
    }

    private void tambahData() {
        if (txtNama.getText().trim().isEmpty() || txtNim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return;
        }
        Mahasiswa m = new Mahasiswa(txtNama.getText(), txtNim.getText(), txtJurusan.getText());
        if (controller.tambahMahasiswa(m)) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        }
    }

    private void ubahData() {
        Mahasiswa m = new Mahasiswa(txtNama.getText(), txtNim.getText(), txtJurusan.getText());
        if (controller.ubahMahasiswa(m)) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        }
    }

    private void hapusData() {
        String nim = txtNim.getText();
        if (controller.hapusMahasiswa(nim)) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        }
    }

    private void kosongkanForm() {
        txtNama.setText(null);
        txtNim.setText(null);
        txtJurusan.setText(null);
    }

    private void cariData() {
        String keyword = txtCari.getText().trim();
        Model.setRowCount(0);
        List<Mahasiswa> list = keyword.isEmpty() ? controller.getAllMahasiswa() : controller.cariMahasiswa(keyword);
        int no = 1;
        for (Mahasiswa m : list) {
            Model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}