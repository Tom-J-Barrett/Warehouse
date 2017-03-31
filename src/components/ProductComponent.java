package components;
import javax.swing.*;
import javax.swing.table.*;
import Database.*;
import inventory.*;
import java.awt.GridLayout;
import java.util.*;
public class ProductComponent extends Component
{
	private JPanel productPanel;
	private Database database;
	private PortalComponent portalComponent;
	public ProductComponent(PortalComponent portalComponent)
	{
		this.portalComponent = portalComponent;
		database = new Database();
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
		List<String> itemColumnTitles = getItemColumnTitles();
		itemColumnTitles.add("Item Selected");
		Object[] columnTitles = itemColumnTitles.toArray();
		List<Item> availableItems = retrieveAvailableItems();
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
			if(checkForProductAttribute(productID.getText(), "ProductID").size() > 0)
				JOptionPane.showMessageDialog(null, "A Product With The ID  " + productID.getText() + " Already Exists");
			else if(checkForProductAttribute(productTitle.getText(), "ProductName").size() > 0)
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
				Product aProduct = new Product(selectedItems, productTitle.getText(), Integer.parseInt(productID.getText()));
				insertNewProduct(aProduct);
				productPanel = new JPanel(new GridLayout(1, 1));
				productPanel.add(createProductsTable());
				portalComponent.updateComponent(productPanel);
			}
		});
		productPanel.add(saveProductButton);
	}
	private JScrollPane createProductsTable()
	{
		JTable productsTable = createTable();
		DefaultTableModel productsModel = (DefaultTableModel)productsTable.getModel();
		getProductTitles().forEach(x -> productsModel.addColumn(x));
		getProducts().forEach(x -> productsModel.addRow(x.toArray()));
		JScrollPane aScrollPane = new JScrollPane(productsTable);
		productsTable.setFillsViewportHeight(true);
		return aScrollPane;
	}
	private List<Item> retrieveAvailableItems()
	{
		List<List<String>> availableItems = database.getTableRows("item", new HashMap<String, String>(), new ArrayList<String>(), "");
		List<Item> selectedItems = new ArrayList<>();
		for(List<String> anAvailableItem : availableItems)
			selectedItems.add(new Item(anAvailableItem.get(1), Integer.parseInt(anAvailableItem.get(0))));
		return selectedItems;
	}
	private List<String> getItemColumnTitles()
	{
		return database.getColumnTitles("item");
	}
	private List<List<String>> checkForProductAttribute(String productValue, String productAttribute)
	{
		HashMap<String, String> selectedParameters = new HashMap<String, String>();
		selectedParameters.put(productAttribute, productValue);
		return database.getTableRows("product", selectedParameters, new ArrayList<String>(), "");
	}
	private void insertNewProduct(Product aNewProduct)
	{
		database.insertTableRow("product", new ArrayList<String>(Arrays.asList(aNewProduct.getProductID() + "", aNewProduct.getProductName())));
		for(Item anItem : aNewProduct.getItems())
			database.insertTableRow("productitems", new ArrayList<String>(Arrays.asList(aNewProduct.getProductID() + "", anItem.getItemID() + "")));
	}
	private List<List<String>> getProducts()
	{
		return database.getJoinedTableRows(new ArrayList<String>(Arrays.asList("productitems", "product", "item")), 
		new ArrayList<String>(Arrays.asList("productitems.product", "product.ProductID", "productitems.item", "item.ItemID")), 
		new HashMap<>(), new ArrayList<String>(Arrays.asList("product.ProductID", "product.ProductName", "item.ItemID", "item.ItemName")), "product.ProductID");
	}
	private List<String> getProductTitles()
	{
		return new ArrayList<String>(Arrays.asList("Product ID", "Product Name", "Item ID", "Item Name"));
	}
}
