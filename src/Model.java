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
		return (calculateQ1(s) * (1+calculateQ2(s))*(1+calculateQ3(s)));
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
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Parameters for utility functions are updated here after "Run Simulation" Button clicked.
	 * Note: Probability will only be used while creating a customer type. 
	 *       For example type1 customer will use U1(utility fuction 1) to decide on which shop to buy the coffee from.
	 */
	
	static double betaOne = 0.0;
	static double betaTwo = 0.0;
	static double betaThree = 0.0;
	static double alphaOne = 0.0;
	static double alphaTwo = 0.0;
	static double alphaThree = 0.0;
	static double probabilityOne = 0.0;
	static double probabilityTwo = 0.0;
	static double probabilityThree = 0.0;
	
	/*
	 * Utility functions are represented here:
	 * 		U1,U2,U3
	 * 
	 */
	
	static double calculateU1(Shop s){
		return (betaOne*calculateQuality(s)-alphaOne*s.recipe.price);
	}
	
	static double calculateU2(Shop s){
		return (betaTwo*calculateQuality(s)-alphaTwo*s.recipe.price);
	}
	
	static double calculateU3(Shop s){
		return (betaThree*calculateQuality(s)-alphaThree*s.recipe.price);
	}
}
