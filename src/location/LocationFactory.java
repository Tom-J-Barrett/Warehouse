package location;

public class LocationFactory {
	public static Location getLocation(int locationID, String locationType){
		if(locationID==1)
			return new InventoryLocation(locationID, locationType);
		else if(locationID==2)
			return new QILocation(locationID, locationType);
		else if(locationID==3)
			return new QIOutLocation(locationID, locationType);
		else if(locationID==4)
			return new ShipLocation(locationID, locationType);
		
		return null;
	}
}
