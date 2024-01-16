
public enum MenuOptions {
	OPTİON1("1. Create new Reservation with Room Type"),
	OPTİON2("2. Display all Reservations"),
	OPTİON3("3. List the reservations for a specific city"),
	OPTİON4("4. Add extra services to a reservation"),
	OPTİON5("5. Calculate total cost for each service"),
	OPTİON6("6. Display the total cost of every customer"),
	OPTİON7("7. Add an Employee"),
	OPTİON8("8. Add a Bill"),
	OPTİON9("9. Get Monthly Balance"),
	OPTİON10("10. List all Services sorted based on cost"),
	OPTİON11("11. List all Reservations sorted based on hotel names"),
	OPTİON12("12. Exit");
	
	private String option;
	
	MenuOptions(String option) {
		this.setOption(option);
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

}
