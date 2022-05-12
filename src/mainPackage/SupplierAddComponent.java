package mainPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SupplierAddComponent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Furnizor furnizor;
	private JTextField tFieldCod;
	private JTextField tFieldNrStoc;
	private JTextArea tCaracteristici;
	private JTextField tFieldDenumire;
	
	
	public SupplierAddComponent(Furnizor furnizor) {
		this.furnizor = furnizor;
		setResizable(false);
		setBounds(100, 100, 450, 486);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.setBounds(100, 338, 89, 23);
		contentPanel.add(btnAdauga);
		
		JButton btnAnuleaza = new JButton("Anuleaza");
		btnAnuleaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnuleaza.setBounds(254, 338, 89, 23);
		contentPanel.add(btnAnuleaza);
		
		JComboBox<String> comboBoxTip = new JComboBox<String>();
		comboBoxTip.setBounds(127, 68, 268, 22);
		comboBoxTip.addItem("PlacaDeBaza");
		comboBoxTip.addItem("Procesor");
		comboBoxTip.addItem("HDD");
		comboBoxTip.addItem("PlacaVideo");
		comboBoxTip.addItem("RAM");
		comboBoxTip.addItem("Carcasa");
		comboBoxTip.addItem("Sursa");
		contentPanel.add(comboBoxTip);
		
		JLabel AdaugaComponentaLBL = new JLabel("Adauga Componenta");
		AdaugaComponentaLBL.setFont(new Font("Tahoma", Font.BOLD, 24));
		AdaugaComponentaLBL.setBounds(100, 11, 268, 39);
		contentPanel.add(AdaugaComponentaLBL);
		
		JLabel lblNewLabel = new JLabel("Tip componenta");
		lblNewLabel.setBounds(24, 72, 81, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblCod = new JLabel("Cod");
		lblCod.setBounds(35, 155, 70, 14);
		contentPanel.add(lblCod);
		
		JLabel lblNrStoc = new JLabel("Nr. stoc");
		lblNrStoc.setBounds(35, 196, 70, 14);
		contentPanel.add(lblNrStoc);
		
		JLabel lblCaracterisitici = new JLabel("Caracterisitici");
		lblCaracterisitici.setBounds(35, 239, 70, 14);
		contentPanel.add(lblCaracterisitici);
		
		tFieldCod = new JTextField();
		tFieldCod.setBounds(127, 152, 268, 20);
		contentPanel.add(tFieldCod);
		tFieldCod.setColumns(10);
		
		tFieldNrStoc = new JTextField();
		tFieldNrStoc.setColumns(10);
		tFieldNrStoc.setBounds(127, 193, 268, 20);
		contentPanel.add(tFieldNrStoc);
		
		tCaracteristici = new JTextArea();
		tCaracteristici.setBounds(254, 229, 268, 70);
		tCaracteristici.setLineWrap(true);
		tCaracteristici.setWrapStyleWord(true);
		contentPanel.add(tCaracteristici);
		
		JScrollPane ScrollPaneCaracteristici = new JScrollPane(tCaracteristici);
		ScrollPaneCaracteristici.setBounds(127, 239, 268, 82);
		contentPanel.add(ScrollPaneCaracteristici);
		
		JLabel lblDenumire = new JLabel("Denumire");
		lblDenumire.setBounds(35, 113, 70, 14);
		contentPanel.add(lblDenumire);
		
		tFieldDenumire = new JTextField();
		tFieldDenumire.setColumns(10);
		tFieldDenumire.setBounds(127, 110, 268, 20);
		contentPanel.add(tFieldDenumire);
		
		
		
		
		//EVENTS
		//ADAUGA BUTTON PRESSED
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(var component: furnizor.getComponente()) {
					if(component.getCod().equals(tFieldCod.getText())) {
					
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog 
								(null, "Componenta deja exista, doriti sa adaugati la/stergeti din stoc?",
										"Componenta existenta", JOptionPane.YES_NO_OPTION)){
							try {
							int input = Integer.parseInt(JOptionPane.showInputDialog(null, "Dati numarul de componente de adaugat/sters(-): "));
							component.setNr_stoc(component.getNr_stoc() + input);
							if (component.getNr_stoc() < 0)
								component.setNr_stoc(0);
							furnizor.WriteComponente();
							dispose();
							return;
							}catch(NumberFormatException ex) {
								 JOptionPane.showMessageDialog(null, "Nu este numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
								 return;
							}
							
						}else {
							return;
						}
					}
				}
				
				
				if(tFieldCod.getText().isEmpty() 
						|| tFieldDenumire.getText().isEmpty()
						|| tFieldNrStoc.getText().isEmpty()
						|| tCaracteristici.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale!",
				    	      "Eroare adaugare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(tCaracteristici.getText().length() > 300) {
				    JOptionPane.showMessageDialog(null, "Campul caracteristici nu poate contine mai mult de 300 caractere!",
				    	      "Eroare adaugare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for(var component: furnizor.getComponente()) {
					if(component.getDenumire().equals(tFieldDenumire.getText())) {
						JOptionPane.showMessageDialog(null, "Exista deja componenta cu aceeasi denumire/cod: " 
								+ component.getCod(), "Eroare adaugare", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				
				Integer nrStoc;
				try {
					nrStoc = Integer.parseInt(tFieldNrStoc.getText());
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Nr stoc nu este numar!", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Componenta newComponent = new Componenta(TipComponenta.valueOf(comboBoxTip.getSelectedItem().toString()),
						tFieldDenumire.getText(), tFieldCod.getText(), nrStoc, tCaracteristici.getText());
				
				furnizor.getComponente().add(newComponent);
				furnizor.WriteComponente();
				dispose();
			}
		});
	}
}
