package employee;
import java.util.*;

public class WHWorker extends Employee
{
    public WHWorker(String username, String password, String firstname, String surname)
    {
    	super(username, password, firstname, surname, "warehouseworker");
    }
    public WHWorker()
    {

    }
    public LinkedHashMap<Integer, List<String>> menu()
    {
        LinkedHashMap<Integer, List<String>> menuItems = new LinkedHashMap<>();
        menuItems.put(1, new ArrayList<>(Arrays.asList("1. Receive Inventory", "Receive Inventory Menu Item Selected")));
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. Ship Order", "Ship Order")));
        menuItems.put(3, new ArrayList<>(Arrays.asList("3. Report", "Report Menu Item Selected")));
        return menuItems;
    }
}
