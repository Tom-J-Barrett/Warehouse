package location;

public class QILocation extends Location {
	private int locationID;
	private String locationType;
	
	QILocation(int locationID, String locationType){
		this.setLocationID(locationID);
		this.setLocationType(locationType);
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
