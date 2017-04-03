package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.GenerateReport;
import control.Ship;

public class ShipComponent extends Component {
	private JPanel shipPanel;
	private PortalComponent portalComponent;
	private Ship ship;
	private DefaultTableModel aModel;
	private int orderID;
	
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
		ship=new Ship();
		List<String> shipOrders=ship.orderColumnsToTable();
		List<List<String>> ordersToSelect=ship.ordervaluesToTable();
		Object[][] columnValues = new Object[ordersToSelect.size()][3];
		Object[] columnTitles = shipOrders.toArray();
		for(int counter = 0; counter < ordersToSelect.size(); counter++)
			columnValues[counter] = new Object[]{ordersToSelect.get(counter).get(0), ordersToSelect.get(counter).get(2), false};
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
		orderID=0;
		for(int counter = 0; counter < aModel.getRowCount(); counter++)
		{
			if(aModel.getValueAt(counter, 2).equals(true)){
				orderID=Integer.parseInt(aModel.getValueAt(counter, 0).toString());
				ship.shipChain(orderID);
				JOptionPane.showMessageDialog(null, "Order "+ orderID+" has been shipped!");
			}
		}
	}
}


