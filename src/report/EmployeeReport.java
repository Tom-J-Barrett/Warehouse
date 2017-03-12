package report;

public class EmployeeReport extends Report{

	public EmployeeReport(){
		this.reportName="Employee Report";
		System.out.println("Employee Information");
	}
	
	@Override
	public String generateReport(){
		return "query";
	}
}
