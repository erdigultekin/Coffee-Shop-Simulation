import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Color;

public class teacherInterface {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teacherInterface window = new teacherInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public teacherInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation Management Console");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(14, 11, 420, 20);
		frame.getContentPane().add(lblCoffeeShopSimulation);
		
		JList connectedPlayersList = new JList();
		connectedPlayersList.setBounds(34, 76, 171, 208);
		frame.getContentPane().add(connectedPlayersList);
		
		JLabel lblConnectedPlayers = new JLabel("Connected Players");
		lblConnectedPlayers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectedPlayers.setBounds(34, 51, 120, 20);
		frame.getContentPane().add(lblConnectedPlayers);
		
		JButton btnRunTheSi = new JButton("Run Simulation");
		btnRunTheSi.setBounds(164, 295, 119, 23);
		frame.getContentPane().add(btnRunTheSi);
		
		JList playerDataList = new JList();
		playerDataList.setBounds(225, 76, 181, 208);
		frame.getContentPane().add(playerDataList);
		
		JLabel lblDataStatus = new JLabel("Data Status");
		lblDataStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataStatus.setBounds(223, 54, 76, 14);
		frame.getContentPane().add(lblDataStatus);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setForeground(Color.RED);
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setBounds(360, 41, 46, 14);
		frame.getContentPane().add(lblDay);
		
		JLabel label = new JLabel("0");
		label.setBounds(409, 41, 46, 14);
		frame.getContentPane().add(label);
	}
}
