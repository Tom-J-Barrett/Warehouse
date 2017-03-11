package report;

public class ScrapReport extends Report{

	public ScrapReport(){
		this.reportName="Scrap Report";
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
