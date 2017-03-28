package report;

public class ScrapReport extends Report{

	public ScrapReport(ReportBuilder b){
		System.out.println("Scrap Information");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
