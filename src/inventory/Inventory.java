package inventory;

import location.Location;

public class Inventory{
	private Item item;
	private Location location;
	
	Inventory(){
		
	}
	
	public Inventory(Item item, Location location){
		this.item=item;
		this.location=location;
	}
	
	public void setItem(Item item)
	{
		this.item=item;
	}
	
	public Item getItem(){
		return item;
	}
	
	public void setLocation(Location location)
	{
		this.location=location;
	}
	
	public Location	getLocation(){
		return location;
	}

}
