package report;

public class InventoryReport extends Report {

	public InventoryReport(){
		this.reportName="Inventory Report";
		System.out.println("Inventory Information");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
