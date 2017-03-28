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
import report.ReportFactory;
import shipment.Shipment;

public class OrderOnQueueReportControl {
	private Report report;
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
	private Item item2;
	private Product product;
	private Product tempProduct;
	private Order order;
	private Inventory inven;
	private ReportFactory factory;
	private String or;
	
	
	OrderOnQueueReportControl(){
		factory=new ReportFactory();
		db=new Database();
		location=new InventoryLocation(123,"3");
		orders=new ArrayList<Order>();
		getOrders();
		getProducts();
		//getItems();
		//createItems();
		//createProduct();
		//createOrder();
		//getInventory();
		//createInventory();
		String name="Order Report";
		inventory=new ArrayList<Inventory>();
		report=new ReportBuilder(factory).
				listOfObjects(orders).
				listOfObjects2(inventory).
				reportName(name).
				location(location).
				build();
		report.printReport();
	}
	
public void createItems(){
		
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		int id2=Integer.parseInt(listOfItems.get(1).get(0));
		String name2=listOfItems.get(1).get(1);
		item=new Item(name,id);
		item2=new Item(name2,id2);
		items.add(item);
		items.add(item2);
	}
	
	public void createProduct(){
		
		String name=listOfProducts.get(0).get(2);
		int id=Integer.parseInt(listOfProducts.get(0).get(0));
		product=new Product(items,name,id);
		products.add(product);
	}
	
	public void createOrder(int i){
		String shipTo="";
		int orderId=Integer.parseInt(listOfOrders.get(i).get(0));
		order=new Order(products,orderId,shipTo);
		orders.add(order);
		System.out.println(orders.size());
	}
	
	public void createInventory(){
		for(int i=0;i<inventoryList.size();i++){
			selectedParametersItem=new HashMap<String, String>();
			String x=inventoryList.get(i).get(2);
			selectedParametersItem.put("ItemID",x);
			columnTitlesItem=new ArrayList<String>();
			columnTitlesItem.add("ItemID");
			columnTitlesItem.add("ItemName");
			columnTitleForSortingItem="ItemID";
			getInventoryItemsFromDatabase();
			
			inventory=new ArrayList<Inventory>();
			String loc=inventoryList.get(i).get(2);
			String name=listOfItems.get(0).get(0);
			int id=Integer.parseInt(listOfItems.get(0).get(1));
			Item item=new Item(name,id);
			inven=new Inventory(item,location);
			inventory.add(inven);
		}
	}
	
	public void getOrders(){
		locationID=location.getLocationID();
	
		columnTitlesOrder=new ArrayList<String>();
		columnTitlesOrder.add("OrderID");
		columnTitlesOrder.add("ProductID");
		
		columnTitleForSortingOrder="ProductID";
		
		selectedParametersOrder=new HashMap<String, String>();
		selectedParametersOrder.put("1","1");
		getOrdersFromDatabase();
	}
	
	
	public void getProducts(){
		for(int i=0;i<listOfOrders.size();i++){
			products=new ArrayList<Product>();
			items=new ArrayList<Item>();
			selectedParametersProduct=new HashMap<String, String>();
			String x=listOfOrders.get(i).get(1);
			selectedParametersProduct.put("ProductID", x);
			
			columnTitlesProduct=new ArrayList<String>();
			columnTitlesProduct.add("ProductID");
			columnTitlesProduct.add("ItemID");
			columnTitlesProduct.add("ProductName");
			columnTitlesProduct.add("Item2ID");
			
			columnTitleForSortingProduct="ProductName";
			getProductsFromDatabase();
			getItems();
			createProduct();

			createOrder(i);
		}
	}
	
	public void getItems(){
		selectedParametersItem=new HashMap<String, String>();
		String x=listOfProducts.get(0).get(1);
		selectedParametersItem.put("ItemID",x);
		or=listOfProducts.get(0).get(3);
		columnTitlesItem=new ArrayList<String>();
		columnTitlesItem.add("ItemID");
		columnTitlesItem.add("ItemName");
		
		columnTitleForSortingItem="ItemID";
		getItemsFromDatabase();
		createItems();
	}
	
	public void getInventory(){
		selectedParametersInventory=new HashMap<String, String>();
		selectedParametersInventory.put("1","1");
		columnTitlesInventory=new ArrayList<String>();
		columnTitlesInventory.add("InventoryID");
		columnTitlesInventory.add("ItemID");
		columnTitlesInventory.add("LocationID");
		columnTitleForSortingInventory="InventoryID";
		getInventoryFromDatabase();
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRowsOr("item", selectedParametersItem, columnTitlesItem, columnTitleForSortingItem, or);
	}
	
	public void getInventoryItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParametersItem, columnTitlesItem, columnTitleForSortingItem);
	}
	
	public void getProductsFromDatabase(){
		listOfProducts=db.getTableRows("product", selectedParametersProduct, columnTitlesProduct, columnTitleForSortingProduct);
	}
	
	public void getOrdersFromDatabase(){
		listOfOrders=db.getTableRows("ordersnotcreated", selectedParametersOrder, columnTitlesOrder, columnTitleForSortingOrder);
	}
	
	public void getInventoryFromDatabase(){
		inventoryList=db.getTableRows("inventory", selectedParametersInventory, columnTitlesInventory, columnTitleForSortingInventory);
	}
}
