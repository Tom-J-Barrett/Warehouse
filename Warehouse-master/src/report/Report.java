package report;

public class Report implements ReportInterface {
	protected String reportName;
	
	Report(){
		
	}
	
	@Override
	public String generateReport(){
		return reportName;
	}
	
	public String getName(){
		return reportName;
	}

}
