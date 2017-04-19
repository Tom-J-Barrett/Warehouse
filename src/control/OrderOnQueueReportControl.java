package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import database.Database;
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
	private List<String> columnTitlesOrder;
	private String columnTitleForSortingOrder;
	
	private HashMap<String, String> selectedParametersProduct;
	private List<String> columnTitlesProduct;
	private String columnTitleForSortingProduct;
	
	private HashMap<String, String> selectedParametersItem;
	private List<String> columnTitlesItem;
	private String columnTitleForSortingItem;
	
	private HashMap<String, String> selectedParametersInventory;
	private List<String> columnTitlesInventory;
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
	private List<String> tableTitles;
	private List<String> joinConditions;
	
	
	public OrderOnQueueReportControl(){
		inventory=new ArrayList<Inventory>();
		factory=new ReportFactory();
		db=new Database();
		location=new InventoryLocation(123,"3");
		orders=new ArrayList<Order>();
		getOrders();
		getProducts();
		getInventory();
		String name="Order Report";
		
		report=new ReportBuilder(factory).
				listOfObjects(orders).
				listOfObjects2(inventory).
				reportName(name).
				location(location).
				build();
		System.out.println("14");
		//report.printReport();
	}
	
	public Report getReport(){
		return report;
	}
	
	public String returnReport(){
		return report.reportString();
	}
	
	public void returnStr(){
		System.out.println(report.reportString());
	}
	
	public void createItems(){
		
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		item=new Item(name,id);
		items.add(item);
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
	}
	
	public void createInventory(){
		inventory=new ArrayList<Inventory>();
		for(int i=0;i<inventoryList.size();i++){
			selectedParametersItem=new HashMap<String, String>();
			String x=inventoryList.get(i).get(1);
			selectedParametersItem.put("ItemID",x);
			columnTitlesItem=new ArrayList<String>();
			columnTitlesItem.add("ItemID");
			columnTitlesItem.add("ItemName");
			columnTitleForSortingItem="ItemID";
			getInventoryItemsFromDatabase();
		
			String loc=inventoryList.get(i).get(2);
			String name=listOfItems.get(0).get(1);
			int id=Integer.parseInt(listOfItems.get(0).get(0));
			Item item=new Item(name,id);
			inven=new Inventory(item,location);
			inventory.add(inven);
		}
	}
	
	public void getOrders(){
		columnTitlesOrder=new ArrayList<String>();
		columnTitlesOrder.add("OrderID");
		columnTitlesOrder.add("ProductID");
		
		columnTitleForSortingOrder="ProductID";
		
		selectedParametersOrder=new HashMap<String, String>();
		selectedParametersOrder.put("1","1");
		System.out.println("1");
		getOrdersFromDatabase();
		System.out.println("2");
	}
	
	
	public void getProducts(){
		for(int i=0;i<listOfOrders.size();i++){
			products=new ArrayList<Product>();
			tableTitles=new ArrayList<String>();
			tableTitles.add("product");
			tableTitles.add("productitems");
			
			joinConditions=new ArrayList<String>();
			joinConditions.add("product.ProductID");
			joinConditions.add("productitems.ProductID");
			
			selectedParametersProduct=new HashMap<String, String>();
			String x=listOfOrders.get(i).get(1);
			selectedParametersProduct.put("productitems.ProductID",x);
			
			columnTitlesProduct=new ArrayList<String>();
			columnTitlesProduct.add("productitems.ProductID");
			columnTitlesProduct.add("productitems.ItemID");
			columnTitlesProduct.add("ProductName");
			
			columnTitleForSortingProduct="ProductName";
			getProductsFromDatabase();
			items=new ArrayList<Item>();
			for(int j=0;j<listOfProducts.size();j++){
				getItems(j);
			}
			createProduct();
			createOrder(i);
		}
	}
	
	public void getItems(int j){
		selectedParametersItem=new HashMap<String, String>();
		String x=listOfProducts.get(j).get(1);
		selectedParametersItem.put("ItemID",x);
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
		createInventory();
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParametersItem, columnTitlesItem, columnTitleForSortingItem);
	}
	
	public void getInventoryItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParametersItem, columnTitlesItem, columnTitleForSortingItem);
	}
	
	public void getProductsFromDatabase(){
		listOfProducts=db.getJoinedTableRows(tableTitles,joinConditions,selectedParametersProduct, columnTitlesProduct, columnTitleForSortingProduct);
	}
	
	public void getOrdersFromDatabase(){
		listOfOrders=db.getTableRows("ordersnotcreated", selectedParametersOrder, columnTitlesOrder, columnTitleForSortingOrder);
	}
	
	public void getInventoryFromDatabase(){
		inventoryList=db.getTableRows("inventory", selectedParametersInventory, columnTitlesInventory, columnTitleForSortingInventory);
	}
}
