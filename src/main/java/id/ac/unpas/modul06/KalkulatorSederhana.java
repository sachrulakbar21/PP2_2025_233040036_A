/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul06;

/**
 *
 * @author ASUS
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Latihan 1 - Kalkulator Sederhana (Layout)
 * Tampilan GUI saja. Menggunakan BorderLayout untuk frame utama dan
 * GridLayout (4x4) untuk tombol-tombol kalkulator. Tombol hanya menuliskan
 * labelnya ke layar (JTextField) — tidak ada perhitungan.
 */
public class KalkulatorSederhana {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Kalkulator Sederhana - Latihan 1");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);
            frame.setLayout(new BorderLayout(5, 5));

            // Layar di atas (NORTH)
            JTextField display = new JTextField();
            display.setEditable(false);
            display.setHorizontalAlignment(JTextField.RIGHT);
            frame.add(display, BorderLayout.NORTH);

            // Panel tombol di tengah menggunakan GridLayout 4x4
            JPanel buttons = new JPanel(new GridLayout(4, 4, 5, 5));

            // Urutan tombol (baris demi baris):
            // 1 2 3 /
            // 4 5 6 *
            // 7 8 9 -
            // . 0 = +
            String[] labels = {
                "1", "2", "3", "/",
                "4", "5", "6", "*",
                "7", "8", "9", "-",
                ".", "0", "=", "+"
            };

            for (String text : labels) {
                JButton b = new JButton(text);
                // listener sederhana: tampilkan label tombol di layar (append)
                b.addActionListener(e -> display.setText(display.getText() + ((JButton) e.getSource()).getText()));
                buttons.add(b);
            }

            frame.add(buttons, BorderLayout.CENTER);

            // Tampilkan jendela
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}