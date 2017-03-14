package employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class WHWorker extends Employee
{
    private String role;
    public WHWorker()
    {

    }
    public List<String> menu()
    {
        return new ArrayList<>(Arrays.asList("1. Receive Inventory", "2. View In Ops QI", "3. Move To Operator Task",
        "4. Move Out Of QI", "5. View Inventory To Be Moved To Operator", "6. Ship Out Links To Other System", "7. LogOut", "8. Exit"));
    }
}
