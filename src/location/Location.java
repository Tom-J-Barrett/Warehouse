package location;

public class Location {
	private int locationID;
	private String locationType;
	
	public Location(){
		System.out.println("Location");
	}
	
	public int getLocationID(){
		return locationID;
	}
	
	public String getLocationType(){
		return locationType;
	}
}
