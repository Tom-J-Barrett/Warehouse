package warehouse;

import inventory.Item;
import location.InventoryOutLocation;
import location.Location;

public class IntelligentInventoryPlacement {
	private Location location;
	public IntelligentInventoryPlacement(){
	}

	public Location placeInventory(Item item) {
		location=new InventoryOutLocation(1,"Inventory Out Location");
		return location;
	}
}
