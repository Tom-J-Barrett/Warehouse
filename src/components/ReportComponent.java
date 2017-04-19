package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import control.GenerateReport;
import control.LogIn;
import control.OrderOnQueueReportControl;
import inventory.Item;
import inventory.Product;
import report.Report;

public class ReportComponent extends Component {
	private JPanel reportPanel;
	private PortalComponent portalComponent;
	private GenerateReport genReport;
	private List<String> reports;
	private MessageObservable observable;
	
	public ReportComponent(PortalComponent portalComponent,MessageObservable observable)
	{
		this.portalComponent = portalComponent;
		this.observable=observable;
	}
	public JPanel getPanel()
	{
		return reportPanel;
	}
	public void createReportPanel()
	{
		reportPanel = new JPanel(new GridLayout(1, 1));
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
		runReportButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  onClick(reportID);
			  } 
			} );
		reportPanel.add(runReportButton);
	}
	
	public void onClick(JTextField reportID){
		int id=Integer.parseInt(reportID.getText());
		if(id==4){
			OrderReportComponent comp=new OrderReportComponent(portalComponent);
			comp.createOrderReportPanel();
			observable.changeData(comp.getPanel());
		}
		
	}
}
