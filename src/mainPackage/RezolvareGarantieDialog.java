package mainPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RezolvareGarantieDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String faultyCompCod = "";
	
	public String run() {
		this.setVisible(true);
		return faultyCompCod;
	}
	
	public RezolvareGarantieDialog(List<String> componente, String denumire) {
		setResizable(false);
		setBounds(100, 100, 450, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == -1) {
					faultyCompCod = "";
					return;
				}
				
				faultyCompCod = comboBox.getSelectedItem().toString().split(":")[0].trim();
			}
		});
		for(var comp : componente) {
			comboBox.addItem(comp);
		}
		comboBox.setBounds(70, 95, 279, 22);
		contentPanel.add(comboBox);
		
		JLabel lblGarantie = new JLabel("");
		lblGarantie.setHorizontalAlignment(SwingConstants.CENTER);
		lblGarantie.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGarantie.setBounds(10, 11, 414, 31);
		contentPanel.add(lblGarantie);
		lblGarantie.setText("RETURNAT - " + denumire);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnOK.setBounds(335, 143, 89, 23);
		contentPanel.add(btnOK);
		
		JLabel lblNewLabel = new JLabel("Componenta defect");
		lblNewLabel.setBounds(70, 70, 196, 14);
		contentPanel.add(lblNewLabel);

	}
}
