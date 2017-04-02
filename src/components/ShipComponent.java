package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.GenerateReport;
import control.OrderOnQueueReportControl;
import control.Ship;
import inventory.Item;

public class ShipComponent extends Component {
	private JPanel shipPanel;
	private PortalComponent portalComponent;
	private GenerateReport genReport;
	private List<String> reports;
	private Object selectedReport;
	private Ship ship;
	
	public ShipComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
	}
	public JPanel getPanel()
	{
		return shipPanel;
	}
	public void createShipPanel()
	{
		shipPanel = new JPanel(new GridLayout(7, 1));
		
		JButton shipButton = createButton("Ship Order");
		Ship ship=new Ship();
		List<String> shipOrders=ship.orderColumnsToTable();
		List<List<String>> ordersToSelect=ship.ordervaluesToTable();
		Object[][] columnValues = new Object[ordersToSelect.size()][ordersToSelect.get(0).size()];
		Object[] columnTitles = new Object[shipOrders.size()];
		for(int i=0; i<columnTitles.length;i++){
			columnTitles[i]=shipOrders.get(i);
		}
		for(int j=0;j<columnValues.length;j++){
			for(int c=0;c<columnValues[0].length;c++){
				columnValues[j][c]=ordersToSelect.get(j).get(c);
			}
		}
		DefaultTableModel aModel = new DefaultTableModel(columnValues, columnTitles);
		JTable orders = new JTable(aModel) 
		{
			private static final long serialVersionUID = 1L;
			public Class getColumnClass(int column) 
            {
                switch (column) 
                {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };		
		JScrollPane aScrollPane = new JScrollPane(orders);
		orders.setFillsViewportHeight(true);
		shipPanel.add(createLabel("Please select order to ship"));
		shipPanel.add(aScrollPane);
		
		
		shipButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  onClick();
			  } 
			} );
		shipPanel.add(shipButton);
	}
	
	public void onClick(){
		/*int id=Integer.parseInt(reportID.getText());
		genReport.getReport(id);
		OrderOnQueueReportControl rep=genReport.returnReport();
		String report=rep.returnReport();	
		//rep.returnStr();
		
		System.out.println(report);
		reportPanel = new JPanel(new GridLayout(1, 1));
		reportPanel.add(createLabel(report));
		portalComponent.updateComponent(reportPanel);*/
	}
}


