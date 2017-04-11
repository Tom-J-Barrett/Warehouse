package employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
public class Operator extends Employee
{
    public Operator(String username, String password, String firstname, String surname)
    {
    	super(username, password, firstname, surname, "operator");
    }
    public Operator()
    {
    	
    }
    public LinkedHashMap<Integer, List<String>> menu()
    {
        LinkedHashMap<Integer, List<String>> menuItems = new LinkedHashMap<>();
        menuItems.put(1, new ArrayList<>(Arrays.asList("1. Report", "Report Menu Item Selected")));
        return menuItems;
    }
}