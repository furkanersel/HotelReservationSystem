
public class Spa extends Services {
	
	private int days;
	public double spaCost = 100;

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public String getServiceType() {
		return "Spa";
	}

	@Override
	public double calculateService() {
		return getDays() * spaCost;
	}

	public double getCost() {
		return calculateService();
	}



};
