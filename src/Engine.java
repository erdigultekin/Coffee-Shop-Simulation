

import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import ChartDirector.ChartViewer;


public class Engine {
	static volatile HashMap<String,Shop> shopMap = new HashMap<String,Shop>();
	static volatile HashMap<String,Shop> shopMapBefore = new HashMap<String,Shop>();
	static volatile HashMap<String,ArrayList<Double>> shopSales = new HashMap<String,ArrayList<Double>>();
	static volatile HashMap<String,ArrayList<Double>> shopBalances = new HashMap<String,ArrayList<Double>>();
	static volatile HashMap<String,ArrayList<Double>> shopU1s = new HashMap<String,ArrayList<Double>>();
	static volatile HashMap<String,ArrayList<Double>> shopU2s = new HashMap<String,ArrayList<Double>>();
	static volatile HashMap<String,ArrayList<Double>> shopU3s = new HashMap<String,ArrayList<Double>>();
	static volatile HashMap<String,ArrayList<Double>> shopPrices = new HashMap<String,ArrayList<Double>>();

	
	static JFrame frameBalance = new JFrame("Balances Chart ");
	static JFrame frameSales = new JFrame("Sales Chart ");
	static JFrame framePrices = new JFrame("Prices Chart ");
	static JFrame frameChart;
	static MultilineChartDrawer charterBalance = new MultilineChartDrawer(0);
	static MultilineChartDrawer charterSales = new MultilineChartDrawer(1);
	static MultilineChartDrawer charterPrices = new MultilineChartDrawer(2);
	static MultilineChartDrawer charter;
	
	public static boolean readyCheck = false;
	private static int customerPopulation = 0;

	public Engine() {

	}

	public static boolean checkDayConsistency(Shop shop){
		if(Teacher.day!=shop.day){
			return false;
		}
		return true;
	}

	public synchronized static void updatePlayerList (Shop shop) {

		Teacher.playerList.addElement(shop.name);
		Teacher.balances.addElement(shop.balance);
		Teacher.sales.addElement(shop.dailySales);
		Teacher.prices.addElement(shop.recipe.price);
		Teacher.sugarMix.addElement(shop.recipe.sugar);
		Teacher.milkMix.addElement(shop.recipe.milk);
		Teacher.coffeeMix.addElement(shop.recipe.coffee);
		Teacher.coffees.addElement(shop.inventory.coffee);
		Teacher.sugars.addElement(shop.inventory.sugar);
		Teacher.milks.addElement(shop.inventory.milk);
		Teacher.cups.addElement(shop.inventory.cups);
		shopMap.put(shop.name, shop);
	}

