package report;

public class ReportFactory {
	public Report createReport(ReportBuilder b, String type){
		Report report=null;
		
		if(type=="Order Report"){
			report=new OrdersOnQueue(b);
		}
		else if(type=="Employee Report"){
			
		}
		else if(type=="Product Report"){
			
		}
		else if(type=="Inventory Report"){
			
		}
		else if(type=="Scrap Report"){
			report= new ScrapReport(b);
		}
		else{
			System.out.println("Report doesn't exist");
		}
		
		return report;
	}
}
