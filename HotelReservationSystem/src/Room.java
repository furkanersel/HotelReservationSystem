public class Room {
	
	private String roomType;
	private int dailyCost;
	private int roomSize;
	private boolean hasBath;
	
	
	public Room(String roomType, int dailyCost, int roomSize, boolean hasBath) {
		super();
		this.roomType = roomType;
		this.dailyCost = dailyCost;
		this.roomSize = roomSize;
		this.hasBath = hasBath;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public boolean isHasBath() {
		return hasBath;
	}

	public void setHasBath(boolean hasBath) {
		this.hasBath = hasBath;
	}

	public void setDailyCost(int dailyCost) {
		this.dailyCost = dailyCost;
	}
	
	public int getDailyCost() {
		return dailyCost;
	}

	public static String RoomInfo() {
		return "\nROOM INFOS:\n" 
			+"\nRoom Type: Single, Daily Cost: 100, Room Size: 15, Has Bath: false\n"
			+"Room Type: Double, Daily Cost: 180, Room Size: 30, Has Bath: false\n"
			+"Room Type: Club, Daily Cost: 250, Room Size: 45, Has Bath: true\n"
			+"Room Type: Family, Daily Cost: 400, Room Size: 50, Has Bath: false\n"
			+"Room Type: Family with View, Daily Cost: 450, Room Size: 50, Has Bath: true\n"
			+"Room Type: Suite, Daily Cost: 650, Room Size: 80, Has Bath: true\n";
		
	}
}
