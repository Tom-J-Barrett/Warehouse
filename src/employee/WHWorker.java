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
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. View In Ops QI", "View In Ops QI Menu Item Selected")));
        menuItems.put(3, new ArrayList<>(Arrays.asList("3. Move To Operator Task", "Move To Operator Task Menu Item Selected")));
        menuItems.put(4, new ArrayList<>(Arrays.asList("4. Move Out Of QI", "Move Out Of QI Menu Item Selected")));
        menuItems.put(5, new ArrayList<>(Arrays.asList("5. View Inventory To Be Moved To Operator", "View Inventory To Be Moved To Operator Menu Item Selected")));
        menuItems.put(6, new ArrayList<>(Arrays.asList("6. Ship Out Links To Other System", "Ship Out Links To Other System Menu Item Selected")));
        return menuItems;
    }
}
