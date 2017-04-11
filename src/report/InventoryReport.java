package report;

import java.util.ArrayList;
import java.util.List;

public class InventoryReport implements Report{
	private String reportName;
	public InventoryReport(){
		this.reportName="Inventory Report";
		System.out.println("Inventory Information");
	}
	
	public void printReport(){
		
	}
	
	public String reportString(){
		return "";
	}
	public void generateTableValues(){
	
	}
	
	public List<List<String>> getTable(){
		List<List<String>> ex= new ArrayList<List<String>>();
		return ex;
	}
}
