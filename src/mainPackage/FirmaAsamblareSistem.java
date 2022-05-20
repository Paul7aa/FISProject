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
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FirmaAsamblareSistem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Firma firma = new Firma();
	private JTextField tFieldCodProdus;
	private JTextField tFieldPret;
	private JTextField tFieldCantitate;
	private JTextField tFieldDenumire;
	private JTextField tFieldMonitor;
	private JTextField tFieldKeyboard;
	private JTextField tFieldMouse;
	private JTextField tFieldGarantie;
	private JComboBox<String> cBoxSursa;
	private JComboBox<String> cBoxCarcasa;
	private JComboBox<String> cBoxRAM;
	private JComboBox<String> cBoxPlacaVideo;
	private JComboBox<String> cBoxMemorieInterna;
	private JComboBox<String> cBoxProcesor;
	private JComboBox<String> cBoxPlacaDeBaza;
	private JComboBox<String> cBoxTipDesktop;
	private JComboBox<String> cBoxSursaL;
	private JComboBox<String> cBoxCarcasaL;
	private JComboBox<String> cBoxRAML;
	private JComboBox<String> cBoxPlacaVideoL;
	private JComboBox<String> cBoxMemorieInternaL;
	private JComboBox<String> cBoxProcesorL;
	private JComboBox<String> cBoxPlacaDeBazaL;
	private JTextField tFieldCodProdusL;
	private JTextField tFieldPretL;
	private JTextField tFieldCantitateL;
	private JTextField tFieldDenumireL;
	private JTextField tFieldGarantieL;
	private JTextField tFieldGreutate;
	private JTextField tFieldCamera;
	private JTextField tFieldMicrofon;
	private JTextField tFieldDiagonala;

	private void SetComponents(JPanel DesktopPanel, JPanel LaptopPanel) {
		Component[] components = DesktopPanel.getComponents();
		for (Component component : components) {
			if (component instanceof JComboBox) {
				if (((JComboBox<String>) component).getModel().getSize() == 0) {
					((JComboBox<String>) component).addItem("None");
				}
			}
		}

		Component[] componentsL = LaptopPanel.getComponents();
		for (Component component : componentsL) {
			if (component instanceof JComboBox) {
				if (((JComboBox<String>) component).getModel().getSize() == 0) {
					((JComboBox<String>) component).addItem("None");
				}
			}
		}

		for (var componenta : firma.getComponente()) {
			switch (componenta.getTip().toString()) {
			case "PlacaDeBaza":
				cBoxPlacaDeBaza.addItem(componenta.getDenumire());
				cBoxPlacaDeBazaL.addItem(componenta.getDenumire());
				break;
			case "Procesor":
				cBoxProcesor.addItem(componenta.getDenumire());
				cBoxProcesorL.addItem(componenta.getDenumire());
				break;
			case "HDD":
			case "SSD":
				cBoxMemorieInterna.addItem(componenta.getDenumire());
				cBoxMemorieInternaL.addItem(componenta.getDenumire());
				break;
			case "PlacaVideo":
				cBoxPlacaVideo.addItem(componenta.getDenumire());
				cBoxPlacaVideoL.addItem(componenta.getDenumire());
				break;
			case "RAM":
				cBoxRAM.addItem(componenta.getDenumire());
				cBoxRAML.addItem(componenta.getDenumire());
				break;
			case "Carcasa":
				cBoxCarcasa.addItem(componenta.getDenumire());
				cBoxCarcasaL.addItem(componenta.getDenumire());
				break;
			case "Sursa":
				cBoxSursa.addItem(componenta.getDenumire());
				cBoxSursaL.addItem(componenta.getDenumire());
				break;
			}
		}
	}

	public FirmaAsamblareSistem() {
		setResizable(false);
		setBounds(100, 100, 600, 875);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 46, 564, 745);
		contentPanel.add(tabbedPane);

		JPanel DesktopPanel = new JPanel();
		tabbedPane.addTab("Desktop", null, DesktopPanel, null);
		DesktopPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cod Produs");
		lblNewLabel.setBounds(87, 11, 78, 22);
		DesktopPanel.add(lblNewLabel);

		JLabel lblPret = new JLabel("Pret");
		lblPret.setBounds(123, 44, 42, 22);
		DesktopPanel.add(lblPret);

		JLabel lblCantitateAsamblata = new JLabel("Cantitate asamblata");
		lblCantitateAsamblata.setBounds(46, 75, 119, 22);
		DesktopPanel.add(lblCantitateAsamblata);

		JLabel lblDenumire = new JLabel("Denumire Sistem");
		lblDenumire.setBounds(62, 108, 108, 22);
		DesktopPanel.add(lblDenumire);

		JLabel lblNewLabel_1 = new JLabel("Componente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(208, 235, 95, 27);
		DesktopPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Placa de baza");
		lblNewLabel_2.setBounds(75, 278, 94, 14);
		DesktopPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Procesor");
		lblNewLabel_2_1.setBounds(96, 312, 73, 14);
		DesktopPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Memorie interna");
		lblNewLabel_2_1_1.setBounds(66, 347, 103, 14);
		DesktopPanel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Placa Video");
		lblNewLabel_2_1_1_1.setBounds(87, 385, 70, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Memorie Operativa");
		lblNewLabel_2_1_1_1_1.setBounds(52, 421, 113, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Carcasa");
		lblNewLabel_2_1_1_1_1_1.setBounds(99, 458, 70, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("Sursa");
		lblNewLabel_2_1_1_1_1_1_1.setBounds(101, 491, 51, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1_1_1);

		JLabel lblTipDesktop = new JLabel("Tip Desktop");
		lblTipDesktop.setBounds(87, 141, 70, 22);
		DesktopPanel.add(lblTipDesktop);

		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("Monitor");
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(99, 530, 51, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_2 = new JLabel("Keyboard");
		lblNewLabel_2_1_1_1_1_1_1_2.setBounds(87, 567, 65, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1_1_1_2);

		JLabel lblNewLabel_2_1_1_1_1_1_1_2_1 = new JLabel("Mouse");
		lblNewLabel_2_1_1_1_1_1_1_2_1.setBounds(96, 602, 42, 14);
		DesktopPanel.add(lblNewLabel_2_1_1_1_1_1_1_2_1);

		tFieldCodProdus = new JTextField();
		tFieldCodProdus.setBounds(183, 12, 252, 20);
		DesktopPanel.add(tFieldCodProdus);
		tFieldCodProdus.setColumns(10);

		tFieldPret = new JTextField();
		tFieldPret.setColumns(10);
		tFieldPret.setBounds(183, 45, 252, 20);
		DesktopPanel.add(tFieldPret);

		tFieldCantitate = new JTextField();
		tFieldCantitate.setColumns(10);
		tFieldCantitate.setBounds(183, 76, 252, 20);
		DesktopPanel.add(tFieldCantitate);

		tFieldDenumire = new JTextField();
		tFieldDenumire.setColumns(10);
		tFieldDenumire.setBounds(183, 109, 252, 20);
		DesktopPanel.add(tFieldDenumire);

		cBoxTipDesktop = new JComboBox<String>();
		cBoxTipDesktop.setModel(new DefaultComboBoxModel<String>(new String[] { "Gaming", "Office" }));
		cBoxTipDesktop.setBounds(183, 141, 252, 22);
		DesktopPanel.add(cBoxTipDesktop);

		cBoxPlacaDeBaza = new JComboBox<String>();
		cBoxPlacaDeBaza.setBounds(179, 273, 256, 22);
		DesktopPanel.add(cBoxPlacaDeBaza);

		cBoxProcesor = new JComboBox<String>();
		cBoxProcesor.setBounds(179, 308, 256, 22);
		DesktopPanel.add(cBoxProcesor);

		cBoxMemorieInterna = new JComboBox<String>();
		cBoxMemorieInterna.setBounds(179, 343, 256, 22);
		DesktopPanel.add(cBoxMemorieInterna);

		cBoxPlacaVideo = new JComboBox<String>();
		cBoxPlacaVideo.setBounds(179, 381, 256, 22);
		DesktopPanel.add(cBoxPlacaVideo);

		cBoxRAM = new JComboBox<String>();
		cBoxRAM.setBounds(179, 417, 256, 22);
		DesktopPanel.add(cBoxRAM);

		cBoxCarcasa = new JComboBox<String>();
		cBoxCarcasa.setBounds(179, 454, 256, 22);
		DesktopPanel.add(cBoxCarcasa);

		cBoxSursa = new JComboBox();
		cBoxSursa.setBounds(179, 487, 256, 22);
		DesktopPanel.add(cBoxSursa);

		tFieldMonitor = new JTextField();
		tFieldMonitor.setBounds(179, 527, 256, 20);
		DesktopPanel.add(tFieldMonitor);
		tFieldMonitor.setColumns(10);

		tFieldKeyboard = new JTextField();
		tFieldKeyboard.setColumns(10);
		tFieldKeyboard.setBounds(179, 564, 256, 20);
		DesktopPanel.add(tFieldKeyboard);

		tFieldMouse = new JTextField();
		tFieldMouse.setColumns(10);
		tFieldMouse.setBounds(179, 599, 256, 20);
		DesktopPanel.add(tFieldMouse);

		JLabel lblGarantie = new JLabel("Garantie");
		lblGarantie.setBounds(95, 182, 57, 22);
		DesktopPanel.add(lblGarantie);

		tFieldGarantie = new JTextField();
		tFieldGarantie.setColumns(10);
		tFieldGarantie.setBounds(183, 183, 65, 20);
		DesktopPanel.add(tFieldGarantie);

		JLabel luni = new JLabel("luni");
		luni.setBounds(258, 182, 57, 22);
		DesktopPanel.add(luni);

		JButton btnAsamblareDesktop = new JButton("ASAMBLARE DESKTOP");
		btnAsamblareDesktop.setBounds(172, 646, 182, 47);
		DesktopPanel.add(btnAsamblareDesktop);

		JPanel LaptopPanel = new JPanel();
		tabbedPane.addTab("Laptop", null, LaptopPanel, null);

		JButton btnAnulare = new JButton("Anulare");
		btnAnulare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnulare.setBounds(468, 802, 89, 23);
		contentPanel.add(btnAnulare);

		JLabel lblAsamblareSistem = new JLabel("ASAMBLARE SISTEM");
		lblAsamblareSistem.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsamblareSistem.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAsamblareSistem.setBounds(90, 11, 414, 37);
		contentPanel.add(lblAsamblareSistem);

		LaptopPanel.setLayout(null);
		tFieldCodProdusL = new JTextField();
		tFieldCodProdusL.setColumns(10);
		tFieldCodProdusL.setBounds(223, 12, 252, 20);
		LaptopPanel.add(tFieldCodProdusL);

		JLabel lblNewLabel_3 = new JLabel("Cod Produs");
		lblNewLabel_3.setBounds(127, 11, 78, 22);
		LaptopPanel.add(lblNewLabel_3);

		JLabel lblPret_1 = new JLabel("Pret");
		lblPret_1.setBounds(163, 44, 42, 22);
		LaptopPanel.add(lblPret_1);

		tFieldPretL = new JTextField();
		tFieldPretL.setColumns(10);
		tFieldPretL.setBounds(223, 45, 252, 20);
		LaptopPanel.add(tFieldPretL);

		JLabel lblCantitateAsamblata_1 = new JLabel("Cantitate asamblata");
		lblCantitateAsamblata_1.setBounds(86, 75, 119, 22);
		LaptopPanel.add(lblCantitateAsamblata_1);

		tFieldCantitateL = new JTextField();
		tFieldCantitateL.setColumns(10);
		tFieldCantitateL.setBounds(223, 76, 252, 20);
		LaptopPanel.add(tFieldCantitateL);

		JLabel lblDenumire_1 = new JLabel("Denumire Sistem");
		lblDenumire_1.setBounds(102, 108, 108, 22);
		LaptopPanel.add(lblDenumire_1);

		tFieldDenumireL = new JTextField();
		tFieldDenumireL.setColumns(10);
		tFieldDenumireL.setBounds(223, 109, 252, 20);
		LaptopPanel.add(tFieldDenumireL);

		JLabel lblTipLaptop = new JLabel("Tip Laptop");
		lblTipLaptop.setBounds(127, 141, 70, 22);
		LaptopPanel.add(lblTipLaptop);

		JComboBox<String> cBoxTipLaptop = new JComboBox<String>();
		cBoxTipLaptop.setModel(new DefaultComboBoxModel(new String[] { "Mini", "Notebook", "Ultrabook", "Gaming" }));
		cBoxTipLaptop.setBounds(223, 141, 252, 22);
		LaptopPanel.add(cBoxTipLaptop);

		JLabel lblGarantie_1 = new JLabel("Garantie");
		lblGarantie_1.setBounds(135, 182, 57, 22);
		LaptopPanel.add(lblGarantie_1);

		tFieldGarantieL = new JTextField();
		tFieldGarantieL.setColumns(10);
		tFieldGarantieL.setBounds(223, 183, 65, 20);
		LaptopPanel.add(tFieldGarantieL);

		JLabel luni_1 = new JLabel("luni");
		luni_1.setBounds(298, 182, 57, 22);
		LaptopPanel.add(luni_1);

		JLabel lblNewLabel_1_1 = new JLabel("Componente");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(248, 235, 95, 27);
		LaptopPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2_2 = new JLabel("Placa de baza");
		lblNewLabel_2_2.setBounds(115, 278, 94, 14);
		LaptopPanel.add(lblNewLabel_2_2);

		cBoxPlacaDeBazaL = new JComboBox<String>();
		cBoxPlacaDeBazaL.setBounds(219, 273, 256, 22);
		LaptopPanel.add(cBoxPlacaDeBazaL);

		JLabel lblNewLabel_2_1_2 = new JLabel("Procesor");
		lblNewLabel_2_1_2.setBounds(136, 312, 73, 14);
		LaptopPanel.add(lblNewLabel_2_1_2);

		cBoxProcesorL = new JComboBox<String>();
		cBoxProcesorL.setBounds(219, 308, 256, 22);
		LaptopPanel.add(cBoxProcesorL);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("Memorie interna");
		lblNewLabel_2_1_1_2.setBounds(106, 347, 103, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_2);

		cBoxMemorieInternaL = new JComboBox<String>();
		cBoxMemorieInternaL.setBounds(219, 343, 256, 22);
		LaptopPanel.add(cBoxMemorieInternaL);

		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Placa Video");
		lblNewLabel_2_1_1_1_2.setBounds(127, 385, 70, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_2);

		cBoxPlacaVideoL = new JComboBox<String>();
		cBoxPlacaVideoL.setBounds(219, 381, 256, 22);
		LaptopPanel.add(cBoxPlacaVideoL);

		JLabel lblNewLabel_2_1_1_1_1_2 = new JLabel("Memorie Operativa");
		lblNewLabel_2_1_1_1_1_2.setBounds(92, 421, 113, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_2);

		cBoxRAML = new JComboBox<String>();
		cBoxRAML.setBounds(219, 417, 256, 22);
		LaptopPanel.add(cBoxRAML);

		JLabel lblNewLabel_2_1_1_1_1_1_2 = new JLabel("Carcasa");
		lblNewLabel_2_1_1_1_1_1_2.setBounds(139, 458, 70, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_2);

		cBoxCarcasaL = new JComboBox<String>();
		cBoxCarcasaL.setBounds(219, 454, 256, 22);
		LaptopPanel.add(cBoxCarcasaL);

		JLabel lblNewLabel_2_1_1_1_1_1_1_3 = new JLabel("Sursa");
		lblNewLabel_2_1_1_1_1_1_1_3.setBounds(141, 491, 51, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_1_3);

		cBoxSursaL = new JComboBox();
		cBoxSursaL.setBounds(219, 487, 256, 22);
		LaptopPanel.add(cBoxSursaL);

		JLabel lblNewLabel_2_1_1_1_1_1_1_3_1 = new JLabel("Greutate");
		lblNewLabel_2_1_1_1_1_1_1_3_1.setBounds(141, 526, 51, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_1_3_1);

		JLabel lblNewLabel_2_1_1_1_1_1_1_3_2 = new JLabel("Camera");
		lblNewLabel_2_1_1_1_1_1_1_3_2.setBounds(141, 562, 51, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_1_3_2);

		JLabel lblNewLabel_2_1_1_1_1_1_1_3_3 = new JLabel("Microfon");
		lblNewLabel_2_1_1_1_1_1_1_3_3.setBounds(141, 597, 51, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_1_3_3);

		JLabel lblNewLabel_2_1_1_1_1_1_1_3_4 = new JLabel("Diagonala");
		lblNewLabel_2_1_1_1_1_1_1_3_4.setBounds(135, 627, 70, 14);
		LaptopPanel.add(lblNewLabel_2_1_1_1_1_1_1_3_4);

		tFieldGreutate = new JTextField();
		tFieldGreutate.setColumns(10);
		tFieldGreutate.setBounds(219, 523, 252, 20);
		LaptopPanel.add(tFieldGreutate);

		tFieldCamera = new JTextField();
		tFieldCamera.setColumns(10);
		tFieldCamera.setBounds(219, 559, 252, 20);
		LaptopPanel.add(tFieldCamera);

		tFieldMicrofon = new JTextField();
		tFieldMicrofon.setColumns(10);
		tFieldMicrofon.setBounds(219, 594, 252, 20);
		LaptopPanel.add(tFieldMicrofon);

		tFieldDiagonala = new JTextField();
		tFieldDiagonala.setColumns(10);
		tFieldDiagonala.setBounds(219, 624, 252, 20);
		LaptopPanel.add(tFieldDiagonala);

		JButton btnAsamblareLaptop = new JButton("ASAMBLARE LAPTOP");
		btnAsamblareLaptop.setBounds(190, 659, 182, 47);
		LaptopPanel.add(btnAsamblareLaptop);

		SetComponents(DesktopPanel, LaptopPanel);

		btnAsamblareDesktop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int perioadaGarantie, cantitateProdus;
					float pretProdus;
					String errorString = "";
					boolean pieseExista = true;

					Component[] components = DesktopPanel.getComponents();
					for (Component component : components) {
						if (component instanceof JTextField) {
							if (((JTextField) component).getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale", "Eroare Asamblare",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}

					try {

						pretProdus = Float.parseFloat(tFieldPret.getText());
						cantitateProdus = Integer.parseInt(tFieldCantitate.getText());
						perioadaGarantie = Integer.parseInt(tFieldGarantie.getText());

					} catch (NumberFormatException ex) {

						JOptionPane.showMessageDialog(null,
								"Campurile Garantie , Pret si Cantitate Asamblata trebuie sa aiba valori numerice",
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;

					}
					
					if (pretProdus <= 0) {
						JOptionPane.showMessageDialog(null, "Pretul nu poate fi 0 sau mai mic",
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (cantitateProdus > 20) {
						JOptionPane.showMessageDialog(null, "Nu se pot asambla mai mult de 20 simultan",
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (perioadaGarantie > 48) {
						JOptionPane.showMessageDialog(null, "Garantia nu poate intrece 48 luni", "Eroare Asamblare",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					for (Component component : components) {
						if (component instanceof JComboBox) {
							String componentaName = ((JComboBox) component).getSelectedItem().toString();
							for (var componenta : firma.getComponente()) {
								if (componentaName.equals(componenta.getDenumire())) {
									if (componenta.getNr_stoc() < cantitateProdus) {
										errorString += componenta.getDenumire() + " - nr : " + componenta.getNr_stoc()
												+ "\n";
										pieseExista = false;
									}
								}
							}
						}
					}

					if (!pieseExista) {
						JOptionPane.showMessageDialog(null, "Nu exista suficiente piese:\n" + errorString,
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (firma.ProductExists(tFieldCodProdus.getText())) {
						JOptionPane.showMessageDialog(null,
								"Produsul cu codul: " + tFieldCodProdusL.getText() + " exista!", "Eroare Asamblare",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (JOptionPane.NO_OPTION == JOptionPane
							.showConfirmDialog(null,
									"Sunteti sigur ca doriti sa asamblati " + cantitateProdus + " desktop-uri "
											+ tFieldDenumire.getText() + "?",
									"Asamblare Sistem", JOptionPane.YES_NO_OPTION))
						return;

					ArrayList<Componenta> componenteSistem = new ArrayList<Componenta>();

					// Add components to list
					for (Component component : components) {
						if (component instanceof JComboBox) {
							if (((JComboBox) component).equals(cBoxTipDesktop))
								continue;

							for (var componentaFirma : firma.getComponente()) {
								if (((JComboBox) component).getSelectedItem().toString().equals("None"))
									continue;
								if (((JComboBox) component).getSelectedItem().toString()
										.equals(componentaFirma.getDenumire())) {
									componentaFirma.setNr_stoc(componentaFirma.getNr_stoc() - cantitateProdus);
									componenteSistem.add(componentaFirma);
								}
							}
						}
					}
					firma.WriteComponente(); //refresh componente

					for (var desktop : firma.getDesktopuri()) {
						if (desktop.getCod().equals(tFieldCodProdus.getText())) {

							JOptionPane.showMessageDialog(null,
									"Desktop-ul cu codul " + desktop.getCod() + " deja exista." + errorString,
									"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					TipDesktop tipDesktop = (cBoxTipDesktop.getSelectedItem().toString().equals("Gaming"))
							? TipDesktop.Gaming
							: TipDesktop.Office;

					Desktop newDesktop = new Desktop(tFieldCodProdus.getText(), pretProdus, cantitateProdus, 0, 0,
							tFieldDenumire.getText(), componenteSistem,
							new Garantie(perioadaGarantie, StatusGarantie.Inactiv, new ArrayList<Componenta>()),
							tFieldMonitor.getText(), tFieldKeyboard.getText(), tFieldMouse.getText(), tipDesktop);

					firma.WriteDesktop(newDesktop);
					JOptionPane.showMessageDialog(null, "Adaugat cu succes!", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Eroare la asamblare sistem:\n" + ex.getMessage(),
							"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});

		btnAsamblareLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int perioadaGarantie, cantitateProdus;
					float pretProdus;
					float diagonala;
					float greutate;
					String errorString = "";
					boolean pieseExista = true;

					Component[] components = LaptopPanel.getComponents();
					for (Component component : components) {
						if (component instanceof JTextField) {
							if (((JTextField) component).getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale", "Eroare Asamblare",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}

					try {

						pretProdus = Float.parseFloat(tFieldPretL.getText());
						cantitateProdus = Integer.parseInt(tFieldCantitateL.getText());
						perioadaGarantie = Integer.parseInt(tFieldGarantieL.getText());
						diagonala = Float.parseFloat(tFieldDiagonala.getText());
						greutate = Float.parseFloat(tFieldGreutate.getText());

					} catch (NumberFormatException ex) {

						JOptionPane.showMessageDialog(null,
								"Campurile Garantie , Pret, Cantitate Asamblata, Diagonala si Greutate trebuie sa aiba valori numerice",
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;

					}

					if (cantitateProdus > 20) {
						JOptionPane.showMessageDialog(null, "Nu se pot asambla mai mult de 20 simultan",
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (perioadaGarantie > 48) {
						JOptionPane.showMessageDialog(null, "Garantia nu poate intrece 48 luni", "Eroare Asamblare",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					for (Component component : components) {
						if (component instanceof JComboBox) {
							String componentaName = ((JComboBox) component).getSelectedItem().toString();
							for (var componenta : firma.getComponente()) {
								if (componentaName.equals(componenta.getDenumire())) {
									if (componenta.getNr_stoc() < cantitateProdus) {
										errorString += componenta.getDenumire() + " - nr : " + componenta.getNr_stoc()
												+ "\n";
										pieseExista = false;
									}
								}
							}
						}
					}

					if (!pieseExista) {
						JOptionPane.showMessageDialog(null, "Nu exista suficiente piese:\n" + errorString,
								"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (firma.ProductExists(tFieldCodProdusL.getText())) {
						JOptionPane.showMessageDialog(null,
								"Produsul cu codul: " + tFieldCodProdusL.getText() + " exista!", "Eroare Asamblare",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (JOptionPane.NO_OPTION == JOptionPane
							.showConfirmDialog(null,
									"Sunteti sigur ca doriti sa asamblati " + cantitateProdus + " laptop-uri "
											+ tFieldDenumireL.getText() + "?",
									"Asamblare Sistem", JOptionPane.YES_NO_OPTION))
						return;

					ArrayList<Componenta> componenteSistem = new ArrayList<Componenta>();

					// Add components to list
					for (Component component : components) {
						if (component instanceof JComboBox) {
							if (((JComboBox) component).equals(cBoxTipLaptop))
								continue;

							for (var componentaFirma : firma.getComponente()) {
								if (((JComboBox) component).getSelectedItem().toString().equals("None"))
									continue;
								if (((JComboBox) component).getSelectedItem().toString()
										.equals(componentaFirma.getDenumire())) {
									componentaFirma.setNr_stoc(componentaFirma.getNr_stoc() - cantitateProdus);
									componenteSistem.add(componentaFirma);
								}
							}
						}
					}
					firma.WriteComponente();

					for (var laptop : firma.getLaptopuri()) {
						if (laptop.getCod().equals(tFieldCodProdusL.getText())) {

							JOptionPane.showMessageDialog(null,
									"Laptop-ul cu codul " + laptop.getCod() + " deja exista." + errorString,
									"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					TipLaptop tipLaptop = TipLaptop.Notebook;
					switch (cBoxTipLaptop.getSelectedItem().toString()) {
					case "Mini":
						tipLaptop = TipLaptop.Mini;
						break;
					case "Gaming":
						tipLaptop = TipLaptop.Gaming;
						break;
					case "Notebook":
						tipLaptop = TipLaptop.Notebook;
						break;
					case "Ultrabook":
						tipLaptop = TipLaptop.Ultrabook;
						break;
					}

					Laptop newLaptop = new Laptop(tFieldCodProdusL.getText(), pretProdus, cantitateProdus, 0, 0,
							tFieldDenumireL.getText(), componenteSistem,
							new Garantie(perioadaGarantie, StatusGarantie.Inactiv, new ArrayList<Componenta>()),
							greutate, tFieldCamera.getText(), tFieldMicrofon.getText(), diagonala, tipLaptop);

					firma.WriteLaptop(newLaptop);
					JOptionPane.showMessageDialog(null, "Adaugat cu succes!", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Eroare la asamblare sistem:\n" + ex.getMessage(),
							"Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
	}
}
