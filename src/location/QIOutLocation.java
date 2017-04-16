package location;

public class QIOutLocation extends Location {
	private int locationID;
	private String locationType;
	
	QIOutLocation(int locationID, String locationType){
		this.setLocationID(locationID);
		this.setLocationType(locationType);
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
}
