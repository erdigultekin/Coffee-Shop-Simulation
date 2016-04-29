
public final class InventoryPrices {
	private InventoryPrices() {
		
	}
	
	public static double coffeeUnit (double order) {
		if(order<2.5){
			return 22.5;
		}else{
			return 20;
		}
	}
	
	public static double cupUnit (double order) {
		if(order<25){
			return 0.125;
		}else{
			return 0.1;
		}
	}
	
	public static double sugarUnit (double order) {
		if(order<2.5){
			return 2.75;
		}else{
			return 2.5;
		}
	}
	
	public static double milkUnit (double order) {
		if(order<5){
			return 2.75;
		}else{
			return 2.5;
		}
	}
	
	public static double coffeeOrder (double order) {
		return order * coffeeUnit(order);
	}
	
	public static double milkOrder (double order) {
		return order * milkUnit(order);
	}
	
	public static double sugarOrder (double order) {
		return order * sugarUnit(order);
	}
	
	public static double cupOrder (double order) {
		return order * cupUnit(order);
	}
	

	}