	public static void controlTeachersApproval() {
		//System.out.println("waiting for ready check from the teacher");
		while(!Engine.readyCheck||!Teacher.readyCheck){
			try {
				//System.out.println("waiting for ready check from the teacher");		
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static Shop getProperShop(Shop shop){
		return shopMap.get(shop.name);
	}

	public static void sendCustomersToShops(){
		if(Teacher.day==1){
			for(Shop shop : shopMap.values()){
				ArrayList<Double> sales = new ArrayList<Double>();
				sales.add((double)shop.dailySales);
				ArrayList<Double> balances = new ArrayList<Double>();
				balances.add(shop.balance);
				ArrayList<Double> prices = new ArrayList<Double>();
				prices.add(0.0);
				ArrayList<Double> u1s = new ArrayList<Double>();
				u1s.add(0.0);
				ArrayList<Double> u2s = new ArrayList<Double>();
				u2s.add(0.0);
				ArrayList<Double> u3s = new ArrayList<Double>();
				u3s.add(0.0);

				shopSales.put(shop.name, sales);
				shopBalances.put(shop.name, balances);
				shopPrices.put(shop.name, prices);
				shopU1s.put(shop.name, u1s);
				shopU2s.put(shop.name, u2s);
				shopU3s.put(shop.name, u3s);
			}
		}
		shopMapBefore = shopMap;
		//Set the customer population size first
		customerPopulation = shopMap.size()*30;
		System.out.println("Customer population : "+customerPopulation);
		//Create the customers with types.
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for(int i=0;i<customerPopulation;i++){
			double r = Math.random();

			if(0<=r&&r<Model.probabilityOne){
				System.out.println("Customer " + i + " hestype: 1");
				customers.add(new Customer(1));
			}else if(Model.probabilityOne<=r&&r<(Model.probabilityOne+Model.probabilityTwo)){
				System.out.println("Customer " + i + " hestype: 2");
				customers.add(new Customer(2));
			}else{
				System.out.println("Customer " + i + " hestype: 3");
				customers.add(new Customer(3));
			}
		}

		//Create the Line Segment Representations
		LineSegmentUtilityRepresentation lsur1 = new LineSegmentUtilityRepresentation();
		LineSegmentUtilityRepresentation lsur2 = new LineSegmentUtilityRepresentation();
		LineSegmentUtilityRepresentation lsur3 = new LineSegmentUtilityRepresentation();
		for(Shop shop : shopMap.values()){
			lsur1.addUtilitySegment(shop.name, Model.calculateU1(shop));
			lsur2.addUtilitySegment(shop.name, Model.calculateU2(shop));
			lsur3.addUtilitySegment(shop.name, Model.calculateU3(shop));
		}

		for(Customer c : customers){
			if(c.type==1){
				sendOneCustomerToAShop(c,lsur1);
			}else if(c.type==2){
				sendOneCustomerToAShop(c,lsur2);				
			}else{
				sendOneCustomerToAShop(c,lsur3);
			}
		}

		for(Shop shop: shopMap.values()){
			ArrayList<Double> sales = shopSales.get(shop.name);
			sales.add((double)shop.dailySales);
			ArrayList<Double> balances = shopBalances.get(shop.name);
			balances.add(shop.balance);
			ArrayList<Double> prices = shopPrices.get(shop.name);
			prices.add(shop.recipe.price);
			ArrayList<Double> u1s = shopU1s.get(shop.name);
			u1s.add(Model.calculateU1(shop));
			ArrayList<Double> u2s = shopU2s.get(shop.name);
			u2s.add(Model.calculateU2(shop));
			ArrayList<Double> u3s = shopU3s.get(shop.name);
			u3s.add(Model.calculateU3(shop));

			shopSales.put(shop.name, sales);
			shopBalances.put(shop.name, balances);
			shopPrices.put(shop.name, prices);
			shopU1s.put(shop.name, u1s);
			shopU2s.put(shop.name, u2s);
			shopU3s.put(shop.name, u3s);
		}
		System.out.println("Printing Test results now...");
		//Method for writing test results to excel
		XLSXReaderWriter.printTestResults("TestResult-Day"+Teacher.day+".xlsx");
		System.out.println("Printed Test results. Drawing Charts now...");
		//Method for creating charts
		drawCharts();
		System.out.println("Drawed Charts now...");

	}

	public static void drawCharts(){
		
		for(int i=0;i<3;i++){
			//Instantiate an instance of this demo module
			charter = new MultilineChartDrawer(i);
			System.out.println("Charter initialized..");
			//Create and set up the main window
			
			if(i==0){
				frameChart=frameBalance;
				charter = charterBalance;
			}else if(i==1){
				frameChart=frameSales;
				charter=charterSales;
			}else{
				frameChart=framePrices;
				charter=charterPrices;
			}
			
			for(WindowListener wl : frameChart.getWindowListeners()){
				frameChart.removeWindowListener(wl);
				frameChart.remove(frameChart.getContentPane());
				JPanel panel = new JPanel();
				frameChart.setContentPane(panel);
			}
			
			for(ComponentListener cl : frameChart.getComponentListeners()){
				frameChart.removeComponentListener(cl);
			}
			
			frameChart.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {frameChart.dispose();} });
			frameChart.getContentPane().setBackground(Color.white);
			System.out.println("Frame created.");
			// Create the chart and put them in the content pane
			ChartViewer viewer = new ChartViewer();
			System.out.println("Viewer created.");
			charter.createChart(viewer,800,600);
			System.out.println("Chart Created.");
			frameChart.getContentPane().add(viewer);
			
			
			//frameChart.setDefaultCloseOperation(frameChart.EXIT_ON_CLOSE);

			// Display the window
			
			frameChart.pack();
			frameChart.setVisible(true);
			System.out.println("Frame is visible now");
			// Resize handler
	        frameChart.addComponentListener(new java.awt.event.ComponentAdapter() {
	            public void componentResized(java.awt.event.ComponentEvent evt) {
	                JFrame tmp = (JFrame)evt.getSource();
	                if (tmp.getTitle().equals("Balances Chart ")) charter=charterBalance;
	                else if (tmp.getTitle().equals("Sales Chart ")) charter=charterSales;
	                else charter = charterPrices;
	                
	                if (tmp.getWidth() < 250 || tmp.getHeight() < 250){
	                	charter.createChart(viewer, 250, 250);
	                    tmp.setSize(250, 250);
	                }else charter.createChart(viewer, tmp.getWidth(), tmp.getHeight());
	            }
	        });
	        
		}
	}

	public static void sendOneCustomerToAShop(Customer c, LineSegmentUtilityRepresentation lsur){
		//System.out.println("#####sendOneCustomerToAShop#####");
		//System.out.println("Customer has type : "+c.type);
		//System.out.println("Customer's line segment utility representation ends at : "+lsur.lastUtilityPoint);
		/*
		for(LineSegment ls : lsur.shopSegmentMap.keySet()){
			System.out.println("Line Segment start: "+ls.start+" end: "+ls.end+" stands for shop: "+lsur.shopSegmentMap.get(ls));
		}
		 */
		while(c.blockedUtilitySize<lsur.lastUtilityPoint&&c.retryCount>0){
			double randomPoint= Math.random()*lsur.lastUtilityPoint;
			System.out.println("Random point is :"+randomPoint);
			boolean nextStep = true;
			for(LineSegment ls : c.blockedLineSegments){
				if(ls.isPointOnSegment(randomPoint)){
					nextStep = false;
				}
			}
			if(nextStep){
				String correspondingShopName = lsur.findCorrespondingShopforUtility(randomPoint);
				//System.out.println("Shop chosen for sale: "+correspondingShopName);
				if(checkAvailabilityForOneSale(shopMap.get(correspondingShopName))){
					makeOneSale(shopMap.get(correspondingShopName));
					break;
				}else{
					LineSegment correspondingLineSegment = lsur.findCorrespondingLineSegmentforUtility(randomPoint);
					c.blockedUtilitySize += (correspondingLineSegment.end-correspondingLineSegment.start);
					c.blockedLineSegments.add(correspondingLineSegment);
					c.retryCount--;
				}
			}
		}
	}

	public static void makeOneSale(Shop shop){
		shop.balance+=shop.recipe.price;
		shop.dailySales++;

		shop.inventory.cups--;
		shop.inventory.setCoffee(shop.inventory.getCoffee()-shop.recipe.coffee);
		shop.inventory.setMilk((double)(shop.inventory.getMilk()-(double)shop.recipe.milk));
		shop.inventory.setSugar(shop.inventory.getSugar()-shop.recipe.sugar);

		shopMap.put(shop.name, shop);
	}

	public static boolean checkAvailabilityForOneSale(Shop shop){
		return (checkCupsInStock(shop)&checkCoffeeInStock(shop)&
				checkMilkInStock(shop)&checkSugarInStock(shop));
	}

	public static boolean checkCupsInStock(Shop shop){
		return (shop.inventory.cups>0);
	}

	public static boolean checkCoffeeInStock(Shop shop){
		return (shop.inventory.getCoffee()>=shop.recipe.coffee);
	}

	public static boolean checkMilkInStock(Shop shop){
		return (shop.inventory.getMilk()>=shop.recipe.milk);
	}

	public static boolean checkSugarInStock(Shop shop){
		return (shop.inventory.getSugar()>=shop.recipe.sugar);
	}

	public static void clearPlaylist(){
		Teacher.playerList.removeAllElements();
		Teacher.balances.removeAllElements();
		Teacher.sales.removeAllElements();
		Teacher.prices.removeAllElements();
		Teacher.milkMix.removeAllElements();
		Teacher.sugarMix.removeAllElements();
		Teacher.coffeeMix.removeAllElements();
		Teacher.coffees.removeAllElements();
		Teacher.sugars.removeAllElements();
		Teacher.milks.removeAllElements();
		Teacher.cups.removeAllElements();
		shopMap.clear();
	}

	public static void printShopDetails(Shop shop){
		if(shop!=null){
			System.out.println("Shop name: "+shop.name);
			System.out.println("Daily Sales: "+shop.dailySales);
			if(shop.inventory!=null){
				System.out.println("Coffee(kg) in inventory: "+shop.inventory.coffee);
				System.out.println("Milk(lt) in inventory: "+shop.inventory.milk);
				System.out.println("Sugar(kg) in inventory: "+shop.inventory.sugar);
			}
		}
	}
}
