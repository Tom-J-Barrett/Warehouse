package control;
import employee.*;
import java.util.Scanner;
public class LogIn
{
    public LogIn()
    {
        attemptLogOn("");
    }
    public void attemptLogOn(String errorMessage)
    {
        if(errorMessage.length() == 0)
            System.out.println("Please enter your username and password");
        else
            System.out.println(errorMessage);
        System.out.println("Your Username Goes Here");
        Scanner aScanner = new Scanner(System.in);
        String username = aScanner.nextLine();
        System.out.println("Your Password Goes Here");
        String password = aScanner.nextLine();
        if(username.length() > 2 && password.length() > 2)
        {
            if(username.startsWith("o") || username.startsWith("O"))
                createMenuForOperator(username, password);
            else if(username.startsWith("w") || username.startsWith("W"))
                createMenuForWarehouseWorker(username, password);
            else if(username.startsWith("m") || username.startsWith("M"))
                createMenuForManager(username, password);
            else
                attemptLogOn("The username " + username + " should begin with a letter representing the type of the user");
        }
        else
        {
            if(username.length() < 2)
                attemptLogOn("The username " + username + " you entered is invalid as it has less than 2 characters");
            if(password.length() < 2)
                attemptLogOn("The password " + password + " you entered is invalid as it has less than 2 characters");
        }
    }
    private void createMenuForOperator(String username, String password)
    {
        Operator anOperator = new Operator();
        anOperator.setUsername(username);
        anOperator.setPassword(password);
        anOperator.menu().values().forEach(x -> System.out.println(x.get(0)));
    	Scanner aScanner = new Scanner(System.in);
    	int selectedMenuOption = aScanner.nextInt();
        if(selectedMenuOption >= 1 && selectedMenuOption <= 3)
        {
            System.out.println(anOperator.menu().get(selectedMenuOption).get(1));
            createMenuForOperator(username, password);
        }
        else if(selectedMenuOption == 4)
            new LogOut();
        else if(selectedMenuOption == 5)
            System.exit(0);
        else
        {
            System.out.println("Invalid Menu Entry Selected. Please try again.");
            createMenuForOperator(username, password);
        }
    }
    private void createMenuForWarehouseWorker(String username, String password)
    {
        WHWorker aWarehouseWorker = new WHWorker();
        aWarehouseWorker.setUsername(username);
        aWarehouseWorker.setPassword(password);
        aWarehouseWorker.menu().values().forEach(x -> System.out.println(x.get(0)));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        if(selectedMenuOption >= 1 && selectedMenuOption <= 6)
        {
            System.out.println(aWarehouseWorker.menu().get(selectedMenuOption).get(1));
            createMenuForWarehouseWorker(username, password);
        }
        else if(selectedMenuOption == 7)
            new LogOut();
        else if(selectedMenuOption == 8)
            System.exit(0);
        else
        {
            System.out.println("Invalid Menu Entry Selected. Please try again.");
            createMenuForWarehouseWorker(username, password);
        }
    }
    private void createMenuForManager(String username, String password)
    {
        Manager aManager = new Manager();
        aManager.setUsername(username);
        aManager.setPassword(password);
        aManager.menu().values().forEach(x -> System.out.println(x.get(0)));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        if(selectedMenuOption >= 1 && selectedMenuOption <= 7)
        {
            System.out.println(aManager.menu().get(selectedMenuOption).get(1));
            createMenuForManager(username, password);
        }
        else if(selectedMenuOption == 8)
            new LogOut();
        else if(selectedMenuOption == 9)
            System.exit(0);
        else
        {
            System.out.println("Invalid Menu Entry Selected. Please try again.");
            createMenuForManager(username, password);
        }
    }
}