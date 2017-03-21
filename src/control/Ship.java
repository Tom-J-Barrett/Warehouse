package control;
import java.util.*;

import Database.Database;

import java.io.*;

import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;
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
	
	private ArrayList<Item> items;
	private List<Product> products;
	private Database db;
	private HashMap<String, String> selectedParameters;
	private ArrayList<String> columnTitles;
	private String columnTitleForSorting;
	private HashMap<String, String> selectedParameters2;
	private ArrayList<String> columnTitles2;
	private String columnTitleForSorting2;
	private HashMap<String, String> selectedParameters3;
	private ArrayList<String> columnTitles3;
	private String columnTitleForSorting3;
	private ArrayList<ArrayList<String>> listOfOrdersToShip;
	private ArrayList<ArrayList<String>> listOfProducts;
	private ArrayList<ArrayList<String>> listOfItems;
	
	private ArrayList<String> shipvalues;
	
	public Ship(Database db){
		this.db=db;
		location=new ShipLocation();
		getOrders();
		getProducts();
		getItems();
		displayOrders();
		createItems();
		createProduct();
		createOrder();
		shipOrder();
	}
	
	public void shipOrder(){
		shipment=new Shipment(location,orderToShip,orderToShip.getShipTo());
		updateDatabase(shipment);
	}
	
	public void createItems(){
		items=new ArrayList<Item>();
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		itemForOrder=new Item(name,id);
		items.add(itemForOrder);
	}
	
	public void createProduct(){
		products=new ArrayList<Product>();
		String name=listOfProducts.get(0).get(2);
		int id=Integer.parseInt(listOfProducts.get(0).get(0));
		productForOrder=new Product(items,name,id);
		products.add(productForOrder);
	}
	
	public void createOrder(){
		//List<Product> products2, int orderID, String shipTo
		int id=Integer.parseInt(listOfOrdersToShip.get(0).get(0));
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
		selectedParameters2=new HashMap<String, String>();
		String x=listOfOrdersToShip.get(0).get(0);
		selectedParameters2.put("ProductID",x);
		
		columnTitles2=new ArrayList<String>();
		columnTitles2.add("ProductID");
		columnTitles2.add("ItemID");
		columnTitles2.add("ProductName");
		
		columnTitleForSorting2="ProductName";
		getProductsFromDatabase();
	}
	
	public void getItems(){
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
		shipvalues=new ArrayList<String>();
		shipvalues.add("1");
		shipvalues.add("1");
		shipvalues.add(Integer.toString(shipment.getOrderID()));
		db.insertTableRow("shipment", shipvalues);
		System.out.println("Shipped Order: "+ shipment.getOrderID());
	}
}
