package components;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import org.pushingpixels.flamingo.api.common.*;
import org.pushingpixels.flamingo.api.common.icon.*;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.api.ribbon.resize.*;
public class Component 
{
	private Font font;
    public Component()
    {
    	font = new Font("Segoe UI", Font.BOLD, 30);
    }
    protected JFrame createFrame(String title)
    {
        JFrame aFrame = new JFrame();
        aFrame.setTitle(title);
        aFrame.pack();
        aFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        aFrame.setFocusable(true);
        return aFrame;
    }
    protected JRibbonFrame createRibbonFrame(String title)
    {
        JRibbonFrame aRibbonFrame = new JRibbonFrame();
        aRibbonFrame.setTitle(title);
        aRibbonFrame.pack();
        aRibbonFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        aRibbonFrame.setFocusable(true);
        return aRibbonFrame;
    }
    protected JScrollPane createScrollPane()
    {
        JScrollPane aScrollPane = new JScrollPane();
        aScrollPane.setFont(font);
        aScrollPane.setVisible(true);
        return aScrollPane;
    }
    protected JProgressBar createProgressBar(int minimum, int maximum)
    {
        JProgressBar aProgressBar = new JProgressBar();
        aProgressBar.setFont(font);
        aProgressBar.setMinimum(minimum);
        aProgressBar.setMaximum(maximum);
        return aProgressBar;
    }
    protected JProgressBar createProgressBar(int minimum, int maximum, int currentValue)
    {
        JProgressBar aProgressBar = createProgressBar(minimum, maximum);
        aProgressBar.setValue(currentValue);
        return aProgressBar;
    }
    protected JTable createTable()
    {
        JTable aTable = new JTable();
        aTable.setFont(font);
        aTable.setCellSelectionEnabled(true);
        aTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return aTable;
    }
    protected JTextField createDisabledTextField(String text)
    {
        JTextField aTextField = createTextField(text);
        aTextField.setEditable(false);
        return aTextField;
    }
    protected JTextField createTextField(String text)
    {
        JTextField aTextField = new JTextField();
        aTextField.setText(text);
        aTextField.setFont(font);
        return aTextField;
    }
    protected JPasswordField createPasswordField(String text)
    {
        JPasswordField aPasswordField = new JPasswordField();
        aPasswordField.setText(text);
        aPasswordField.setFont(font);
        return aPasswordField;
    }
    protected JLabel createLabel(String text)
    {
        JLabel aLabel = new JLabel();
        aLabel.setHorizontalTextPosition(JLabel.CENTER);
        aLabel.setVerticalTextPosition(JLabel.CENTER);
        aLabel.setText(text);
        aLabel.setFont(font);
        return aLabel;
    }
    protected JButton createButton(String text)
    {
        JButton aButton = new JButton();
        aButton.setFont(font);
        aButton.setText(text);
        return aButton;
    }
    protected JComboBox createDropDown(ArrayList<String> items)
    {
        JComboBox aDropDown = new JComboBox();
        aDropDown.setFont(font);
        items.forEach(x -> aDropDown.addItem(x));
        return aDropDown;
    }
    protected JButton createTile(String caption, String imagePath, int totalNumberOfTiles)
    {
        JButton aTile = new JButton();
        aTile.setHorizontalTextPosition(JButton.CENTER);
        aTile.setVerticalTextPosition(JButton.CENTER);
        aTile.setFont(font);
        aTile.setText(caption);
        return aTile;
    }
    protected ResizableIcon getIconFromPhoto(String resource)
    {
        return ImageWrapperResizableIcon.getIcon(JRibbonFrame.class.getClassLoader().getResource(resource), new Dimension(48, 48));
    }
    protected RibbonTask createRibbonTask(String title, JRibbonBand[] bands)
    {
        return new RibbonTask(title, bands);
    }
    protected JRibbonBand createRibbonBand(String title)
    {
        JRibbonBand aRibbonBand = new JRibbonBand(title, new EmptyResizableIcon(16));
        aRibbonBand.setResizePolicies(Arrays.asList(new CoreRibbonResizePolicies.None(aRibbonBand.getControlPanel()),
        new IconRibbonBandResizePolicy(aRibbonBand.getControlPanel())));
        return aRibbonBand;
    }
    protected JCommandToggleButton createCommandToggleButton(String title)
    {
        return new JCommandToggleButton(title, new EmptyResizableIcon(16));
    }
    protected JCommandButton createCommandButton(String title)
    {
        return new JCommandButton(title, new EmptyResizableIcon(16));
    }
    protected RibbonApplicationMenu createApplicationMenu()
    {
        RibbonApplicationMenu ribbonMainMenu = new RibbonApplicationMenu();
        return ribbonMainMenu;
    }
    protected RibbonApplicationMenuEntryPrimary createApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntryPrimary(new EmptyResizableIcon(16), menuTitle, aListener, JCommandButton.CommandButtonKind.ACTION_ONLY);
    }
    protected RibbonApplicationMenuEntrySecondary createMinorApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntrySecondary(new EmptyResizableIcon(16), menuTitle, aListener, JCommandButton.CommandButtonKind.ACTION_ONLY);
    }
    protected RibbonApplicationMenuEntryFooter createFooterApplicationMenuEntry(String menuTitle, ActionListener aListener)
    {
        return new RibbonApplicationMenuEntryFooter(new EmptyResizableIcon(16), menuTitle, aListener);
    }
}
