/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul10;

import id.ac.unpas.modul10.view.MahasiswaApp;
import javax.swing.SwingUtilities;

public class main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
	}
}