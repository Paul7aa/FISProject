package mainPackage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirmaAdaugaAux extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tFieldCod;
	private JTextField tFieldPret;
	private JTextField tFieldCantitate;
	private JTextField tFieldDenumire;
	private JComboBox<String> comboBoxTip;

	public FirmaAdaugaAux() {
		setResizable(false);
		setBounds(100, 100, 390, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblAdaugareAux = new JLabel("ADAUGARE SISTEM AUXILIAR");
		lblAdaugareAux.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdaugareAux.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAdaugareAux.setBounds(10, 21, 354, 28);
		contentPanel.add(lblAdaugareAux);

		JLabel lblCod = new JLabel("Cod Produs");
		lblCod.setHorizontalAlignment(SwingConstants.CENTER);
		lblCod.setBounds(26, 80, 118, 14);
		contentPanel.add(lblCod);

		JLabel lblPret = new JLabel("Pret");
		lblPret.setHorizontalAlignment(SwingConstants.CENTER);
		lblPret.setBounds(26, 112, 118, 14);
		contentPanel.add(lblPret);

		JLabel lblCantitate = new JLabel("Cantitate");
		lblCantitate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantitate.setBounds(26, 146, 118, 14);
		contentPanel.add(lblCantitate);

		JLabel lblDenumire = new JLabel("Denumire");
		lblDenumire.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenumire.setBounds(26, 182, 118, 14);
		contentPanel.add(lblDenumire);

		JLabel lblTip = new JLabel("Tip Sistem Aux");
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setBounds(26, 222, 118, 14);
		contentPanel.add(lblTip);

		tFieldCod = new JTextField();
		tFieldCod.setBounds(154, 77, 86, 20);
		contentPanel.add(tFieldCod);
		tFieldCod.setColumns(10);

		tFieldPret = new JTextField();
		tFieldPret.setColumns(10);
		tFieldPret.setBounds(154, 109, 86, 20);
		contentPanel.add(tFieldPret);

		tFieldCantitate = new JTextField();
		tFieldCantitate.setColumns(10);
		tFieldCantitate.setBounds(154, 143, 86, 20);
		contentPanel.add(tFieldCantitate);

		tFieldDenumire = new JTextField();
		tFieldDenumire.setColumns(10);
		tFieldDenumire.setBounds(154, 179, 185, 20);
		contentPanel.add(tFieldDenumire);

		comboBoxTip = new JComboBox<String>();
		comboBoxTip.addItem("Imprimanta");
		comboBoxTip.addItem("Copiator");
		comboBoxTip.addItem("Scanner");
		comboBoxTip.addItem("Microfon");
		comboBoxTip.addItem("Casti");
		comboBoxTip.addItem("Mouse");
		comboBoxTip.addItem("Monitor");
		comboBoxTip.addItem("Keyboard");
		comboBoxTip.setBounds(154, 218, 185, 22);
		contentPanel.add(comboBoxTip);

		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.setBounds(72, 276, 106, 41);
		contentPanel.add(btnAdauga);

		JButton btnAnuleaza = new JButton("Anuleaza");
		btnAnuleaza.setBounds(203, 276, 106, 41);
		contentPanel.add(btnAnuleaza);

		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Firma firma = new Firma();
				float pretProdus = 0;
				int cantitateProdus = 0;
				Component[] components = contentPanel.getComponents();
				for (Component component : components) {
					if (component instanceof JTextField) {
						if (((JTextField) component).getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale!", "Eroare adaugare!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				
				try {

					pretProdus = Float.parseFloat(tFieldPret.getText());
					cantitateProdus = Integer.parseInt(tFieldCantitate.getText());

				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null,
							"Campurile Pret si Cantitate trebuie sa aiba valori numerice",
							"Eroare adaugare!", JOptionPane.ERROR_MESSAGE);
					return;

				}
				
				if (pretProdus <= 0) {
					JOptionPane.showMessageDialog(null, "Pretul nu poate fi 0 sau mai mic",
							"Eroare adaugare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				if (cantitateProdus > 20) {
					JOptionPane.showMessageDialog(null, "Nu se pot asambla mai mult de 20 simultan",
							"Eroare adaugare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (firma.ProductExists(tFieldCod.getText())) {
					JOptionPane.showMessageDialog(null, "Produsul cu codul: " + tFieldCod.getText() + " deja exista!",
							"Eroare adaugare!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (JOptionPane.NO_OPTION == JOptionPane
						.showConfirmDialog(null,
								"Sunteti sigur ca doriti adaugati " + cantitateProdus + " auxiliare "
										+ tFieldDenumire.getText() + "?",
								"Adaugare auxiliare", JOptionPane.YES_NO_OPTION))
					return;
				
				TipSistemAux tip = TipSistemAux.Imprimanta;
				
				switch(comboBoxTip.getSelectedItem().toString().trim()) {
				case "Imprimanta":
					tip = TipSistemAux.Imprimanta;
					break;
				case "Copiator":
					tip = TipSistemAux.Copiator;
					break;
				case "Scanner":
					tip = TipSistemAux.Scanner;
					break;
				case "Microfon":
					tip = TipSistemAux.Microfon;
					break;
				case "Casti":
					tip = TipSistemAux.Casti;
					break;
				case "Mouse":
					tip = TipSistemAux.Mouse;
					break;
				case "Monitor":
					tip = TipSistemAux.Monitor;
					break;
				case "Keyboard":
					tip = TipSistemAux.Keyboard;
					break;
					
				}
				
				SistemAuxiliar sistem = new SistemAuxiliar(tFieldCod.getText(), pretProdus, cantitateProdus, 0, 0, tFieldDenumire.getText(), tip);
				
				firma.WriteAux(sistem);
				dispose();
				JOptionPane.showMessageDialog(null, "Adaugat cu succes!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnAnuleaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
