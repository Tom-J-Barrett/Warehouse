package report;

public class ProductReport extends Report {

	public ProductReport(){
		this.reportName="Product Report";
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
