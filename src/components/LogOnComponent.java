package components;
import java.awt.*;
import javax.swing.*;
import Database.Utilities;
import control.LogIn;
public class LogOnComponent extends Component
{
	public LogOnComponent()
	{
		SwingUtilities.invokeLater(() -> createLogOnFrame());
	}
	public void createLogOnFrame()
	{
		JFrame frame = createFrame("Log On");
		JPanel panel = new JPanel(new GridLayout(5, 1));
		panel.add(createLabel("Please enter your username"));
		JTextField usernameTextField = createTextField("");
		panel.add(usernameTextField);
		panel.add(createLabel("Please enter your password"));
		JPasswordField passwordTextField = createPasswordField("");
		panel.add(passwordTextField);
		JButton submitButton = createButton("Log On To The Portal");
		submitButton.addActionListener((x) -> 
		{
			LogIn aLogIn = new LogIn("");
			String logOnMessage = aLogIn.processLogOn(usernameTextField.getText(), Utilities.convertCharArrayToString(passwordTextField.getPassword()));
			if(logOnMessage.length() > 0)
				JOptionPane.showMessageDialog(null, logOnMessage);
			else
			{
				new PortalComponent(new PortalFrame(), aLogIn.getEmployee());
				frame.dispose();
			}
		});
		panel.add(submitButton);
		frame.add(panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
}
