package mainPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Firma firma = new Firma();
	
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1067, 808);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirma = new JLabel("FIRMA");
		lblFirma.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirma.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirma.setBounds(205, 11, 664, 35);
		contentPane.add(lblFirma);
		
		JButton btnComandaFurnizor = new JButton("COMANDA FURNIZOR");
		btnComandaFurnizor.setBounds(433, 91, 201, 89);
		contentPane.add(btnComandaFurnizor);
		
		
		btnComandaFurnizor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComandaFurnizorDialog dialog = new ComandaFurnizorDialog(firma);
				dialog.setModal(true);
				dialog.setVisible(true);
			}
		});
	}

}
