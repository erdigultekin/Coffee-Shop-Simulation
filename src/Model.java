
public class Model {
	
	/*
	 * Ingredients max values.
	 * Note: Please don't make any change here. Client program won't recognize these changes.
	 *  So the simulation will crash.
	 */
	static double coffeeMax = 15.0;
	static double milkMax = 200.0;
	static double sugarMax = 15.0;
	
	/*
	 * Quality calculation represented here with 4 parts:
	 * 		Q1 calculation,Q2 calculation,Q3 calculation,Qtotal calculation
	 * All formulations are made with normalized values. You can make changes here. 
	 */
	
	public static double calculateQuality(Shop s){
		double Qtotal = 1.0;
		
		Qtotal = calculateQ1(s) * (1+calculateQ2(s))*(1+calculateQ3(s));
		
		return Qtotal;
	}

	private static double calculateQ3(Shop s) {
		double ingredientRatio = s.recipe.sugar/sugarMax;
		if(ingredientRatio<0.67){
			return (1.5)*ingredientRatio;
		}else{
			return (1-ingredientRatio)*3;
		}
	}

	private static double calculateQ2(Shop s) {
		double ingredientRatio = s.recipe.milk/milkMax;
		if(ingredientRatio<0.67){
			return (1.5)*ingredientRatio;
		}else{
			return (1-ingredientRatio)*3;
		}
	}

	private static double calculateQ1(Shop s) {
		double ingredientRatio = s.recipe.coffee/coffeeMax;
		if(ingredientRatio<0.67){
			return (1.5)*ingredientRatio;
		}else{
			return (1-ingredientRatio)*(1.5)+0.5;
		}
	}
}
