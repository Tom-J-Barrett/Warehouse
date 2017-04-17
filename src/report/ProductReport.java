package report;

import java.util.ArrayList;
import java.util.List;

public class ProductReport implements Report{
	private String reportName;

	public ProductReport(){
	}
	
	public void printReport(){
		System.out.println(reportName);
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
