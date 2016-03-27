
public class Customer {
	//customer has a type 1,2 or 3.
	int type;
	//retryCount represents number of trials that a customer tries to find a shop
	int retryCount;
	
	public Customer(int type){
		this.type=type;
		this.retryCount=3;
	}
	
	
}
