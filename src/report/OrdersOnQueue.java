package report;
import java.util.List;

import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;

public class OrdersOnQueue extends Report {
	private List<Order> orders;
	private List<Product> products;
	private List<Item> items;
	private List<Inventory> inventory;
	private Location location;
	//private Order order;
	private int locationID=0;
	private String locationType;
//	private List<Movement> movements;
	//private Employee employee;
	
	public OrdersOnQueue(ReportBuilder b){
		this.reportName=b.reportName;
		this.orders=b.orders;
		/*this.products=b.products;
		this.items=b.items;
		this.inventory=b.inventory;*/
		this.location=b.location;
		this.locationID=location.getLocationID();
		this.locationType=location.getLocationType();
		System.out.println("created");
		//this.employee=b.employee;	
	}
	
	@Override
	public String generateReport(){
		return "";
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(Order order: orders){
			//for(Product product: order.getProducts()){
			//	for(Item item: product.getItems())
			//}
		}
	}
}
