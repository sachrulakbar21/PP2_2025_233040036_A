package id.ac.unpas.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Contoh BorderLayout");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // komponen utama
            JLabel label = new JLabel("Label ada di Atas (NORTH)");
            JButton btnSouth = new JButton("Tombol ada di Bawah (SOUTH)");
            JButton btnWest  = new JButton("WEST");
            JButton btnEast  = new JButton("EAST");
            JButton btnCenter= new JButton("CENTER");

            // aksi tombol SOUTH (yang sudah ada)
            btnSouth.addActionListener(e -> label.setText("Tombol di SOUTH diklik!"));

            // tugas : tambahkan aksi untuk WEST, EAST, CENTER
            btnWest.addActionListener(e -> label.setText("Tombol di WEST diklik!"));
            btnEast.addActionListener(e -> label.setText("Tombol di EAST diklik!"));
            btnCenter.addActionListener(e -> label.setText("Tombol di CENTER diklik!"));

            // tambah ke layout
            frame.add(label, BorderLayout.NORTH);
            frame.add(btnSouth, BorderLayout.SOUTH);
            frame.add(btnWest, BorderLayout.WEST);
            frame.add(btnEast, BorderLayout.EAST);
            frame.add(btnCenter, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
