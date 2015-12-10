

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
		for(Shop shop : Engine.shopMap.values()){
			//Currently making 3 sales for each shop without updating their inventory.
			shop.balance+=3*shop.recipe.price;
			shop.dailySales= 3;
			shopMap.put(shop.name, shop);
		}
	}
	
	public static void clearPlaylist(){
		Teacher.playerList.removeAllElements();
		shopMap.clear();
	}
}
