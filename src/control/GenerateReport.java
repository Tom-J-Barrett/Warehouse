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
	
	public GenerateReport(){
		getReportFromUser();
	}
	
	public void getReportFromUser(){
		Scanner in=new Scanner(System.in);
		System.out.println("What report would you like to run?: ");
		System.out.println("1.Product Report");
		System.out.println("2.Scrap Report");
		System.out.println("3.Inventory Report");
		System.out.println("4.Orders to be processed Report");
		reportID=Integer.parseInt(in.nextLine());
		
		switch(reportID){
			case 1:report=new ProductReport(); break;
			case 2:scrapReport=new ScrapReportControl(); break;
			case 3:report=new InventoryReport(); break;
			case 4:orderReport=new OrderOnQueueReportControl(); break;
		}
		in.close();
	}
	
	public void runReport(){
		//reportName=report.getName();
		//query=report.generateReport();
		//queryDatabase(reportName,query);
	}
	
	public void queryDatabase(String reportName, String query){
		reportOutput();
	}
	
	public void reportOutput(){
		System.out.println("Result set from database");
	}
}
