package Vendor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Vendors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendors frame = new Vendors();
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
	public Vendors() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 601, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton departmentStore = new JButton("Department Store");
		departmentStore.setBounds(201, 56, 176, 52);
		departmentStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens Department Store
				 */
				DepartmentStore ds = new DepartmentStore();
				ds.setVisible(true);
				dispose();
			}
		});
		contentPane.add(departmentStore);
		
		JButton restaurant = new JButton("Restaurant");
		restaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens restaurant
				 */
				Restaurant rstrnt = new Restaurant();
				rstrnt.setVisible(true);
				dispose();
			}
		});
		restaurant.setBounds(201, 156, 176, 45);
		contentPane.add(restaurant);
		
		JButton superMarket = new JButton("Super Market");
		superMarket.setBounds(201, 256, 176, 45);
		superMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens super market
				 */
				SuperMarket sm = new SuperMarket();
				sm.setVisible(true);
				dispose();
			}
		});
		contentPane.add(superMarket);
		
		JButton onlineBookStore = new JButton("Online Book Store");
		onlineBookStore.setBounds(201, 356, 176, 45);
		onlineBookStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * opens online book store
				 */
				OnlineBookStore obs = new OnlineBookStore();
				obs.setVisible(true);
				dispose();
			}
		});
		contentPane.add(onlineBookStore);
		
		JLabel lblEnjoyShopping = new JLabel("Enjoy Shopping!");
		lblEnjoyShopping.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		lblEnjoyShopping.setBounds(411, 501, 128, 24);
		contentPane.add(lblEnjoyShopping);
	}

}
