package Control;
import java.util.*;

import report.EmployeeReport;
import report.InventoryReport;
import report.ProductReport;
import report.Report;
import report.ScrapReport;

public class GenerateReport {
	private Report report;
	private String reportName;
	private int reportID;
	private String query;
	
	public GenerateReport(){
		getReportFromUser();
		runReport();
	}
	
	public void getReportFromUser(){
		Scanner in=new Scanner(System.in);
		System.out.println("What report would you like to run?: ");
		System.out.println("1.Employee Report");
		System.out.println("2.Product Report");
		System.out.println("3.Scrap Report");
		System.out.println("4.Inventory Report");
		reportID=Integer.parseInt(in.nextLine());
		in.close();
		
		switch(reportID){
			case 1:report=new EmployeeReport(); break;
			case 2:report=new ProductReport(); break;
			case 3:report=new ScrapReport(); break;
			case 4:report=new InventoryReport(); break;
		}
	}
	
	public void runReport(){
		reportName=report.getName();
		query=report.generateReport();
		queryDatabase(reportName,query);
	}
	
	public void queryDatabase(String reportName, String query){
		
	}
}
