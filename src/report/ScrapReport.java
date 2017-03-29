package report;

import java.util.List;

public class ScrapReport extends Report{
	private List<List<String>> scrap;
	private String reportName;
	
	public ScrapReport(ReportBuilder b){
		this.reportName=b.reportName;
		this.scrap=b.list;
	}
	
	public void printReport(){
		System.out.println(reportName);
		for(int i=0;i<scrap.size();i++){
			System.out.println("Order ID: "+scrap.get(i).get(1)+"	" +scrap.get(i).get(2));
		}
	}
}
