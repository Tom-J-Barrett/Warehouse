package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ReceiveInventory;
import inventory.Inventory;
import inventory.Item;
import location.Location;
import location.LocationFactory;

public class ReceiveComponent extends Component{
	
	private JPanel receivePanel;
	private PortalComponent portalComponent;
	private DefaultTableModel aModel;
	private DefaultTableModel bModel;
	private ReceiveInventory receive;
	private int itemID;

	
	public ReceiveComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
	}
	public JPanel getPanel()
	{
		return receivePanel;
	}
	public void createReceivePanel()
	{
		receivePanel = new JPanel(new GridLayout(7, 1));
		
		JButton receiveButton = createButton("Receive Item");
		receive=new ReceiveInventory();
		List<String> itemTableColumns=receive.getItemColumnsForTable();
		List<List<String>> itemsToSelect=receive.getItems();
		List<String> locationTableColumns=receive.getLocationColumnsForTable();
		List<List<String>> locationToSelect=receive.getLocations();
		
		Object[][] itemColumnValues = new Object[itemsToSelect.size()][3];
		Object[][] locationColumnValues = new Object[locationToSelect.size()][3];
		Object[] itemColumnTitles = itemTableColumns.toArray();
		Object[] locationColumnTitles = locationTableColumns.toArray();
		for(int counter = 0; counter < itemsToSelect.size(); counter++)
			itemColumnValues[counter] = new Object[]{itemsToSelect.get(counter).get(0), itemsToSelect.get(counter).get(1), false};
		for(int counter = 0; counter < locationToSelect.size(); counter++)
			locationColumnValues[counter] = new Object[]{locationToSelect.get(counter).get(0), locationToSelect.get(counter).get(1), false};
		aModel = new DefaultTableModel(itemColumnValues, itemColumnTitles);
		bModel = new DefaultTableModel(locationColumnValues, locationColumnTitles);
		JTable items = new JTable(aModel) 
		{
			private static final long serialVersionUID = 1L;
			public Class<?> getColumnClass(int column) 
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
        JTable locations = new JTable(bModel) 
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
        
		JScrollPane aScrollPane = new JScrollPane(items);
		JScrollPane bScrollPane = new JScrollPane(locations);
		items.setFillsViewportHeight(true);
		locations.setFillsViewportHeight(true);
		receivePanel.add(createLabel("Please select item and location"));
		receivePanel.add(aScrollPane);
		receivePanel.add(bScrollPane);
		
		receiveButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  onClick();
			  } 
			} );
		
		receivePanel.add(receiveButton);
	}
	
	public void onClick(){
		itemID = 0;
		for(int counter = 0; counter < aModel.getRowCount(); counter++)
		{
			if(aModel.getValueAt(counter, 2).equals(true)){
				itemID=Integer.parseInt(aModel.getValueAt(counter, 0).toString());
				Item item = new Item("Yup", itemID);
				Location location = LocationFactory.getLocation(1, "Yup");
				Inventory inventory = new Inventory(item, location);
				receive.addToInventory(inventory, 1);
				JOptionPane.showMessageDialog(null, "Item "+ itemID+" has been added!");
			}
		}
	}
}
