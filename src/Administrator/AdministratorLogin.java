package Administrator;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class AdministratorLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorLogin frame = new AdministratorLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public AdministratorLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Administrator Login");
		lblAdminLogin.setFont(new Font("Sylfaen", Font.PLAIN, 35));
		lblAdminLogin.setBounds(68, 87, 369, 74);
		contentPane.add(lblAdminLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 269, 215, 23);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblPassword.setBounds(68, 272, 84, 20);
		contentPane.add(lblPassword);
		
		JLabel loginMsg = new JLabel("");
		loginMsg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		loginMsg.setForeground(Color.red);
		loginMsg.setBounds(200, 479, 200, 26);
		getContentPane().add(loginMsg);
		
		JButton login = new JButton("Login");
		login.setBounds(332, 338, 89, 23);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (passwordField.getText().equals("admin")) {
				AdminAccount aa = new AdminAccount();
				aa.setVisible(true);
				dispose();
				} else 
					loginMsg.setText("Password is incorrect");
			}
			
		});
		contentPane.add(login);
	}
}
