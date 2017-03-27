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
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. Approve Shipping", "Approve Shipping Menu Item Selected")));
        menuItems.put(3, new ArrayList<>(Arrays.asList("3. Delete/Scrap Units", "Delete/Scrap Units Menu Item Selected")));
        menuItems.put(4, new ArrayList<>(Arrays.asList("4. Add New Employee", "Add New Employee Menu Item Selected")));
        menuItems.put(5, new ArrayList<>(Arrays.asList("5. View Replenishment", "View Replenishment Menu Item Selected")));
        menuItems.put(6, new ArrayList<>(Arrays.asList("6. Approve Out Of QI Ops", "Approve Out Of QI Ops Menu Item Selected")));
        menuItems.put(7, new ArrayList<>(Arrays.asList("7. Report", "Report Menu Item Selected")));
        return menuItems;
    }
}