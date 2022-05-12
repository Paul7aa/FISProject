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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Component;

public class SupplierFrame extends JFrame {

	private JTextArea descriptionTextArea;
	private JTextArea comandaTextArea;
	private JPanel contentPane;
	private Furnizor furnizor;
	private JList componenteList;
	private JList comenziList;
	private DefaultListModel componenteListModel = new DefaultListModel();
	private DefaultListModel comenziListModel = new DefaultListModel();

	public void RefreshLists() {
		componenteListModel.clear();
		if (furnizor.getComponente() != null) {
			for (var component : furnizor.getComponente()) {
				componenteListModel.addElement(component.getTip() + "  " + component.getCod() + " : " + component.getDenumire());
			}
		}
		
		comenziListModel.clear();
		if(furnizor.getComenziPrimite() != null) {
			for (var comanda : furnizor.getComenziPrimite()) {
				comenziListModel.addElement(comanda.getDataInregistrarii() + " " + comanda.getStatus());
			}
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
		adaugaBtn.setBounds(161, 515, 152, 52);
		contentPane.add(adaugaBtn);

		componenteList = new JList(componenteListModel);
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

		comenziList = new JList(comenziListModel);
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

		componenteList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (componenteList.getSelectedIndex() != -1) {
					descriptionTextArea.setText(
							furnizor.getComponente().get(componenteList.getSelectedIndex()).toString());
				}
			}
		});
		
		comenziList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (comenziList.getSelectedIndex() != -1) {
					comandaTextArea.setText(
							furnizor.getComenziPrimite().get(comenziList.getSelectedIndex()).toString());
				}
			}
		});
	}
}
