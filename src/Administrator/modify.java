package Administrator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class modify extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */

	
	public modify() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateANew = new JLabel("Modify credit limit to a credit card");
		lblCreateANew.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblCreateANew.setBounds(139, 64, 294, 45);
		contentPane.add(lblCreateANew);
		
		JLabel lblCreditcardNumber = new JLabel("CreditCard Number :");
		lblCreditcardNumber.setBounds(38, 249, 134, 14);
		contentPane.add(lblCreditcardNumber);
		
		JLabel lblLimit = new JLabel("Limit :");
		lblLimit.setBounds(38, 320, 134, 14);
		contentPane.add(lblLimit);
		
		textField_2 = new JTextField();
		textField_2.setBounds(204, 310, 161, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	
		textField_1 = new JTextField();
		textField_1.setBounds(204, 230, 161, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		
		JLabel Msg = new JLabel("");
		Msg.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		Msg.setForeground(Color.red);
		Msg.setBounds(100, 369, 400, 26);
		getContentPane().add(Msg);
		
		JButton create = new JButton("Change");
		create.setBounds(333, 404, 89, 23);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String card = textField_1.getText();
				String limit = textField_2.getText();
				
				if (!isNumeric(card)) 
					Msg.setText("Put number in credit card!");
				else if (!isNumeric(limit)) 
					Msg.setText("Put number in credit limit!");
				else if(!cardExist(card)) {
					Msg.setText("Card doesn't exist in the system");
				} else  {
				mod(card, limit);
				dispose();
				}

	
			}
			
		});
		contentPane.add(create);
		
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(333, 458, 89, 23);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(cancel);
	}
	
	public boolean cardExist(String card) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			
			ResultSet myRs = myStmt.executeQuery("select * from cardholders");
			while (myRs.next()) {
				if (myRs.getString("cardNumber").equals(card)) {
					System.out.println("card exists");
					return true;
				}
			} 
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	    }
		return false;
	}
	
	public void mod(String card, String limit) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://149.4.211.180:3306/cali8332?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
		               "cali8332", "23058332");
			Statement myStmt = myConn.createStatement();
			
			String query = "update cardholders set creditLimit = " + limit + " where cardnumber = " + card + ";";

			myStmt.executeUpdate(query);

			
	    } catch (Exception exc) {
	        System.err.println("Got an exception!");
	        System.err.println(exc.getMessage());
	    }
	}
	
	public boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;

    }

}
