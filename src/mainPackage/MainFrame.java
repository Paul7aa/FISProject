package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	private Firma firma = new Firma();
	Furnizor furnizor1 = new Furnizor("supplier");
	Furnizor furnizor2 = new Furnizor("supplier2");

	private JList<String> componenteComenziList;
	private DefaultListModel<String> componenteComenziListModel = new DefaultListModel<String>();
	private JTextArea componentaComandaDescriptionTextArea;
	private JLabel lblComponenteComenzi;
	private JList<String> desktopList;
	private DefaultListModel<String> desktopListModel = new DefaultListModel<String>();
	private JList<String> laptopList;
	private DefaultListModel<String> laptopListModel = new DefaultListModel<String>();
	private JList<String> auxList;
	private DefaultListModel<String> auxListModel = new DefaultListModel<String>();
	private JList<String> promoList;
	private DefaultListModel<String> promoListModel = new DefaultListModel<String>();
	private JList<String> comenziList;
	private DefaultListModel<String> comenziListModel = new DefaultListModel<String>();
	private JList<String> comenziPList;
	private DefaultListModel<String> comenziPListModel = new DefaultListModel<String>();

	public void RefreshLists() {
		componenteComenziListModel.clear();
		switch (lblComponenteComenzi.getText()) {
		case "Componente Disponibile":
			firma.ReadComponente();
			if (firma.getComponente() != null) {
				for (var component : firma.getComponente()) {
					String faulty = "";
					if (component.isFaulty())
						faulty = " - (faulty)";
					componenteComenziListModel.addElement(
							component.getTip() + "  " + component.getCod() + " : " + component.getDenumire() + faulty);
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

		RefreshProduse();
		RefreshComenzi();
	}
	
	public void RefreshComenzi() {
		comenziListModel.clear();
		comenziPListModel.clear();
		
		firma.ReadComenziClient();
		firma.ReadComenziPClient();

		for (var comandaClient : firma.getComenziClient()) {
			comenziListModel.addElement(comandaClient.getDataPlasarii() + " : " + comandaClient.getStatusComanda());
		}
		
		for (var comandaPClient : firma.getComenziPClient()) {
			comenziPListModel.addElement(comandaPClient.getDataPlasarii() + " : " + comandaPClient.getStatusComanda());
		}
	}

	public void RefreshProduse() {
		desktopListModel.clear();
		firma.ReadDesktopuri();
		if (firma.getDesktopuri() != null) {
			for (var desktop : firma.getDesktopuri()) {
				desktopListModel.addElement(desktop.getCod() + " : " + desktop.getDenumire() + " - cantitate : " + desktop.getCantitate());
			}
		}

		laptopListModel.clear();
		firma.ReadLaptopuri();
		if (firma.getLaptopuri() != null) {
			for (var laptop : firma.getLaptopuri()) {
				laptopListModel.addElement(laptop.getCod() + " : " + laptop.getDenumire() + " - cantitate : " + laptop.getCantitate());
			}
		}

		auxListModel.clear();
		firma.ReadAux();
		if (firma.getAuxuri() != null) {
			for (var aux : firma.getAuxuri()) {
				auxListModel.addElement(aux.getTip() + " " + aux.getCod() + " : " + aux.getDenumire() + " - cantitate : " + aux.getCantitate());
			}
		}

		promoListModel.clear();
		firma.ReadPromo();
		if (firma.getPromouri() != null) {
			for (var promo : firma.getPromouri()) {
				promoListModel.addElement(promo.getCod() + " : " + promo.getDenumire() + " - cantitate : " + promo.getCantitate());
			}
		}

	}

	public void CheckGarantii() {
		
		for (var desktop: firma.getDesktopuri()) {
			if(desktop.getGarantie().getStatus() == StatusGarantie.Activ) {
				List<String>componenteSistem = new ArrayList<String>();
				for(var comp : desktop.getComponente()) {
					componenteSistem.add(comp.getCod() + " : " + comp.getDenumire());
				}
				RezolvareGarantieDialog newDialog = new RezolvareGarantieDialog(componenteSistem, desktop.getDenumire());
				newDialog.setModal(true);
				String codDefect = newDialog.run();
				
				if(codDefect.equals(""))
					return;
				
				for (var comp : firma.getComponente()) {
					if(comp.getCod().equals(codDefect)) {
						comp.setFaulty(true);
					}
				}
			}
			desktop.getGarantie().setStatus(StatusGarantie.Inactiv);
		}
		for (var laptop: firma.getLaptopuri()) {
			if(laptop.getGarantie().getStatus() == StatusGarantie.Activ) {
				List<String>componenteSistem = new ArrayList<String>();
				for(var comp : laptop.getComponente()) {
					componenteSistem.add(comp.getCod() + " : " + comp.getDenumire());
				}
				RezolvareGarantieDialog newDialog = new RezolvareGarantieDialog(componenteSistem, laptop.getDenumire());
				newDialog.setModal(true);
				String codDefect = newDialog.run();
				
				if(codDefect.equals(""))
					return;
				
				for (var comp : firma.getComponente()) {
					if(comp.getCod().equals(codDefect)) {
						comp.setFaulty(true);
					}
				}
			}
			laptop.getGarantie().setStatus(StatusGarantie.Inactiv);
		}
		firma.WriteComponente();
		firma.WriteDesktopuri();
		firma.WriteLaptopuri();
	}
	
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFirma = new JLabel("FIRMA");
		lblFirma.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirma.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirma.setBounds(331, 2, 395, 35);
		contentPane.add(lblFirma);

		JButton btnComandaFurnizor = new JButton("COMANDA FURNIZOR");
		btnComandaFurnizor.setBounds(725, 733, 153, 48);
		contentPane.add(btnComandaFurnizor);

		componenteComenziList = new JList<String>(componenteComenziListModel);
		contentPane.add(componenteComenziList);

		JScrollPane scrollPaneComponente = new JScrollPane(componenteComenziList);
		scrollPaneComponente.setBounds(710, 102, 331, 418);
		contentPane.add(scrollPaneComponente);

		JButton btnListeComponenteComenzi = new JButton("COMENZI TRIMISE");
		btnListeComponenteComenzi.setBounds(888, 733, 153, 48);
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
		scrollPaneDetaliiComponenta.setBounds(716, 541, 325, 181);
		contentPane.add(scrollPaneDetaliiComponenta);

		JButton btnCautaComponenta = new JButton("CAUTA COMPONENTA");
		btnCautaComponenta.setBounds(794, 787, 179, 48);
		contentPane.add(btnCautaComponenta);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(985, 11, 89, 23);
		contentPane.add(btnLogout);

		JTabbedPane tabbedPaneProduse = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneProduse.setBounds(364, 79, 323, 702);
		contentPane.add(tabbedPaneProduse);

		JPanel desktopPanel = new JPanel();
		tabbedPaneProduse.addTab("Desktop", null, desktopPanel, null);
		desktopPanel.setLayout(null);

		desktopList = new JList<String>(desktopListModel);
		contentPane.add(desktopList);

		JScrollPane scrollPaneDesktop = new JScrollPane(desktopList);
		scrollPaneDesktop.setBounds(10, 11, 298, 332);
		desktopPanel.add(scrollPaneDesktop);

		JScrollPane scrollPaneDesktopDescription = new JScrollPane();
		scrollPaneDesktopDescription.setBounds(10, 354, 298, 262);
		desktopPanel.add(scrollPaneDesktopDescription);

		JTextArea textAreaDesktopDescription = new JTextArea();
		scrollPaneDesktopDescription.setViewportView(textAreaDesktopDescription);

		JButton btnStergereDesktop = new JButton("STERGE");
		btnStergereDesktop.setBounds(161, 627, 131, 36);
		desktopPanel.add(btnStergereDesktop);

		JButton btnAsamblareSistem = new JButton("ASAMBLARE");
		btnAsamblareSistem.setBounds(20, 627, 131, 36);
		desktopPanel.add(btnAsamblareSistem);

		JPanel laptopPanel = new JPanel();
		tabbedPaneProduse.addTab("Laptop", null, laptopPanel, null);
		laptopPanel.setLayout(null);

		laptopList = new JList<String>(laptopListModel);
		contentPane.add(laptopList);

		JScrollPane scrollPaneLaptopList = new JScrollPane(laptopList);
		scrollPaneLaptopList.setBounds(10, 11, 298, 332);
		laptopPanel.add(scrollPaneLaptopList);

		JScrollPane scrollPaneLaptopDescription = new JScrollPane();
		scrollPaneLaptopDescription.setBounds(10, 354, 298, 262);
		laptopPanel.add(scrollPaneLaptopDescription);

		JTextArea textAreaLaptopDescription = new JTextArea();
		scrollPaneLaptopDescription.setViewportView(textAreaLaptopDescription);

		JButton btnStergereLaptop = new JButton("STERGE");
		btnStergereLaptop.setBounds(161, 627, 131, 36);
		laptopPanel.add(btnStergereLaptop);

		JButton btnAsamblareSistemL = new JButton("ASAMBLARE");
		btnAsamblareSistemL.setBounds(20, 627, 131, 36);
		laptopPanel.add(btnAsamblareSistemL);

		JPanel auxiliarePanel = new JPanel();
		tabbedPaneProduse.addTab("Auxiliare", null, auxiliarePanel, null);
		auxiliarePanel.setLayout(null);

		auxList = new JList<String>(auxListModel);
		contentPane.add(auxList);

		JScrollPane scrollPaneAuxiliareList = new JScrollPane(auxList);
		scrollPaneAuxiliareList.setBounds(10, 11, 298, 332);
		auxiliarePanel.add(scrollPaneAuxiliareList);

		JScrollPane scrollPaneAuxiliarDescription = new JScrollPane();
		scrollPaneAuxiliarDescription.setBounds(10, 354, 298, 262);
		auxiliarePanel.add(scrollPaneAuxiliarDescription);

		JTextArea textAreaAuxiliarDescription = new JTextArea();
		scrollPaneAuxiliarDescription.setViewportView(textAreaAuxiliarDescription);

		JButton btnStergereAuxiliar = new JButton("STERGE");
		btnStergereAuxiliar.setBounds(161, 627, 131, 36);
		auxiliarePanel.add(btnStergereAuxiliar);

		JButton btnAdaugareaAuxiliar = new JButton("ADAUGARE AUX");
		btnAdaugareaAuxiliar.setBounds(20, 627, 131, 36);
		auxiliarePanel.add(btnAdaugareaAuxiliar);

		JPanel promoPanel = new JPanel();
		tabbedPaneProduse.addTab("Promo", null, promoPanel, null);
		promoPanel.setLayout(null);

		promoList = new JList<String>(promoListModel);
		contentPane.add(promoList);

		JScrollPane scrollPanePromoList = new JScrollPane(promoList);
		scrollPanePromoList.setBounds(10, 11, 298, 332);
		promoPanel.add(scrollPanePromoList);

		JScrollPane scrollPanePromoDescription = new JScrollPane();
		scrollPanePromoDescription.setBounds(10, 354, 298, 262);
		promoPanel.add(scrollPanePromoDescription);

		JTextArea textAreaPromoDescription = new JTextArea();
		scrollPanePromoDescription.setViewportView(textAreaPromoDescription);

		JButton btnStergerePromo = new JButton("STERGE");
		btnStergerePromo.setBounds(161, 627, 131, 36);
		promoPanel.add(btnStergerePromo);

		JButton btnAdaugarePromo = new JButton("ADAUGARE");
		btnAdaugarePromo.setBounds(20, 627, 131, 36);
		promoPanel.add(btnAdaugarePromo);

		JLabel lblSisteme = new JLabel("Produse");
		lblSisteme.setHorizontalAlignment(SwingConstants.CENTER);
		lblSisteme.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSisteme.setBounds(369, 42, 314, 35);
		contentPane.add(lblSisteme);
		
		JButton btnCautaProdus = new JButton("CAUTA PRODUS");
		btnCautaProdus.setBounds(439, 787, 179, 48);
		contentPane.add(btnCautaProdus);
		
		JLabel lblComenzi = new JLabel("Comenzi");
		lblComenzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblComenzi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComenzi.setBounds(25, 42, 314, 35);
		contentPane.add(lblComenzi);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 79, 347, 702);
		contentPane.add(tabbedPane);
		
		JPanel comenziPanel = new JPanel();
		tabbedPane.addTab("Comenzi", null, comenziPanel, null);
		comenziPanel.setLayout(null);
		
		comenziList = new JList<String>(comenziListModel);
		contentPane.add(comenziList);
		
		JScrollPane comenziScrollPane = new JScrollPane(comenziList);
		comenziScrollPane.setBounds(10, 11, 322, 333);
		comenziPanel.add(comenziScrollPane);
		
		JScrollPane scrollPaneComanda = new JScrollPane();
		scrollPaneComanda.setBounds(10, 355, 322, 262);
		comenziPanel.add(scrollPaneComanda);
		
		JTextArea textAreaComanda = new JTextArea();
		scrollPaneComanda.setViewportView(textAreaComanda);
		
		JButton btnAcceptaComanda = new JButton("ACCEPTA");
		btnAcceptaComanda.setBounds(190, 628, 131, 36);
		comenziPanel.add(btnAcceptaComanda);
		
		JPanel personalizatPanel = new JPanel();
		tabbedPane.addTab("Sisteme Personalizate", null, personalizatPanel, null);
		personalizatPanel.setLayout(null);
		
		comenziPList = new JList<String>(comenziPListModel);
		contentPane.add(comenziPList);
		
		JScrollPane comenziPScrollPane = new JScrollPane(comenziPList);
		comenziPScrollPane.setBounds(10, 11, 322, 333);
		personalizatPanel.add(comenziPScrollPane);
		
		JScrollPane scrollPaneComandaP = new JScrollPane();
		scrollPaneComandaP.setBounds(10, 355, 322, 262);
		personalizatPanel.add(scrollPaneComandaP);
		
		JTextArea textAreaComandaP = new JTextArea();
		scrollPaneComandaP.setViewportView(textAreaComandaP);
		
		JButton btnAcceptaComandaP = new JButton("ACCEPTA");
		btnAcceptaComandaP.setBounds(190, 628, 131, 36);
		personalizatPanel.add(btnAcceptaComandaP);

		CheckGarantii();
		
		// Refresh View
		RefreshLists();
		
		// ADDING
		// FUNCTIONS------------------------------------------------------------------------------------

		btnAsamblareSistem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirmaAsamblareSistem asamblareDialog = new FirmaAsamblareSistem();
				asamblareDialog.setModal(true);
				asamblareDialog.setVisible(true);

				RefreshLists();
			}
		});

		btnAsamblareSistemL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAsamblareSistem.doClick();
			}
		});

		btnAdaugareaAuxiliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirmaAdaugaAux adaugareAuxDialog = new FirmaAdaugaAux();
				adaugareAuxDialog.setModal(true);
				adaugareAuxDialog.setVisible(true);

				RefreshLists();
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
		
		btnAdaugarePromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirmaAdaugaPromo newPromoFrame = new FirmaAdaugaPromo();
				newPromoFrame.setModal(true);
				newPromoFrame.setVisible(true);
				
				RefreshLists();
			}
		});

		btnAcceptaComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comenziList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Selectati comanda!", "Eroare acceptare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				firma.AcceptaComanda(comenziList.getSelectedIndex());
				RefreshLists();
				JOptionPane.showMessageDialog(null, "Comanda a fost acceptata", "Acceptare succes", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnAcceptaComandaP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comenziPList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Selectati comanda!", "Eroare acceptare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				firma.AcceptaComandaP(comenziPList.getSelectedIndex());
				RefreshLists();
				JOptionPane.showMessageDialog(null, "Comanda a fost acceptata", "Acceptare succes", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		// ------------------------------------------------------------------------------------------------------
		// DELETING
		// FUNCTIONS---------------------------------------------------------------------------------------------

		btnStergereDesktop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (desktopList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Nu s-a selectat nici un sistem!", "Eroare stergere!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
						"Sunteti siguri ca doriti sa stergeti sistemul selectat?", "STERGERE SISTEM",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
					firma.DeleteDesktop(firma.getDesktopuri().get(desktopList.getSelectedIndex()));
					RefreshLists();
				}
			}
		});

		btnStergereLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (laptopList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Nu s-a selectat nici un sistem!", "Eroare stergere!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
						"Sunteti siguri ca doriti sa stergeti sistemul selectat?", "STERGERE SISTEM",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
					firma.DeleteLaptop(firma.getLaptopuri().get(laptopList.getSelectedIndex()));
					RefreshLists();
				}
			}
		});

		btnStergereAuxiliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (auxList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Nu s-a selectat nici un auxiliar!", "Eroare stergere!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
						"Sunteti siguri ca doriti sa stergeti auxiliarul selectat?", "STERGERE AUXILIAR",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
					firma.DeleteAux(firma.getAuxuri().get(auxList.getSelectedIndex()));
					RefreshLists();
				}
			}
		});

		// ------------------------------------------------------------------------------------------------------
		// LOGOUT------------------------------------------------------------------------------------------------

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				dispose();
				loginFrame.setVisible(true);
			}
		});

		// ------------------------------------------------------------------------------------------------------
		// LIST
		// LISTENERS---------------------------------------------------------------------------------------------

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

				textAreaLaptopDescription.setText(firma.getLaptopuri().get(laptopList.getSelectedIndex()).toString());

			}
		});

		auxList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (auxList.getSelectedIndex() == -1)
					return;

				textAreaAuxiliarDescription.setText(firma.getAuxuri().get(auxList.getSelectedIndex()).toString());

			}
		});
		
		promoList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (promoList.getSelectedIndex() == -1)
					return;

				textAreaPromoDescription.setText(firma.getPromouri().get(promoList.getSelectedIndex()).toString());

			}
		});
		
		comenziList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (comenziList.getSelectedIndex() == -1)
					return;

				textAreaComanda.setText(firma.getComenziClient().get(comenziList.getSelectedIndex()).toString());

			}
		});

		comenziPList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (comenziPList.getSelectedIndex() == -1)
					return;

				textAreaComandaP.setText(firma.getComenziPClient().get(comenziPList.getSelectedIndex()).toString());

			}
		});
	

		// ---------------------------------------------------------------------------------------------------------
		// SEARCH
		// BUTTONS--------------------------------------------------------------------------------------------------

		btnCautaComponenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exista = false;
				int stocFirma = 0;
				int stocFurnizori = 0;
				String codCautat = JOptionPane.showInputDialog(null, "Dati codul componentei pe care o cautati:");
				if (codCautat == null)
					return;

				if (codCautat.isEmpty()) {
					JOptionPane.showMessageDialog(null, "S-a introdus cod gol!", "Eroare!", JOptionPane.ERROR_MESSAGE);
					return;
				}

				for (var component : firma.getComponente()) {
					if (component.getCod().equals(codCautat)) {
						stocFirma = component.getNr_stoc();
						exista = true;
					}
				}
				for (var component : furnizor1.getComponente()) {
					if (component.getCod().equals(codCautat)) {
						stocFurnizori += component.getNr_stoc();
						exista = true;
					}
				}
				for (var component : furnizor2.getComponente()) {
					if (component.getCod().equals(codCautat)) {
						stocFurnizori += component.getNr_stoc();
						exista = true;
					}
				}
				if (!exista) {
					JOptionPane.showMessageDialog(null, "Componenta cu codul " + codCautat + " nu exista!", "Eroare!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					for (int i = 0; i < componenteComenziListModel.size(); i++) {
						if (componenteComenziListModel.get(i).contains(codCautat)) {
							componenteComenziList.setSelectedIndex(i);
							break;
						}
					}
					JOptionPane.showMessageDialog(null,
							"STOCUL FIRMEI PENTRU PIESA CU CODUL " + codCautat + " ESTE: " + stocFirma
									+ "\nSTOCUL FURNIZORILOR PENTRU PIESA CU CODUL " + codCautat + " ESTE: "
									+ stocFurnizori,
							"STOC", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		btnCautaProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codCautat = JOptionPane.showInputDialog(null, "Dati codul produsului pe care il cautati:");
				if (codCautat == null)
					return;

				if (codCautat.isEmpty()) {
					JOptionPane.showMessageDialog(null, "S-a introdus cod gol!", "Eroare cautare produs!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					String informatii = "";
					String returnate = "";
					boolean exista = false;
					for(var produs : firma.getProduse()) {
						if (produs.getCod().toLowerCase().equals(codCautat.toLowerCase())) {
							exista = true;
							informatii = "Cod Produs : " + codCautat.toLowerCase();
							if(produs instanceof Desktop) {
								 informatii += "\nTip : Desktop \nDenumire produs : " + ((Desktop)produs).getDenumire();
								 returnate += ((Desktop)produs).getGarantie().getNrReturnat();
							}else {
								if(produs instanceof Laptop) {
									informatii += "\nTip : Laptop \nDenumire produs : " +((Laptop)produs).getDenumire();
									 returnate += ((Laptop)produs).getGarantie().getNrReturnat();
									 tabbedPaneProduse.setSelectedIndex(1);
								}else {
									if(produs instanceof SistemAuxiliar) {
										informatii +="\nTip : Aux \nDenumire produs : " + ((SistemAuxiliar)produs).getDenumire();
										 tabbedPaneProduse.setSelectedIndex(2);
									}else {
										if(produs instanceof Promo) {
											informatii += "\nTip : Promo \nDenumire produs : " +((Promo)produs).getDenumire();
											 tabbedPaneProduse.setSelectedIndex(3);
										}
									}
								}
							}
							
							informatii += "\nStoc Disponibil : " + produs.getCantitate() + "\nCantitate comandata : " + produs.getCantitateComandata() +
									"\nVandut : " + produs.getCantitateLivrata();
							informatii = (!returnate.isEmpty())? informatii + "\nReturnate pentru garantie : " + returnate : informatii;
							
							break;
						}
					}	
					
					if(!exista) {
						JOptionPane.showMessageDialog(null, "Produsul cu codul " + codCautat +" nu exista!" , "Eroare cautare produs!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					JOptionPane.showMessageDialog(null, "Produs gasit!\n" + informatii , "Produs gasit", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare cautare produs!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// --------------------------------------------------------------------------------------------------------
		// REFRESH COMPONENTE
		// COMENZI-------------------------------------------------------------------------------------------------

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

		// --------------------------------------------------------------------------------------------------------
	}
}
