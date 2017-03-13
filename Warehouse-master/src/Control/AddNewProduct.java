package Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Inventory.Item;
import Inventory.Product;


public class AddNewProduct {
	private Product product;
	private List<Item> items;
	private ArrayList<Item> productItems;
	private Item item1;
	private Item item2;
	private Item item3;
	private String productName;
	private int productID, itemID;


	public AddNewProduct(){
		items=new ArrayList<Item>();
		productItems=new ArrayList<Item>();
		getInfoFromDatabase();
		getID();
		getName();
		displayItems();
		addProduct();
	}

	public void addProduct(){
		product=new Product(productItems, productName, productID);
		updateDatabase(product);
	}


	public void createItemList(){
		item1=new Item("item1",1);
		item2=new Item("item2",2);
		item3=new Item("item3",3);
		items.add(item1);
		items.add(item2);
		items.add(item3);
	}

	public void getName(){

		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter name of new product: ");
		productName = sc.nextLine();
	}

	public void displayItems(){

		System.out.println("Items : ");

		for (Item item : items) {
			System.out.println(item.getItemID());
		}

		for(int i=1; i<=2; i++ ) {	
			Scanner sc=new Scanner(System.in);
			System.out.println("Please select itemID "+i+" : ");
			itemID=Integer.parseInt(sc.nextLine());

			for (Item item : items) {
				if (itemID == item.getItemID()){
					productItems.add(item);
				}
			}
		}
	}

	public void getID(){

		Scanner sc=new Scanner(System.in);
		System.out.println("Choose a ID");
		productID = sc.nextInt();		
		//to change for auto incrementation from db
	}

	public void getInfoFromDatabase(){
		createItemList();
	}

	public void updateDatabase(Product product){

		System.out.println("Product added : "+ product.getProductID() +" - "+ product.getProductName()+" - "+ product.getItems());
	}
}
