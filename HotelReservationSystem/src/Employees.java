import java.util.InputMismatchException;
import java.util.Scanner;
public class Employees implements Calculable{

	private String name;
	private String surname;
	private double monthlyPayment;
	private int id;
	
	Scanner input = new Scanner(System.in);
	
	public Employees() {
		System.out.println();
		
		System.out.println("Name:");
		String name = input.nextLine();
		setName(name);
		
		System.out.println("Surname:");
		String surname = input.nextLine();
		setSurname(surname);
		
		System.out.println("ID:");
		int id = input.nextInt();
		setId(id);
		try {
			System.out.println("Monthly Payment:");
			double monthlyPayment = input.nextDouble();
			setMonthlyPayment(monthlyPayment);
		} catch (InputMismatchException e) {
			System.err.println("Monthly Payment must be a numeric value!");
			input.nextLine();
			System.out.println("Monthly Payment:");
			double monthlyPayment = input.nextDouble();
			setMonthlyPayment(monthlyPayment);
		}
		System.out.println();
	}


    public String getName() {
		return name;
	}



    public void setName(String name) {
		this.name = name;
	}



    public String getSurname() {
		return surname;
	}



    public void setSurname(String surname) {
		this.surname = surname;
	}



    public double getMonthlyPayment() {
		return monthlyPayment;
	}





	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = ( monthlyPayment < 0 ) ? 0 : monthlyPayment;
	}



    public int getId() {
		return id;
	}



    public void setId(int id) {
		this.id = id;
	}


    public double getCost() {
		
		return getMonthlyPayment();
	
    }

	
}
