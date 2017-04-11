package report;
import java.util.ArrayList;
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
	private List<List<String>> tableValues;
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
	
	public List<Order> getOrders(){
		return orders;
	}
	
	public String getName(){
		return reportName;
	}
	
	public List<List<String>> getTable(){
		return tableValues;
	}
	
	public void generateTableValues(){
		tableValues = new ArrayList<List<String>>();
		tableValues.add(new ArrayList<String>());
		tableValues.add(new ArrayList<String>());
		tableValues.add(new ArrayList<String>());
		tableValues.add(new ArrayList<String>());
		tableValues.get(0).add("Order");
		tableValues.get(1).add("Product");
		tableValues.get(2).add("Item");
		tableValues.get(3).add("Instruction");
		for(int i=0;i<orders.size();i++){
			product=orders.get(i).getProducts().get(0);
			items=product.getItems();
			int x=orders.get(i).getOrderID();
			tableValues.get(0).add(Integer.toString(x));
			System.out.println("	Product ID: "+ product.getProductID());
			System.out.println("	Product Name: "+ product.getProductName());
			for(Item item: items){
				System.out.println("		Item ID: "+ item.getItemID());
				System.out.println("		Item ID: "+ item.getItemName());
				boolean itemC=false;
				placement=new IntelligentInventoryPlacement();
				for(Inventory inven: inventory){
					if((inven.getItem().getItemID()==item.getItemID()) && itemC==false){
						itemC=true;
						System.out.println("Collect " +item.getItemName() + " from Location " + inven.getLocation().getLocationID());
						System.out.println("Place "+ item.getItemName()+ " in location "+ placement.placeInventory(item).getLocationID()+".");
					}
				}
				if(itemC==false){
					System.out.println("We are our of stock of item "+ item.getItemName());
				}
			}
			System.out.println();
		}
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(Order order: orders){
			product=order.getProducts().get(0);
			items=product.getItems();
			System.out.println("Order : "+ order.getOrderID());
			System.out.println("	Product ID: "+ product.getProductID());
			System.out.println("	Product Name: "+ product.getProductName());
			for(Item item: items){
				System.out.println("		Item ID: "+ item.getItemID());
				System.out.println("		Item ID: "+ item.getItemName());
				boolean itemC=false;
				placement=new IntelligentInventoryPlacement();
				for(Inventory inven: inventory){
					if((inven.getItem().getItemID()==item.getItemID()) && itemC==false){
						itemC=true;
						System.out.println("Collect " +item.getItemName() + " from Location " + inven.getLocation().getLocationID());
						System.out.println("Place "+ item.getItemName()+ " in location "+ placement.placeInventory(item).getLocationID()+".");
					}
				}
				if(itemC==false){
					System.out.println("We are our of stock of item "+ item.getItemName());
				}
			}
			System.out.println();
		}
	}
	
	public String reportString(){
		String report="";
		report+="<html>";
		report+="<br>"+reportName+"</br>\n";
		report+="<br></br>";
		report+="<br></br>";
		for(Order order: orders){
			products=order.getProducts();
			for(Product product: products){
				items=product.getItems();;
				report+=("<br>Order : "+ order.getOrderID()+"</br>\n");
				report+="<br></br>";
				report+=("<br>	Product ID: "+ product.getProductID()+"</br>\n");
				report+=("<br>	Product Name: "+ product.getProductName()+"</br>\n");
				report+="<br></br>";
				for(Item item: items){
					report+=("<br>		Item ID: "+ item.getItemID()+"</br>\n");
					report+=("<br>		Item ID: "+ item.getItemName()+"</br>\n");
					boolean itemC=false;
					placement=new IntelligentInventoryPlacement();
					for(Inventory inven: inventory){
						if((inven.getItem().getItemID()==item.getItemID()) && itemC==false){
							itemC=true;
							report+=("<br>Collect " +item.getItemName() + " from Location " + inven.getLocation().getLocationID()+"</br>\n");
							report+=("<br>Place "+ item.getItemName()+ " in location "+ placement.placeInventory(item).getLocationID()+"."+"</br>\n");
						}
					}
					if(itemC==false){
						report+=("<br>We are our of stock of item "+ item.getItemName()+"</br>\n");
					}
				}
				report+="<br></br>";
			}
			report+="<br>\n</br>";
		}
		report+="</html>";
		return report;
	}
	
}
