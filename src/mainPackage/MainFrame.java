package mainPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private Firma firma = new Firma();
	Furnizor furnizor1 = new Furnizor("supplier");
	Furnizor furnizor2 = new Furnizor("supplier2");
	
	private JList<String> componenteComenziList;
	private DefaultListModel<String>  componenteComenziListModel = new DefaultListModel<String> ();
	private JTextArea componentaComandaDescriptionTextArea;
	private JLabel lblComponenteComenzi;
	
	public void RefreshLists(){
		componenteComenziListModel.clear();
		switch(lblComponenteComenzi.getText()) {
		case "Componente Disponibile":
			if (firma.getComponente() != null) {
				for (var component : firma.getComponente()) {
					componenteComenziListModel
							.addElement(component.getTip() + "  " + component.getCod() + " : " + component.getDenumire());
				}
			}
			break;
		case "Comenzi Trimise":
			
			if (furnizor1.getComenziPrimite() != null) {
				for (var comanda : furnizor1.getComenziPrimite()) {
					componenteComenziListModel
							.addElement(furnizor1.getDenumire() + " --- " + comanda.getDataInregistrarii() + " : " + comanda.getStatus());
				}
			}

			if (furnizor2.getComenziPrimite() != null) {
				for (var comanda : furnizor2.getComenziPrimite()) {
					componenteComenziListModel
							.addElement(furnizor2.getDenumire() + " --- " + comanda.getDataInregistrarii() + " : " + comanda.getStatus());
				}
			}
			
			break;
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
		btnCautaComponenta.setBounds(802, 733, 153, 48);
		contentPane.add(btnCautaComponenta);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(985, 11, 89, 23);
		contentPane.add(btnLogout);
		
		//Refresh View
		RefreshLists();
		
		
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
			}
		});
		
		
		componenteComenziList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				switch(lblComponenteComenzi.getText()) {
				case "Componente Disponibile":
					if (componenteComenziList.getSelectedIndex() != -1) {
						componentaComandaDescriptionTextArea
								.setText(firma.getComponente().get(componenteComenziList.getSelectedIndex()).toString());
					}
					break;
				case "Comenzi Trimise":
					if(componenteComenziList.getSelectedIndex() != -1) {
						if(componenteComenziList.getSelectedIndex() <= furnizor1.getComenziPrimite().size() - 1) {
							componentaComandaDescriptionTextArea
							.setText(furnizor1.getComenziPrimite().get(componenteComenziList.getSelectedIndex()).toString());
						}else {
							componentaComandaDescriptionTextArea
							.setText(furnizor2.getComenziPrimite().get(componenteComenziList.getSelectedIndex() - furnizor1.getComenziPrimite().size()).toString());
						}
					}
					
					
					break;
				}
			}
		});
		
		btnListeComponenteComenzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lblComponenteComenzi.getText().equals("Componente Disponibile")) {
					lblComponenteComenzi.setText("Comenzi Trimise");
					btnListeComponenteComenzi.setText("COMPONENTE");
				}
				else
				{
					lblComponenteComenzi.setText("Componente Disponibile");
					btnListeComponenteComenzi.setText("COMENZI TRIMISE");
				}
				
				componentaComandaDescriptionTextArea.setText("");
				componenteComenziList.setSelectedIndex(-1);
				RefreshLists();
			}
		});

	}
}
