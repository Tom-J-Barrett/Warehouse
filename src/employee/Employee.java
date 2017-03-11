package employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee
{
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String DOB;
    private String role;
    public Employee(String username, String password, String firstname, String surname, String DOB, String role)
    {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.DOB = DOB;
        this.role = role;
    }
    public Employee()
    {

    }
    public String toString()
    {
        return username + " " + password + " " + firstname + " " + surname + " " + DOB + " " + role;
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
    public String getDOB()
    {
        return DOB;
    }
    public String getRole()
    {
        return role;
    }
    public List<String> menu()
    {
        return new ArrayList<>(Arrays.asList("1. Log Out", "2. Exit"));
    }
}

