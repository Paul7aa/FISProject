package mainPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernametField;
	private JPasswordField passwordtField;
	private JLabel lblErrorMsg;
	private ClientFrame clientFrame;
	private SupplierFrame supplierFrame;
	private MainFrame mainFrame;
	private static LoginFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(75, 70, 72, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassowrd = new JLabel("Password");
		lblPassowrd.setBounds(75, 109, 72, 14);
		contentPane.add(lblPassowrd);

		lblErrorMsg = new JLabel("");
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMsg.setBounds(75, 150, 282, 14);
		lblErrorMsg.setVisible(false);
		contentPane.add(lblErrorMsg);

		usernametField = new JTextField();
		usernametField.setBounds(157, 67, 196, 20);
		contentPane.add(usernametField);
		usernametField.setColumns(10);

		passwordtField = new JPasswordField();
		passwordtField.setColumns(10);
		passwordtField.setBounds(157, 106, 196, 20);
		contentPane.add(passwordtField);

		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(118, 189, 89, 23);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblErrorMsg.setVisible(false);
				lblErrorMsg.setText("");

				if (usernametField.getText().isEmpty() || passwordtField.getPassword().length == 0) {
					lblErrorMsg.setText("Cannot leave fields empty!");
					lblErrorMsg.setVisible(true);
					return;
				}

				// if login == client && password == client -> client page

				switch (usernametField.getText().toLowerCase()) {
				case "client":
					if (String.valueOf(passwordtField.getPassword()).equals("client")) {
						clientFrame = new ClientFrame();
						clientFrame.setVisible(true);
						frame.dispose();
					}
					
					break;
				case "operator":
					if (String.valueOf(passwordtField.getPassword()).equals("operator")) {
						mainFrame = new MainFrame();
						mainFrame.setVisible(true);
						frame.dispose();
					}

						break;
				case "supplier2":
				case "supplier":
					if (String.valueOf(passwordtField.getPassword()).equals(usernametField.getText().toLowerCase())) {
						supplierFrame = new SupplierFrame(usernametField.getText().toLowerCase());
						supplierFrame.setVisible(true);
						frame.dispose();
					}
						break;
				}
				
				lblErrorMsg.setVisible(true);
				lblErrorMsg.setText("Wrong username/password!");

			}
		});

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancelBtn.setBounds(229, 189, 89, 23);
		contentPane.add(cancelBtn);

	}
}
