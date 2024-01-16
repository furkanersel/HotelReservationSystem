
public class Laundry extends Services{
	
	private int clothingPieces;
	public double laundryCost = 20;
	
	public int getClothingPieces() {
		return clothingPieces;
	}

	public void setClothingPieces(int clothingPieces) {
		this.clothingPieces = clothingPieces;
	}

	@Override
	public String getServiceType() {
		
		return "Laundry";
	}

	@Override
	public double calculateService() {
		return getClothingPieces() * laundryCost;
	}

	public double getCost() {
		
		return calculateService();
	}


	

	
};
