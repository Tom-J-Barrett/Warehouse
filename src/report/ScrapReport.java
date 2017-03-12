package report;

public class ScrapReport extends Report{

	public ScrapReport(){
		this.reportName="Scrap Report";
		System.out.println("Scrap Information");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
