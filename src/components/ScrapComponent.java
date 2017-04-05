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

import control.Scrap;

public class ScrapComponent extends Component {
	private JPanel scrapPanel;
	private PortalComponent portalComponent;
	private Scrap scrap;
	private DefaultTableModel aModel;
	private int orderID;
	private String shipID;
	
	public ScrapComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
	}
	public JPanel getPanel()
	{
		return scrapPanel;
	}
	public void createScrapPanel()
	{
		scrapPanel = new JPanel(new GridLayout(7, 1));
		
		JButton scrapButton = createButton("Scrap Order");
		
		scrap=new Scrap();
		List<String> scrapOrders=scrap.orderColumnsToTable();
		List<List<String>> ordersToSelect=scrap.ordervaluesToTable();
		Object[][] columnValues = new Object[ordersToSelect.size()][3];
		Object[] columnTitles = scrapOrders.toArray();
		for(int counter = 0; counter < ordersToSelect.size(); counter++)
			columnValues[counter] = new Object[]{ordersToSelect.get(counter).get(0),ordersToSelect.get(counter).get(1), ordersToSelect.get(counter).get(2), false};
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
                    	return Integer.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };		
		JScrollPane aScrollPane = new JScrollPane(orders);
		orders.setFillsViewportHeight(true);
		scrapPanel.add(createLabel("Please select an order to scrap"));
		scrapPanel.add(aScrollPane);
		
		scrapButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  onClick();
			  } 
			} );
		
		scrapPanel.add(scrapButton);
	}
	
	public void onClick(){
		//orderID=3;
		for(int counter = 0; counter < aModel.getRowCount(); counter++)
		{
			if(aModel.getValueAt(counter, 3).equals(true)){
				orderID=Integer.parseInt(aModel.getValueAt(counter, 0).toString());
				shipID=aModel.getValueAt(counter, 2).toString();
				scrap.scrapChain(orderID, shipID);
				JOptionPane.showMessageDialog(null, "Order "+ orderID+" has been scrapped!");
			}
		}
	}
}


