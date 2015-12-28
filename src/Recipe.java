import java.io.Serializable;

public class Recipe implements Serializable{
	
	private static final long serialVersionUID = 7526472295622776150L;
	
	int coffee;
	int milk;
	int sugar;
	int price;
	
	public Recipe(int coffee, int milk, int sugar, int price){
		this.coffee = coffee;
		this.milk = milk;
		this.sugar = sugar;
		this.price = price;
	}	
}
