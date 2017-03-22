package location;

public class InventoryOutLocation extends Location {
	private int locationID;
	private String locationType;

	InventoryOutLocation(int locationID, String locationType){
		this.locationID=locationID;
		this.locationType=locationType;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
}
