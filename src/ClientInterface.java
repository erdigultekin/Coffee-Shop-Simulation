import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ClientInterface {

	public static JFrame frame;
	public static String userName = null;
	public static String IPAddress = null;
	public String status ="Please submit your recipe.";
	public double coffeeUnitPrice = 21.0;
	public double milkUnitPrice = 2.7;
	public double sugarUnitPrice = 2.5;
	public double cupUnitPrice = 0.1;
	public static Shop shop;
	public Shop updatedShop;

	public  ClientInterface() {
		
		//Frame
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Heading label
		
		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(239, 11, 217, 28);
		frame.getContentPane().add(lblCoffeeShopSimulation);
		
		//Game status information labels
		
		/* day indicator */
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDay.setForeground(Color.RED);
		lblDay.setBounds(15, 360, 46, 14);
		frame.getContentPane().add(lblDay);
		
		JLabel currentDayLabel = new JLabel("1");
		currentDayLabel.setBounds(45, 360, 46, 14);
		frame.getContentPane().add(currentDayLabel);
		
		/* balance indicator */
		
		JLabel lblCurrentBalance = new JLabel("Current Balance:");
		lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentBalance.setForeground(Color.RED);
		lblCurrentBalance.setBounds(15, 380, 100, 14);
		frame.getContentPane().add(lblCurrentBalance);

		JLabel currentBalanceLabel = new JLabel("100");
		currentBalanceLabel.setBounds(115, 380, 46, 14);
		frame.getContentPane().add(currentBalanceLabel);
		
		/* daily sales indicator */
		
		JLabel lblDailySales = new JLabel("Previous Day Sales:");
		lblDailySales.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDailySales.setForeground(Color.RED);
		lblDailySales.setBounds(15, 400, 118, 14);
		frame.getContentPane().add(lblDailySales);

		JLabel previousDaySales = new JLabel("0");
		previousDaySales.setBounds(130, 400, 46, 14);
		frame.getContentPane().add(previousDaySales);
		
		/* game status indicator */
		
		JLabel lblGameStatus = new JLabel("Game Status:");
		lblGameStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGameStatus.setForeground(Color.RED);
		lblGameStatus.setBounds(15, 425, 90, 14);
		frame.getContentPane().add(lblGameStatus);

		JLabel dataStatusLabel = new JLabel(status);
		dataStatusLabel.setBounds(115, 425, 450, 14);
		frame.getContentPane().add(dataStatusLabel);
		
		//Coffee recipe
		
		JLabel lblCoffeeRecipe = new JLabel("Coffee Recipe");
		lblCoffeeRecipe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCoffeeRecipe.setBounds(15, 125, 100, 20);
		frame.getContentPane().add(lblCoffeeRecipe);

		/* coffee amount selector */
		
		JLabel lblCoffee = new JLabel("Coffee (g)");
		lblCoffee.setBounds(15, 170, 80, 20);
		frame.getContentPane().add(lblCoffee);
		
		JSlider coffeePerCup = new JSlider();
		coffeePerCup.setValue(10);
		coffeePerCup.setMaximum(15);
		coffeePerCup.setBounds(100, 170, 100, 20);
		frame.getContentPane().add(coffeePerCup);
		
		JLabel coffeeSliderLabel = new JLabel("10");
		coffeeSliderLabel.setBounds(205, 170, 25, 15);
		frame.getContentPane().add(coffeeSliderLabel);

		/* milk amount selector */
		
		JLabel lblMilk = new JLabel("Milk (ml)");
		lblMilk.setBounds(15, 200, 80, 20);
		frame.getContentPane().add(lblMilk);
		
		JSlider milkPerCup = new JSlider();
		milkPerCup.setMaximum(200);
		milkPerCup.setBounds(100, 200, 100, 20);
		milkPerCup.setValue(130);
		frame.getContentPane().add(milkPerCup);
		
		JLabel milkSliderLabel = new JLabel("130");
		milkSliderLabel.setBounds(205, 200, 25, 15);
		frame.getContentPane().add(milkSliderLabel);
		

		/* sugar amount selector */
		
		JLabel lblSugar = new JLabel("Sugar (g)");
		lblSugar.setBounds(15, 230, 80, 20);
		frame.getContentPane().add(lblSugar);
		
		JSlider sugarPerCup = new JSlider();
		sugarPerCup.setMaximum(20);
		sugarPerCup.setValue(6);
		sugarPerCup.setBounds(100, 230, 100, 20);
		frame.getContentPane().add(sugarPerCup);
		
		JLabel sugarSliderLabel = new JLabel("6");
		sugarSliderLabel.setBounds(205, 230, 25, 15);
		frame.getContentPane().add(sugarSliderLabel);

		/* sales price selector */
		
		JLabel lblSalesPrice = new JLabel("Price ($)");
		lblSalesPrice.setBounds(15, 260, 80, 20);
		frame.getContentPane().add(lblSalesPrice);
		
		JTextField priceField = new DoubleJTextField();
		priceField.setBounds(100, 260, 100, 20);
		frame.getContentPane().add(priceField);
		priceField.setColumns(10);

		/* recipe submit button */
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(100, 300, 100, 20);
		frame.getContentPane().add(submitButton);
		
		//Inventory

		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInventory.setBounds(250, 125, 100, 20);
		frame.getContentPane().add(lblInventory);
		
		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStocks.setBounds(320, 150, 46, 14);
		frame.getContentPane().add(lblStocks);
		
		JLabel lblOrderAmount = new JLabel("Order Amount");
		lblOrderAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrderAmount.setBounds(390, 150, 80, 14);
		frame.getContentPane().add(lblOrderAmount);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(510, 150, 34, 14);
		frame.getContentPane().add(lblPrice);
		
		/* cup inventory */
		
		JLabel lblCupsInventory = new JLabel("Cups");
		lblCupsInventory.setBounds(250, 260, 80, 14);
		frame.getContentPane().add(lblCupsInventory);
		
		JLabel cupStockLabel = new JLabel("0");
		cupStockLabel.setBounds(340, 260, 46, 14);
		frame.getContentPane().add(cupStockLabel);
		
		/* coffee inventory */

		JLabel lblCoffeeInventory = new JLabel("Coffee (kg)");
		lblCoffeeInventory.setBounds(250, 170, 80, 14);
		frame.getContentPane().add(lblCoffeeInventory);
		
		JLabel coffeeStockLabel = new JLabel("0");
		coffeeStockLabel.setBounds(340, 170, 46, 14);
		frame.getContentPane().add(coffeeStockLabel);
		
		/* sugar inventory */

		JLabel lblSugarInventory = new JLabel("Sugar (kg)");
		lblSugarInventory.setBounds(250, 230, 80, 14);
		frame.getContentPane().add(lblSugarInventory);
		
		JLabel sugarStockLabel = new JLabel("0");
		sugarStockLabel.setBounds(340, 230, 46, 14);
		frame.getContentPane().add(sugarStockLabel);
		
		/* milk inventory */

		JLabel lblMilkInventory = new JLabel("Milk (lt)");
		lblMilkInventory.setBounds(250, 200, 80, 14);
		frame.getContentPane().add(lblMilkInventory);

		JLabel milkStockLabel = new JLabel("0");
		milkStockLabel.setBounds(340, 200, 46, 14);
		frame.getContentPane().add(milkStockLabel);

		//Prices
		/* cup prices */
		
		JLabel cupPriceLabel = new JLabel("$0.125");
		cupPriceLabel.setBounds(510, 260, 46, 14);
		frame.getContentPane().add(cupPriceLabel);
		
		/* coffee prices */

		JLabel coffeePriceLabel = new JLabel("$22.5");
		coffeePriceLabel.setBounds(510, 170, 46, 14);
		frame.getContentPane().add(coffeePriceLabel);
		
		/* sugar prices */

		JLabel sugarPriceLabel = new JLabel("$2.75");
		sugarPriceLabel.setBounds(510, 230, 46, 14);
		frame.getContentPane().add(sugarPriceLabel);
		
		/* milk prices */

		JLabel milkPriceLabel = new JLabel("$2.75");
		milkPriceLabel.setBounds(510, 200, 46, 14);
		frame.getContentPane().add(milkPriceLabel);
		
		//Inventory items purchase
		
		/* cup purchase */
		
		JLabel cupOrderLabel = new JLabel("0");
		cupOrderLabel.setBounds(480, 260, 25, 15);
		frame.getContentPane().add(cupOrderLabel);

		JSlider cupOrderSlider = new JSlider();
		cupOrderSlider.setMaximum(50);
		cupOrderSlider.setValue(0);
		cupOrderSlider.setBounds(380, 260, 100, 20);
		frame.getContentPane().add(cupOrderSlider);
		
		/* coffee purchase */
		
		JLabel coffeeOrderLabel = new JLabel("0");
		coffeeOrderLabel.setBounds(480, 170, 25, 15);
		frame.getContentPane().add(coffeeOrderLabel);
		
		JSlider coffeeOrderSlider = new JSlider();
		coffeeOrderSlider.setMaximum(5);
		coffeeOrderSlider.setValue(0);
		coffeeOrderSlider.setBounds(380, 170, 100, 20);
		frame.getContentPane().add(coffeeOrderSlider);

		/* sugar purchase */
		
		JLabel sugarOrderLabel = new JLabel("0");
		sugarOrderLabel.setBounds(480, 230, 25, 15);
		frame.getContentPane().add(sugarOrderLabel);

		JSlider sugarOrderSlider = new JSlider();
		sugarOrderSlider.setMaximum(5);
		sugarOrderSlider.setValue(0);
		sugarOrderSlider.setBounds(380, 230, 100, 20);
		frame.getContentPane().add(sugarOrderSlider);
		
		/* milk purchase */
		
		JLabel milkOrderLabel = new JLabel("0");
		milkOrderLabel.setBounds(480, 200, 25, 15);
		frame.getContentPane().add(milkOrderLabel);
		
		JSlider milkOrderSlider = new JSlider();
		milkOrderSlider.setMaximum(10);
		milkOrderSlider.setValue(0);
		milkOrderSlider.setBounds(380, 200, 100, 20);
		frame.getContentPane().add(milkOrderSlider);
		
		//Inventory buy button

		JButton buyButton = new JButton("Buy");
		buyButton.setBounds(350, 300, 90, 20);
		frame.getContentPane().add(buyButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(235, 140, 10, 186);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.RED);
		separator_1.setBounds(15, 120, 545, 3);
		frame.getContentPane().add(separator_1);
		
		JLabel lblLabel = new JLabel("Instructions");
		lblLabel.setForeground(Color.RED);
		lblLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLabel.setBounds(20, 35, 530, 20);
		frame.getContentPane().add(lblLabel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.RED);
		separator_2.setBounds(10, 55, 545, 3);
		frame.getContentPane().add(separator_2);
		
		JLabel lblFillYour = new JLabel("1- Fill your inventory (Right side). Choose order amount and then use the buy button.");
		lblFillYour.setBounds(15, 60, 550, 14);
		frame.getContentPane().add(lblFillYour);
		
		JLabel lblDetermineThe = new JLabel("2- Determine the ingredients of your coffee and set the price (Left side).");
		lblDetermineThe.setBounds(15, 80, 550, 14);
		frame.getContentPane().add(lblDetermineThe);
		
		JLabel lblClickOn = new JLabel("3- Click on the submit button (Left side).");
		lblClickOn.setBounds(15, 99, 550, 14);
		frame.getContentPane().add(lblClickOn);
		
		/* buy button action listener */
		buyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Calculate the total value of orders
				double cupOrderValue = InventoryPrices.cupOrder(cupOrderSlider.getValue());
				double coffeeOrderValue = InventoryPrices.coffeeOrder(coffeeOrderSlider.getValue());
				double milkOrderValue = InventoryPrices.milkOrder(milkOrderSlider.getValue());
				double sugarOrderValue = InventoryPrices.sugarOrder(sugarOrderSlider.getValue());
				
				double totalOrderValue = cupOrderValue + coffeeOrderValue + milkOrderValue + sugarOrderValue;
				//Check if there is enough balance for orders
				if(shop.balance>=totalOrderValue){
					
					//Buy ordered items and decrease balance
					
					shop.inventory.coffee = shop.inventory.coffee + coffeeOrderSlider.getValue();
					
					shop.inventory.milk = shop.inventory.milk + milkOrderSlider.getValue();
					
					shop.inventory.sugar = shop.inventory.sugar + sugarOrderSlider.getValue();
					
					shop.inventory.cups = shop.inventory.cups + cupOrderSlider.getValue();
					
					shop.balance = shop.balance - totalOrderValue;
					
					//Update the GUI labels
					
					cupStockLabel.setText(""+shop.inventory.cups);
					coffeeStockLabel.setText(""+shop.inventory.coffee);
					milkStockLabel.setText(""+shop.inventory.milk);
					sugarStockLabel.setText(""+shop.inventory.sugar);
					currentBalanceLabel.setText(""+shop.balance);
					
					status = "Selected items added to your inventory.";
					dataStatusLabel.setText(status);
					
				}else{
					status = "You don't have enough balance!";
					dataStatusLabel.setText(status);
				}
				
				//Clean local order values for the next order
				cupOrderValue = 0.0;
				coffeeOrderValue = 0.0;
				milkOrderValue = 0.0;
				sugarOrderValue = 0.0;
				totalOrderValue = 0.0;
			}
		});

		/* submit button action listener */
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(priceField.getText().equals("")){
					status = "Please set the coffee price for the next day!";
					dataStatusLabel.setText(status);
				}else{
					
					
					//Disable submit and buy buttons during the submit actions
					submitButton.setEnabled(false);
					buyButton.setEnabled(false);
					
					//Get recipe values
					Recipe recipe = new Recipe(coffeePerCup.getValue(),milkPerCup.getValue(),sugarPerCup.getValue(), Double.parseDouble(priceField.getText()));
					//Update the shop's recipe
					shop.recipe = recipe;

					try {
						//Send and receive the shop object
						updatedShop = Client.connect(shop);
						
						//Update shop data with coming shop object's data
						shop = updatedShop;
						shop.inventory.milk = 0.0;
						
						//Clean up the milk inventory
						//shop.inventory.milk = 0;
						
						System.out.println("Daily sales: "+shop.dailySales);
						
						//Update the GUI with new data
						cupStockLabel.setText(""+shop.inventory.cups);
						coffeeStockLabel.setText(""+shop.inventory.coffee);
						milkStockLabel.setText(""+shop.inventory.milk);
						sugarStockLabel.setText(""+shop.inventory.sugar);
						currentBalanceLabel.setText(""+shop.balance);
						previousDaySales.setText(""+ shop.dailySales);
						currentDayLabel.setText(""+shop.day);
						
						//Enable submit and buy buttons

						submitButton.setEnabled(true);
						buyButton.setEnabled(true);
						
						//Update game status

						status = "Please set your recipe and inventory for the next day.";
						dataStatusLabel.setText(status);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				

			}
		});

		//Recipe change listeners
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
		
		//Inventory change listeners

		coffeeOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				coffeeOrderLabel.setText(""+coffeeOrderSlider.getValue());
				coffeePriceLabel.setText("$"+InventoryPrices.coffeeUnit(coffeeOrderSlider.getValue()));
			}
		});

		milkOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				milkOrderLabel.setText(""+milkOrderSlider.getValue());
				milkPriceLabel.setText("$"+InventoryPrices.milkUnit(milkOrderSlider.getValue()));
			}
		});

		sugarOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				sugarOrderLabel.setText(""+sugarOrderSlider.getValue());
				sugarPriceLabel.setText("$"+InventoryPrices.sugarUnit(sugarOrderSlider.getValue()));
			}
		});

		cupOrderSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				cupOrderLabel.setText(""+cupOrderSlider.getValue());
				cupPriceLabel.setText("$"+InventoryPrices.cupUnit(cupOrderSlider.getValue()));
			}
		});
		
		//set frame visible
		frame.setVisible(true);
	}


	public static void main (String [] args){
		//Initialize the interface
		ClientInterface cl = new ClientInterface();
		
		while(IPAddress == null || IPAddress.equals("")){
			//Get server IP address from the user
			IPAddress = JOptionPane.showInputDialog(frame, "Please enter the IP address of server:", null);
		}
		
		while(userName == null || userName.equals("")){
			//Get user name from the user
			userName = JOptionPane.showInputDialog(frame, "What is your name?", null);
		}
		
		//Initialize shop object
		shop= new Shop(userName);
	}
}