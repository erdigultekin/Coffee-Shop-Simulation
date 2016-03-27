import java.util.ArrayList;

public class Customer {
	//customer has a type 1,2 or 3.
	int type;
	//retryCount represents number of trials that a customer tries to find a shop
	int retryCount;
	//blockedUtilitySize represent the total utilities of shops that the customer decided to buy from.
	double blockedUtilitySize;
	//blockedLineSegments represent the line segments that the customer deny to use.
	ArrayList<LineSegment> blockedLineSegments;
	
	public Customer(int type){
		this.type=type;
		this.retryCount=3;
		this.blockedUtilitySize=0;
		this.blockedLineSegments = new ArrayList<LineSegment>();
	}
	
	
}
