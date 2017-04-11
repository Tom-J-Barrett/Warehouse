package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.OrderOnQueueReportControl;
import control.Ship;
import report.OrdersOnQueue;
import report.Report;

public class OrderReportComponent extends Component {
	private JPanel orderReportPanel;
	private PortalComponent portalComponent;
	private DefaultTableModel aModel;
	
	public OrderReportComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
	}
	
	public JPanel getPanel()
	{
		return orderReportPanel;
	}
	public void createOrderReportPanel()
	{
		orderReportPanel = new JPanel(new GridLayout(7, 1));
		OrderOnQueueReportControl rep=new OrderOnQueueReportControl();
		Report report= rep.getReport();
		report.generateTableValues();
		List<List<String>> reportValues= report.getTable();
		List<String> titles=new ArrayList<String>();
		for(int i=0; i<reportValues.get(0).size();i++){
			titles.add(reportValues.get(0).get(i));
		}
		Object[][] columnValues = new Object[reportValues.size()][3];
		Object[] columnTitles = titles.toArray();
		//for(int counter = 0; counter < ordersToSelect.size(); counter++)
		//	columnValues[counter] = new Object[]{ordersToSelect.get(counter).get(0), ordersToSelect.get(counter).get(2), false};
		aModel = new DefaultTableModel(columnValues, columnTitles);
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
                        return Integer.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };	
        JScrollPane aScrollPane = new JScrollPane(orders);
		orders.setFillsViewportHeight(true);
		orderReportPanel.add(aScrollPane);
	}
}
