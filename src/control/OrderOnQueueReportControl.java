package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.Location;
import report.OrdersOnQueue;
import report.OrdersOnQueueBuilder;
import report.Report;
import shipment.Shipment;

public class OrderOnQueueReportControl {
	private OrdersOnQueue report;
	private Location location;
	private int locationID,orderID;
	
	private List<Item> items;
	private List<Product> products;
	private List<Order> orders;
	private List<Inventory> inventory;
	private Database db;
	
	private HashMap<String, String> selectedParametersOrder;
	private ArrayList<String> columnTitlesOrder;
	private String columnTitleForSortingOrder;
	
	private HashMap<String, String> selectedParametersProduct;
	private ArrayList<String> columnTitlesProduct;
	private String columnTitleForSortingProduct;
	
	private HashMap<String, String> selectedParametersItem;
	private ArrayList<String> columnTitlesItem;
	private String columnTitleForSortingItem;
	
	private HashMap<String, String> selectedParametersItemInventory;
	private ArrayList<String> columnTitlesInventory;
	private String columnTitleForSortingInventory;
	
	private List<List<String>> listOfOrders;
	private List<List<String>> listOfProducts;
	private List<List<String>> listOfItems;
	private List<List<String>> inventoryList;
	
	OrderOnQueueReportControl(){
		//report=new OrdersOnQueueBuilder().
	}
	
	/*public void createItems(){
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
		int id=Integer.parseInt(listOfOrdersToShip.get(0).get(0));
		String shipTo="Address";
		orderToShip=new Order(products,id,shipTo);
	}
	
	public void createInventory(){
		int id=Integer.parseInt(listOfOrdersToShip.get(0).get(0));
		String shipTo="Address";
		orderToShip=new Order(products,id,shipTo);
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
	
	public void getInventory(){
		
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParameters3, columnTitles3, columnTitleForSorting3);
	}
	
	public void getProductsFromDatabase(){
		listOfProducts=db.getTableRows("product", selectedParameters2, columnTitles2, columnTitleForSorting2);
	}
	
	public void getOrdersFromDatabase(){
		listOfOrders=db.getTableRows("ordertable", selectedParameters, columnTitles, columnTitleForSorting);
	}
	
	public void getInventoryFromDatabase(){
		inventoryList=db.getTableRows("ordertable", selectedParameters, columnTitles, columnTitleForSorting);
	}
	
	public void updateDatabase(Shipment shipment){
		
	}*/
	
	
}
