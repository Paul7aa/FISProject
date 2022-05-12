package mainPackage;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ComandaFurnizorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Furnizor furnizor;
	private JComboBox<String> cBoxFurnizor;
	private JComboBox<String> cBoxComponente;
	private JComboBox<String> cBoxCantitate;
	private List<String> addedItems = new ArrayList<String>();
	private JList <String> componenteList;
	private DefaultListModel <String> componenteListModel = new  DefaultListModel<String>();
	
	public ComandaFurnizorDialog(Firma firma) {
		setResizable(false);
		setBounds(100, 100, 450, 565);
		getContentPane().setLayout(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnTrimiteComanda = new JButton("Trimite comanda");
		btnTrimiteComanda.setBounds(57, 462, 148, 53);
		getContentPane().add(btnTrimiteComanda);
		
		JButton btnAnuleaza = new JButton("Anuleaza");
		btnAnuleaza.setBounds(229, 462, 148, 53);
		getContentPane().add(btnAnuleaza);
		
		JLabel lblNewLabel = new JLabel("Furnizor");
		lblNewLabel.setBounds(48, 50, 49, 14);
		getContentPane().add(lblNewLabel);
		
		cBoxFurnizor = new JComboBox<String>();
		cBoxFurnizor.setBounds(107, 46, 270, 22);
		getContentPane().add(cBoxFurnizor);
		
		JLabel lblComponente = new JLabel("Componenta");
		lblComponente.setBounds(23, 92, 78, 14);
		getContentPane().add(lblComponente);
		
		cBoxComponente = new JComboBox<String>();
		cBoxComponente.setBounds(107, 88, 270, 22);
		getContentPane().add(cBoxComponente);
		
		cBoxCantitate = new JComboBox<String>();
		cBoxCantitate.setBounds(107, 131, 93, 22);
		getContentPane().add(cBoxCantitate);
		
		//hard-coded suppliers RIP
		cBoxFurnizor.addItem("supplier");
		cBoxFurnizor.addItem("supplier2");
		cBoxFurnizor.setSelectedIndex(-1);
		
		JLabel lblCantitate = new JLabel("Cantitate");
		lblCantitate.setBounds(40, 135, 57, 14);
		getContentPane().add(lblCantitate);
		
		componenteList = new JList<String>(componenteListModel);
		componenteList.setBounds(20, -2, 459, 425);
		
		JScrollPane scrollPane = new JScrollPane(componenteList);
		scrollPane.setBounds(27, 186, 380, 131);
		getContentPane().add(scrollPane);
		
		JButton btnAdaugaComponenta = new JButton("Adauga");
		btnAdaugaComponenta.setBounds(245, 131, 89, 23);
		getContentPane().add(btnAdaugaComponenta);
		
		JButton btnStergeComponenta = new JButton("Sterge");
		btnStergeComponenta.setBounds(302, 328, 89, 23);
		getContentPane().add(btnStergeComponenta);
		
		JLabel lblNewLabel_1 = new JLabel("COMANDA COMPONENTE\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(52, 11, 320, 28);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblObservatii = new JLabel("Observatii");
		lblObservatii.setBounds(40, 406, 57, 14);
		getContentPane().add(lblObservatii);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(119, 379, 285, 63);
		getContentPane().add(scrollPane_1);
		
		JTextArea textAreaObservatii = new JTextArea();
		textAreaObservatii.setWrapStyleWord(true);
		textAreaObservatii.setLineWrap(true);
		scrollPane_1.setViewportView(textAreaObservatii);
		
		btnAnuleaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		cBoxFurnizor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBoxCantitate.removeAllItems();
				cBoxComponente.removeAllItems();
				if (cBoxFurnizor.getSelectedIndex() == -1)
					return;
				furnizor = new Furnizor(cBoxFurnizor.getSelectedItem().toString());
				if(furnizor.getComponente()!=null) {
					for(var component : furnizor.getComponente()) {
						
						if(addedItems.contains(component.getDenumire()))
							continue;
						else
							cBoxComponente.addItem(component.getTip() + " : " + component.getDenumire());
						
					}
				}
				cBoxComponente.setSelectedIndex(-1);
			}
		});
		
		cBoxComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBoxCantitate.removeAllItems();
				
				if(cBoxComponente.getSelectedIndex() == -1)
					return;
				
				if(furnizor.getComponente()!=null) {
					for(var component : furnizor.getComponente()) {
						if(component.getDenumire().equals(cBoxComponente.getSelectedItem().toString().split(":")[1].trim())) {
							for(int i = 0; i < component.getNr_stoc(); i++) {
								cBoxCantitate.addItem(Integer.toString(i+1));
							}
							break;
						}
					}
				}
				
			}
		});
		
		btnAdaugaComponenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cBoxFurnizor.getSelectedIndex() == -1 || cBoxComponente.getSelectedIndex() == -1 
						|| cBoxCantitate.getSelectedIndex() == -1 || furnizor == null) {
					JOptionPane.showMessageDialog(null, "Componenta nu este selectata!", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String newListItem = cBoxComponente.getSelectedItem().toString() + " - cantitate: " + cBoxCantitate.getSelectedItem().toString();
				componenteListModel.addElement(newListItem);
				addedItems.add(cBoxComponente.getSelectedItem().toString().split(":")[1].trim());
				cBoxComponente.removeItem(cBoxComponente.getSelectedItem());
				cBoxComponente.setSelectedIndex(-1);
				
				
				//disable cBox because you can order from only one supplier
				cBoxFurnizor.setEnabled(false);
			}
		});
		
		btnStergeComponenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(componenteList.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog(null, "Selectati componenta pe care doriti sa o stergeti", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String itemToBeRemoved = componenteList.getSelectedValue();
				componenteListModel.removeElement(itemToBeRemoved);
				addedItems.remove(itemToBeRemoved.split("-")[0].split(":")[1].trim());
				cBoxFurnizor.setSelectedIndex(cBoxFurnizor.getSelectedIndex());//trigger eventhandler
				
				if(componenteListModel.size() == 0)
					cBoxFurnizor.setEnabled(true);
			}
		});
		
		btnTrimiteComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(componenteListModel.size() == 0) {
					JOptionPane.showMessageDialog(null, "Nu exista componente in comanda!", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				List <Componenta> componenteComanda = new ArrayList<Componenta>();
				List<Integer> componenteQuantity = new ArrayList<Integer>();
				
				for(int i = 0; i < componenteListModel.size(); i++) {
					String componentName = componenteListModel.get(i).split("-")[0].split(":")[1].trim();
					int componentQuantity = Integer.parseInt(componenteListModel.get(i).split(":")[2].trim());
					for(Componenta componenta : furnizor.getComponente()){
						if(componenta.getDenumire().equals(componentName)) {
							componenteComanda.add(componenta);
							componenteQuantity.add(componentQuantity);
							break;
						}
					}
				}
				
				Comanda c  = new Comanda(LocalDate.now().toString(), componenteComanda, componenteQuantity, furnizor, StatusComanda.InAsteptare, textAreaObservatii.getText());
				firma.TrimiteComandaFurnizor(c);
				dispose();
			}
		});
	}

}
