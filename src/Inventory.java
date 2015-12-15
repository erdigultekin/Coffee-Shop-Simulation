
import java.io.Serializable;

public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 7526472295622776148L;
	
	public int cups;
	public double coffee;
	public double milk;
	public double sugar;
	
	public Inventory(int cups, double coffee, double milk, double sugar){
		this.cups = cups;
		this.coffee = coffee;
		this.milk = milk;
		this.sugar = sugar;
	}

	public double getCoffee() {
		double cof = this.coffee * 1000;
		return cof;
	}

	public void setCoffee(double cof) {
		this.coffee = cof / 1000;
	}

	public double getMilk() {
		double mlk = milk*1000;
		return mlk;
	}

	public void setMilk(double mlk) {
		this.milk = mlk/1000;
	}

	public double getSugar() {
		double sgr = sugar * 1000;
		return sgr;
	}

	public void setSugar(double sgr) {
		this.sugar = sgr/1000;
	}

	
}
