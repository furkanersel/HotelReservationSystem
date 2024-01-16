import java.util.InputMismatchException;
import java.util.Scanner;
public class Bills implements Calculable{
	
	private String type;
	private double amount;
	private String month;
	
	Scanner input = new Scanner(System.in);
	
	public Bills() {
		System.out.println();
		
		System.out.println("Type:");
		String type = input.nextLine();
		setType(type);
		try {
			System.out.println("Amount:");
			double amount = input.nextDouble();
			setAmount(amount);
		} catch (InputMismatchException e) {
			System.err.println("Bill Amount must be a numeric value!");
			input.nextLine();
			System.out.println("Amount:");
			double amount = input.nextDouble();
			setAmount(amount);
		}
		input.nextLine();
		System.out.println("Month:");
		String month = input.nextLine();
		setMonth(month);
		
		System.out.println();
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = ( amount < 0 ) ? 0 : amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		if(Reservation.CheckMonth(month)) {
			this.month = month;
			}
		else {
			String newReservationMonth = month;
			while(!(Reservation.CheckMonth(newReservationMonth))) {
				System.out.print("Wrong input try again(You must be begin with capital letter): ");
				newReservationMonth = input.nextLine();
				this.month = newReservationMonth;
			}
		}
	}

	public double getCost() {
		
		return getAmount();
	}
	
}
