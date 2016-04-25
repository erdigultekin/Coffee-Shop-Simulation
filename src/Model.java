public class Model {
	
	/*
	 * Ingredients max values.
	 * Note: Please don't make any change here. Client program won't recognize these changes.
	 *  So the simulation will crash.
	 */
	static double coffeeMax = 15.0;
	static double milkMax = 200.0;
	static double sugarMax = 10.0;
	
	/*
	 * Quality calculation represented here with 4 parts:
	 * 		Q1 calculation,Q2 calculation,Q3 calculation,Qtotal calculation
	 * All formulations are made with normalized values. You can make changes here. 
	 */
	
	public static double calculateQuality(Shop s){
		return (calculateQ1(s) * (1+calculateQ2(s))*(1+calculateQ3(s)));
	}

	public static double calculateQ3(Shop s) {
		double ingredientRatio = s.recipe.sugar/sugarMax;
		
		if(ingredientRatio<((double)2/3)){
			System.out.println("Less");
			return (1.5)*ingredientRatio;
		}else{
			System.out.println("More");
			return (1-ingredientRatio)*3;
		}
	}

	public static double calculateQ2(Shop s) {
		double ingredientRatio = s.recipe.milk/milkMax;
		if(ingredientRatio<((double)2/3)){
			return (1.5)*ingredientRatio;
		}else{
			return (1-ingredientRatio)*3;
		}
	}

	public static double calculateQ1(Shop s) {
		double ingredientRatio = s.recipe.coffee/coffeeMax;
		if(ingredientRatio<((double)2/3)){
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
		double utility = (betaOne*calculateQuality(s)-alphaOne*s.recipe.price);
		System.out.println("Shop: "+s.name+" Beta1:" + betaOne+ " Qualitiy 1: " + calculateQuality(s) + " Alpha1: " + alphaOne+ " Price: "+ s.recipe.price);
		if(utility>0) {
			System.out.println("Utility 1: " + utility);
			return utility;
			}
		else {
			System.out.println("Utility 1: 0");
			return 0;
			}
	}
	
	static double calculateU2(Shop s){
		double utility = (betaTwo*calculateQuality(s)-alphaTwo*s.recipe.price);
		System.out.println("Shop: "+s.name+" Beta2:" + betaTwo+ " Qualitiy 2: " + calculateQuality(s) + " Alpha2: " + alphaTwo+ " Price: "+ s.recipe.price);
		if(utility>0) {
			System.out.println("Utility 2: " + utility);
			return utility;
			}
		else {
			System.out.println("Utility 2: 0");
			return 0;
			}
	}
	
	static double calculateU3(Shop s){
		double utility = (betaThree*calculateQuality(s)-alphaThree*s.recipe.price);
		System.out.println("Shop: "+s.name+" Beta3:" + betaThree+ " Qualitiy 3: " + calculateQuality(s) + " Alpha3: " + alphaThree+ " Price: "+ s.recipe.price);
		if(utility>0) {
			System.out.println("Utility 3: " + utility);
			return utility;
			}
		else {
			System.out.println("Utility 3: 0");
			return 0;
			}
	}
}
