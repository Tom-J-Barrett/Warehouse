package shipment;

import approval.Approval;
import inventory.Order;
import location.Location;

public class Shipment implements Shipable {
	
	private Location location;
	private Approval approval;
	private Order order;
	private String shipTo;
	private boolean isShipable;
	
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
		return isShipable;
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
	
	public Order getOrder()
    {
        return order;
    }
	
    public void setOrder(Order order)
    {
        this.order = order;
    }

}
