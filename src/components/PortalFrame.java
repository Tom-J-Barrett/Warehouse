package components;

import java.util.*;
import javax.swing.*;
import org.pushingpixels.flamingo.api.ribbon.*;
public class PortalFrame implements Observer
{
	private JRibbonFrame portalFrame;
	private JPanel existingPanel;
	public PortalFrame()
	{
		
	}
	public void setRibbonFrame(JRibbonFrame aRibbonFrame)
	{
		this.portalFrame = aRibbonFrame;
		portalFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		portalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		portalFrame.setVisible(true);
	}
	@Override
	public void update(Observable anObservable, Object aPanel) 
	{
		if(existingPanel != null)
			portalFrame.remove(existingPanel);
		existingPanel = (JPanel)aPanel;
		portalFrame.add(existingPanel);
		portalFrame.invalidate();
		portalFrame.revalidate();
	}
}