package report;

public class ProductReport implements Report{
	private String reportName;

	public ProductReport(ReportBuilder b){
		this.reportName=b.reportName;
	}
	
	public ProductReport(){

	}
	
	public void printReport(){
		System.out.println(reportName);
	}
	
	public String reportString(){
		return "";
	}
}
