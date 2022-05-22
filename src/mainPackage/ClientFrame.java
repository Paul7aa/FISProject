package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
import java.awt.Component;

public class ClientFrame extends JFrame {

	private Client client = new Client();
	private Firma firma = new Firma();
	private JList<String> desktopList;
	private DefaultListModel<String> desktopListModel = new DefaultListModel<String>();
	private JList<String> laptopList;
	private DefaultListModel<String> laptopListModel = new DefaultListModel<String>();
	private JList<String> auxList;
	private DefaultListModel<String> auxListModel = new DefaultListModel<String>();
	private JList<String> promoList;
	private DefaultListModel<String> promoListModel = new DefaultListModel<String>();
	private JList<String> cosList;
	private DefaultListModel<String> cosListModel = new DefaultListModel<String>();
	private JList<String> comenziList;
	private DefaultListModel<String> comenziListModel = new DefaultListModel<String>();
	private JList<String> comenziPList;
	private DefaultListModel<String> comenziPListModel = new DefaultListModel<String>();
	private JList<String> produseClientList;
	private DefaultListModel<String> produseClientListModel = new DefaultListModel<String>();

	private List<Produs> produseComandate = new ArrayList<Produs>();
	private List<Componenta> sistemPersonalizat = new ArrayList<Componenta>();

	private JPanel contentPane;
	JLabel lblPret;

	public void UpdatePrice() {

		float price = 0;
		GetProduseComandate();

		if (produseComandate.size() == 0) {
			lblPret.setText("0");
			return;
		}

		for (var produs : produseComandate) {
			price += produs.getPret();
		}

		lblPret.setText(String.valueOf(price));
	}

	public void UpdateProduseStatus() {
		for (var produsComandat : produseComandate) {
			for (var desktop : firma.getDesktopuri()) {
				if (produsComandat.getCod().equals(desktop.getCod())) {
					desktop.setCantitateComandata(desktop.getCantitateComandata() + 1);
					break;
				}
			}
			for (var laptop : firma.getLaptopuri()) {
				if (produsComandat.getCod().equals(laptop.getCod())) {
					laptop.setCantitateComandata(laptop.getCantitateComandata() + 1);
					break;
				}
			}

			for (var aux : firma.getAuxuri()) {
				if (produsComandat.getCod().equals(aux.getCod())) {
					aux.setCantitateComandata(aux.getCantitateComandata() + 1);
					break;
				}
			}

			for (var promo : firma.getPromouri()) {
				if (produsComandat.getCod().equals(promo.getCod())) {
					promo.setCantitateComandata(promo.getCantitateComandata() + 1);
					break;
				}
			}
		}

		firma.WriteAuxuri();
		firma.WritePromouri();
		firma.WriteDesktopuri();
		firma.WriteLaptopuri();

	}

	public void GetProduseComandate() {

		produseComandate.clear();
		for (int i = 0; i < cosListModel.size(); i++) {
			for (var produs : firma.getProduse()) {
				if (produs instanceof Desktop) {
					if (((Desktop) produs).getDenumire().equals(cosListModel.get(i))) {
						produseComandate.add(((Desktop) produs));
						break;
					}
				} else {
					if (produs instanceof Laptop) {
						if (((Laptop) produs).getDenumire().equals(cosListModel.get(i))) {
							produseComandate.add(((Laptop) produs));
							break;
						}
					} else {
						if (produs instanceof SistemAuxiliar) {
							if (((SistemAuxiliar) produs).getDenumire().equals(cosListModel.get(i))) {
								produseComandate.add(((SistemAuxiliar) produs));

								break;
							}
						} else {
							if (produs instanceof Promo) {
								if (((Promo) produs).getDenumire().equals(cosListModel.get(i))) {
									produseComandate.add(((Promo) produs));
									break;
								}
							}
						}
					}
				}

			}
		}
	}

	public void RefreshView() {
		RefreshProduse();
		RefreshComenzi();
		RefreshProduseClient();
	}

