package mainPackage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FirmaAdaugaPromo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tFieldCod;
	private JTextField tFieldDenumire;
	private JTextField tFieldCantitate;
	Firma firma = new Firma();
	private float pretInitial;
	private float pretRedus;
	List<String> produseSelectate = new ArrayList<String>();
	JList<String> produseList = new JList<String>();
	DefaultListModel<String> produseListModel = new DefaultListModel<String>();
	JLabel lblPretRedus;
	JLabel lblPretInitial;
	JSpinner spinnerReducere;

	public void CalculatePrice() {
		if (produseListModel.size() == 0) {
			lblPretRedus.setText("0");
			lblPretInitial.setText("0");
			return;
		}

		pretInitial = 0;
		pretRedus = 0;

		for (int i = 0; i < produseListModel.size(); i++) {
			for (var produs : firma.getProduse()) {
				if (produs.getCod().equals(produseListModel.get(i).split(":")[0].trim()))
					pretInitial += produs.getPret();
			}
		}

		pretRedus = pretInitial - pretInitial * (Integer) spinnerReducere.getValue() / 100;

		lblPretRedus.setText(Float.toString(pretRedus));
		lblPretInitial.setText(Float.toString(pretInitial));

	}

	public FirmaAdaugaPromo() {
		setResizable(false);
		setBounds(100, 100, 450, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ADAUGARE PROMO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 414, 29);
		contentPanel.add(lblNewLabel);

		JLabel lblCod = new JLabel("Cod");
		lblCod.setHorizontalAlignment(SwingConstants.CENTER);
		lblCod.setBounds(20, 71, 112, 14);
		contentPanel.add(lblCod);

		JLabel lblDenumire = new JLabel("Denumire");
		lblDenumire.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenumire.setBounds(20, 156, 112, 14);
		contentPanel.add(lblDenumire);

		JLabel lblCantitate = new JLabel("Cantitate");
		lblCantitate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantitate.setBounds(20, 112, 112, 14);
		contentPanel.add(lblCantitate);

		JLabel lblProduse = new JLabel("Produse");
		lblProduse.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduse.setBounds(20, 199, 112, 14);
		contentPanel.add(lblProduse);

		tFieldCod = new JTextField();
		tFieldCod.setBounds(142, 68, 86, 20);
		contentPanel.add(tFieldCod);
		tFieldCod.setColumns(10);

		tFieldDenumire = new JTextField();
		tFieldDenumire.setColumns(10);
		tFieldDenumire.setBounds(142, 153, 227, 20);
		contentPanel.add(tFieldDenumire);

		tFieldCantitate = new JTextField();
		tFieldCantitate.setColumns(10);
		tFieldCantitate.setBounds(142, 109, 86, 20);
		contentPanel.add(tFieldCantitate);

		JComboBox<String> cBoxProduse = new JComboBox<String>();
		cBoxProduse.setBounds(142, 195, 227, 22);
		contentPanel.add(cBoxProduse);

		// intialize produse cbox

		for (var produs : firma.getProduse()) {
			if (produs instanceof Promo)
				continue;
			if (produs instanceof Sistem)
				cBoxProduse.addItem(produs.getCod() + " : " + ((Sistem) produs).getDenumire());
			if (produs instanceof SistemAuxiliar)
				cBoxProduse.addItem(produs.getCod() + " : " + ((SistemAuxiliar) produs).getDenumire());
		}

		cBoxProduse.setSelectedIndex(-1);

		produseList = new JList(produseListModel);
		JScrollPane scrollPaneProduse = new JScrollPane(produseList);
		scrollPaneProduse.setBounds(56, 266, 328, 154);
		contentPanel.add(scrollPaneProduse);

		JButton btnAdauga = new JButton("Adauga");
		btnAdauga.setBounds(197, 232, 89, 23);
		contentPanel.add(btnAdauga);

		JButton btnAnulare = new JButton("Anulare");
		btnAnulare.setBounds(218, 586, 178, 49);
		contentPanel.add(btnAnulare);

		JButton btnAdaugare = new JButton("Adaugare promo");
		btnAdaugare.setBounds(35, 586, 173, 49);
		contentPanel.add(btnAdaugare);

		JButton btnSterge = new JButton("Sterge");
		btnSterge.setBounds(296, 232, 89, 23);
		contentPanel.add(btnSterge);

		JLabel lblReducere = new JLabel("Reducere");
		lblReducere.setHorizontalAlignment(SwingConstants.CENTER);
		lblReducere.setBounds(20, 447, 112, 14);
		contentPanel.add(lblReducere);

		JLabel lblPretRedus111 = new JLabel("Pret Redus");
		lblPretRedus111.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretRedus111.setBounds(20, 535, 112, 14);
		contentPanel.add(lblPretRedus111);

		JLabel lblPretInitial111 = new JLabel("Pret Initial");
		lblPretInitial111.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretInitial111.setBounds(20, 502, 112, 14);
		contentPanel.add(lblPretInitial111);

		spinnerReducere = new JSpinner();
		spinnerReducere.setModel(new SpinnerNumberModel(10, 10, 90, 5));
		spinnerReducere.setBounds(146, 440, 51, 29);
		contentPanel.add(spinnerReducere);
		spinnerReducere.setEditor(new JSpinner.DefaultEditor(spinnerReducere));

		lblPretInitial = new JLabel("");
		lblPretInitial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPretInitial.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretInitial.setBounds(130, 502, 98, 14);
		contentPanel.add(lblPretInitial);

		lblPretRedus = new JLabel("");
		lblPretRedus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPretRedus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPretRedus.setBounds(130, 535, 98, 14);
		contentPanel.add(lblPretRedus);

		btnAnulare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cBoxProduse.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Produsul nu este selectat!", "Eroare adaugare",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				produseListModel.addElement(cBoxProduse.getSelectedItem().toString());
				produseSelectate.add(cBoxProduse.getSelectedItem().toString());
				cBoxProduse.removeItem(cBoxProduse.getSelectedItem());
				cBoxProduse.setSelectedIndex(-1);

				CalculatePrice();
			}
		});

		btnSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (produseList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Selectati produsul pe care doriti sa il stergeti", "Eroare",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String itemToBeRemoved = produseList.getSelectedValue();
				produseListModel.removeElement(itemToBeRemoved);
				produseSelectate.remove(itemToBeRemoved);
				cBoxProduse.addItem(itemToBeRemoved);

				CalculatePrice();
			}
		});

		spinnerReducere.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				CalculatePrice();
			}
		});

		btnAdaugare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cantitate= 0;
				Component[] components = contentPanel.getComponents();

				for (var component : components) {
					if (component instanceof JTextField) {
						if (((JTextField) component).getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nu se pot lasa campuri goale!", "Eroare adaugare!",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				
				if(produseListModel.size() < 2) {
					JOptionPane.showMessageDialog(null, "Promo-ul trebuie sa include minim 2 produse!", "Eroare adaugare!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					cantitate = Integer.parseInt(tFieldCantitate.getText());
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Cantitatea trebuie sa aiba valoare numerica potrivita!", "Eroare adaugare!",
							JOptionPane.ERROR_MESSAGE);
				}
				
				List<Produs> produsePromo = new ArrayList<Produs>();
				
				for(int i = 0; i < produseListModel.size(); i++) {
					for(var produs : firma.getProduse())
						if(produseListModel.get(i).toString().split(":")[0].trim().equals(produs.getCod())){
							if(produs instanceof Desktop) {
								produsePromo.add((Desktop)produs);
							}else {
								if(produs instanceof Laptop) {
									produsePromo.add((Laptop)produs);
								}else {
									if(produs instanceof SistemAuxiliar) {
										produsePromo.add((SistemAuxiliar)produs);
									}
								}
							}
						}
				}
				
				
				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null,
						"Sunteti siguri ca doriti sa adaugati promo-ul " + tFieldDenumire.getText() + " ?", "Adaugare promo",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) {
					return;
				}
			
				
				Promo newPromo = new Promo(tFieldCod.getText().trim(), pretInitial, cantitate, 0, 0, tFieldDenumire.getText(),
						produsePromo, (Integer)spinnerReducere.getValue());
				
				firma.WritePromo(newPromo);
				dispose();
				
			}
		});

	}
}
