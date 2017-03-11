package report;

public class InventoryReport extends Report {

	public InventoryReport(){
		this.reportName="Inventory Report";
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
