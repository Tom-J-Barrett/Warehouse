package control;
import employee.Employee;
import inventory.Inventory;
import inventory.Item;
import report.Report;
import report.ReportBuilder;
import report.ReportFactory;

import java.util.*;

import database.Database;

public class ScrapReportControl {
	
	private Database db;
	private List<List<String>> listOfScrap;
	private HashMap<String, String> selectedParameters;
	private ArrayList<String> columnTitles;
	private String columnTitleForSorting;
	private ReportFactory factory;
	private Report report;
	
	ScrapReportControl(){
		factory=new ReportFactory();
		db=new Database();
		getScrap();
		
		String name="Scrap Report";
		report=new ReportBuilder(factory).
				listOfObjects(listOfScrap).
				reportName(name).
				build();
		report.printReport();
	}
	
	public void getScrap(){
		columnTitles=new ArrayList<String>();
		columnTitles.add("scraplogID");
		columnTitles.add("orderID");
		columnTitles.add("time");
		
		columnTitleForSorting="scraplogID";
		
		selectedParameters=new HashMap<String, String>();
		selectedParameters.put("1","1");
		getScrapFromDatabase();
	}
	
	public void getScrapFromDatabase(){
		listOfScrap=db.getTableRows("scraplog", selectedParameters, columnTitles, columnTitleForSorting);
	}
}
