package components;
import javax.swing.*;
import javax.swing.table.*;

import control.*;
import database.*;
import inventory.*;
import java.awt.GridLayout;
import java.util.*;
public class ProductComponent extends Component
{
	private JPanel productPanel;
	private PortalComponent portalComponent;
	private AddNewProduct addNewProduct;
	public ProductComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
		this.addNewProduct = new AddNewProduct(new Database(), "gui");
	}
	public JPanel getPanel()
	{
		return productPanel;
	}
	public void createProductsPanel()
	{
		productPanel = new JPanel(new GridLayout(1, 1));
		productPanel.add(createProductsTable());
		portalComponent.updateComponent(productPanel);
	}
	public void createAddProductPanel()
	{
		productPanel = new JPanel(new GridLayout(7, 1));
		productPanel.add(createLabel("Please enter the product ID"));
		JTextField productID = createTextField("");
		productPanel.add(productID);
		productPanel.add(createLabel("Please enter the product title"));
		JTextField productTitle = createTextField("");
		productPanel.add(productTitle);
		List<String> itemColumnTitles = addNewProduct.getItemColumnTitles();
		itemColumnTitles.add("Item Selected");
		Object[] columnTitles = itemColumnTitles.toArray();
		List<Item> availableItems = addNewProduct.retrieveAvailableItems();
		Object[][] columnValues = new Object[availableItems.size()][3];
		for(int counter = 0; counter < availableItems.size(); counter++)
			columnValues[counter] = new Object[]{availableItems.get(counter).getItemID(), availableItems.get(counter).getItemName(), false};
		DefaultTableModel aModel = new DefaultTableModel(columnValues, columnTitles);
		JTable productItems = new JTable(aModel) 
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
		JScrollPane aScrollPane = new JScrollPane(productItems);
		productItems.setFillsViewportHeight(true);
		productPanel.add(createLabel("Please select items that make up your product"));
		productPanel.add(aScrollPane);
		JButton saveProductButton = createButton("Add Your New Product");
		saveProductButton.addActionListener(x -> 
		{
			if(productID.getText().length() == 0)
				JOptionPane.showMessageDialog(null, "The Product ID Must Consist Of A Valid Positive Integer Number");
			else if(productTitle.getText().length() == 0)
				JOptionPane.showMessageDialog(null, "The Product Title Must Consist Of A Valid String");
			else if(!productID.getText().matches("\\d+"))
				JOptionPane.showMessageDialog(null, "A Valid ID Must Consist Of A Positive Integer Number");
			else if(addNewProduct.checkForProductAttribute(productID.getText(), "ProductID").size() > 0)
				JOptionPane.showMessageDialog(null, "A Product With The ID  " + productID.getText() + " Already Exists");
			else if(addNewProduct.checkForProductAttribute(productTitle.getText(), "ProductName").size() > 0)
				JOptionPane.showMessageDialog(null, "A Product With The Title " + productTitle.getText() + " Already Exists");
			else
			{
				ArrayList<Item> selectedItems = new ArrayList<>();
				for(int counter = 0; counter < aModel.getRowCount(); counter++)
				{
					if(aModel.getValueAt(counter, 2).equals(true))
						selectedItems.add(new Item(aModel.getValueAt(counter, 1).toString(), 
						Integer.parseInt(aModel.getValueAt(counter, 0).toString())));
				}
				if(selectedItems.size() == 0)
					JOptionPane.showMessageDialog(null, "You have not selected any items for this product");
				else
				{
					Product aProduct = new Product(selectedItems, productTitle.getText(), Integer.parseInt(productID.getText()));
					addNewProduct.insertNewProduct(aProduct);
					productPanel = new JPanel(new GridLayout(1, 1));
					productPanel.add(createProductsTable());
					portalComponent.updateComponent(productPanel);
				}
			}
		});
		productPanel.add(saveProductButton);
	}
	private JScrollPane createProductsTable()
	{
		JTable productsTable = createTable();
		DefaultTableModel productsModel = (DefaultTableModel)productsTable.getModel();
		addNewProduct.getProductTitles().forEach(x -> productsModel.addColumn(x));
		addNewProduct.getProducts().forEach(x -> productsModel.addRow(x.toArray()));
		JScrollPane aScrollPane = new JScrollPane(productsTable);
		productsTable.setFillsViewportHeight(true);
		return aScrollPane;
	}
}
