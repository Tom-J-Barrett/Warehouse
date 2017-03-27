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
	public PortalComponent(PortalFrame aPortalFrame, Employee anEmployee)
	{
		this.aPortalFrame = aPortalFrame;
		this.anEmployee = anEmployee;
		this.observable = new MessageObservable();
		observable.addObserver(aPortalFrame);
		portalFrame = createRibbonFrame("Portal");
		this.aPortalFrame.setRibbonFrame(portalFrame);
		SwingUtilities.invokeLater(() -> createPortal());
	}
	private void createPortal()
	{
		JRibbonBand userActionsBand = createRibbonBand("User Actions");
		LinkedHashMap<Integer, List<String>> menuItems = anEmployee.menu();
		for(Map.Entry<Integer, List<String>> aMenuItem : menuItems.entrySet())
		{
			String buttonCaption = aMenuItem.getValue().get(0);
			buttonCaption = buttonCaption.substring(buttonCaption.indexOf('.') + 1);
			JCommandButton aButton = createCommandButton(buttonCaption);
			aButton.addActionListener(x -> 
			{
				JLabel aLabel = createLabel(aMenuItem.getValue().get(1));
				JPanel aPanel = new JPanel(new GridLayout(1, 1));
				aPanel.add(aLabel);
				observable.changeData(aPanel);
			});
			userActionsBand.addCommandButton(aButton, RibbonElementPriority.TOP);
		}
		RibbonTask aTask = createRibbonTask("User Actions", new JRibbonBand[]{userActionsBand});
		portalFrame.getRibbon().addTask(aTask);
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
}
