package id.ac.unpas.modul05;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Asus
 */
public class Latihan2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Buat objek JFrame
            JFrame frame = new JFrame("Jendela dengan Label");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // 2. Buat komponen JLabel
            JLabel label = new JLabel("Ini adalah JLabel.");
            
            // 3. Tambahkan JLabel ke JFrame
            // JFrame secara default pakai BorderLayout
            // .add() akan menaruh komponen ke CENTER secara default
            frame.add(label);
            
            // 4. Tampilkan jendela
            frame.setVisible(true);
        });
    }
}
