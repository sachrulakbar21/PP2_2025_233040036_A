/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul06;

/**
 *
 * @author ASUS
 */

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class KonverterSuhu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Konverter Suhu - Latihan 2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(350, 120);
            frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

            JLabel lblC = new JLabel("Celcius:");
            JTextField txtC = new JTextField(8);
            JButton btnKonversi = new JButton("Konversi");
            JLabel lblF = new JLabel("Fahrenheit:");
            JLabel lblHasil = new JLabel("");

            btnKonversi.addActionListener(e -> { e.getActionCommand();
                String input = txtC.getText().trim();
                if (input.isEmpty()) {
                    lblHasil.setText("Input kosong");
                    return;
                }
                try {

                  // Mengubah input string menjadi angka desimal (double)
                    double c = Double.parseDouble(input);

                    // menghitung celcius Fahrenheit dengan rumus (c * 9/5) + 32
                    double f = (c * 9.0 / 5.0) + 32.0;
                    lblHasil.setText(String.format("%.2f °F", f));
                } catch (NumberFormatException ex) {

                  // Jika input bukan angka atau kosong, label menampilkan pesan error yang sesuai.
                    lblHasil.setText("Input tidak valid");
                }
            });

            frame.add(lblC);
            frame.add(txtC);
            frame.add(btnKonversi);
            frame.add(lblF);
            frame.add(lblHasil);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}