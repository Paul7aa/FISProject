package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

public class SupplierFrame extends JFrame {

	private JTextArea descriptionTextArea;
	private JTextArea comandaTextArea;
	private JPanel contentPane;
	private Furnizor furnizor;
	private JList<String> componenteList;
	private JList<String> comenziList;
	private DefaultListModel<String> componenteListModel = new DefaultListModel<String>();
	private DefaultListModel<String> comenziListModel = new DefaultListModel<String>();
	private String componenteInsuficiente;
	private JTextField txtCodComponenta;

	public void RefreshLists() {
		componenteListModel.clear();
		if (furnizor.getComponente() != null) {
			for (var component : furnizor.getComponente()) {
				componenteListModel
						.addElement(component.getTip() + "  " + component.getCod() + " : " + component.getDenumire());
			}
		}

		comenziListModel.clear();
		if (furnizor.getComenziPrimite() != null) {
			for (var comanda : furnizor.getComenziPrimite()) {
				comenziListModel.addElement(comanda.getDataInregistrarii() + " " + comanda.getStatus());
			}
		}
	}

	public boolean VerificaStocSuficient(Comanda comandaPrimita) {
		boolean result = true;
		componenteInsuficiente = "";

		for (var componenta : comandaPrimita.getInventar()) {
			for (var componentaInStoc : furnizor.getComponente()) {
				if (componenta.getCod().equals(componentaInStoc.getCod())) {
					if (componentaInStoc.getNr_stoc() < comandaPrimita.getCantitati()
							.get(comandaPrimita.getInventar().indexOf(componenta))) {
						componenteInsuficiente += componentaInStoc.getDenumire() + "\n";
						result = false;
					}
				}
			}
		}

		return result;
	}

	public void AcceptaComanda(Comanda comandaPrimita) {
		try {
			// get components for operator:
			Firma firma = new Firma();

			// pentru fiecare componenta din comanda
			for (var componenta : comandaPrimita.getInventar()) {
				for (var componentaInStoc : furnizor.getComponente()) {
					if (componenta.getCod().equals(componentaInStoc.getCod())) {

						// pentru fiecare componenta din comanda se sterge din stocul furnizorului
						// numarul de componente comandate
						componentaInStoc.setNr_stoc(componentaInStoc.getNr_stoc()
								- comandaPrimita.getCantitati().get(comandaPrimita.getInventar().indexOf(componenta)));

						boolean componentaExistaInFirma = false;

						// se adauga la stocul firmei daca exista produsul
						for (var componentaInFirma : firma.getComponente()) {
							if (componentaInFirma.getCod().equals(componenta.getCod())) {
								componentaInFirma.setNr_stoc(componentaInFirma.getNr_stoc() + comandaPrimita
										.getCantitati().get(comandaPrimita.getInventar().indexOf(componenta)));
								componentaExistaInFirma = true;
								break;
							}
						}

						if (!componentaExistaInFirma) {
							// se creeaza un nou obiect de componenta cu nr de inventar pentru firma
							Componenta nouaComponenta = new Componenta(componenta, comandaPrimita.getCantitati()
									.get(comandaPrimita.getInventar().indexOf(componenta)));
							firma.getComponente().add(nouaComponenta);
						}
					}
				}
			}

			// setare status comanda (Livrata)
			comandaPrimita.setStatus(StatusComanda.Livrata);

			// reactualizare jsonuri
			firma.WriteComponente();
			furnizor.WriteComponente();
			furnizor.UpdateComenziPrimite();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Eroare acceptare comanda", JOptionPane.ERROR_MESSAGE);
		}
	}

