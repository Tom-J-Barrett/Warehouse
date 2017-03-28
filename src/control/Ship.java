package control;
import java.util.*;

import Database.Database;

import java.io.*;

import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;
import location.LocationFactory;
import location.ShipLocation;
import shipment.Shipment;

public class Ship {
	private Shipment shipment;
	private Location location;
	private int locationID,orderID;
	private Order order1;
	private Order order2;
	
	private Order orderToShip;
	private Item itemForOrder;
	private Product productForOrder;
	
	private List<Item> items;
	private List<Product> products;
	private Database db;
	private HashMap<String, String> selectedParameters;
	private List<String> columnTitles;
	private String columnTitleForSorting;
	private HashMap<String, String> selectedParameters2;
	private List<String> columnTitles2;
	private String columnTitleForSorting2;
	private HashMap<String, String> selectedParameters3;
	private List<String> columnTitles3;
	private String columnTitleForSorting3;
	private List<List<String>> listOfOrdersToShip;
	private List<List<String>> listOfProducts;
	private List<List<String>> listOfItems;
	private int index;
	private List<String> shipvalues;
	
	public Ship(Database db){
		this.db=db;
		location=LocationFactory.getLocation(4,"Ship Location");
		getOrders();
		displayOrders();
		getProducts();
		getItems();
		createItems();
		createProduct();
		createOrder();
		shipOrder();
	}
	
	public void shipOrder(){
		System.out.println("ship order");
		shipment=new Shipment(location,orderToShip,orderToShip.getShipTo());
		updateDatabase(shipment);
	}
	
	public void createItems(){
		System.out.println("create items");
		items=new ArrayList<Item>();
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		itemForOrder=new Item(name,id);
		items.add(itemForOrder);
	}
	
	public void createProduct(){
		System.out.println("create product");
		products=new ArrayList<Product>();
		String name=listOfProducts.get(0).get(2);
		int id=Integer.parseInt(listOfProducts.get(0).get(0));
		productForOrder=new Product(items,name,id);
		products.add(productForOrder);
	}
	
	public void createOrder(){
		System.out.println("create order");
		//List<Product> products2, int orderID, String shipTo
		int id=Integer.parseInt(listOfOrdersToShip.get(index).get(0));
		String shipTo="Address";
		orderToShip=new Order(products,id,shipTo);
	}
	
	public void displayOrders(){
		for(int i=0;i<listOfOrdersToShip.size();i++){
			String x=listOfOrdersToShip.get(i).get(0);
			System.out.println("Order: "+ x);
		}
		Scanner in=new Scanner(System.in);
		System.out.println("Please enter orderID: ");
		orderID=Integer.parseInt(in.nextLine());
		in.close();
	}
	
	public void getOrders(){
		locationID=location.getLocationID();

		columnTitles=new ArrayList<String>();
		columnTitles.add("OrderID");
		columnTitles.add("ProductID");
		columnTitles.add("LocationID");
		
		columnTitleForSorting="ProductID";
		
		selectedParameters=new HashMap<String, String>();
		selectedParameters.put("LocationID", "3");
		getOrdersFromDatabase();
	}
	
	public void getProducts(){
		index=0;
		for(int i=0;i<listOfOrdersToShip.size();i++){
			if(Integer.parseInt(listOfOrdersToShip.get(i).get(0))==orderID){
				index=i;
			}
		}
		
		selectedParameters2=new HashMap<String, String>();
		String x=listOfOrdersToShip.get(index).get(1);
		selectedParameters2.put("ProductID",x);
		
		columnTitles2=new ArrayList<String>();
		columnTitles2.add("ProductID");
		columnTitles2.add("ItemID");
		columnTitles2.add("ProductName");
		
		columnTitleForSorting2="ProductName";
		getProductsFromDatabase();
	}
	
	public void getItems(){
		System.out.println("get items");
		selectedParameters3=new HashMap<String, String>();
		String x=listOfProducts.get(0).get(0);
		selectedParameters3.put("ItemID",x);
		
		columnTitles3=new ArrayList<String>();
		columnTitles3.add("ItemID");
		columnTitles3.add("ItemName");
		
		columnTitleForSorting3="ItemID";
		getItemsFromDatabase();
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParameters3, columnTitles3, columnTitleForSorting3);
	}
	
	public void getProductsFromDatabase(){
		listOfProducts=db.getTableRows("product", selectedParameters2, columnTitles2, columnTitleForSorting2);
	}
	
	public void getOrdersFromDatabase(){
		listOfOrdersToShip=db.getTableRows("ordertable", selectedParameters, columnTitles, columnTitleForSorting);
	}
	
	public void updateDatabase(Shipment shipment){
		System.out.println("update");
		shipvalues=new ArrayList<String>();
		int maximumValueOfColumn = db.getMaxValueOfColumn("shipment", "ShipmentID");
		shipvalues.add(Integer.toString(maximumValueOfColumn+1));
		shipvalues.add("1");
		shipvalues.add(Integer.toString(shipment.getOrderID()));
		db.insertTableRow("shipment", shipvalues);
		System.out.println("Shipped Order: "+ shipment.getOrderID());
	}
}
