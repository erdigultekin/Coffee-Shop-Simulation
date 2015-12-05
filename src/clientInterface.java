import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;

public class clientInterface {

	private JFrame frame;
	private JTextField salesPrice;
	private JTextField cupOrderAmount;
	private JTextField coffeeOrderAmount;
	private JTextField sugarOrderAmount;
	private JTextField milkOrderAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientInterface window = new clientInterface();
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
	public clientInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(151, 11, 217, 28);
		frame.getContentPane().add(lblCoffeeShopSimulation);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setForeground(Color.RED);
		lblDay.setBounds(20, 290, 46, 14);
		frame.getContentPane().add(lblDay);
		
		JLabel lblNewLabel = new JLabel("Coffee Recipe");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 60, 100, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JSlider coffeePerCup = new JSlider();
		coffeePerCup.setBounds(80, 95, 100, 20);
		frame.getContentPane().add(coffeePerCup);
		
		JLabel lblCoffee = new JLabel("Coffee");
		lblCoffee.setBounds(25, 95, 45, 20);
		frame.getContentPane().add(lblCoffee);
		
		JSlider milkPerCup = new JSlider();
		milkPerCup.setBounds(80, 130, 100, 20);
		frame.getContentPane().add(milkPerCup);
		
		JLabel lblNewLabel_1 = new JLabel("Milk");
		lblNewLabel_1.setBounds(25, 125, 45, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSlider sugarPerCup = new JSlider();
		sugarPerCup.setBounds(80, 161, 100, 20);
		frame.getContentPane().add(sugarPerCup);
		
		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setBounds(25, 155, 45, 20);
		frame.getContentPane().add(lblSugar);
		
		salesPrice = new JTextField();
		salesPrice.setBounds(103, 200, 80, 20);
		frame.getContentPane().add(salesPrice);
		salesPrice.setColumns(10);
		
		JLabel lblSalesPrice = new JLabel("Sales Price");
		lblSalesPrice.setBounds(25, 200, 85, 20);
		frame.getContentPane().add(lblSalesPrice);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(85, 230, 90, 20);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInventory.setBounds(250, 60, 100, 20);
		frame.getContentPane().add(lblInventory);
		
		JLabel lblCups = new JLabel("Cups");
		lblCups.setBounds(245, 100, 46, 14);
		frame.getContentPane().add(lblCups);
		
		JLabel lblCoffee_1 = new JLabel("Coffee");
		lblCoffee_1.setBounds(245, 130, 46, 14);
		frame.getContentPane().add(lblCoffee_1);
		
		JLabel lblSugar_1 = new JLabel("Sugar");
		lblSugar_1.setBounds(245, 160, 46, 14);
		frame.getContentPane().add(lblSugar_1);
		
		JLabel lblMilk = new JLabel("Milk");
		lblMilk.setBounds(245, 190, 46, 14);
		frame.getContentPane().add(lblMilk);
		
		JLabel lblAvailable = new JLabel("Stocks");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvailable.setBounds(289, 84, 46, 14);
		frame.getContentPane().add(lblAvailable);
		
		JLabel label = new JLabel("10");
		label.setBounds(300, 100, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("10");
		label_1.setBounds(300, 130, 46, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("10");
		label_2.setBounds(300, 160, 46, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("10");
		label_3.setBounds(300, 190, 46, 14);
		frame.getContentPane().add(label_3);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderAmount.setBounds(350, 84, 80, 14);
		frame.getContentPane().add(lblOrderAmount);
		
		cupOrderAmount = new JTextField();
		cupOrderAmount.setBounds(350, 100, 86, 14);
		frame.getContentPane().add(cupOrderAmount);
		cupOrderAmount.setColumns(10);
		
		coffeeOrderAmount = new JTextField();
		coffeeOrderAmount.setBounds(350, 130, 86, 14);
		frame.getContentPane().add(coffeeOrderAmount);
		coffeeOrderAmount.setColumns(10);
		
		sugarOrderAmount = new JTextField();
		sugarOrderAmount.setBounds(350, 160, 86, 14);
		frame.getContentPane().add(sugarOrderAmount);
		sugarOrderAmount.setColumns(10);
		
		milkOrderAmount = new JTextField();
		milkOrderAmount.setBounds(350, 190, 86, 14);
		frame.getContentPane().add(milkOrderAmount);
		milkOrderAmount.setColumns(10);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(350, 215, 90, 20);
		frame.getContentPane().add(btnBuy);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentBalance.setForeground(Color.RED);
		lblCurrentBalance.setBounds(20, 313, 100, 14);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(124, 313, 46, 14);
		frame.getContentPane().add(lblBalance);
		
		JLabel lblDay_1 = new JLabel("Day");
		lblDay_1.setBounds(55, 290, 46, 14);
		frame.getContentPane().add(lblDay_1);
		
		JLabel lblDailySales = new JLabel("Daily Sales:");
		lblDailySales.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDailySales.setForeground(Color.RED);
		lblDailySales.setBounds(20, 334, 80, 14);
		frame.getContentPane().add(lblDailySales);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setBounds(90, 334, 46, 14);
		frame.getContentPane().add(lblSales);
		
		JLabel lblConnectionStatus = new JLabel("Connection Status:");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectionStatus.setForeground(Color.RED);
		lblConnectionStatus.setBounds(20, 359, 116, 14);
		frame.getContentPane().add(lblConnectionStatus);
		
		JLabel lblDataSent = new JLabel("Data has sent, waiting for the teacher.");
		lblDataSent.setBounds(139, 359, 314, 14);
		frame.getContentPane().add(lblDataSent);
	}
}
