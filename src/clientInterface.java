import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class clientInterface {

	public static JFrame frame;
	public static JTextField salesPrice;
	public static JTextField cupOrderAmount;
	public static JTextField coffeeOrderAmount;
	public static JTextField sugarOrderAmount;
	public static JTextField milkOrderAmount;
	public static JTextField userName;

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(151, 5, 217, 28);
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
		salesPrice.setBounds(100, 200, 80, 15);
		frame.getContentPane().add(salesPrice);
		salesPrice.setColumns(10);
		
		JLabel lblSalesPrice = new JLabel("Sales Price");
		lblSalesPrice.setBounds(25, 200, 85, 20);
		frame.getContentPane().add(lblSalesPrice);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(100, 230, 85, 15);
		frame.getContentPane().add(submitButton);
		
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
		
		JLabel cupStockLabel = new JLabel("10");
		cupStockLabel.setBounds(300, 100, 46, 14);
		frame.getContentPane().add(cupStockLabel);
		
		JLabel coffeeStockLabel = new JLabel("10");
		coffeeStockLabel.setBounds(300, 130, 46, 14);
		frame.getContentPane().add(coffeeStockLabel);
		
		JLabel sugarStockLabel = new JLabel("10");
		sugarStockLabel.setBounds(300, 160, 46, 14);
		frame.getContentPane().add(sugarStockLabel);
		
		JLabel milkStockLabel = new JLabel("10");
		milkStockLabel.setBounds(300, 190, 46, 14);
		frame.getContentPane().add(milkStockLabel);
		
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
		
		JButton buyButton = new JButton("Buy");
		buyButton.setBounds(350, 215, 90, 20);
		frame.getContentPane().add(buyButton);
		
		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentBalance.setForeground(Color.RED);
		lblCurrentBalance.setBounds(20, 310, 100, 14);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel currentBalanceLabel = new JLabel("Balance");
		currentBalanceLabel.setBounds(120, 310, 46, 14);
		frame.getContentPane().add(currentBalanceLabel);
		
		JLabel currentDayLabel = new JLabel("Day");
		currentDayLabel.setBounds(50, 290, 46, 14);
		frame.getContentPane().add(currentDayLabel);
		
		JLabel lblDailySales = new JLabel("Previous Day Sales:");
		lblDailySales.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDailySales.setForeground(Color.RED);
		lblDailySales.setBounds(20, 330, 118, 14);
		frame.getContentPane().add(lblDailySales);
		
		JLabel previousDaySales = new JLabel("Sales");
		previousDaySales.setBounds(135, 330, 46, 14);
		frame.getContentPane().add(previousDaySales);
		
		JLabel lblConnectionStatus = new JLabel("Connection Status:");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectionStatus.setForeground(Color.RED);
		lblConnectionStatus.setBounds(20, 350, 116, 14);
		frame.getContentPane().add(lblConnectionStatus);
		
		JLabel dataStatusLabel = new JLabel("Data has sent, waiting for the teacher.");
		dataStatusLabel.setBounds(130, 350, 314, 14);
		frame.getContentPane().add(dataStatusLabel);
		
		JLabel lblPrice = new JLabel("Price ($)");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(470, 84, 64, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel cupPriceLabel = new JLabel("5");
		cupPriceLabel.setBounds(490, 101, 46, 14);
		frame.getContentPane().add(cupPriceLabel);
		
		JLabel coffeePriceLabel = new JLabel("5");
		coffeePriceLabel.setBounds(490, 130, 46, 14);
		frame.getContentPane().add(coffeePriceLabel);
		
		JLabel sugarPriceLabel = new JLabel("5");
		sugarPriceLabel.setBounds(490, 160, 46, 14);
		frame.getContentPane().add(sugarPriceLabel);
		
		JLabel milkPriceLabel = new JLabel("5");
		milkPriceLabel.setBounds(490, 190, 46, 14);
		frame.getContentPane().add(milkPriceLabel);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(20, 35, 80, 14);
		frame.getContentPane().add(lblUserName);
		
		userName = new JTextField();
		userName.setBounds(90, 35, 80, 15);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(190, 35, 80, 15);
		frame.getContentPane().add(btnSubmit);
		
		JLabel coffeeSliderLabel = new JLabel("55");
		coffeeSliderLabel.setBounds(190, 95, 20, 15);
		frame.getContentPane().add(coffeeSliderLabel);
		
		JLabel milkSliderLabel = new JLabel("60");
		milkSliderLabel.setBounds(190, 130, 20, 15);
		frame.getContentPane().add(milkSliderLabel);
		
		JLabel sugarSliderLabel = new JLabel("75");
		sugarSliderLabel.setBounds(190, 160, 20, 15);
		frame.getContentPane().add(sugarSliderLabel);
		frame.setVisible(true);
	}
}
