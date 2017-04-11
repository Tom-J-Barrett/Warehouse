package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import database.Database;
import inventory.Inventory;
import inventory.Item;
import location.Location;
import location.LocationFactory;

public class ReceiveInventory {
	private ArrayList<String> itemColumnsForTable = new ArrayList<String>();
	private ArrayList<String> locationColumnsForTable = new ArrayList<String>();
	Database db = new Database();
	public ReceiveInventory(){
	}

	public void addToInventory(Inventory inventory, int quantity){
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
	
	public List<List<String>> getItems(){
		List<List<String>> items;
		ArrayList<String> columnTitles = new ArrayList<String>();
		columnTitles.add("ItemID");
		columnTitles.add("ItemName");
		items = db.getTableRows("item", new HashMap<String, String>(), columnTitles, "ItemID");
		return items;
		
	}
	
	public List<List<String>> getLocations(){
		List<List<String>> locations;
		ArrayList<String> columnTitles = new ArrayList<String>();
		columnTitles.add("LocationID");
		columnTitles.add("LocationType");
		locations = db.getTableRows("locations", new HashMap<String, String>(), columnTitles, "LocationID");
		return locations;
	}
	
	public List<String> getItemColumnsForTable(){
		itemColumnsForTable.add("ItemID");
		itemColumnsForTable.add("ItemName");
		return itemColumnsForTable;
	}
	
	public List<String> getLocationColumnsForTable(){
		locationColumnsForTable.add("LocationID");
		locationColumnsForTable.add("LocationType");
		return locationColumnsForTable;
	}
		
}
