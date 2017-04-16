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
	private int locationID;

	
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
		locationID=0;
		Location location = LocationFactory.getLocation(0, "");
		Item item = new Item();
		for(int counter = 0; counter < aModel.getRowCount(); counter++)
		{
			if(aModel.getValueAt(counter, 2).equals(true)){
				itemID=(Integer.parseInt(aModel.getValueAt(counter, 0).toString()));
				String itemName = aModel.getValueAt(counter, 1).toString();
				item = new Item(itemName, itemID);
			}
		}
		for(int i = 0; i < bModel.getRowCount(); i++)
		{
			if(bModel.getValueAt(i, 2).equals(true)){
				locationID=(Integer.parseInt(bModel.getValueAt(i, 0).toString()));
				String locationName = bModel.getValueAt(i, 1).toString();
				location = LocationFactory.getLocation(locationID, locationName);
				int yup = LocationFactory.getLocation(locationID, locationName).getLocationID();
				JOptionPane.showMessageDialog(null, "Item added to inventory!");
			}
		}
		Inventory inventory = new Inventory(item, location);
		receive.addToInventory(inventory, 1);
	}
}
