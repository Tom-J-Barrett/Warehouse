package employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class Employee
{
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String role;
    public Employee(String username, String password, String firstname, String surname, String role)
    {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.role = role;
    }
    public Employee(){
    	
    }
	public String toString()
    {
        return username + " " + password + " " + firstname + " " + surname + " " + role;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getFirstname()
    {
        return firstname;
    }
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    public String getSurname()
    {
        return surname;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    public void setRole(String role)
    {
    	this.role = role;
    }
    public String getRole()
    {
        return role;
    }
    public LinkedHashMap<Integer, List<String>> menu()
    {
        LinkedHashMap<Integer, List<String>> menuItems = new LinkedHashMap<>();
        menuItems.put(1, new ArrayList<>(Arrays.asList("1. Log Out", "Log Out Menu Item Selected")));
        menuItems.put(2, new ArrayList<>(Arrays.asList("2. Exit", "Exit Menu Item Selected")));
        return menuItems;
    }
}

