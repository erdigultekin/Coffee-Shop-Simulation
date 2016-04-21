
public class ModelTest {
	public static void main(String[] args){
		Model.betaOne=10;
		Model.alphaOne=5;
		
		
		
		Shop s = new Shop("ege");
		s.recipe = new Recipe(0, 0, 0, 8);
		
		System.out.println("0/15 = "+((double)2/3));
		
		System.out.println("Calculate Quality Result: "+Model.calculateQuality(s));
		System.out.println("Q1 Result: "+Model.calculateQ1(s));
		System.out.println("Q2 Result: "+Model.calculateQ2(s));
		System.out.println("Q3 Result: "+Model.calculateQ3(s));
	}
}
