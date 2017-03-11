package Shipment;

import Approval.Approval;
import Inventory.Order;
import Location.Location;

public class Shipment implements Shipable {
	
	private Location location;
	private Approval approval;
	private Order order;
	private String shipTo;
	
	Shipment(){
		
	}
	
	public Shipment(Location location, Order order, String shipTo){
		this.location=location;
		this.order=order;
		this.shipTo=shipTo;
		approval=new Approval();	
	}

	@Override
	public boolean isShipable() {
		return true;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Approval getApproval() {
		return approval;
	}

	public void setApproval(Approval approval) {
		this.approval = approval;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	
	public int getOrderID(){
		return order.getOrderID();
	}

}
