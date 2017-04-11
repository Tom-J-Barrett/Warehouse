package report;

import java.util.List;

public interface Report {
	public void printReport();
	public String reportString();
	public void generateTableValues();
	public List<List<String>> getTable();
}
