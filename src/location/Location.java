package location;

public abstract class Location {
	private int locationID;
	private String locationType;
	
	public Location(){
	}
	
	public int getLocationID(){
		return locationID;
	}
	
	public String getLocationType(){
		return locationType;
	}
}
