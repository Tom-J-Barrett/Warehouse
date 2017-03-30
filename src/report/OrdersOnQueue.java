package report;
import java.util.List;

import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;
import warehouse.IntelligentInventoryPlacement;

public class OrdersOnQueue implements Report{

	private List<Order> orders;
	private List<Product> products;
	private List<Item> items;
	private List<Inventory> inventory;
	private Location location;
	private IntelligentInventoryPlacement placement;
	private int locationID=0;
	private String locationType;
	private Product product;
	private Item item;
	private Item item2;
	private String reportName;
	
	public OrdersOnQueue(ReportBuilder b){
		this.reportName=b.reportName;
		this.orders=b.list;
		this.inventory=b.list2;
		this.location=b.location;
		this.locationID=location.getLocationID();
		this.locationType=location.getLocationType();
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(Order order: orders){
			product=order.getProducts().get(0);
			item=product.getItems().get(0);
			item2=product.getItems().get(1);
			System.out.println("Order : "+ order.getOrderID());
			System.out.println("	Product ID: "+ product.getProductID());
			System.out.println("	Product Name: "+ product.getProductName());
			System.out.println("		Item ID: "+ item.getItemID());
			System.out.println("		Item ID: "+ item.getItemName());
			System.out.println("		Item ID: "+ item2.getItemID());
			System.out.println("		Item ID: "+ item2.getItemName());
			boolean itemC=false;
			boolean item2C=false;
			placement=new IntelligentInventoryPlacement();
			for(Inventory inven: inventory){
				if((inven.getItem().getItemID()==item.getItemID()) && itemC==false){
					itemC=true;
					System.out.println("Collect " +item.getItemName() + " from Location " + inven.getLocation().getLocationID());
					System.out.println("Place "+ item.getItemName()+ " in location "+ placement.placeInventory(item).getLocationID()+".");
				}
				else if((inven.getItem().getItemID()==item2.getItemID())&& item2C==false){
					item2C=true;
					System.out.println("Collect " +item2.getItemName() + " from Location " + inven.getLocation().getLocationID());
					System.out.println("Place "+ item2.getItemName()+ " in location "+ placement.placeInventory(item2).getLocationID()+".");
				}
			}
			if(itemC==false){
				System.out.println("We are our of stock of item "+ item.getItemName());
			}
			else if(item2C==false){
				System.out.println("We are our of stock of item "+ item2.getItemName());
			}
			
			System.out.println();
		}
	}
	
}
