

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JList;


public class Engine {
	static final int playableDays = 14;
	static volatile HashMap<String,Shop> shopMap = new HashMap<String,Shop>();
	public static boolean readyCheck = false;
	private static int customerPopulation = 0;

	public Engine() {

	}

	public static boolean checkDayConsistency(Shop shop){
		if(Teacher.day!=shop.day||Teacher.day>playableDays){
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
		//Set the customer population size first
		customerPopulation = shopMap.size()*20;

		//Create the customers with types.
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for(int i=0;i<customerPopulation;i++){
			double r = Math.random();
			if(0<=r&&r<Model.probabilityOne){
				customers.add(new Customer(1));
			}else if(Model.probabilityOne<=r&&r<(Model.probabilityOne+Model.probabilityTwo)){
				customers.add(new Customer(2));
			}else{
				customers.add(new Customer(3));
			}
		}

		//Create the Line Segment Representations
		ArrayList<Shop> shops = new ArrayList<Shop>();
		LineSegmentUtilityRepresentation lsur1 = new LineSegmentUtilityRepresentation();
		LineSegmentUtilityRepresentation lsur2 = new LineSegmentUtilityRepresentation();
		LineSegmentUtilityRepresentation lsur3 = new LineSegmentUtilityRepresentation();
		for(Shop shop : shopMap.values()){
			shops.add(shop);
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
		/*
		//Then create a sorted ArrayList for iterating through shops with price in ascending order.
		ArrayList<Shop> shops = new ArrayList<Shop>();
		for(int i=1;i<=15;i++){
			for(Shop shop: shopMap.values()){
				if(shop.recipe.price==i){
					shop.dailySales = 0;
					shops.add(shop);
					System.out.println(shop.name+"'s shop added to shops.");
				}
			}
		}

		//Then send one customer to each shop if it has enough resources
		int index = 0;
		for(Shop shop:shops){
			if(checkAvailabilityForOneSale(shop)){
				shops.set(index,makeOneSale(shop));
				customerPopulation--;
			}
		}

		//Then send the rest of customers to shops according to price.
		int indexCovered = 0;
		index = 0;
		while(customerPopulation>0&&index<shops.size()){
			Shop shop = shops.get(index);
			if(checkAvailabilityForOneSale(shop)){
				if(isCustomerWillingToBuy()){
					shops.set(index,makeOneSale(shop));
					customerPopulation--;
					index = indexCovered;
				}else{
					if(index<(shops.size()-1)){
						index++;
					}else{
						index = indexCovered;
						customerPopulation--;
					}
				}

			}else{
				index++;
				indexCovered = index;
			}
		}
		 */
		//Then update the hashmap with new shops;
		for(Shop shop: shops){
			shopMap.put(shop.name, shop);
		}
	}

	public static void sendOneCustomerToAShop(Customer c, LineSegmentUtilityRepresentation lsur){
		while(c.blockedUtilitySize<lsur.lastUtilityPoint&&c.retryCount>0){
			double randomPoint= Math.random()*lsur.lastUtilityPoint;
			boolean nextStep = true;
			for(LineSegment ls : c.blockedLineSegments){
				if(ls.isPointOnSegment(randomPoint)){
					nextStep = false;
				}
			}
			if(nextStep){
				String correspondingShopName = lsur.findCorrespondingShopforUtility(randomPoint);
				if(checkAvailabilityForOneSale(shopMap.get(correspondingShopName))){
					makeOneSale(shopMap.get(correspondingShopName));
					break;
				}else{
					LineSegment correspondingLineSegment = lsur.findCorrespondingLineSegmentforUtility(randomPoint);
					c.blockedUtilitySize += correspondingLineSegment.end-correspondingLineSegment.start;
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
		shop.inventory.setMilk(shop.inventory.getMilk()-shop.recipe.milk);
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
		return (shop.inventory.getCoffee()>shop.recipe.coffee);
	}

	public static boolean checkMilkInStock(Shop shop){
		return (shop.inventory.getMilk()>shop.recipe.milk);
	}

	public static boolean checkSugarInStock(Shop shop){
		return (shop.inventory.getSugar()>shop.recipe.sugar);
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
