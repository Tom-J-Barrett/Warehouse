package control;
import java.util.*;

import report.InventoryReport;
import report.ProductReport;
import report.Report;
import report.ScrapReport;
import control.OrderOnQueueReportControl;

public class GenerateReport {
	private Report report;
	private String reportName;
	private int reportID;
	private String query;
	private OrderOnQueueReportControl orderReport;
	private ScrapReportControl scrapReport;
	private List<String> reports;
	private Object selectedReport;
	
	public GenerateReport(){
	}
	
	public void getReport(int x){
		reportID=x;

		switch(reportID){
			case 1:report=new ProductReport(); break;
			case 2:scrapReport=new ScrapReportControl(); break;
			case 3:report=new InventoryReport(); break;
			case 4:orderReport=new OrderOnQueueReportControl(); break;
		}
	}
	
	public OrderOnQueueReportControl returnReport(){
		return orderReport;
	}
	
	public void runReport(){
	}
	
	public void queryDatabase(String reportName, String query){
		reportOutput();
	}
	
	public void reportOutput(){
		System.out.println("Result set from database");
	}
	
	public List<String> getChoices(){
		reports=new ArrayList<String>();
		reports.add("1: Product Report");
		reports.add("2: Scrap Report");
		reports.add("3: Inventory Report");
		reports.add("4: Order Report");
		return reports;
	}
}
