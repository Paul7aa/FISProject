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
	private JTextField textField;
	private JComboBox cBoxSursa;
	private JComboBox cBoxCarcasa;
	private JComboBox cBoxRAM;
	private JComboBox cBoxPlacaVideo;
	private JComboBox cBoxMemorieInterna;
	private JComboBox cBoxProcesor;
	private JComboBox cBoxPlacaDeBaza;
	private JComboBox cBoxTipDesktop;
	
	private void SetComponents(JPanel DesktopPanel, JPanel LaptopPanel) {
		Component[] components = DesktopPanel.getComponents();
		for(Component component : components){
			if (component instanceof JComboBox) {
				if (((JComboBox)component).getModel().getSize() == 0) {
					((JComboBox)component).addItem("None");
				}
			}
		}
			
			for(var componenta : firma.getComponente()) {
				switch(componenta.getTip().toString()) {
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
		
		cBoxTipDesktop = new JComboBox();
		cBoxTipDesktop.setModel(new DefaultComboBoxModel(new String[] {"Gaming", "Office"}));
		cBoxTipDesktop.setBounds(183, 141, 252, 22);
		DesktopPanel.add(cBoxTipDesktop);
		
		cBoxPlacaDeBaza = new JComboBox();
		cBoxPlacaDeBaza.setBounds(179, 273, 256, 22);
		DesktopPanel.add(cBoxPlacaDeBaza);
		
		cBoxProcesor = new JComboBox();
		cBoxProcesor.setBounds(179, 308, 256, 22);
		DesktopPanel.add(cBoxProcesor);
		
		cBoxMemorieInterna = new JComboBox();
		cBoxMemorieInterna.setBounds(179, 343, 256, 22);
		DesktopPanel.add(cBoxMemorieInterna);
		
		cBoxPlacaVideo = new JComboBox();
		cBoxPlacaVideo.setBounds(179, 381, 256, 22);
		DesktopPanel.add(cBoxPlacaVideo);
		
		cBoxRAM = new JComboBox();
		cBoxRAM.setBounds(179, 417, 256, 22);
		DesktopPanel.add(cBoxRAM);
		
		cBoxCarcasa = new JComboBox();
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(183, 183, 65, 20);
		DesktopPanel.add(textField);
		
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
		
		
		SetComponents(DesktopPanel, LaptopPanel);
		
		btnAsamblareDesktop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component[] components = DesktopPanel.getComponents();
				for(Component component : components){
					if (component instanceof JTextField) {
						if (((JTextField)component).getText().isEmpty()) {
							 JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale", "Eroare Asamblare", JOptionPane.ERROR_MESSAGE);
							 return;
						}
					}
				}
			}
		});
	}
}
