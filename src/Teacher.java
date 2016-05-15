
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Teacher {
	static DefaultListModel playerList;
	static DefaultListModel balances;
	static DefaultListModel sales;
	static DefaultListModel prices;
	static DefaultListModel coffeeMix;
	static DefaultListModel sugarMix;
	static DefaultListModel milkMix;
	static DefaultListModel coffees;
	static DefaultListModel sugars;
	static DefaultListModel milks;
	static DefaultListModel cups;
	public static boolean readyCheck;
	public static int day;
	private JFrame frame;
	public static JTextField betaOne;
	public static JTextField betaTwo;
	public static JTextField betaThree;
	public static JTextField alphaOne;
	public static JTextField alphaTwo;
	public static JTextField alphaThree;
	public static JTextField probabilityOne;
	public static JTextField probabilityTwo;
	public static JTextField probabilityThree;

	public Teacher(){
		playerList = new DefaultListModel();
		balances = new DefaultListModel();
		sales = new DefaultListModel();
		prices = new DefaultListModel();
		sugarMix = new DefaultListModel();
		milkMix = new DefaultListModel();
		coffeeMix = new DefaultListModel();
		coffees = new DefaultListModel();
		milks = new DefaultListModel();
		sugars = new DefaultListModel();
		cups = new DefaultListModel();
		day = 1;
		frame = new JFrame();
		frame.setBounds(100, 100, 894, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCoffeeShopSimulation = new JLabel("Coffee Shop Simulation Management Console");
		lblCoffeeShopSimulation.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCoffeeShopSimulation.setBounds(218, 5, 420, 20);
		frame.getContentPane().add(lblCoffeeShopSimulation);

		JList connectedPlayersList = new JList(playerList);
		connectedPlayersList.setBounds(30, 80, 115, 243);
		frame.getContentPane().add(connectedPlayersList);

		JLabel lblConnectedPlayers = new JLabel("Players");
		lblConnectedPlayers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConnectedPlayers.setBounds(65, 60, 40, 20);
		frame.getContentPane().add(lblConnectedPlayers);

		JButton runButton = new JButton("Run Simulation");
		runButton.setBounds(727, 476, 119, 64);
		frame.getContentPane().add(runButton);

		JLabel lblDay = new JLabel("Day:");
		lblDay.setForeground(Color.RED);
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDay.setBounds(760, 420, 40, 25);
		frame.getContentPane().add(lblDay);

		JLabel dayLabel = new JLabel("1");
		dayLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		dayLabel.setBounds(800, 420, 40, 25);
		frame.getContentPane().add(dayLabel);
		
		JLabel lblBalance = new JLabel("Balance ($)");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalance.setBounds(160, 60, 60, 20);
		frame.getContentPane().add(lblBalance);
		
		JLabel lblSales = new JLabel("Prev. Sales");
		lblSales.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSales.setBounds(255, 60, 60, 20);
		frame.getContentPane().add(lblSales);
		
		JLabel lblCoffeePrice = new JLabel("Coffee Price ($)");
		lblCoffeePrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCoffeePrice.setBounds(330, 60, 82, 20);
		frame.getContentPane().add(lblCoffeePrice);
		
		JList balanceList = new JList(balances);
		balanceList.setBounds(155, 80, 70, 243);
		frame.getContentPane().add(balanceList);
		
		JList salesList = new JList(sales);
		salesList.setBounds(240, 80, 80, 243);
		frame.getContentPane().add(salesList);
		
		JList priceList = new JList(prices);
		priceList.setBounds(330, 80, 70, 243);
		frame.getContentPane().add(priceList);
		
		JLabel lblRecipe = new JLabel("Recipes");
		lblRecipe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRecipe.setBounds(480, 45, 60, 15);
		frame.getContentPane().add(lblRecipe);
		
		JLabel lblCoffee = new JLabel("Coffee (g)");
		lblCoffee.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCoffee.setBounds(430, 60, 60, 14);
		frame.getContentPane().add(lblCoffee);
		
		JList coffeeMixList = new JList(coffeeMix);
		coffeeMixList.setBounds(430, 80, 50, 243);
		frame.getContentPane().add(coffeeMixList);
		
		JLabel lblSugar = new JLabel("Sugar (g)");
		lblSugar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSugar.setBounds(490, 60, 50, 14);
		frame.getContentPane().add(lblSugar);
		
		JList sugarMixList = new JList(sugarMix);
		sugarMixList.setBounds(490, 80, 46, 243);
		frame.getContentPane().add(sugarMixList);
		
		JList milkMixList = new JList(milkMix);
		milkMixList.setBounds(550, 80, 50, 243);
		frame.getContentPane().add(milkMixList);
		
		JLabel lblMilk = new JLabel("Milk (ml)");
		lblMilk.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMilk.setBounds(550, 60, 50, 14);
		frame.getContentPane().add(lblMilk);
		
		JLabel lblInventories = new JLabel("Inventories");
		lblInventories.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInventories.setBounds(680, 45, 80, 15);
		frame.getContentPane().add(lblInventories);
		
		JLabel lblCups = new JLabel("Cups");
		lblCups.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCups.setBounds(620, 60, 46, 14);
		frame.getContentPane().add(lblCups);
		
		JList cupsList = new JList(cups);
		cupsList.setBounds(620, 80, 50, 243);
		frame.getContentPane().add(cupsList);
		
		JLabel lblCoffeekg = new JLabel("Coffee (kg)");
		lblCoffeekg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblCoffeekg.setBounds(680, 60, 60, 14);
		frame.getContentPane().add(lblCoffeekg);
		
		JList coffeeList = new JList(coffees);
		coffeeList.setBounds(680, 80, 50, 243);
		frame.getContentPane().add(coffeeList);
		
		JLabel lblSugar_1 = new JLabel("Sugar (kg)");
		lblSugar_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSugar_1.setBounds(740, 60, 55, 14);
		frame.getContentPane().add(lblSugar_1);
		
		JList sugarList = new JList(sugars);
		sugarList.setBounds(740, 80, 53, 243);
		frame.getContentPane().add(sugarList);
		
		JLabel lblMilklt = new JLabel("Milk (lt)");
		lblMilklt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMilklt.setBounds(800, 60, 46, 14);
		frame.getContentPane().add(lblMilklt);
		
		JList milkList = new JList(milks);
		milkList.setBounds(800, 80, 51, 243);
		frame.getContentPane().add(milkList);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.WHITE);
		separator.setBounds(415, 80, 5, 240);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.WHITE);
		separator_1.setBounds(610, 80, 5, 240);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 330, 830, 20);
		frame.getContentPane().add(separator_2);
		
		JLabel lblUtilityFunctions = new JLabel("Utility Functions");
		lblUtilityFunctions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUtilityFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtilityFunctions.setBounds(330, 340, 220, 25);
		frame.getContentPane().add(lblUtilityFunctions);
		
		JLabel lblFunction = new JLabel("Population 3");
		lblFunction.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFunction.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunction.setBounds(40, 520, 100, 20);
		frame.getContentPane().add(lblFunction);
		
		JLabel label = new JLabel("Population 1");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(40, 420, 100, 20);
		frame.getContentPane().add(label);
		
		JLabel lblFunction_1 = new JLabel("Population 2");
		lblFunction_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFunction_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunction_1.setBounds(40, 470, 100, 20);
		frame.getContentPane().add(lblFunction_1);
		
		JLabel lblBeta = new JLabel("Beta");
		lblBeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblBeta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBeta.setBounds(150, 370, 86, 20);
		frame.getContentPane().add(lblBeta);
		
		betaOne = new DoubleJTextField();
		betaOne.setBounds(150, 420, 86, 20);
		frame.getContentPane().add(betaOne);
		betaOne.setColumns(10);
		
		betaTwo = new DoubleJTextField();
		betaTwo.setColumns(10);
		betaTwo.setBounds(150, 470, 86, 20);
		frame.getContentPane().add(betaTwo);
		
		betaThree = new DoubleJTextField();
		betaThree.setColumns(10);
		betaThree.setBounds(150, 520, 86, 20);
		frame.getContentPane().add(betaThree);
		
		alphaOne = new DoubleJTextField();
		alphaOne.setBounds(350, 420, 86, 20);
		frame.getContentPane().add(alphaOne);
		alphaOne.setColumns(10);
		
		alphaTwo = new DoubleJTextField();
		alphaTwo.setColumns(10);
		alphaTwo.setBounds(350, 470, 86, 20);
		frame.getContentPane().add(alphaTwo);
		
		alphaThree = new DoubleJTextField();
		alphaThree.setColumns(10);
		alphaThree.setBounds(350, 520, 86, 20);
		frame.getContentPane().add(alphaThree);
		
		probabilityOne = new DoubleJTextField();
		probabilityOne.setBounds(550, 420, 86, 20);
		frame.getContentPane().add(probabilityOne);
		probabilityOne.setColumns(10);
		
		probabilityTwo = new DoubleJTextField();
		probabilityTwo.setColumns(10);
		probabilityTwo.setBounds(550, 470, 86, 20);
		frame.getContentPane().add(probabilityTwo);
		
		probabilityThree = new DoubleJTextField();
		probabilityThree.setColumns(10);
		probabilityThree.setBounds(550, 520, 86, 20);
		frame.getContentPane().add(probabilityThree);
		
		JLabel lblqualityCoefficient = new JLabel("(Quality Coefficient)");
		lblqualityCoefficient.setHorizontalAlignment(SwingConstants.CENTER);
		lblqualityCoefficient.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblqualityCoefficient.setBounds(130, 390, 120, 20);
		frame.getContentPane().add(lblqualityCoefficient);
		
		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlpha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlpha.setBounds(350, 370, 86, 20);
		frame.getContentPane().add(lblAlpha);
		
		JLabel lblpriceCofficient = new JLabel("(Price Coefficient)");
		lblpriceCofficient.setHorizontalAlignment(SwingConstants.CENTER);
		lblpriceCofficient.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblpriceCofficient.setBounds(335, 390, 120, 20);
		frame.getContentPane().add(lblpriceCofficient);
		
		JLabel lblProbability = new JLabel("Probability");
		lblProbability.setHorizontalAlignment(SwingConstants.CENTER);
		lblProbability.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProbability.setBounds(550, 370, 86, 20);
		frame.getContentPane().add(lblProbability);
		
		JLabel lblfractionOfTotal = new JLabel("(Fraction of Total Population)");
		lblfractionOfTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblfractionOfTotal.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblfractionOfTotal.setBounds(510, 390, 180, 20);
		frame.getContentPane().add(lblfractionOfTotal);
		
		JLabel lblConnectedStudents = new JLabel("Connected Students");
		lblConnectedStudents.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConnectedStudents.setBounds(150, 46, 130, 15);
		frame.getContentPane().add(lblConnectedStudents);

		frame.setVisible(true);
	

		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(checkUtilityFunctions()){
				
				setUtilityFunctions();
				
				Engine.sendCustomersToShops();

				Engine.readyCheck = true;
				Teacher.readyCheck = true;

				day++;
				dayLabel.setText(""+day);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Engine.readyCheck = false;
				Teacher.readyCheck = false;
			
				Engine.clearPlaylist();
			}else{
				JOptionPane.showMessageDialog(null,"Please fill the utility function variables properly!","Error",JOptionPane.WARNING_MESSAGE);
			}
				
			}
		});

	
	
	}
	public boolean checkUtilityFunctions() {
		
		boolean utiltyFunctionsFilled = true;
		
		if(betaOne.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(betaTwo.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(betaThree.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(alphaOne.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(alphaTwo.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(alphaThree.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(probabilityOne.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(probabilityTwo.getText().isEmpty()) utiltyFunctionsFilled = false;
		if(probabilityThree.getText().isEmpty()) utiltyFunctionsFilled = false;
		
		if(!probabilityOne.getText().isEmpty() || !probabilityTwo.getText().isEmpty() || !probabilityThree.getText().isEmpty() ) {
			double p1 = Double.parseDouble(probabilityOne.getText());
			double p2 = Double.parseDouble(probabilityTwo.getText());
			double p3 = Double.parseDouble(probabilityThree.getText());
			double np1 = p1 / (p1 + p2 + p3);
			double np2 = p2 / (p1 + p2 + p3);
			double np3 = 1.0 - np1 - np2;
			double probability = np1 + np2 + np3;
			if(probability != 1.0) utiltyFunctionsFilled = false;
		}
		
		return utiltyFunctionsFilled;
	}
	
	public void setUtilityFunctions(){
		Model.alphaOne = Double.parseDouble(alphaOne.getText());
		Model.alphaTwo = Double.parseDouble(alphaTwo.getText());
		Model.alphaThree = Double.parseDouble(alphaThree.getText());
		Model.betaOne = Double.parseDouble(betaOne.getText());
		Model.betaTwo = Double.parseDouble(betaTwo.getText());
		Model.betaThree = Double.parseDouble(betaThree.getText());
		
		double p1 = Double.parseDouble(probabilityOne.getText());
		double p2 = Double.parseDouble(probabilityTwo.getText());
		double p3 = Double.parseDouble(probabilityThree.getText());
		
		double np1 = p1 / (p1 + p2 + p3);
		double np2 = p2 / (p1 + p2 + p3);
		double np3 = 1.0 - np1 - np2;
		
		Model.probabilityOne = np1;
		Model.probabilityTwo = np2;
		Model.probabilityThree = np3;
	}
}
