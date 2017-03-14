package control;

import com.sun.javafx.image.BytePixelSetter;
import employee.Employee;
import employee.Manager;
import employee.Operator;
import employee.WHWorker;

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
        aScanner.close();
        if(username.length() > 2 && password.length() > 2)
        {
            System.out.println("You have been logged in successfully as " + username);
            if(username.startsWith("o") || username.startsWith("O"))
                createMenuForOperator(username, password);
            else if(username.startsWith("w") || username.startsWith("W"))
                createMenuForWarehouseWorker(username, password);
            else if(username.startsWith("m") || username.startsWith("M"))
                createMenuForManager(username, password);
            else
                createMenuForEmployee(username, password);
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
        System.out.println("You have been logged in as an operator");
        Operator anOperator = new Operator();
        anOperator.setUsername(username);
        anOperator.setPassword(password);
        anOperator.menu().forEach(x -> System.out.println(x));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        aScanner.close();
        switch(selectedMenuOption)
        {
            case 1:     System.out.println("Use Inventory Menu Item Selected");
                        createMenuForOperator(username, password);
                        break;
            case 2:     System.out.println("View Orders To Be Made Menu Item Selected");
                        createMenuForOperator(username, password);
                        break;
            case 3:     System.out.println("Move To QI Production Menu Item Selected");
                        createMenuForOperator(username, password);
                        break;
            case 4:     new LogOut();
                        break;
            case 5:     System.exit(0);
                        break;
            default:    System.out.println("Invalid Menu Entry Selected. Please try again.");
                        createMenuForOperator(username, password);
                        break;
        }
    }
    private void createMenuForEmployee(String username, String password)
    {
        System.out.println("You have been logged in as an employee");
        Employee anEmployee = new Employee();
        anEmployee.setUsername(username);
        anEmployee.setPassword(password);
        anEmployee.menu().forEach(x -> System.out.println(x));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        aScanner.close();
        switch(selectedMenuOption)
        {
            case 1:     new LogOut();
                        break;
            case 2:     System.exit(0);
                        break;
            default:    System.out.println("Invalid Menu Entry Selected. Please try again.");
                        createMenuForEmployee(username, password);
                        break;
        }
    }
    private void createMenuForWarehouseWorker(String username, String password)
    {
        System.out.println("You have been logged in as a warehouse worker");
        WHWorker aWarehouseWorker = new WHWorker();
        aWarehouseWorker.setUsername(username);
        aWarehouseWorker.setPassword(password);
        aWarehouseWorker.menu().forEach(x -> System.out.println(x));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        aScanner.close();
        switch(selectedMenuOption)
        {
            case 1:     System.out.println("Receive Inventory Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 2:     System.out.println("View In Ops QI Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 3:     System.out.println("Move To Operator Task Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 4:     System.out.println("Move Out Of QI Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 5:     System.out.println("View Inventory To Be Moved To Operator Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 6:     System.out.println("Ship Out Links To Other System Menu Item Selected");
                        createMenuForWarehouseWorker(username, password);
                        break;
            case 7:     new LogOut();
                        break;
            case 8:     System.exit(0);
                        break;
            default:    System.out.println("Invalid Menu Entry Selected. Please try again.");
                        createMenuForWarehouseWorker(username, password);
                        break;
        }
    }
    private void createMenuForManager(String username, String password)
    {
        System.out.println("You have been logged in as a manager");
        Manager aManager = new Manager();
        aManager.setUsername(username);
        aManager.setPassword(password);
        aManager.menu().forEach(x -> System.out.println(x));
        Scanner aScanner = new Scanner(System.in);
        int selectedMenuOption = aScanner.nextInt();
        aScanner.close();
        switch(selectedMenuOption)
        {
            case 1:     System.out.println("Add New Product Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 2:     System.out.println("Approve Shipping Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 3:     System.out.println("Delete/Scrap Units Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 4:     System.out.println("Add New Employee Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 5:     System.out.println("View Replenishment Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 6:     System.out.println("Approve Out Of QI Ops Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 7:     System.out.println("Report Menu Item Selected");
                        createMenuForManager(username, password);
                        break;
            case 8:     new LogOut();
                        break;
            case 9:     System.exit(0);
                        break;
            default:    System.out.println("Invalid Menu Entry Selected. Please try again.");
                        createMenuForManager(username, password);
                        break;
        }
    }
}












