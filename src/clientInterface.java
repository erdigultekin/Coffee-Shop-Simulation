import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class clientInterface {

	public JFrame frame;
	public JTextField userNameTextBox;
	public String userName;
	public int coffeeMix;
	public int milkMix;
	public int price;
	public int day;
	public double balance = 100;
	public int sales;
	public String status ="Please enter your name";
	public int coffee;
	public int milk;
	public int sugar;
	public int cup;
	public double coffeeUnitPrice = 1.5;
	public double milkUnitPrice = 2.7;
	public double sugarUnitPrice = 2.5;
	public double cupUnitPrice = 3.1;
	
	public  clientInterface() {
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
		coffeePerCup.setBounds(100, 90, 100, 20);
		frame.getContentPane().add(coffeePerCup);
		
		JLabel lblCoffee = new JLabel("Coffee (mg)");
		lblCoffee.setBounds(15, 90, 80, 20);
		frame.getContentPane().add(lblCoffee);
		
		JSlider milkPerCup = new JSlider();
		milkPerCup.setBounds(100, 120, 100, 20);
		frame.getContentPane().add(milkPerCup);
		
		JLabel lblNewLabel_1 = new JLabel("Milk (ml)");
		lblNewLabel_1.setBounds(15, 120, 80, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSlider sugarPerCup = new JSlider();
		sugarPerCup.setBounds(100, 150, 100, 20);
		frame.getContentPane().add(sugarPerCup);
		
		JLabel lblSugar = new JLabel("Sugar (mg)");
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
		
		JLabel cupStockLabel = new JLabel("0");
		cupStockLabel.setBounds(300, 100, 46, 14);
		frame.getContentPane().add(cupStockLabel);
		
		JLabel coffeeStockLabel = new JLabel("0");
		coffeeStockLabel.setBounds(300, 130, 46, 14);
		frame.getContentPane().add(coffeeStockLabel);
		
		JLabel sugarStockLabel = new JLabel("0");
		sugarStockLabel.setBounds(300, 160, 46, 14);
		frame.getContentPane().add(sugarStockLabel);
		
		JLabel milkStockLabel = new JLabel("0");
		milkStockLabel.setBounds(300, 190, 46, 14);
		frame.getContentPane().add(milkStockLabel);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderAmount.setBounds(350, 84, 80, 14);
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
		lblPrice.setBounds(470, 84, 64, 14);
		frame.getContentPane().add(lblPrice);
		
		JLabel cupPriceLabel = new JLabel(""+cupUnitPrice);
		cupPriceLabel.setBounds(490, 101, 46, 14);
		frame.getContentPane().add(cupPriceLabel);
		
		JLabel coffeePriceLabel = new JLabel(""+coffeeUnitPrice);
		coffeePriceLabel.setBounds(490, 130, 46, 14);
		frame.getContentPane().add(coffeePriceLabel);
		
		JLabel sugarPriceLabel = new JLabel(""+sugarUnitPrice);
		sugarPriceLabel.setBounds(490, 160, 46, 14);
		frame.getContentPane().add(sugarPriceLabel);
		
		JLabel milkPriceLabel = new JLabel(""+milkUnitPrice);
		milkPriceLabel.setBounds(490, 190, 46, 14);
		frame.getContentPane().add(milkPriceLabel);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setBounds(15, 265, 80, 14);
		frame.getContentPane().add(lblUserName);
		
		userNameTextBox = new JTextField();
		userNameTextBox.setBounds(80, 264, 80, 15);
		frame.getContentPane().add(userNameTextBox);
		userNameTextBox.setColumns(10);
		
		JButton nameButton = new JButton("OK");
		nameButton.setBounds(180, 265, 80, 15);
		frame.getContentPane().add(nameButton);
		
		JLabel coffeeSliderLabel = new JLabel("50");
		coffeeSliderLabel.setBounds(205, 90, 25, 15);
		frame.getContentPane().add(coffeeSliderLabel);
		
		JLabel milkSliderLabel = new JLabel("50");
		milkSliderLabel.setBounds(205, 120, 25, 15);
		frame.getContentPane().add(milkSliderLabel);
		
		JLabel sugarSliderLabel = new JLabel("50");
		sugarSliderLabel.setBounds(205, 150, 25, 15);
		frame.getContentPane().add(sugarSliderLabel);
		
		JSlider priceSlider = new JSlider();
		priceSlider.setMaximum(15);
		priceSlider.setMinimum(1);
		priceSlider.setValue(1);
		priceSlider.setBounds(100, 180, 100, 20);
		frame.getContentPane().add(priceSlider);
		
		JLabel priceSliderLabel = new JLabel("1");
		priceSliderLabel.setBounds(205, 180, 25, 15);
		frame.getContentPane().add(priceSliderLabel);
		
		JSlider cupOrderSlider = new JSlider();
		cupOrderSlider.setMaximum(25);
		cupOrderSlider.setValue(0);
		cupOrderSlider.setBounds(340, 100, 100, 20);
		frame.getContentPane().add(cupOrderSlider);
		
		JSlider coffeeOrderSlider = new JSlider();
		coffeeOrderSlider.setMaximum(25);
		coffeeOrderSlider.setValue(0);
		coffeeOrderSlider.setBounds(340, 130, 100, 20);
		frame.getContentPane().add(coffeeOrderSlider);
		
		JSlider sugarOrderSlider = new JSlider();
		sugarOrderSlider.setMaximum(25);
		sugarOrderSlider.setValue(0);
		sugarOrderSlider.setBounds(340, 160, 100, 20);
		frame.getContentPane().add(sugarOrderSlider);
		
		JSlider milkOrderSlider = new JSlider();
		milkOrderSlider.setMaximum(25);
		milkOrderSlider.setValue(0);
		milkOrderSlider.setBounds(340, 190, 100, 20);
		frame.getContentPane().add(milkOrderSlider);
		
		JLabel cupOrderLabel = new JLabel("0");
		cupOrderLabel.setBounds(440, 100, 25, 15);
		frame.getContentPane().add(cupOrderLabel);
		
		JLabel coffeeOrderLabel = new JLabel("0");
		coffeeOrderLabel.setBounds(440, 130, 25, 15);
		frame.getContentPane().add(coffeeOrderLabel);
		
		JLabel sugarOrderLabel = new JLabel("0");
		sugarOrderLabel.setBounds(440, 160, 25, 15);
		frame.getContentPane().add(sugarOrderLabel);
		
		JLabel milkOrderLabel = new JLabel("0");
		milkOrderLabel.setBounds(440, 190, 25, 15);
		frame.getContentPane().add(milkOrderLabel);
		frame.setVisible(true);
		
		submitButton.setEnabled(false);
		
		
		nameButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    userName = userNameTextBox.getText();
		    nameButton.setEnabled(false);
		    userNameTextBox.setEnabled(false);
		    submitButton.setEnabled(true);
		    
		    status = "Please determine your coffee recipe, coffe price and press submit button.";
			dataStatusLabel.setText(status);
		  }
		});
		
		buyButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  
			double orderAmount = ((coffeeOrderSlider.getValue() * coffeeUnitPrice) + (milkOrderSlider.getValue() * milkUnitPrice) + (sugarOrderSlider.getValue() * sugarUnitPrice) + (cupOrderSlider.getValue() * cupUnitPrice));
			  
			if(balance>orderAmount){
			balance = balance - orderAmount;
			coffee = coffee + coffeeOrderSlider.getValue();
			milk = milk + milkOrderSlider.getValue();
			sugar = sugar + sugarOrderSlider.getValue();
			cup = cup + cupOrderSlider.getValue();
			
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
		
		
		priceSlider.addChangeListener(new ChangeListener()
		{
		  public void stateChanged(ChangeEvent e)
		  {
			  priceSliderLabel.setText(""+priceSlider.getValue());
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
		clientInterface cl = new clientInterface();
	}
}
