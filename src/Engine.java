

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
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
		shopMap.put(shop.name, shop);
	}

	public static void controlTeachersApproval() {
		System.out.println("waiting for ready check from the teacher");
		while(!Engine.readyCheck||!Teacher.readyCheck){
			try {
				System.out.println("waiting for ready check from the teacher");		
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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

		//Then create a sorted ArrayList for iterating through shops with price in ascending order.
		ArrayList<Shop> shops = new ArrayList<Shop>();
		for(int i=1;i<=10;i++){
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
		index = 0;
		while(customerPopulation>0&&index<shops.size()){
			Shop shop = shops.get(index);
			if(checkAvailabilityForOneSale(shop)){
				shops.set(index,makeOneSale(shop));
				customerPopulation--;
			}else{
				index++;
			}
		}

		//Then update the hashmap with new shops;
		for(Shop shop: shops){
			shopMap.put(shop.name, shop);
		}
		/*
		for(Shop shop : Engine.shopMap.values()){
			//Currently making 3 sales for each shop without updating their inventory.
			shop.balance+=3*shop.recipe.price;
			shop.dailySales= 3;
			shopMap.put(shop.name, shop);

		}
		 */
	}

	public static Shop makeOneSale(Shop shop){
		shop.balance+=shop.recipe.price;
		shop.dailySales++;

		shop.inventory.cups--;
		shop.inventory.setCoffee(shop.inventory.getCoffee()-shop.recipe.coffee);
		shop.inventory.setMilk(shop.inventory.getMilk()-shop.recipe.milk);
		shop.inventory.setSugar(shop.inventory.getSugar()-shop.recipe.sugar);

		return shop;
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
