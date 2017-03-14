package employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager extends Employee
{
    private String role;
    public Manager()
    {

    }
    public List<String> menu()
    {
        return new ArrayList<String>(Arrays.asList("1. Add New Product", "2. Approve Shipping", "3. Delete/Scrap Units", "4. Add New Employee", "5. View Replenishment",
        "6. Approve Out Of QI Ops", "7. Report", "8. LogOut", "9. Exit"));
    }
}
