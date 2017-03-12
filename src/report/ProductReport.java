package report;

public class ProductReport extends Report {

	public ProductReport(){
		this.reportName="Product Report";
		System.out.println("Product Information");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
