package report;

import java.util.*;
import inventory.Order;
import inventory.Product;
import inventory.Item;
import inventory.Inventory;
import location.Location;
import movement.Movement;
import employee.Employee;
import report.OrdersOnQueue;

public final class ReportBuilder<T> {
	public OrdersOnQueue order;
	public List<T> orders;
	public Location location;
	public int locationID=0;
	public String locationType;
	public String reportName;
	public ReportFactory factory;
	
	public ReportBuilder(ReportFactory factory){
		this.factory=factory;
	}
	
	public ReportBuilder<T> listOfObjects(List<T> list){
		this.orders=list;
		return this;
	}

	public ReportBuilder reportName(String reportName){
		this.reportName=reportName;
		return this;
	}
	
	public ReportBuilder location(Location location){
		this.location=location;
		return this;
	}
	
	public Report build(){
		if(orders==null){
			System.out.println("No list of orders");
		}
		if(location==null){
			System.out.println("No location");
		}
		if(reportName==null){
			System.out.println("No name");
		}
		return factory.createReport(this,reportName);
	}
	
	/*public OrdersOnQueue build(){
		if(orders==null){
			System.out.println("No list of orders");
		}
		if(location==null){
			System.out.println("No location");
		}
		if(reportName==null){
			System.out.println("No name");
		}
		return new OrdersOnQueue(this);
	}*/
}