	public SupplierFrame(String furnizorName) {
		this.furnizor = new Furnizor(furnizorName);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSupplier = new JLabel("FURNIZOR - ");
		lblSupplier.setText(lblSupplier.getText() + furnizorName);
		lblSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupplier.setBounds(142, 11, 664, 35);
		contentPane.add(lblSupplier);

		JButton adaugaBtn = new JButton("Adauga in stoc");
		adaugaBtn.setBounds(305, 515, 152, 52);
		contentPane.add(adaugaBtn);

		componenteList = new JList<String>(componenteListModel);
		componenteList.setBounds(20, -2, 459, 425);
		contentPane.add(componenteList);

		JLabel lblNewLabel = new JLabel("Componente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(189, 56, 105, 29);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPaneComponente = new JScrollPane(componenteList);
		scrollPaneComponente.setBounds(20, 84, 437, 272);
		contentPane.add(scrollPaneComponente);

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setBounds(489, 363, 262, 122);
		contentPane.add(descriptionTextArea);

		JScrollPane scrollPaneDescription = new JScrollPane(descriptionTextArea);
		scrollPaneDescription.setBounds(20, 382, 437, 122);
		contentPane.add(scrollPaneDescription);

		JLabel lblComenzi = new JLabel("Comenzi");
		lblComenzi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComenzi.setBounds(662, 56, 75, 29);
		contentPane.add(lblComenzi);

		comenziList = new JList<String>(comenziListModel);
		contentPane.add(comenziList);

		JScrollPane scrollPaneComenzi = new JScrollPane(comenziList);
		scrollPaneComenzi.setBounds(467, 83, 457, 273);
		contentPane.add(scrollPaneComenzi);

		comandaTextArea = new JTextArea();
		comandaTextArea.setWrapStyleWord(true);
		comandaTextArea.setLineWrap(true);
		comandaTextArea.setEditable(false);

		JScrollPane scrollPaneComanda = new JScrollPane(comandaTextArea);
		scrollPaneComanda.setBounds(467, 382, 457, 122);
		contentPane.add(scrollPaneComanda);

		JButton acceptaComandaBtn = new JButton("Accepta comanda");
		acceptaComandaBtn.setBounds(628, 515, 152, 52);
		contentPane.add(acceptaComandaBtn);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(835, 11, 89, 23);
		contentPane.add(btnLogout);

		JButton btnCautaComponenta = new JButton("Cauta Componenta");
		btnCautaComponenta.setBounds(142, 515, 152, 52);
		contentPane.add(btnCautaComponenta);

		txtCodComponenta = new JTextField();
		txtCodComponenta.setBounds(20, 515, 112, 52);
		contentPane.add(txtCodComponenta);
		txtCodComponenta.setColumns(10);

		// functions
		RefreshLists();

		// EVENTS
		adaugaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierAddComponent addFrame = new SupplierAddComponent(furnizor);
				addFrame.setModal(true);
				addFrame.setVisible(true);
				RefreshLists();
				componenteList.setSelectedIndex(componenteListModel.size() - 1);

			}
		});

		acceptaComandaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comenziList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Nu este selectata nici o comanda!", "Eroare acceptare comanda",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!comenziListModel.get(comenziList.getSelectedIndex()).toString().contains("InAsteptare")) {
					JOptionPane.showMessageDialog(null, "Comanda selectata a fost deja livrata!",
							"Eroare acceptare comanda", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
						"Sunteti siguri ca doriti sa acceptati comanda?", "Acceptare comanda",
						JOptionPane.YES_NO_OPTION)) {
					if (VerificaStocSuficient(furnizor.getComenziPrimite().get(comenziList.getSelectedIndex()))) {
						AcceptaComanda(furnizor.getComenziPrimite().get(comenziList.getSelectedIndex()));
						RefreshLists();
					} else {
						JOptionPane.showMessageDialog(null,
								"Nu exista componenta suficiente in stoc! \nComponente lipsa:\n"
										+ componenteInsuficiente,
								"Eroare acceptare comanda", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnCautaComponenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exista = false;
				int stocFurnizor = 0;

				for (var component : furnizor.getComponente()) {
					if (component.getCod().equals(txtCodComponenta.getText())) {
						stocFurnizor = component.getNr_stoc();
						exista = true;
					}
				}
				if (!exista) {
					JOptionPane.showMessageDialog(null,
							"Componenta cu codul " + txtCodComponenta.getText() + " nu exista!", "Eroare!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					for (int i = 0; i < componenteListModel.size(); i++) {
						if (componenteListModel.get(i).contains(txtCodComponenta.getText())) {
							componenteList.setSelectedIndex(i);
							break;
						}
					}
					JOptionPane
							.showMessageDialog(null,
									"\nSTOCUL FURNIZORULUI PENTRU PIESA CU CODUL " + txtCodComponenta.getText()
											+ " ESTE: " + stocFurnizor,
									"STOC DISPONIBIL", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		componenteList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (componenteList.getSelectedIndex() != -1) {
					descriptionTextArea
							.setText(furnizor.getComponente().get(componenteList.getSelectedIndex()).toString());
				}
			}
		});

		comenziList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (comenziList.getSelectedIndex() != -1) {
					comandaTextArea
							.setText(furnizor.getComenziPrimite().get(comenziList.getSelectedIndex()).toString());
				}
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				dispose();
				loginFrame.setVisible(true);
			}
		});
	}
}
