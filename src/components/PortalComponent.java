package components;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import org.pushingpixels.flamingo.api.common.*;
import org.pushingpixels.flamingo.api.ribbon.*;
import employee.*;
public class PortalComponent extends Component
{
	private JRibbonFrame portalFrame;
	private PortalFrame aPortalFrame;
	private Employee anEmployee;
	private MessageObservable observable;
	private RibbonTask userActionsTask;
	private JRibbonBand userActionsBand;
	public PortalComponent(PortalFrame aPortalFrame, Employee anEmployee)
	{
		this.aPortalFrame = aPortalFrame;
		this.anEmployee = anEmployee;
		this.observable = new MessageObservable();
		observable.addObserver(aPortalFrame);
		portalFrame = createRibbonFrame(anEmployee.getFirstname()+" "+anEmployee.getSurname());
		this.aPortalFrame.setRibbonFrame(portalFrame);
		SwingUtilities.invokeLater(() -> createPortal());
	}
	public void updateComponent(JPanel aComponent)
	{
		observable.changeData(aComponent);
	}
	private void createPortal()
	{
		userActionsBand = createRibbonBand("User Actions");
		LinkedHashMap<Integer, List<String>> menuItems = anEmployee.menu();
		JCommandButton aButton = createCommandButton("");
		userActionsBand.addCommandButton(aButton, RibbonElementPriority.TOP);
		for(Map.Entry<Integer, List<String>> aMenuItem : menuItems.entrySet())
		{
			String buttonCaption = aMenuItem.getValue().get(0);
			buttonCaption = buttonCaption.substring(buttonCaption.indexOf('.') + 1);
			System.out.println(buttonCaption);
			switch(buttonCaption){
				case " Add New Product": createButtonAddNewProduct();break;
				case " Delete/Scrap Units":createButtonScrap() ;break;
				case " Report": createButtonReport();break;
				case " Receive Inventory": createButtonReceive();break;
				case " Ship Order": createButtonShip();break;
			}
		}
		userActionsTask = createRibbonTask("User Actions", new JRibbonBand[]{userActionsBand});
		portalFrame.getRibbon().addTask(userActionsTask);
		//portalFrame.getRibbon().addTask(manageProductsTask);
		RibbonApplicationMenu aRibbonApplicationMenu = new RibbonApplicationMenu();
		aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Log Out", x -> 
		{
			new LogOnComponent();
			portalFrame.dispose();
		}));
		aRibbonApplicationMenu.addFooterEntry(createFooterApplicationMenuEntry("Exit", x -> {System.exit(0);}));
		portalFrame.getRibbon().setApplicationMenu(aRibbonApplicationMenu);
		portalFrame.setVisible(true);
	}
	
	public void createButtonAddNewProduct(){
		System.out.println("New product?");
		JCommandButton addNewProductCommandButton = createCommandButton("Add New Product");
		addNewProductCommandButton.addActionListener(x ->
		{
			ProductComponent addNewProductComponent = new ProductComponent(this);
			addNewProductComponent.createAddProductPanel();
			observable.changeData(addNewProductComponent.getPanel());
		});
		userActionsBand.addCommandButton(addNewProductCommandButton, RibbonElementPriority.TOP);
	}
	
	public void createButtonScrap(){
		JCommandButton scrapCommandButton = createCommandButton("Scrap");
		scrapCommandButton.addActionListener(x ->
		{
			ScrapComponent scrapComponent = new ScrapComponent(this);
			scrapComponent.createScrapPanel();
			observable.changeData(scrapComponent.getPanel());
		});
		userActionsBand.addCommandButton(scrapCommandButton, RibbonElementPriority.TOP);	
	}
	
	public void createButtonReport(){
		JCommandButton runReportCommandButton = createCommandButton("Run Report");
		runReportCommandButton.addActionListener(x ->
		{
			ReportComponent runReportComponent = new ReportComponent(this);
			runReportComponent.createSelectReportPanel();
			observable.changeData(runReportComponent.getPanel());
		});
		userActionsBand.addCommandButton(runReportCommandButton, RibbonElementPriority.TOP);
	}
	
	public void createButtonReceive(){
		JCommandButton receiveCommandButton = createCommandButton("Receive Inventory");
		receiveCommandButton.addActionListener(x ->
		{
			ReceiveComponent receive = new ReceiveComponent(this);
			receive.createReceivePanel();
			observable.changeData(receive.getPanel());
		});
		userActionsBand.addCommandButton(receiveCommandButton, RibbonElementPriority.TOP);
	}
	
	public void createButtonShip(){
		JCommandButton shipCommandButton = createCommandButton("Ship Order");
		shipCommandButton.addActionListener(x ->
		{
			ShipComponent ship = new ShipComponent(this);
			ship.createShipPanel();
			observable.changeData(ship.getPanel());
		});
		userActionsBand.addCommandButton(shipCommandButton, RibbonElementPriority.TOP);
	}
}
