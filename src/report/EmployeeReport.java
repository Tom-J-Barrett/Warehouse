package report;

public class EmployeeReport extends Report{

	public EmployeeReport(){
		this.reportName="Employee Report";
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
