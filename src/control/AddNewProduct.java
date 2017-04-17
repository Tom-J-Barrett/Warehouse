package control;

import java.util.*;

import database.Database;
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

	public AddNewProduct() {
		this.db = new Database();
		/*
		 * if(userType == "cli") { getItems(); setID(); setName(); setItems();
		 * addProduct(); }
		 */
	}

	public void addProduct() {
		product = new Product(items, ProductName, ProductID);
		updateDatabase(product);
	}

	public void setID() {
		System.out.println("Please select an ID :");
		ProductID = setID(new Scanner(System.in));
	}
	
	protected int setID(Scanner scanner) {
		return scanner.nextInt();
	}

	public void setName() {
		System.out.println("Please select the name of the new product :");
		ProductName = setName(new Scanner(System.in));
	}
	
	protected String setName(Scanner scanner){
		return scanner.nextLine();
	}

	public void setItems() {
		items = new ArrayList<Item>();
		System.out.println("Items : ");
		System.out.println(listOfItems);
		Scanner in = new Scanner(System.in);
		for (int i = 1; i <= 2; i++) {
			System.out.println("Please select item nÂ°" + i + " :");
			int id = in.nextInt();
			String name = listOfItems.get(id - 1).get(1);
			item = new Item(name, id);
			items.add(item);
		}

	}

	public void getItems() {
		selectedParametersItems = new HashMap<String, String>();
		// selectedParametersItems.put("1","1");

		columnTitlesItems = new ArrayList<String>();
		columnTitlesItems.add("ItemID");
		columnTitlesItems.add("ItemName");

		columnTitleForSortingItems = "ItemID";
		getItemsFromDatabase();
	}

	public void getItemsFromDatabase() {
		listOfItems = db.getTableRows("item", selectedParametersItems, columnTitlesItems, columnTitleForSortingItems);
	}

	public void updateDatabase(Product product) {
		insertData = new ArrayList<String>();
		insertData.add(String.valueOf(ProductID));
		insertData.add(ProductName);
		insertData.add(String.valueOf(items.get(0).getItemID()));
		insertData.add(String.valueOf(items.get(1).getItemID()));
		db.insertTableRow("product", insertData);
		System.out.println("Added " + product);
	}

	public List<Item> retrieveAvailableItems() {
		List<List<String>> availableItems = db.getTableRows("item", new HashMap<String, String>(),
				new ArrayList<String>(), "");
		List<Item> selectedItems = new ArrayList<>();
		for (List<String> anAvailableItem : availableItems)
			selectedItems.add(new Item(anAvailableItem.get(1), Integer.parseInt(anAvailableItem.get(0))));
		return selectedItems;
	}

	public List<String> getItemColumnTitles() {
		return db.getColumnTitles("item");
	}

	public List<List<String>> checkForProductAttribute(String productValue, String productAttribute) {
		HashMap<String, String> selectedParameters = new HashMap<String, String>();
		selectedParameters.put(productAttribute, productValue);
		return db.getTableRows("product", selectedParameters, new ArrayList<String>(), "");
	}

	public void insertNewProduct(Product aNewProduct) {
		db.insertTableRow("product",
				new ArrayList<String>(Arrays.asList(aNewProduct.getProductID() + "", aNewProduct.getProductName())));
		for (Item anItem : aNewProduct.getItems())
			db.insertTableRow("productitems",
					new ArrayList<String>(Arrays.asList(aNewProduct.getProductID() + "", anItem.getItemID() + "")));
	}

	public List<List<String>> getProducts() {
		System.out.println("Trying to get those products");
		return db.getJoinedTableRows(new ArrayList<String>(Arrays.asList("productitems", "product", "item")),
				new ArrayList<String>(Arrays.asList("productitems.productID", "product.ProductID",
						"productitems.itemID", "item.ItemID")),
				new HashMap<>(),
				new ArrayList<String>(
						Arrays.asList("product.ProductID", "product.ProductName", "item.ItemID", "item.ItemName")),
				"product.ProductID");
	}

	public List<String> getProductTitles() {
		return new ArrayList<String>(Arrays.asList("Product ID", "Product Name", "Item ID", "Item Name"));
	}
}
