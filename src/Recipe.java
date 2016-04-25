import java.io.Serializable;

public class Recipe implements Serializable{
	
	private static final long serialVersionUID = 7526472295622776150L;
	
	int coffee;
	int milk;
	int sugar;
	double price;
	
	public Recipe(int coffee, int milk, int sugar, double price){
		this.coffee = coffee;
		this.milk = milk;
		this.sugar = sugar;
		this.price = price;
	}

	public Recipe(double coffee2, double milk2, double sugar2, double price2) {
		this.coffee = (int)coffee2;
		this.milk = (int)milk2;
		this.sugar = (int)sugar2;
		this.price = price2;
	}	
}
