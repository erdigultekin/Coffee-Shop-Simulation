import java.io.Serializable;

public class Shop implements Serializable{

	private static final long serialVersionUID = 7526472295622776148L;

	public Double balance;
	public int dailySales;
	public String name;
	public int day;
	public Inventory inventory;
	public Recipe recipe;

	public Shop(String name){
		balance = 100.0;
		day = 1;
		dailySales = 0;
		this.name = name;
		this.inventory = new Inventory(0,0,0,0);
	}
	
	public boolean hasHigherPriceThan(Shop shop){
		if(this.recipe.price>shop.recipe.price){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasLowerPriceThan(Shop shop){
		if(this.recipe.price>shop.recipe.price){
			return true;
		}else{
			return false;
		}
	}
}