
public abstract class Services implements Calculable{
	
	protected int CustomerID;
	
	
	public abstract String getServiceType(); 
	
	public abstract double calculateService(); 
	
	public void displayServiceInfo() {
		
		System.out.printf("Customer ID: %d, Service Type: %s, Cost: %.2f\n",
				getCustomerID(),getServiceType(),calculateService());
	
	}
		
	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	
	
}
