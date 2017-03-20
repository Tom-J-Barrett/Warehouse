package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.Database;
import inventory.Item;
import location.Location;

public class ReceiveInventory {
	Scanner in = new Scanner(System.in);
	public ReceiveInventory(Database db){
		String item, location, inventoryID;
		int quantity;
		System.out.println("Please enter Inventory ID:");
		inventoryID = in.nextLine();
		System.out.println("Please enter Item ID:");
		item = in.nextLine();
		System.out.println("Please enter Location ID:");
		location = in.nextLine();
		System.out.println("Please enter quantity:");
		quantity = in.nextInt();
		addToInventory(item,location,quantity,inventoryID, db);
	}

	public void addToInventory(/*Item*/ String item, /*Location*/ String location, int quantity, String inventoryID, Database db){
		int counter =0;
		ArrayList<String> insertData = new ArrayList<String>();
		for(int i = 0; i < quantity; i++){
			insertData = addDataToList(insertData, item /*.getItemID()*/, location/*.getLocationID()*/, inventoryID);
			counter++;
			db.insertTableRow("inventory", insertData);
		}

		System.out.println(counter + " Items Added to inventory");
		
	}
	
	public ArrayList<String> addDataToList(ArrayList<String> insertData, String itemID, String locationID, String inventoryID){
		insertData.clear();
		insertData.add(inventoryID);
		insertData.add(itemID);
		insertData.add(locationID);
		return insertData;
	}
		
}
