package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import inventory.Item;
import inventory.Order;
import inventory.Product;


public class Scrap {

	private ArrayList<Item> productItems;
	private Item item1;
	private Item item2;
	
	private List<Product> products;
	private Product product1;
	private Product product2;
	
	private List<Order> orders;
	private Order order1;
	private Order order2;
	
	private List<Order> scrapOrders;
	
	
	

	public Scrap(){
	
		productItems=new ArrayList<Item>();
		products=new ArrayList<Product>();
		orders=new ArrayList<Order>();
		scrapOrders=new ArrayList<Order>();
		
		getInfoFromDatabase();
		displayOrders();
		
		
		
	}
	
	
	public void createOrderList(){
		
		item1=new Item("item1",1);
		item2=new Item("item2",2);
		productItems.add(item1);
		productItems.add(item2);
		
		product1=new Product(productItems, "product1", 1);
		product2=new Product(productItems, "product2", 2);
		products.add(product1);
		products.add(product2);
		
		order1=new Order(products, 1, "place1");
		order2=new Order(products, 2, "place2");
		orders.add(order1);
		orders.add(order2);		
	}
	
	public void displayOrders(){
		
		System.out.println("Orders : ");

		for (Order order : orders) {
			System.out.println(order.getOrderID());
		}
		
		System.out.println("Please select an order to scrap :");
		Scanner sc=new Scanner(System.in);
		int orderID=Integer.parseInt(sc.nextLine());
		
	for(Iterator<Order> it = orders.iterator(); it.hasNext();){
			Order order = it.next();
			if(orderID == order.getOrderID()){
				scrapOrders.add(order);
				it.remove();
			}	
		}
		
		System.out.println("Orders : ");

		for (Order order : orders) {
			System.out.println(order.getOrderID());
		}
		
		System.out.println("Scraped orders : ");

		for (Order order : scrapOrders) {
			System.out.println(order.getOrderID());
		}
		
	}
	
	public void getInfoFromDatabase(){
		createOrderList();
	}
	
	
}
