package location;

public class InventoryLocation extends Location {
	private int locationID;
	private String locationType;

	InventoryLocation(int locationID, String locationType){
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
