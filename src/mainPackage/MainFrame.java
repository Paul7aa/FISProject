package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	private Firma firma = new Firma();
	Furnizor furnizor1 = new Furnizor("supplier");
	Furnizor furnizor2 = new Furnizor("supplier2");

	private JList<String> componenteComenziList;
	private DefaultListModel<String> componenteComenziListModel = new DefaultListModel<String>();
	private JTextArea componentaComandaDescriptionTextArea;
	private JLabel lblComponenteComenzi;
	private JTextField txtCodComponenta;
	private JList<String> desktopList;
	private DefaultListModel<String> desktopListModel = new DefaultListModel<String>();
	private JList<String> laptopList;
	private DefaultListModel<String> laptopListModel = new DefaultListModel<String>();

	public void RefreshLists() {
		componenteComenziListModel.clear();
		switch (lblComponenteComenzi.getText()) {
		case "Componente Disponibile":
			firma.ReadComponente();
			if (firma.getComponente() != null) {
				for (var component : firma.getComponente()) {
					componenteComenziListModel.addElement(
							component.getTip() + "  " + component.getCod() + " : " + component.getDenumire());
				}
			}
			break;
		case "Comenzi Trimise":
			furnizor1.ReadComenziPrimite();
			furnizor2.ReadComenziPrimite();
			if (furnizor1.getComenziPrimite() != null) {
				for (var comanda : furnizor1.getComenziPrimite()) {
					componenteComenziListModel.addElement(furnizor1.getDenumire() + " --- "
							+ comanda.getDataInregistrarii() + " : " + comanda.getStatus());
				}
			}

			if (furnizor2.getComenziPrimite() != null) {
				for (var comanda : furnizor2.getComenziPrimite()) {
					componenteComenziListModel.addElement(furnizor2.getDenumire() + " --- "
							+ comanda.getDataInregistrarii() + " : " + comanda.getStatus());
				}
			}

			break;
		}

		RefreshSisteme();
	}

	public void RefreshSisteme() {
		desktopListModel.clear();
		firma.ReadDesktopuri();
		if (firma.getDesktopuri() != null) {
			for (var desktop : firma.getDesktopuri()) {
				desktopListModel.addElement(desktop.getDenumire() + " - cantitate : " + desktop.getCantitate());
			}
		}

		laptopListModel.clear();
		firma.ReadLaptopuri();
		if (firma.getLaptopuri() != null) {
			for (var laptop : firma.getLaptopuri()) {
				laptopListModel.addElement(laptop.getDenumire() + " - cantitate : " + laptop.getCantitate());
			}
		}
	}

	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirma = new JLabel("FIRMA");
		lblFirma.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirma.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirma.setBounds(339, 11, 395, 35);
		contentPane.add(lblFirma);

		JButton btnComandaFurnizor = new JButton("COMANDA FURNIZOR");
		btnComandaFurnizor.setBounds(716, 674, 153, 48);
		contentPane.add(btnComandaFurnizor);

		componenteComenziList = new JList<String>(componenteComenziListModel);
		contentPane.add(componenteComenziList);

		JScrollPane scrollPaneComponente = new JScrollPane(componenteComenziList);
		scrollPaneComponente.setBounds(710, 79, 331, 441);
		contentPane.add(scrollPaneComponente);

		JButton btnListeComponenteComenzi = new JButton("COMENZI TRIMISE");
		btnListeComponenteComenzi.setBounds(888, 674, 153, 48);
		contentPane.add(btnListeComponenteComenzi);

		lblComponenteComenzi = new JLabel("Componente Disponibile");
		lblComponenteComenzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblComponenteComenzi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComponenteComenzi.setBounds(710, 42, 331, 35);
		contentPane.add(lblComponenteComenzi);

		componentaComandaDescriptionTextArea = new JTextArea();
		componentaComandaDescriptionTextArea.setLineWrap(true);
		componentaComandaDescriptionTextArea.setWrapStyleWord(true);
		componentaComandaDescriptionTextArea.setEditable(false);
		contentPane.add(componentaComandaDescriptionTextArea);

		JScrollPane scrollPaneDetaliiComponenta = new JScrollPane(componentaComandaDescriptionTextArea);
		scrollPaneDetaliiComponenta.setBounds(716, 541, 325, 122);
		contentPane.add(scrollPaneDetaliiComponenta);

		JButton btnCautaComponenta = new JButton("CAUTA COMPONENTA");
		btnCautaComponenta.setBounds(888, 733, 153, 48);
		contentPane.add(btnCautaComponenta);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(985, 11, 89, 23);
		contentPane.add(btnLogout);

		txtCodComponenta = new JTextField();
		txtCodComponenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodComponenta.setBounds(716, 733, 153, 48);
		contentPane.add(txtCodComponenta);
		txtCodComponenta.setColumns(10);

		JButton btnAsamblareSistem = new JButton("ASAMBLARE SISTEM");
		btnAsamblareSistem.setBounds(459, 733, 153, 48);
		contentPane.add(btnAsamblareSistem);

		JTabbedPane tabbedPaneSisteme = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneSisteme.setBounds(364, 79, 323, 643);
		contentPane.add(tabbedPaneSisteme);

		JPanel desktopPanel = new JPanel();
		tabbedPaneSisteme.addTab("Desktop", null, desktopPanel, null);
		desktopPanel.setLayout(null);

		desktopList = new JList<String>(desktopListModel);
		contentPane.add(desktopList);

		JScrollPane scrollPaneDesktop = new JScrollPane(desktopList);
		scrollPaneDesktop.setBounds(10, 11, 298, 332);
		desktopPanel.add(scrollPaneDesktop);

		JScrollPane scrollPaneDesktopDescription = new JScrollPane();
		scrollPaneDesktopDescription.setBounds(10, 354, 298, 250);
		desktopPanel.add(scrollPaneDesktopDescription);

		JTextArea textAreaDesktopDescription = new JTextArea();
		scrollPaneDesktopDescription.setViewportView(textAreaDesktopDescription);

		JPanel laptopPanel = new JPanel();
		tabbedPaneSisteme.addTab("Laptop", null, laptopPanel, null);
		laptopPanel.setLayout(null);

		laptopList = new JList<String>(laptopListModel);
		contentPane.add(laptopList);

		JScrollPane scrollPaneLaptopList = new JScrollPane(laptopList);
		scrollPaneLaptopList.setBounds(10, 11, 298, 335);
		laptopPanel.add(scrollPaneLaptopList);

		JScrollPane scrollPaneLaptopDescription = new JScrollPane();
		scrollPaneLaptopDescription.setBounds(10, 357, 298, 247);
		laptopPanel.add(scrollPaneLaptopDescription);

		JTextArea textAreaLaptopDescription = new JTextArea();
		scrollPaneLaptopDescription.setViewportView(textAreaLaptopDescription);

		JLabel lblSisteme = new JLabel("Sisteme");
		lblSisteme.setHorizontalAlignment(SwingConstants.CENTER);
		lblSisteme.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSisteme.setBounds(369, 42, 314, 35);
		contentPane.add(lblSisteme);

		// Refresh View
		RefreshLists();

		btnAsamblareSistem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirmaAsamblareSistem asamblareDialog = new FirmaAsamblareSistem();
				asamblareDialog.setModal(true);
				asamblareDialog.setVisible(true);

				RefreshLists();
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				dispose();
				loginFrame.setVisible(true);
			}
		});

		btnComandaFurnizor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComandaFurnizorDialog dialog = new ComandaFurnizorDialog(firma);
				dialog.setModal(true);
				dialog.setVisible(true);

				RefreshLists();
			}
		});

		componenteComenziList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (componenteComenziList.getSelectedIndex() == -1)
					return;

				switch (lblComponenteComenzi.getText()) {
				case "Componente Disponibile":
					if (componenteComenziList.getSelectedIndex() != -1) {
						componentaComandaDescriptionTextArea.setText(
								firma.getComponente().get(componenteComenziList.getSelectedIndex()).toString());
					}
					break;
				case "Comenzi Trimise":
					if (componenteComenziList.getSelectedIndex() != -1) {
						if (componenteComenziList.getSelectedIndex() <= furnizor1.getComenziPrimite().size() - 1) {
							componentaComandaDescriptionTextArea.setText(furnizor1.getComenziPrimite()
									.get(componenteComenziList.getSelectedIndex()).toString());
						} else {
							componentaComandaDescriptionTextArea.setText(furnizor2.getComenziPrimite().get(
									componenteComenziList.getSelectedIndex() - furnizor1.getComenziPrimite().size())
									.toString());
						}
					}

					break;
				}
			}
		});

		desktopList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (desktopList.getSelectedIndex() == -1)
					return;

				textAreaDesktopDescription
						.setText(firma.getDesktopuri().get(desktopList.getSelectedIndex()).toString());

			}
		});

		laptopList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (laptopList.getSelectedIndex() == -1)
					return;

				textAreaLaptopDescription
						.setText(firma.getLaptopuri().get(laptopList.getSelectedIndex()).toString());

			}
		});

		
		btnListeComponenteComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lblComponenteComenzi.getText().equals("Componente Disponibile")) {
					lblComponenteComenzi.setText("Comenzi Trimise");
					btnListeComponenteComenzi.setText("COMPONENTE");
				} else {
					lblComponenteComenzi.setText("Componente Disponibile");
					btnListeComponenteComenzi.setText("COMENZI TRIMISE");
				}

				componentaComandaDescriptionTextArea.setText("");
				componenteComenziList.setSelectedIndex(-1);
				RefreshLists();
			}
		});

		btnCautaComponenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exista = false;
				int stocFirma = 0;
				int stocFurnizori = 0;
				for (var component : firma.getComponente()) {
					if (component.getCod().equals(txtCodComponenta.getText())) {
						stocFirma = component.getNr_stoc();
						exista = true;
					}
				}
				for (var component : furnizor1.getComponente()) {
					if (component.getCod().equals(txtCodComponenta.getText())) {
						stocFurnizori += component.getNr_stoc();
						exista = true;
					}
				}
				for (var component : furnizor2.getComponente()) {
					if (component.getCod().equals(txtCodComponenta.getText())) {
						stocFurnizori += component.getNr_stoc();
						exista = true;
					}
				}
				if (!exista) {
					JOptionPane.showMessageDialog(null,
							"Componenta cu codul " + txtCodComponenta.getText() + " nu exista!", "Eroare!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					for (int i = 0; i < componenteComenziListModel.size(); i++) {
						if (componenteComenziListModel.get(i).contains(txtCodComponenta.getText())) {
							componenteComenziList.setSelectedIndex(i);
							break;
						}
					}
					JOptionPane.showMessageDialog(null,
							"STOCUL FIRMEI PENTRU PIESA CU CODUL " + txtCodComponenta.getText() + " ESTE: " + stocFirma
									+ "\nSTOCUL FURNIZORILOR PENTRU PIESA CU CODUL " + txtCodComponenta.getText()
									+ " ESTE: " + stocFurnizori,
							"STOC", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

	}
}
