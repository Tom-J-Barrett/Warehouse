package employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Operator extends Employee
{
    private String role;
    public Operator()
    {

    }
    public List<String> menu()
    {
        return new ArrayList<>(Arrays.asList("1. Use Inventory", "2. View Orders To Be Made", "3. Move To QI Production", "4. LogOut", "5. Exit"));
    }
}