package control;

import java.util.*;

import Database.Database;
import inventory.Item;
import inventory.Product;

/*WORKING BUT STILL A PROBLEM WITH DUPLICATE IN DATABASE*/

public class AddNewProduct {
	private Product product;
	private Item item;
	private Database db;
	
	private String ProductName;
	private int ProductID;
	
	private List<Item> items;

	private List<List<String>> listOfItems;
	private HashMap<String, String> selectedParametersItems;
	private List<String> columnTitlesItems;
	private String columnTitleForSortingItems;

	private List<String> insertData;
	
	public AddNewProduct(Database db){
		this.db=db;
		getItems();
		setID();
		setName();
		setItems();
		addProduct();
	}
	
	public void addProduct(){
		product=new Product(items, ProductName, ProductID);
		updateDatabase(product);
	}
	
	public void setID(){
		Scanner in=new Scanner(System.in);
		System.out.println("Please select an ID :");
		ProductID = in.nextInt();	
	}
	
	public void setName(){
		Scanner in=new Scanner(System.in);
		System.out.println("Please select the name of the new product :");
		ProductName= in.nextLine();		
	}
	
	public void setItems(){
		items=new ArrayList<Item>();
		System.out.println("Items : ");
		System.out.println(listOfItems);
		Scanner in=new Scanner(System.in);
		for(int i=1; i<=2; i++){
			System.out.println("Please select item n°"+i+" :");
			int id=in.nextInt();
			String name=listOfItems.get(id-1).get(1);
			item=new Item(name,id);
			items.add(item);
		}

	}
		
	public void getItems(){
		selectedParametersItems=new HashMap<String, String>();
	//	selectedParametersItems.put("1","1");
		
		columnTitlesItems=new ArrayList<String>();
		columnTitlesItems.add("ItemID");
		columnTitlesItems.add("ItemName");
		
		columnTitleForSortingItems="ItemID";
		getItemsFromDatabase();
	}
	
	public void getItemsFromDatabase(){
		listOfItems=db.getTableRows("item", selectedParametersItems, columnTitlesItems, columnTitleForSortingItems);
	}
	
	public void updateDatabase(Product product){
		insertData=new ArrayList<String>();
		insertData.add(String.valueOf(ProductID));
		insertData.add(ProductName);
		insertData.add(String.valueOf(items.get(0).getItemID()));
		insertData.add(String.valueOf(items.get(1).getItemID()));
		db.insertTableRow("product", insertData);
		System.out.println("Added " +product);
	}
}
