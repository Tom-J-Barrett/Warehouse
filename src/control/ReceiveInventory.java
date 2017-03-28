package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import inventory.Inventory;
import inventory.Item;
import location.Location;
import location.LocationFactory;

public class ReceiveInventory {
	Scanner in = new Scanner(System.in);
	public ReceiveInventory(Database db){
		Location location;
		Item item;
		String itemID, locationID, locationType;
		int quantity;
		System.out.println("Please enter Item ID:");
		itemID = in.nextLine();
		System.out.println("Please enter Location ID:");
		locationID = in.nextLine();
		System.out.println("Please enter Location Name:");
		locationType = in.nextLine();
		System.out.println("Please enter quantity:");
		quantity = in.nextInt();
		item = new Item("",Integer.parseInt(itemID));
		location = LocationFactory.getLocation(Integer.parseInt(locationID), locationType);
		Inventory inventory = new Inventory(item, location);
		addToInventory(inventory, quantity, db);
	}

	public void addToInventory(Inventory inventory, int quantity, Database db){
		int counter =0;
		ArrayList<String> insertData = new ArrayList<String>();
		for(int i = 0; i < quantity; i++){
			insertData = addDataToList(insertData, Integer.toString(inventory.getItem().getItemID()), Integer.toString(inventory.getLocation().getLocationID()), db);
			counter++;
			db.insertTableRow("inventory", insertData);
		}

		System.out.println(counter + " Items Added to inventory");
		
	}
	
	public ArrayList<String> addDataToList(ArrayList<String> insertData, String itemID, String locationID, Database db){
		insertData.clear();
    	System.out.println("Yup");
		insertData.add(Integer.toString((db.getMaxValueOfColumn("inventory", "inventoryID")+1)));
    	System.out.println("Yup");
		insertData.add(itemID);
		insertData.add(locationID);
		return insertData;
	}
		
}
