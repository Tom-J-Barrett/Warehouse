package control;

import employee.Employee;

import java.util.Scanner;

public class LogIn
{
    private Employee anEmployee;
   /* public static void main(String[] args)
    {
        new LogIn();
    }*/
    public LogIn()
    {
        anEmployee = new Employee();
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
            anEmployee.setUsername(username);
            anEmployee.setPassword(password);
            System.out.println("You have been logged in successfully as " + anEmployee.getUsername());
            anEmployee.menu().forEach(x -> System.out.println(x));
            int selectedMenuOption = aScanner.nextInt();
            if(selectedMenuOption == 1)
                new LogOut();
            else if(selectedMenuOption == 2)
                System.exit(0);
            else
                System.exit(0);
        }
        else
        {
            if(username.length() < 2)
                attemptLogOn("The username " + username + " you entered is invalid as it has less than 2 characters");
            if(password.length() < 2)
                attemptLogOn("The password " + password + " you entered is invalid as it has less than 2 characters");
        }
    }
}