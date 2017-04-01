package components;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Database.Database;
import Database.Utilities;
import control.GenerateReport;
import control.LogIn;
import inventory.Item;
import inventory.Product;

public class ReportComponent extends Component {
	private JPanel reportPanel;
	private Database database;
	private PortalComponent portalComponent;
	private GenerateReport genReport;
	private List<String> reports;
	private Object selectedReport;
	
	public ReportComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
		database = new Database();
	}
	public JPanel getPanel()
	{
		return reportPanel;
	}
	public void createReportPanel()
	{
		reportPanel = new JPanel(new GridLayout(1, 1));
		//reportPanel.add(createProductsTable());
		portalComponent.updateComponent(reportPanel);
	}
	public void createSelectReportPanel()
	{
		reportPanel = new JPanel(new GridLayout(7, 1));
		genReport=new GenerateReport();
		reports=genReport.getChoices();
		for(String choice: reports){
			reportPanel.add(createLabel(choice));
		}
		reportPanel.add(createLabel("Please enter the report ID"));
		JTextField reportID = createTextField("");
		reportPanel.add(reportID);
		JButton runReportButton = createButton("Run Report");
		runReportButton.addActionListener(x -> 
		{
			int id=Integer.parseInt(reportID.getText());
			String report=genReport.returnReport().returnReport();	
			reportPanel.add(createLabel(report));
			
			portalComponent.updateComponent(reportPanel);
		});
		reportPanel.add(runReportButton);
	}
}
