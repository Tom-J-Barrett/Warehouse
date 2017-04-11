package report;

import java.util.ArrayList;
import java.util.List;

public class ScrapReport implements Report{
	private String reportName;
	private List<List<String>> scrap;
	
	public ScrapReport(ReportBuilder b){
		this.reportName=b.reportName;
		this.scrap=b.list;
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(int i=0;i<scrap.size();i++){
			System.out.println("Order ID: "+scrap.get(i).get(1)+"	" +scrap.get(i).get(2));
		}
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
