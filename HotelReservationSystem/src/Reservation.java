
import java.util.Scanner;

import javax.swing.JOptionPane;
public class Reservation extends Services implements Comparable<Reservation>{
	
	public Room room; 
	protected String hotelName;
	private String cityName;
	private String reservationMonth;
	private int reservationStart;
	private int reservationEnd ;
	private int dailyCost;
	Scanner input = new Scanner(System.in);
	static int totalNumOfReservations = 0;
	
	public Reservation(){
		
		
		String cityName = JOptionPane.showInputDialog("Enter City: ");
		setCityName(cityName);
		
		
		String hotelName = JOptionPane.showInputDialog("Enter Hotel Name: ");
		setHotelName(hotelName);
		
	/*  
	  	String roomType = null;
		String rooms[] = {"Single","Double","Club","Family","Family with View","Suite"};
		List<String> roomList = new ArrayList<String>();
		try {
			for (String room : rooms) {
				roomList.add(room);
			}
			System.out.print("Room Type: ");
			roomType = input.nextLine();
			if(!(roomList.contains(roomType))) {
				throw new RoomTypeException("\nRoom Type is not valid!\n");
			}
		} catch (RoomTypeException e) {
			while(!(roomList.contains(roomType))) {
				System.err.println(e.getMessage());
				System.out.print("Room Type: ");
				roomType = input.nextLine();
			}
		}
		if(roomType.equals("Single")) {
			Single room1 = new Single();
			this.room = room1;
		}
		else if(roomType.equals("Double")) {
			Double room2 = new Double();
			this.room = room2;
		}
		else if(roomType.equals("Club")) {
			Club room3 = new Club();
			this.room = room3;
		}
		else if(roomType.equals("Family")) {
			Family room4 = new Family();
			this.room = room4;
		}
		else if(roomType.equals("Family with View")) {
			Family_with_View room5 = new Family_with_View();
			this.room = room5;
		}
		else if(roomType.equals("Suite")) {
			Suite room6 = new Suite();
			this.room = room6;
		}
	*/
		
		
		String ReservationMonth = JOptionPane.showInputDialog("Reservation Month: ");
		setReservationMonth(ReservationMonth);
		
	    try {
	    	int ReservationStart = Integer.parseInt(JOptionPane.showInputDialog("Reservation Start: "));
			setReservationStart(ReservationStart);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Reservation Start must be a numeric value!", "ERROR", JOptionPane.ERROR_MESSAGE);
			int ReservationStart = Integer.parseInt(JOptionPane.showInputDialog("Reservation Start: "));
			setReservationStart(ReservationStart);
		}
		
	    try {
	    	int ReservationEnd = Integer.parseInt(JOptionPane.showInputDialog("Reservation End: "));
			setReservationEnd(ReservationEnd);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Reservation End must be a numeric value!", "ERROR", JOptionPane.ERROR_MESSAGE);
			int ReservationEnd = Integer.parseInt(JOptionPane.showInputDialog("Reservation End: "));
			setReservationEnd(ReservationEnd);
		}
		
	}
	
	
	public Reservation(String cityName,String hotelName,String reservationMonth, int reservationStart,
			int reservationEnd) {
		
		this.cityName = cityName;
		this.hotelName = hotelName;
		this.reservationMonth = reservationMonth;
		this.reservationStart = reservationStart;
		this.reservationEnd = reservationEnd;
		
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
		
	}
	public String getCityName() {
		return cityName;
		
	}
	public int calculateTotalPrice(String month){
		String[] summerMonth = {"June", "July", "August"};
		for(int i=0; i<3; i++) {
			if(summerMonth[i].equals(month)) {
				return 2 * (room.getDailyCost() * (getReservationEnd()-getReservationStart()));
				}
		}
		return (room.getDailyCost() * (getReservationEnd()-getReservationStart()));
	}
	
	public int calculateTotalPrice() {
		return getDailyCost() * (getReservationEnd()-getReservationStart());
	
	}

/*	public String displayInfo() {
		
		return "Reservation ID: " + GetCountReservations() + "\n"+ "Reservation at " + getHotelName() +
				" starts on " +  getReservationMonth() + " " + getReservationStart() + " and ends on " + getReservationMonth() + " " + getReservationEnd(); 
				
	}
*/		
	
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	@Override
	public String toString() {
		return "Reservation at " + getHotelName() +
				" starts on " +  getReservationMonth() + " " + getReservationStart() + " and ends on " + getReservationMonth() + " " + getReservationEnd();
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setReservationMonth(String reservationMonth) {
		if(CheckMonth(reservationMonth)) {
			this.reservationMonth = reservationMonth;
			}
		else {
			
			String newReservationMonth = reservationMonth;
			while(!(CheckMonth(newReservationMonth))) {
				JOptionPane.showMessageDialog(null, "Wrong input try again(You must be begin with capital letter):", "ERROR", JOptionPane.ERROR_MESSAGE);
				newReservationMonth = JOptionPane.showInputDialog("Reservation Month: ");
				this.reservationMonth = newReservationMonth;
			}
		}
	}
	public String getReservationMonth() {
		return reservationMonth;
	}
	public void setReservationStart(int reservationStart) {
		if(reservationStart<31 && reservationStart>0) {
			this.reservationStart = reservationStart;
		}
		else {
			int newReservationStart = reservationStart;
			while(!(newReservationStart<31 && newReservationStart>0)) {
				JOptionPane.showMessageDialog(null, "Reservation Start must be smaller than '30'"
						+ " and grather than '0'!", "ERROR", JOptionPane.ERROR_MESSAGE);
				newReservationStart = Integer.parseInt(JOptionPane.showInputDialog("Reservation Start: "));
				this.reservationStart = newReservationStart;
		}
		}
	}
	public int getReservationStart() {
		return reservationStart;
	}
	public void setReservationEnd(int reservationEnd) {
		if(reservationEnd<31 && reservationEnd>reservationStart) {
			this.reservationEnd = reservationEnd;
		}
		else {
			int newReservationEnd = reservationEnd;
			while(!(newReservationEnd<31 && newReservationEnd>reservationStart)) {
				JOptionPane.showMessageDialog(null, "Reservation End must be smaller than Reservation Start"
						+ " and also it can not be grather than '30'!", "ERROR", JOptionPane.ERROR_MESSAGE);
				newReservationEnd = Integer.parseInt(JOptionPane.showInputDialog("Reservation End: "));
				this.reservationEnd = newReservationEnd;
		}
		}
	}
	public int getReservationEnd() {
		return reservationEnd;
	}
	public void setDailyCost(int dailyCost) {
		this.dailyCost = dailyCost;
	}
	public int getDailyCost() {
		return dailyCost;
	}
	public static int GetCountReservations() {
		 return totalNumOfReservations;
	}
	public static boolean CheckMonth(String input) {
		String[] Month = {"January","February","March","April","May","June","July","August","September","November","December"};
		for (String month : Month) {
			if(input.equals(month)) 
				return true;
		}
		return false;
	}
	
	@Override
	public void displayServiceInfo() {
		System.out.printf("Hotel Name: %s, Customer ID: %d, Service Type: %s, Cost: %.2f\n",
				getHotelName(), getCustomerID(), getServiceType(), calculateService());
	}

	@Override
	public String getServiceType() {
		
		return "Room booking";
	}

	@Override
	public double calculateService() {
		return calculateTotalPrice(getReservationMonth());
	}
	
	
	public int compareTo(Reservation o) {
		return this.getHotelName().compareTo(o.getHotelName());
	}
	
	public double getCost() {
		
		return calculateService();
	}
	
	
}
