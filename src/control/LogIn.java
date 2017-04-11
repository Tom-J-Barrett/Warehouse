package control;
import employee.*;

import java.util.*;

import database.Database;
public class LogIn
{
    private Employee anEmployee;
    private String logOnType;
	public LogIn(String logOnType)
    {
        this.logOnType = logOnType;
		if(logOnType == "cli")
        	attemptLogOn("");
    }
	public List<String> getUserDetails()
	{
		return new ArrayList<String>(Arrays.asList(anEmployee.getFirstname(), anEmployee.getSurname(), anEmployee.getUsername(), anEmployee.getPassword()));
	}
	public Employee getEmployee()
	{
		return anEmployee;
	}
    public void attemptLogOn(String errorMessage)
    {
        if(errorMessage.length() == 0)
            System.out.println("Please enter your valid username and password");
        else
            System.out.println(errorMessage);
        System.out.println("Your Username Goes Here");
        Scanner aScanner = new Scanner(System.in);
        String username = aScanner.nextLine();
        System.out.println("Your Password Goes Here");
        String password = aScanner.nextLine();
        if(username.length() > 2 && password.length() > 2)
        {
            String logOnMessage = processLogOn(username, password);
            if(logOnMessage.length() > 0)
            	attemptLogOn(logOnMessage);
            else
            	createMenuForEmployee();
        }
        else
        {
            if(username.length() < 2)
                attemptLogOn("The username " + username + " you entered is invalid as it has less than 2 characters");
            if(password.length() < 2)
                attemptLogOn("The password " + password + " you entered is invalid as it has less than 2 characters");
        }
    }
    public String processLogOn(String username, String password)
    {
    	String errorMessage = "";
    	Database database = new Database();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("username", username);
        List<List<String>> selectedEmployeeDetails =  database.getTableRows("employee", parameters, new ArrayList<String>(), "");
        if(selectedEmployeeDetails.size() > 0)
        {
            List<String> currentEmployeeDetails = selectedEmployeeDetails.get(0);
            if(currentEmployeeDetails.get(2).equals(password))
            {
            	if(currentEmployeeDetails.get(5).equals("operator"))
            		anEmployee = new Operator(currentEmployeeDetails.get(1),currentEmployeeDetails.get(2),currentEmployeeDetails.get(3),
            				currentEmployeeDetails.get(4));
            	else if(currentEmployeeDetails.get(5).equals("manager"))
            		anEmployee = new Manager(currentEmployeeDetails.get(1),currentEmployeeDetails.get(2),currentEmployeeDetails.get(3),
            				currentEmployeeDetails.get(4));
            	else if(currentEmployeeDetails.get(5).equals("warehouseworker"))
            		anEmployee = new WHWorker(currentEmployeeDetails.get(1),currentEmployeeDetails.get(2),currentEmployeeDetails.get(3),
            				currentEmployeeDetails.get(4));
            }
            else
            	errorMessage = "The password " + password + " is not a valid password for " + username;
        }
        else
            errorMessage = "The username " + username + " is not a valid username";
        return errorMessage;
    }
    private void createMenuForEmployee()
    {
    	anEmployee.menu().values().forEach(x -> System.out.println(x.get(0)));
    	System.out.println(anEmployee.menu().size() + 1 + ": Log Out");
    	System.out.println(anEmployee.menu().size() + 2 + ": Exit");
    	Scanner aScanner = new Scanner(System.in);
    	int selectedMenuOption = aScanner.nextInt();
    	if(selectedMenuOption >= 1 && selectedMenuOption <= anEmployee.menu().values().size())
    	{
    		System.out.println(anEmployee.menu().get(selectedMenuOption).get(1));
    		createMenuForEmployee();
    	}
    	else if(selectedMenuOption == anEmployee.menu().values().size() + 1)
    		new LogOut();
    	else if(selectedMenuOption == anEmployee.menu().values().size() + 2)
    		System.exit(0);
    	else
    	{
    		System.out.println("Invalid Menu Entry Selected. Please try again.");
    		createMenuForEmployee();
    	}
    }
}