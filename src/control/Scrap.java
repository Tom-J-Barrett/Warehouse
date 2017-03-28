package control;
import java.util.*;

import Database.Database;

import java.io.*;

import inventory.Item;
import inventory.Order;
import inventory.Product;

public class Scrap {

	private int orderID;
	private Order order;

	

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
	private List<List<String>> listOfOrders;
	private List<List<String>> listOfProducts;
	private List<List<String>> listOfItems;
	
	private List<String> shipvalues;
	
	private String x;
	private String shipTo;
	
	public Scrap(Database db){
		this.db=db;
		getOrders();
		getProducts();
		getItems();
		displayOrders();
		createItems();
		createProduct();
		scrapOrder();
	}
	
	public void scrapOrder(){
		order = new Order(products, orderID, shipTo);
		updateDatabase(order);
	}
			
	public void createProduct(){
		products=new ArrayList<Product>();
		String name=listOfProducts.get(0).get(2);
		int id=Integer.parseInt(listOfProducts.get(0).get(0));
		productForOrder=new Product(items,name,id);
		products.add(productForOrder);
	}
	
	public void createItems(){
		items=new ArrayList<Item>();
		int id=Integer.parseInt(listOfItems.get(0).get(0));
		String name=listOfItems.get(0).get(1);
		itemForOrder=new Item(name,id);
		items.add(itemForOrder);
	}
	
	public void displayOrders(){
		for(int i=0;i<listOfOrders.size();i++){
			 x=listOfOrders.get(i).get(0);
			 shipTo=listOfOrders.get(i).get(2);
			System.out.println("Order: "+ x);
		}
		Scanner in=new Scanner(System.in);
		System.out.println("Please enter orderID: ");
		orderID=Integer.parseInt(in.nextLine());
		selectedParameters.put("OrderID", String.valueOf(orderID));		
	}
	
	public void getOrders(){
		
		selectedParameters=new HashMap<String, String>();
		columnTitles=new ArrayList<String>();
		columnTitles.add("OrderID");
		columnTitles.add("ProductID");
		columnTitles.add("LocationID");
		
		columnTitleForSorting="ProductID";
		
		
		getOrdersFromDatabase();
	}
	
	public void getProducts(){
		selectedParameters2=new HashMap<String, String>();
	
		
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
		listOfOrders=db.getTableRows("ordertable", selectedParameters, columnTitles, columnTitleForSorting);
	}
	
	public void updateDatabase(Order order){
		
		Date date = new Date();
		Object param = new java.sql.Timestamp(date.getTime());
		shipvalues=new ArrayList<String>();
		shipvalues.add(String.valueOf(orderID));
		shipvalues.add(String.valueOf(orderID));
		shipvalues.add(String.valueOf(param));
		db.insertTableRow("scraplog", shipvalues);
		db.removeTableRow("ordertable", selectedParameters);
		System.out.println("Scrapped "+order);
		
	}
}
