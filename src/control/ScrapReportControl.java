package control;
import employee.Employee;
import inventory.Item;
import Database.Database;
import java.util.*;

public class ScrapReportControl {
	
	private Database db;
	private List<List<String>> listOfScrap;
	private HashMap<String, String> selectedParameters;
	private ArrayList<String> columnTitles;
	private String columnTitleForSorting;
	
	ScrapReportControl(){
		
	}
	
	public void getScrap(){
		//items=new ArrayList<Item>();
		//int id=Integer.parseInt(listOfItems.get(0).get(0));
		//String name=listOfItems.get(0).get(1);
		//item=new Item(name,id);
		//items.add(item);
	}
	
	public void getScrapFromDatabase(){
		listOfScrap=db.getTableRows("scraplog", selectedParameters, columnTitles, columnTitleForSorting);
	}
}
