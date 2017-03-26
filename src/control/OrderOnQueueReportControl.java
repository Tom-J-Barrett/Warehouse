package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import inventory.Order;
import inventory.Product;
import location.InventoryLocation;
import location.Location;
import report.OrdersOnQueue;
import report.Report;
import report.ReportBuilder;
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
	
	private HashMap<String, String> selectedParametersInventory;
	private ArrayList<String> columnTitlesInventory;
	private String columnTitleForSortingInventory;
	
	private List<List<String>> listOfOrders;
	private List<List<String>> listOfProducts;
	private List<List<String>> listOfItems;
	private List<List<String>> inventoryList;
	private Item item;
	private Product product;
	private Order order;
	private Inventory inven;
	
	
	OrderOnQueueReportControl(){
		//Employee emp=new Employee();
		db=new Database();
		location=new InventoryLocation(123,"3");
		getOrders();
		getProducts();
		getItems();
		String name="report";
		
		report=new ReportBuilder().
				listOfObjects(orders).
				reportName(name).
				location(location).
				build();
	}
	
public void createItems(){
		items=new ArrayList<Item>();
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		item=new Item(name,id);
		items.add(item);
	}
	
	public void createProduct(){
		products=new ArrayList<Product>();
		String name=listOfProducts.get(0).get(2);
		int id=Integer.parseInt(listOfProducts.get(0).get(0));
		product=new Product(items,name,id);
		products.add(product);
	}
	
	public void createOrder(){
		orders=new ArrayList<Order>();
		String name=listOfOrders.get(0).get(2);
		String shipTo="";
		int id=Integer.parseInt(listOfOrders.get(0).get(0));
		order=new Order(products,id,shipTo);
		orders.add(order);
	}
	
	public void createInventory(){
		inventory=new ArrayList<Inventory>();
		String name=inventoryList.get(0).get(2);
		int id=Integer.parseInt(inventoryList.get(0).get(0));
		inven=new Inventory(item,location);
		inventory.add(inven);
	}
	
	public void getOrders(){
		locationID=location.getLocationID();

		columnTitlesOrder=new ArrayList<String>();
		columnTitlesOrder.add("OrderID");
		columnTitlesOrder.add("ProductID");
		columnTitlesOrder.add("LocationID");
		
		columnTitleForSortingOrder="ProductID";
		
		selectedParametersOrder=new HashMap<String, String>();
		selectedParametersOrder.put("LocationID", "3");
		getOrdersFromDatabase();
	}
	
	
	public void getProducts(){
		selectedParametersProduct=new HashMap<String, String>();
		String x=listOfOrders.get(0).get(0);
		selectedParametersProduct.put("ProductID",x);
		
		columnTitlesProduct=new ArrayList<String>();
		columnTitlesProduct.add("ProductID");
		columnTitlesProduct.add("ItemID");
		columnTitlesProduct.add("ProductName");
		
		columnTitleForSortingProduct="ProductName";
		getProductsFromDatabase();
	}
	
	public void getItems(){
		selectedParametersItem=new HashMap<String, String>();
		String x=listOfProducts.get(0).get(0);
		selectedParametersItem.put("ItemID",x);
		
		columnTitlesItem=new ArrayList<String>();
		columnTitlesItem.add("ItemID");
		columnTitlesItem.add("ItemName");
		
		columnTitleForSortingItem="ItemID";
		getItemsFromDatabase();
	}
	
	public void getInventory(){
		
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParametersItem, columnTitlesItem, columnTitleForSortingItem);
	}
	
	public void getProductsFromDatabase(){
		listOfProducts=db.getTableRows("product", selectedParametersProduct, columnTitlesProduct, columnTitleForSortingProduct);
	}
	
	public void getOrdersFromDatabase(){
		listOfOrders=db.getTableRows("ordertable", selectedParametersOrder, columnTitlesOrder, columnTitleForSortingOrder);
	}
	
	
	/*public void getInventoryFromDatabase(){
		inventoryList=db.getTableRows("inventory", selectedParametersInventory, columnTitlesInventory, columnTitleForSortingInventory);
	}*/
	
	public void updateDatabase(){
		
	}
	
	
}
