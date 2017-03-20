package employee;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
public class Operator extends Employee
{
    private String role;
    public Operator()
    {

    }
    public LinkedHashMap<Integer, List<String>> menu()
    {
        LinkedHashMap<Integer, List<String>> menuItems = new LinkedHashMap<>();
        menuItems.put(1, new ArrayList<>(Arrays.asList("1. Use Inventory", "Use Inventory Menu Item Selected")));
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. View Orders To Be Made", "View Orders To Be Made Menu Item Selected")));
        menuItems.put(3, new ArrayList<>(Arrays.asList("3. Move To QI Production", "Move To QI Production Menu Item Selected")));
        menuItems.put(4, new ArrayList<>(Arrays.asList("4. Log Out", "Log Out Menu Item Selected")));
        menuItems.put(5, new ArrayList<>(Arrays.asList("5. Exit", "Exit Menu Item Selected")));
        return menuItems;
    }
}