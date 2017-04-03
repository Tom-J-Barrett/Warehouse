package Control;

import java.util.Scanner;

public class LogOut
{
    public LogOut()
    {
        performLogout();
    }
    public void performLogout()
    {
        System.out.println("You have been logged out successfully");
        System.out.println("Select an action from the list");
        System.out.println("1. Log In");
        System.out.println("2. Exit");
        Scanner in = new Scanner(System.in);
        int selectedMenuItem = in.nextInt();
        if(selectedMenuItem == 1)
            new LogIn();
        else if(selectedMenuItem == 2)
            System.exit(0);
        else
            System.exit(0);
    }
}