	public void RefreshProduse() {
		desktopListModel.clear();
		firma.ReadDesktopuri();
		if (firma.getDesktopuri() != null) {
			for (var desktop : firma.getDesktopuri()) {
				desktopListModel
						.addElement(desktop.getDenumire() + ((desktop.getCantitate() <= 2) ? " STOC LIMITAT!" : ""));
			}
		}

		laptopListModel.clear();
		firma.ReadLaptopuri();
		if (firma.getLaptopuri() != null) {
			for (var laptop : firma.getLaptopuri()) {

				laptopListModel
						.addElement(laptop.getDenumire() + ((laptop.getCantitate() <= 2) ? " STOC LIMITAT!" : ""));
			}
		}

		auxListModel.clear();
		firma.ReadAux();
		if (firma.getAuxuri() != null) {
			for (var aux : firma.getAuxuri()) {
				auxListModel.addElement(aux.getDenumire() + ((aux.getCantitate() <= 2) ? " STOC LIMITAT!" : ""));
			}
		}

		promoListModel.clear();
		firma.ReadPromo();
		if (firma.getPromouri() != null) {
			for (var promo : firma.getPromouri()) {
				promoListModel.addElement(promo.getDenumire() + ((promo.getCantitate() <= 2) ? " STOC LIMITAT!" : ""));
			}
		}

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

	public void RefreshProduseClient() {
		produseClientListModel.clear();
		client.ReadProduse();
		String denumire = "";
		for (var produs : client.getProduse()) {
			for(var produsFirma : firma.getProduse()) {
				if(produsFirma.getCod().equals(produs.getCod())) {
					if(produsFirma instanceof Desktop) {
						denumire = ((Desktop)produsFirma).getDenumire();
					}
					if(produsFirma instanceof Laptop) {
						denumire = ((Laptop)produsFirma).getDenumire();
					}
					if(produsFirma instanceof SistemAuxiliar) {
						denumire = ((SistemAuxiliar)produsFirma).getDenumire();
					}
					if(produsFirma instanceof Promo) {
						denumire = ((Promo)produsFirma).getDenumire();
					}
				}
			}
			produseClientListModel.addElement(produs.getCod() + " : " + denumire);
		}
		
		
	}
	
	
	public void AdaugaCos(JList list, String listtype) {
		DefaultListModel listModel = (DefaultListModel) list.getModel();

		if (list.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Nu ati selectat nici un produs!", "Eroare adaugare cos",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String selectedValue = list.getSelectedValue().toString();
		if (selectedValue.contains(" STOC LIMITAT!"))
			selectedValue = selectedValue.split("STOC LIMITAT!")[0].trim();

		for (int i = 0; i < cosListModel.size(); i++) {
			if (cosListModel.get(i).toString().equals(selectedValue)) {
				JOptionPane.showMessageDialog(null, "Produsul deja exista in cos!", "Eroare adaugare cos",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		cosListModel.addElement(selectedValue);
		UpdatePrice();

	}

	public ClientFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 851);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktopList = new JList<String>(desktopListModel);
		contentPane.add(desktopList);
		laptopList = new JList<String>(laptopListModel);
		contentPane.add(laptopList);
		auxList = new JList<String>(auxListModel);
		contentPane.add(auxList);
		promoList = new JList<String>(promoListModel);
		contentPane.add(promoList);
		cosList = new JList<String>(cosListModel);
		contentPane.add(cosList);
		comenziList = new JList<String>(comenziListModel);
		contentPane.add(comenziList);
		comenziPList = new JList<String>(comenziPListModel);
		contentPane.add(comenziPList);
		produseClientList = new JList<String>(produseClientListModel);
		contentPane.add(produseClientList);

		JLabel lblNewLabel = new JLabel("CLIENT");
		lblNewLabel.setBounds(0, 11, 984, 42);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(885, 11, 89, 23);
		contentPane.add(btnLogout);

		JLabel lblNewLabel_1 = new JLabel("MAGAZIN");
		lblNewLabel_1.setBounds(10, 50, 467, 33);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 83, 467, 485);
		contentPane.add(tabbedPane);

		JPanel desktopPanel = new JPanel();
		tabbedPane.addTab("Desktopuri", null, desktopPanel, null);
		desktopPanel.setLayout(null);

		JScrollPane scrollPaneDesktop = new JScrollPane(desktopList);
		scrollPaneDesktop.setBounds(10, 11, 442, 195);
		desktopPanel.add(scrollPaneDesktop);

		JScrollPane scrollPaneDescription_3 = new JScrollPane();
		scrollPaneDescription_3.setBounds(10, 217, 442, 160);
		desktopPanel.add(scrollPaneDescription_3);

		JTextArea textAreaDesktop = new JTextArea();
		scrollPaneDescription_3.setViewportView(textAreaDesktop);

		JButton btnAdaugaCosDesktop = new JButton("Adauga la co\u0219");
		btnAdaugaCosDesktop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdaugaCosDesktop.setBounds(313, 400, 139, 46);
		desktopPanel.add(btnAdaugaCosDesktop);

		JPanel laptopPanel = new JPanel();
		tabbedPane.addTab("Laptopuri", null, laptopPanel, null);
		laptopPanel.setLayout(null);

		JScrollPane scrollPaneLaptop = new JScrollPane(laptopList);
		scrollPaneLaptop.setBounds(10, 11, 442, 195);
		laptopPanel.add(scrollPaneLaptop);

		JScrollPane scrollPaneDescription_2 = new JScrollPane();
		scrollPaneDescription_2.setBounds(10, 217, 442, 160);
		laptopPanel.add(scrollPaneDescription_2);

		JTextArea textAreaLaptop = new JTextArea();
		scrollPaneDescription_2.setViewportView(textAreaLaptop);

		JButton btnAdaugaCosLaptop = new JButton("Adauga la co\u0219");
		btnAdaugaCosLaptop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdaugaCosLaptop.setBounds(313, 400, 139, 46);
		laptopPanel.add(btnAdaugaCosLaptop);

		JPanel auxPanel = new JPanel();
		tabbedPane.addTab("Auxiliare", null, auxPanel, null);
		auxPanel.setLayout(null);

		JScrollPane scrollPaneAux = new JScrollPane(auxList);
		scrollPaneAux.setBounds(10, 11, 442, 195);
		auxPanel.add(scrollPaneAux);

		JScrollPane scrollPaneDescription_1 = new JScrollPane();
		scrollPaneDescription_1.setBounds(10, 217, 442, 160);
		auxPanel.add(scrollPaneDescription_1);

		JTextArea textAreaAux = new JTextArea();
		scrollPaneDescription_1.setViewportView(textAreaAux);

		JButton btnAdaugaCosAux = new JButton("Adauga la co\u0219");
		btnAdaugaCosAux.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdaugaCosAux.setBounds(313, 400, 139, 46);
		auxPanel.add(btnAdaugaCosAux);

		JPanel promoPanel = new JPanel();
		tabbedPane.addTab("Promotii", null, promoPanel, null);
		promoPanel.setLayout(null);

		JScrollPane scrollPanePromo = new JScrollPane(promoList);
		scrollPanePromo.setBounds(10, 11, 442, 195);
		promoPanel.add(scrollPanePromo);

		JScrollPane scrollPaneDescription = new JScrollPane();
		scrollPaneDescription.setBounds(10, 217, 442, 160);
		promoPanel.add(scrollPaneDescription);

		JTextArea textAreaPromo = new JTextArea();
		scrollPaneDescription.setViewportView(textAreaPromo);

		JButton btnAdaugaCosPromo = new JButton("Adauga la co\u0219");
		btnAdaugaCosPromo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdaugaCosPromo.setBounds(313, 400, 139, 46);
		promoPanel.add(btnAdaugaCosPromo);

		JLabel lblNewLabel_1_1 = new JLabel("CO\u0218");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 580, 174, 33);
		contentPane.add(lblNewLabel_1_1);

		JScrollPane scrollPaneCos = new JScrollPane(cosList);
		scrollPaneCos.setBounds(10, 617, 467, 130);
		contentPane.add(scrollPaneCos);

		JButton btnStergeCos = new JButton("Sterge");
		btnStergeCos.setBounds(10, 758, 89, 23);
		contentPane.add(btnStergeCos);

		JButton btnTrimiteComanda = new JButton("Trimite comand\u0103");
		btnTrimiteComanda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTrimiteComanda.setBounds(326, 758, 151, 46);
		contentPane.add(btnTrimiteComanda);

		JButton btnSistemPersonalizat = new JButton("Sistem Personalizat");
		btnSistemPersonalizat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSistemPersonalizat.setBounds(142, 758, 174, 46);
		contentPane.add(btnSistemPersonalizat);

		JLabel lblComenzi = new JLabel("COMENZI");
		lblComenzi.setHorizontalAlignment(SwingConstants.CENTER);
		lblComenzi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblComenzi.setBounds(507, 50, 467, 33);
		contentPane.add(lblComenzi);

		JTabbedPane tabbedPaneProduse = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneProduse.setBounds(517, 83, 440, 485);
		contentPane.add(tabbedPaneProduse);

		JPanel panelComenzi = new JPanel();
		tabbedPaneProduse.addTab("Comenzi Produse", null, panelComenzi, null);
		panelComenzi.setLayout(null);

		JScrollPane scrollPaneComenzi = new JScrollPane(comenziList);
		scrollPaneComenzi.setBounds(10, 11, 415, 192);
		panelComenzi.add(scrollPaneComenzi);

		JScrollPane scrollPaneComandaDescription = new JScrollPane();
		scrollPaneComandaDescription.setBounds(10, 214, 415, 161);
		panelComenzi.add(scrollPaneComandaDescription);

		JTextArea textAreaComanda = new JTextArea();
		scrollPaneComandaDescription.setViewportView(textAreaComanda);

		JPanel panelComenziPersonalizate = new JPanel();
		tabbedPaneProduse.addTab("Comenzi Sisteme Personalizate", null, panelComenziPersonalizate, null);
		panelComenziPersonalizate.setLayout(null);

		JScrollPane scrollPaneComenziP = new JScrollPane(comenziPList);
		scrollPaneComenziP.setBounds(10, 11, 415, 192);
		panelComenziPersonalizate.add(scrollPaneComenziP);

		JScrollPane scrollPaneComandaPDescription = new JScrollPane();
		scrollPaneComandaPDescription.setBounds(10, 214, 415, 161);
		panelComenziPersonalizate.add(scrollPaneComandaPDescription);

		JTextArea textAreaComandaP = new JTextArea();
		scrollPaneComandaPDescription.setViewportView(textAreaComandaP);

		lblPret = new JLabel("");
		lblPret.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPret.setBounds(376, 591, 89, 14);
		contentPane.add(lblPret);
		
		
		
		JScrollPane scrollPaneProduse = new JScrollPane(produseClientList);
		scrollPaneProduse.setBounds(517, 617, 440, 130);
		contentPane.add(scrollPaneProduse);
		
		JButton btnActivareGarantie = new JButton("Activare Garantie");
		btnActivareGarantie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnActivareGarantie.setBounds(814, 758, 143, 46);
		contentPane.add(btnActivareGarantie);
		btnActivareGarantie.setEnabled(false);
		RefreshView();

		produseClientList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(produseClientList.getSelectedIndex() == -1)
				{
					return;
				}
				
				String produsCod = produseClientList.getSelectedValue().split(":")[0].trim();
				
				boolean gasit = false;
				
				for (var desktop: firma.getDesktopuri()) {
					if(desktop.getCod().equals(produsCod)) {
						gasit = true;
						break;
					}
				}
				for (var laptop: firma.getLaptopuri()) {
					if(laptop.getCod().equals(produsCod)) {
						gasit = true;
						break;
					}
				}
				
				btnActivareGarantie.setEnabled(gasit);
			}
		});
		
		desktopList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (desktopList.getSelectedIndex() == -1)
					return;

				textAreaDesktop.setText(firma.getDesktopuri().get(desktopList.getSelectedIndex()).toStringClient());

			}
		});

		laptopList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (laptopList.getSelectedIndex() == -1)
					return;

				textAreaLaptop.setText(firma.getLaptopuri().get(laptopList.getSelectedIndex()).toStringClient());

			}
		});

		auxList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (auxList.getSelectedIndex() == -1)
					return;

				textAreaAux.setText(firma.getAuxuri().get(auxList.getSelectedIndex()).toStringClient());

			}
		});

		promoList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				if (promoList.getSelectedIndex() == -1)
					return;

				textAreaPromo.setText(firma.getPromouri().get(promoList.getSelectedIndex()).toString());

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

		btnAdaugaCosDesktop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdaugaCos(desktopList, "desktop");
			}
		});

		btnAdaugaCosLaptop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdaugaCos(laptopList, "laptop");
			}
		});

		btnAdaugaCosAux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdaugaCos(auxList, "aux");
			}
		});

		btnAdaugaCosPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdaugaCos(promoList, "promo");
			}
		});

		btnStergeCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cosList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Nu ati selectat nici un produs din cos!",
							"Eroare stergere din cos", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String itemToBeRemoved = cosList.getSelectedValue();
				cosListModel.removeElement(itemToBeRemoved);
				UpdatePrice();
			}
		});

		btnSistemPersonalizat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientSistemPersonalizat dialog = new ClientSistemPersonalizat();
				dialog.setModal(true);
				sistemPersonalizat.clear();
				sistemPersonalizat = dialog.run();

				if (sistemPersonalizat.size() == 0)
					return;
				String comandaPersonalizata = "";
				for (var component : sistemPersonalizat) {
					comandaPersonalizata += "\n" + component.getDenumire();
				}

				StatusPlata statusPlata = StatusPlata.Neplatit;

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Achitati cu cardul?",
						"Trimitere Comanda Cos", JOptionPane.YES_NO_OPTION)) {
					JOptionPane.showInputDialog("Numarul cardului");
					statusPlata = StatusPlata.Platit;
				}

				if (JOptionPane.NO_OPTION == JOptionPane
						.showConfirmDialog(null,
								"Sunteti sigur ca doriti sa trimiteti comanda de sistem personalizat cu componentele: "
										+ comandaPersonalizata + "?",
								"Comanda Sistem Personalizat", JOptionPane.YES_NO_OPTION))
					return;

				ComandaPersonalizatClient comandaP = new ComandaPersonalizatClient(produseComandate,
						0, statusPlata, StatusComanda.InAsteptare,
						LocalDate.now().toString(), sistemPersonalizat);

				
				
				client.TrimiteComandaPersonalizata(comandaP);
				JOptionPane.showMessageDialog(null, "Comanda trimisa cu succes!", "Comanda",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		btnTrimiteComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GetProduseComandate();

				if (produseComandate.size() != cosListModel.size()) {
					JOptionPane.showMessageDialog(null, "Eroare la adaugare!", "Eroare la adaugare!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (produseComandate.size() == 0) {
					JOptionPane.showMessageDialog(null, "Cosul este gol!", "Eroare la adaugare!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				StatusPlata statusPlata = StatusPlata.Neplatit;

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Achitati cu cardul?",
						"Trimitere Comanda Cos", JOptionPane.YES_NO_OPTION)) {
					JOptionPane.showInputDialog("Numarul cardului");
					statusPlata = StatusPlata.Platit;
				}

				UpdateProduseStatus();

				ComandaClient comanda = new ComandaClient(produseComandate, Float.parseFloat(lblPret.getText()),
						statusPlata, StatusComanda.InAsteptare, LocalDate.now().toString());

				client.TrimiteComanda(comanda);

				JOptionPane.showMessageDialog(null, "Comanda trimisa cu succes!", "Comanda",
						JOptionPane.INFORMATION_MESSAGE);
				cosListModel.clear();
				RefreshView();
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				dispose();
				loginFrame.setVisible(true);
			}
		});
		
		btnActivareGarantie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(produseClientList.getSelectedIndex() == -1)
				{
					JOptionPane.showMessageDialog(null,"Selectati un produs!!!", "Eroare garantie", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String produsCod = produseClientList.getSelectedValue().split(":")[0].trim();
				boolean gasit = false;
				
				for (var desktop: firma.getDesktopuri()) {
					if(desktop.getCod().equals(produsCod)) {
						desktop.getGarantie().setStatus(StatusGarantie.Activ);
						desktop.getGarantie().setNrReturnat(desktop.getGarantie().getNrReturnat()+1);
						gasit = true;
						break;
					}
				}
				for (var laptop: firma.getLaptopuri()) {
					if(laptop.getCod().equals(produsCod)) {
						laptop.getGarantie().setStatus(StatusGarantie.Activ);
						laptop.getGarantie().setNrReturnat(laptop.getGarantie().getNrReturnat()+1);
						gasit = true;
						break;
					}
				}
				
				firma.WriteDesktopuri();
				firma.WriteLaptopuri();
				
				if(gasit)
					JOptionPane.showMessageDialog(null,"Garantia a fost activata", "Activata", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
