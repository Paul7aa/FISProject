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

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ClientSistemPersonalizat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Firma firma = new Firma();
	JComboBox<String> cBoxSursa;
	JComboBox<String> cBoxPlacaDeBaza;
	JComboBox<String> cBoxProcesor;
	JComboBox<String> cBoxPlacaVideo;
	JComboBox<String> cBoxRAM;
	JComboBox<String> cBoxMemorieInterna;
	JComboBox<String> cBoxCarcasa;
	private List<Componenta> componenteSistem = new ArrayList<Componenta>();

	public List<Componenta> run() {
		this.setVisible(true);
		return componenteSistem;
	}

	private void SetComponents() {
		Component[] components = contentPanel.getComponents();
		for (Component component : components) {
			if (component instanceof JComboBox) {
				if (((JComboBox<String>) component).getModel().getSize() == 0) {
					((JComboBox<String>) component).addItem("None");
				}
			}
		}
		
		
		for (var componenta : firma.getComponente()) {
			if (componenta.getNr_stoc() > 0)
				switch (componenta.getTip().toString()) {
				case "PlacaDeBaza":
					cBoxPlacaDeBaza.addItem(componenta.getDenumire());
					break;
				case "Procesor":
					cBoxProcesor.addItem(componenta.getDenumire());
					break;
				case "HDD":
				case "SSD":
					cBoxMemorieInterna.addItem(componenta.getDenumire());
					break;
				case "PlacaVideo":
					cBoxPlacaVideo.addItem(componenta.getDenumire());
					break;
				case "RAM":
					cBoxRAM.addItem(componenta.getDenumire());
					break;
				case "Carcasa":
					cBoxCarcasa.addItem(componenta.getDenumire());
					break;
				case "Sursa":
					cBoxSursa.addItem(componenta.getDenumire());
					break;
				}
		}
	}

	public ClientSistemPersonalizat() {
		setResizable(false);
		setBounds(100, 100, 600, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnAnulare = new JButton("ANULARE");
		btnAnulare.setBounds(299, 384, 183, 47);
		contentPanel.add(btnAnulare);

		JLabel lblAsamblareSistem = new JLabel("COMANDA SISTEM");
		lblAsamblareSistem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsamblareSistem.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAsamblareSistem.setBounds(86, 23, 414, 37);
		contentPanel.add(lblAsamblareSistem);

		cBoxSursa = new JComboBox<String>();
		cBoxSursa.setBounds(199, 311, 256, 22);
		contentPanel.add(cBoxSursa);

		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Sursa");
		lblNewLabel_2_1_1_1_1_1_1.setBounds(121, 315, 51, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Carcasa");
		lblNewLabel_2_1_1_1_1_1.setBounds(119, 282, 70, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1_1);

		cBoxCarcasa = new JComboBox<String>();
		cBoxCarcasa.setBounds(199, 278, 256, 22);
		contentPanel.add(cBoxCarcasa);

		cBoxRAM = new JComboBox<String>();
		cBoxRAM.setBounds(199, 241, 256, 22);
		contentPanel.add(cBoxRAM);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Memorie Operativa");
		lblNewLabel_2_1_1_1_1.setBounds(72, 245, 113, 14);
		contentPanel.add(lblNewLabel_2_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Placa Video");
		lblNewLabel_2_1_1_1.setBounds(107, 209, 70, 14);
		contentPanel.add(lblNewLabel_2_1_1_1);

		cBoxPlacaVideo = new JComboBox<String>();
		cBoxPlacaVideo.setBounds(199, 205, 256, 22);
		contentPanel.add(cBoxPlacaVideo);

		cBoxMemorieInterna = new JComboBox<String>();
		cBoxMemorieInterna.setBounds(199, 167, 256, 22);
		contentPanel.add(cBoxMemorieInterna);

		JLabel lblNewLabel_2_1_1 = new JLabel("Memorie interna");
		lblNewLabel_2_1_1.setBounds(86, 171, 103, 14);
		contentPanel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Procesor");
		lblNewLabel_2_1.setBounds(116, 136, 73, 14);
		contentPanel.add(lblNewLabel_2_1);

		cBoxProcesor = new JComboBox<String>();
		cBoxProcesor.setBounds(199, 132, 256, 22);
		contentPanel.add(cBoxProcesor);

		cBoxPlacaDeBaza = new JComboBox<String>();
		cBoxPlacaDeBaza.setBounds(199, 97, 256, 22);
		contentPanel.add(cBoxPlacaDeBaza);

		JLabel lblNewLabel_2 = new JLabel("Placa de baza");
		lblNewLabel_2.setBounds(95, 102, 94, 14);
		contentPanel.add(lblNewLabel_2);

		JButton btnTrimiteComanda = new JButton("TRIMITE COMANDA");
		btnTrimiteComanda.setBounds(107, 384, 182, 47);
		contentPanel.add(btnTrimiteComanda);

		SetComponents();

		btnAnulare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnTrimiteComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<String> componenteDenumire = new ArrayList<String>();

				Component[] pageComponents = contentPanel.getComponents();
				for (int i = 0; i < pageComponents.length; i++) {
					if (!(pageComponents[i] instanceof JComboBox))
						continue;
					if (((JComboBox) pageComponents[i]).getSelectedItem().toString().equals("None")) {
						JOptionPane.showMessageDialog(null, "Sistemul trebuie sa contina toate componentele!",
								"Eroare adaugare sistem personalizat", JOptionPane.ERROR_MESSAGE);
						return;
					}

					for (var component : firma.getComponente()) {
						if (component.getDenumire()
								.equals(((JComboBox) pageComponents[i]).getSelectedItem().toString())) {
							componenteSistem.add(component);
						}
					}

				}

				dispose();
			}
		});

	}

}
