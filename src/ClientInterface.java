import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ClientInterface {

	public static JFrame frame;
	public static String userName = "";
	public static String IPAddress = "";
	public int coffeeMix;
	public int milkMix;
	public int sugarMix;
	public int price;
	public int day;
	public double balance = 100;
	public int sales;
	public String status ="Please submit your recipe.";
	public int coffee;
	public int milk;
	public int sugar;
	public int cup;
	public double coffeeUnitPrice = 21.0;
	public double milkUnitPrice = 2.7;
	public double sugarUnitPrice = 2.5;
	public double cupUnitPrice = 0.1;
	public static Shop shop;
	public Shop updatedShop;
	private JTextField priceField;

	public  ClientInterface() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(151, 10, 217, 28);
		frame.getContentPane().add(lblCoffeeShopSimulation);

		JLabel lblDay = new JLabel("Day:");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setForeground(Color.RED);
		lblDay.setBounds(15, 290, 46, 14);
		frame.getContentPane().add(lblDay);

		JLabel lblNewLabel = new JLabel("Coffee Recipe");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(15, 60, 100, 20);
		frame.getContentPane().add(lblNewLabel);

		JSlider coffeePerCup = new JSlider();
		coffeePerCup.setValue(10);
		coffeePerCup.setMaximum(15);
		coffeePerCup.setBounds(100, 90, 100, 20);
		frame.getContentPane().add(coffeePerCup);

		JLabel lblCoffee = new JLabel("Coffee (g)");
		lblCoffee.setBounds(15, 90, 80, 20);
		frame.getContentPane().add(lblCoffee);

		JSlider milkPerCup = new JSlider();
		milkPerCup.setValue(130);
		milkPerCup.setMaximum(200);
		milkPerCup.setBounds(100, 120, 100, 20);
		frame.getContentPane().add(milkPerCup);

		JLabel lblNewLabel_1 = new JLabel("Milk (ml)");
		lblNewLabel_1.setBounds(15, 120, 80, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JSlider sugarPerCup = new JSlider();
		sugarPerCup.setMaximum(10);
		sugarPerCup.setValue(6);
		sugarPerCup.setBounds(100, 150, 100, 20);
		frame.getContentPane().add(sugarPerCup);

		JLabel lblSugar = new JLabel("Sugar (g)");
		lblSugar.setBounds(15, 150, 80, 20);
		frame.getContentPane().add(lblSugar);

		JLabel lblSalesPrice = new JLabel("Price ($)");
		lblSalesPrice.setBounds(15, 180, 80, 20);
		frame.getContentPane().add(lblSalesPrice);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(100, 210, 100, 20);
		frame.getContentPane().add(submitButton);

		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInventory.setBounds(250, 60, 100, 20);
		frame.getContentPane().add(lblInventory);

		JLabel lblCups = new JLabel("Cups");
		lblCups.setBounds(250, 100, 80, 14);
		frame.getContentPane().add(lblCups);

		JLabel lblCoffee_1 = new JLabel("Coffee (kg)");
		lblCoffee_1.setBounds(250, 130, 80, 14);
		frame.getContentPane().add(lblCoffee_1);

		JLabel lblSugar_1 = new JLabel("Sugar (kg)");
		lblSugar_1.setBounds(250, 160, 80, 14);
		frame.getContentPane().add(lblSugar_1);

		JLabel lblMilk = new JLabel("Milk (lt)");
		lblMilk.setBounds(250, 190, 80, 14);
		frame.getContentPane().add(lblMilk);

		JLabel lblAvailable = new JLabel("Stocks");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvailable.setBounds(320, 84, 46, 14);
		frame.getContentPane().add(lblAvailable);

		JLabel cupStockLabel = new JLabel("0");
		cupStockLabel.setBounds(340, 100, 46, 14);
		frame.getContentPane().add(cupStockLabel);

		JLabel coffeeStockLabel = new JLabel("0");
		coffeeStockLabel.setBounds(340, 130, 46, 14);
		frame.getContentPane().add(coffeeStockLabel);

		JLabel sugarStockLabel = new JLabel("0");
		sugarStockLabel.setBounds(340, 160, 46, 14);
		frame.getContentPane().add(sugarStockLabel);

		JLabel milkStockLabel = new JLabel("0");
		milkStockLabel.setBounds(340, 190, 46, 14);
		frame.getContentPane().add(milkStockLabel);

		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderAmount.setBounds(390, 84, 80, 14);
		frame.getContentPane().add(lblOrderAmount);

		JButton buyButton = new JButton("Buy");
		buyButton.setBounds(350, 215, 90, 20);
		frame.getContentPane().add(buyButton);

		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentBalance.setForeground(Color.RED);
		lblCurrentBalance.setBounds(15, 310, 100, 14);
		frame.getContentPane().add(lblCurrentBalance);

		JLabel currentBalanceLabel = new JLabel("100");
		currentBalanceLabel.setBounds(115, 310, 46, 14);
		frame.getContentPane().add(currentBalanceLabel);

		JLabel currentDayLabel = new JLabel("1");
		currentDayLabel.setBounds(45, 290, 46, 14);
		frame.getContentPane().add(currentDayLabel);

		JLabel lblDailySales = new JLabel("Previous Day Sales:");
		lblDailySales.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDailySales.setForeground(Color.RED);
		lblDailySales.setBounds(15, 330, 118, 14);
		frame.getContentPane().add(lblDailySales);

		JLabel previousDaySales = new JLabel("0");
		previousDaySales.setBounds(130, 330, 46, 14);
		frame.getContentPane().add(previousDaySales);

		JLabel lblConnectionStatus = new JLabel("Game Status:");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConnectionStatus.setForeground(Color.RED);
		lblConnectionStatus.setBounds(15, 350, 116, 14);
		frame.getContentPane().add(lblConnectionStatus);

		JLabel dataStatusLabel = new JLabel(status);
		dataStatusLabel.setBounds(110, 350, 450, 14);
		frame.getContentPane().add(dataStatusLabel);

		JLabel lblPrice = new JLabel("Price ($)");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(490, 84, 64, 14);
		frame.getContentPane().add(lblPrice);

		JLabel cupPriceLabel = new JLabel(""+cupUnitPrice);
		cupPriceLabel.setBounds(510, 101, 46, 14);
		frame.getContentPane().add(cupPriceLabel);

		JLabel coffeePriceLabel = new JLabel(""+coffeeUnitPrice);
		coffeePriceLabel.setBounds(510, 130, 46, 14);
		frame.getContentPane().add(coffeePriceLabel);

		JLabel sugarPriceLabel = new JLabel(""+sugarUnitPrice);
		sugarPriceLabel.setBounds(510, 160, 46, 14);
		frame.getContentPane().add(sugarPriceLabel);

		JLabel milkPriceLabel = new JLabel(""+milkUnitPrice);
		milkPriceLabel.setBounds(510, 190, 46, 14);
		frame.getContentPane().add(milkPriceLabel);

		JLabel coffeeSliderLabel = new JLabel("10");
		coffeeSliderLabel.setBounds(205, 90, 25, 15);
		frame.getContentPane().add(coffeeSliderLabel);

		JLabel milkSliderLabel = new JLabel("130");
		milkSliderLabel.setBounds(205, 120, 25, 15);
		frame.getContentPane().add(milkSliderLabel);

		JLabel sugarSliderLabel = new JLabel("6");
		sugarSliderLabel.setBounds(205, 150, 25, 15);
		frame.getContentPane().add(sugarSliderLabel);

		JSlider cupOrderSlider = new JSlider();
		cupOrderSlider.setMaximum(50);
		cupOrderSlider.setValue(0);
		cupOrderSlider.setBounds(380, 100, 100, 20);
		frame.getContentPane().add(cupOrderSlider);

		JSlider coffeeOrderSlider = new JSlider();
		coffeeOrderSlider.setMaximum(10);
		coffeeOrderSlider.setValue(0);
		coffeeOrderSlider.setBounds(380, 130, 100, 20);
		frame.getContentPane().add(coffeeOrderSlider);

		JSlider sugarOrderSlider = new JSlider();
		sugarOrderSlider.setMaximum(10);
		sugarOrderSlider.setValue(0);
		sugarOrderSlider.setBounds(380, 160, 100, 20);
		frame.getContentPane().add(sugarOrderSlider);

		JSlider milkOrderSlider = new JSlider();
		milkOrderSlider.setMaximum(10);
		milkOrderSlider.setValue(0);
		milkOrderSlider.setBounds(380, 190, 100, 20);
		frame.getContentPane().add(milkOrderSlider);

		JLabel cupOrderLabel = new JLabel("0");
		cupOrderLabel.setBounds(480, 100, 25, 15);
		frame.getContentPane().add(cupOrderLabel);

		JLabel coffeeOrderLabel = new JLabel("0");
		coffeeOrderLabel.setBounds(480, 130, 25, 15);
		frame.getContentPane().add(coffeeOrderLabel);

		JLabel sugarOrderLabel = new JLabel("0");
		sugarOrderLabel.setBounds(480, 160, 25, 15);
		frame.getContentPane().add(sugarOrderLabel);

		JLabel milkOrderLabel = new JLabel("0");
		milkOrderLabel.setBounds(480, 190, 25, 15);
		frame.getContentPane().add(milkOrderLabel);
		
		JTextField priceField = new DoubleJTextField();
		priceField.setBounds(100, 180, 100, 20);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);
		frame.setVisible(true);

		buyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				double orderAmount = ((coffeeOrderSlider.getValue() * coffeeUnitPrice) + (milkOrderSlider.getValue() * milkUnitPrice) + (sugarOrderSlider.getValue() * sugarUnitPrice) + (cupOrderSlider.getValue() * cupUnitPrice));

				if(balance>orderAmount){
					balance = balance - orderAmount;
					shop.balance = balance;
					coffee = coffee + coffeeOrderSlider.getValue();
					shop.inventory.coffee = coffee;
					milk = milk + milkOrderSlider.getValue();
					shop.inventory.milk = milk;
					sugar = sugar + sugarOrderSlider.getValue();
					shop.inventory.sugar = sugar;
					cup = cup + cupOrderSlider.getValue();
					shop.inventory.cups = cup;

					cupStockLabel.setText(""+cup);
					coffeeStockLabel.setText(""+coffee);
					milkStockLabel.setText(""+milk);
					sugarStockLabel.setText(""+sugar);
					currentBalanceLabel.setText(""+balance);

					status = "Selected items added to your inventory.";
					dataStatusLabel.setText(status);

				}else{
					status = "You don't have enough balance!";
					dataStatusLabel.setText(status);
				}
			}
		});

		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				submitButton.setEnabled(false);
				buyButton.setEnabled(false);

				Recipe recipe = new Recipe(coffeePerCup.getValue(),milkPerCup.getValue(),sugarPerCup.getValue(), Double.parseDouble(priceField.getText()));
				shop.recipe = recipe;

				status = "Sending data...";
				dataStatusLabel.setText(status);

				try {
					updatedShop = Client.connect(shop);
					shop.balance = updatedShop.balance;
					shop.dailySales = updatedShop.dailySales;
					shop.day = updatedShop.day;
					shop.inventory = updatedShop.inventory;
					shop.inventory.milk = 0;
					System.out.println("Daily sales: "+updatedShop.dailySales);

					cupStockLabel.setText(""+shop.inventory.cups);
					coffeeStockLabel.setText(""+shop.inventory.coffee);
					milkStockLabel.setText(""+shop.inventory.milk);
					sugarStockLabel.setText(""+shop.inventory.sugar);
					currentBalanceLabel.setText(""+shop.balance);
					previousDaySales.setText(""+ shop.dailySales);
					currentDayLabel.setText(""+shop.day);

					submitButton.setEnabled(true);
					buyButton.setEnabled(true);

					status = "Please set your recipe and inventory for the next day.";
					dataStatusLabel.setText(status);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		coffeePerCup.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				coffeeSliderLabel.setText(""+coffeePerCup.getValue());
			}
		});

		milkPerCup.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				milkSliderLabel.setText(""+milkPerCup.getValue());
			}
		});

		sugarPerCup.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				sugarSliderLabel.setText(""+sugarPerCup.getValue());
			}
		});

		coffeeOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				coffeeOrderLabel.setText(""+coffeeOrderSlider.getValue());
			}
		});

		milkOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				milkOrderLabel.setText(""+milkOrderSlider.getValue());
			}
		});

		sugarOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				sugarOrderLabel.setText(""+sugarOrderSlider.getValue());
			}
		});

		cupOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				cupOrderLabel.setText(""+cupOrderSlider.getValue());
			}
		});
	}


	public static void main (String [] args){
		ClientInterface cl = new ClientInterface();
		IPAddress = JOptionPane.showInputDialog(frame,
                "Please enter the IP address of server:", null);
		shop= new Shop(userName);
		
		userName = JOptionPane.showInputDialog(frame,
                "What is your name?", null);
		shop= new Shop(userName);
	}
}