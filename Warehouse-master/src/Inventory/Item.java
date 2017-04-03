package Inventory;

public class Item{

	private String itemName;
	private int itemID;
	
	public Item(){
	}
	
	public Item(String itemName, int itemID){
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

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", itemID=" + itemID + "]";
	}

}
