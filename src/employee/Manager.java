package employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Manager extends Employee
{
    public Manager(String username, String password, String firstname, String surname)
    {
    	super(username, password, firstname, surname, "manager");
    }
    public Manager()
    {

    }
    public LinkedHashMap<Integer, List<String>> menu()
    {
        LinkedHashMap<Integer, List<String>> menuItems = new LinkedHashMap<>();
        menuItems.put(1, new ArrayList<>(Arrays.asList("1. Add New Product", "Add New Product Menu Item Selected")));
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. Delete/Scrap Units", "Delete/Scrap Units Menu Item Selected")));
        menuItems.put(3, new ArrayList<>(Arrays.asList("3. Receive Inventory", "Receive Inventory Menu Item Selected")));
        menuItems.put(4, new ArrayList<>(Arrays.asList("4. Ship Order", "Ship Order")));
        menuItems.put(5, new ArrayList<>(Arrays.asList("5. Report", "Report Menu Item Selected")));
        return menuItems;
    }
}