package Inventory;

public class Item{

	private String itemName;
	private int itemID;
	
	Item(){
	
	}
	
	Item(String itemName, int itemID){
		this.itemName=itemName;
		this.itemID=itemID;
	}
	
	public void setItemName(String itemName){
		this.itemName=itemName;
	}
	
	public String getItemName(){
		return itemName;
	}
	
	public int getItemID(){
		return itemID;
	}

}
