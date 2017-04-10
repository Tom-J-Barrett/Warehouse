package Database;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.lang.System.out;

public class Database
{
    private String jdbcDriver = "com.mysql.jdbc.Driver";
    private String databaseUrl = "jdbc:mysql://localhost:3306/warehouse";
    private String user = "root";
    private String password = "aicahsah";
    private String databaseTitle = "warehouse";
    private String orValue;
    public Database()
    {
        try
        {
            Class.forName(jdbcDriver);
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    /*
    This method is difficult to use as it takes a lot of parameters. The first 2 parameters are essential. The last 3 parameters are not necessary as they just filter
    and refine the data.
    This method creates a join statement to retrieve data from 2 or more tables. The tableTitles parameter is for the titles of the tables. The joinConditions parameters
    is for the join conditions that join the tables together. The selectedParameters is for the filters that are applied to the SQL statement. If the selectedParameters
    is set to an empty HashMap all rows of the selected tables of the database will be returned. The columnTitles is for the selected columns from the selected tables.
    If the columnTitles is set to an empty ArrayList all columns from the selected tables will be returned. If the columnTitleForSorting parameter is initialised with a
    string variable with a length greater than zero the results are sorted in ascending order by the specified column title.

    Sample Usage
    Database aDatabase = new Database();
    ArrayList<String> tableTitles = new ArrayList<>(Arrays.asList("firstweights", "drivers"));
    ArrayList<String> joinConditions = new ArrayList<>(Arrays.asList("firstweights.driver", "drivers.code"));
    HashMap<String, String> selectedValues = new HashMap<>();
    selectedValues.put("drivers.code", "2");
    ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("firstweights.code", "firstweights.weight", "firstweights.date", "drivers.firstname",
    "drivers.lastname"));
    ArrayList<ArrayList<String>> retrievedTableRows = aDatabase.getJoinedTableRows(tableTitles, joinConditions, selectedValues, desiredColumns, "");
    */
    public List<List<String>> getJoinedTableRows(List<String> tableTitles, List<String> joinConditions,
                                                           HashMap<String, String> selectedParameters, List<String> columnTitles, String columnTitleForSorting)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            StringBuilder sqlStatement = new StringBuilder("select ");
            if(columnTitles.size() == 0)
                sqlStatement.append("*");
            else
            {
                columnTitles.forEach(x -> sqlStatement.append(x + ", "));
                sqlStatement.setLength(sqlStatement.length() - 2);
            }
            if(tableTitles.size() >= 1)
                sqlStatement.append(" from " + tableTitles.get(0));
            for(int counter = 0; counter < (tableTitles.size() - 1); counter++)
            {
                sqlStatement.append(" join " + tableTitles.get(counter + 1) + " on " + joinConditions.get(counter * 2) + " = " +
                joinConditions.get((counter * 2) + 1));
            }
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            if(columnTitleForSorting.length() > 0)
                sqlStatement.append(" order by " + columnTitleForSorting + " asc");
            System.out.println(sqlStatement.toString());
            List<List<String>> retrievedRows = retrieveResults(sqlStatement.toString(), connection.createStatement());
            currentStatement.close();
            connection.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    /*
    This method is utilised for getting data from a single table in the database. It is easier to use than the previous method. The first parameter called tableName is
    essential whereas the other 3 parameters are utilised for filtering and sorting the rows. The parameter tableName is used to specify the name of the table in the
    database. The parameter selectedParameters is used to filter the selected rows based on the selected criteria. If the selectedParameters is set to an empty HashMap
    all data in the table will be returned. The parameter columnTitles is used to select columns from the table. If the parameter contains no elements all the columns in
    the table will be returned. The parameter columnTitleForSorting is utilised to order data in ascending order in the table based on the selected column title. If the
    parameter columnTitleForSorting has a length of 0 no ordering will occur.

    Sample Usage
    Database aDatabase = new Database();
    HashMap<String, String> selectedValues = new HashMap<>();
    selectedValues.put("drivers.code", "2");
    ArrayList<String> desiredColumns = new ArrayList<>(Arrays.asList("drivers.firstname", "drivers.lastname"));
    ArrayList<ArrayList<String>> retrievedTableRows = aDatabase.getTableRows("drivers", selectedValues, desiredColumns, "");
    */
    public List<List<String>> getTableRows(String tableName, HashMap<String, String> selectedParameters, List<String> columnTitles,
                                                     String columnTitleForSorting)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            StringBuilder sqlStatement = new StringBuilder("select ");
            if(columnTitles.size() == 0){
            	sqlStatement.append("*");
            }    
            else
            {
                columnTitles.forEach(x -> sqlStatement.append(x + ", "));
                sqlStatement.setLength(sqlStatement.length() - 2);
            }
            sqlStatement.append(" from " + tableName);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            if(columnTitleForSorting.length() > 0)
                sqlStatement.append(" order by " + columnTitleForSorting + " asc");
            //System.out.println(sqlStatement.toString());
           List<List<String>> retrievedRows = retrieveResults(sqlStatement.toString(), connection.createStatement());
            currentStatement.close();
            connection.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    
    public List<List<String>> getTableRowsOr(String tableName, HashMap<String, String> selectedParameters, List<String> columnTitles,
            String columnTitleForSorting, String or)
	{
		try
		{
			Connection connection = DriverManager.getConnection(databaseUrl, user, password);
			Statement currentStatement = connection.createStatement();
			StringBuilder sqlStatement = new StringBuilder("select ");
			if(columnTitles.size() == 0){
			sqlStatement.append("*");
			}    
			else
			{
				columnTitles.forEach(x -> sqlStatement.append(x + ", "));
				sqlStatement.setLength(sqlStatement.length() - 2);
			}
			sqlStatement.append(" from " + tableName);
			addParametersToSQLStatement(selectedParameters, sqlStatement);
			selectedParameters.forEach((x, y) ->
            {
               orValue=x;
            });
			sqlStatement.append(" or "+ orValue+ "="+or);
			if(columnTitleForSorting.length() > 0)
				sqlStatement.append(" order by " + columnTitleForSorting + " asc");
			//System.out.println(sqlStatement.toString());
			List<List<String>> retrievedRows = retrieveResults(sqlStatement.toString(), connection.createStatement());
			currentStatement.close();
			connection.close();
			//System.out.println(sqlStatement.toString());
			return retrievedRows;
		}
		catch(Exception error)
		{
			JOptionPane.showMessageDialog(null, error);
			return new ArrayList<>();
		}
	}
    /*
    This method is very easy to utilise. This method is utilised to get the max value of a specified column from a specified table. It takes two parameters
    which are both required. The parameter tableName is required for the name of the desired table of the database. The parameter columnName is required for the
    name of the desired column of the table.

    Sample Usage
    Database aDatabase = new Database();
    int maximumValueOfColumn = aDatabase.getMaxValueOfColumn("drivers", "code");
    */
    public int getMaxValueOfColumn(String tableName, String columnName)
    {
        try
        {
            int maxValue = 0;
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            ResultSet selectedRows = currentStatement.executeQuery("select max(" + columnName + ") from " + tableName);
            while(selectedRows.next())
                maxValue = selectedRows.getInt(1);
            selectedRows.close();
            currentStatement.close();
            connection.close();
            return maxValue;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return -1;
        }
    }
    /*
    This method is very easy to utilise. It takes just one parameter which is the name of the table. It returns an ArrayList which contains the column titles of the
    specified table.

    Sample Usage
    Database aDatabase = new Database();
    ArrayList<String> columnTitlesOfDriversTable = aDatabase.getColumnTitles("drivers");
    */
    public List<String> getColumnTitles(String tableName)
    {
        try
        {
            List<String> columnTitles = new ArrayList<>();
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            Statement currentStatement = connection.createStatement();
            ResultSet selectedRows = currentStatement.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE '" + tableName + "' AND " +
            "TABLE_SCHEMA LIKE '" + databaseTitle + "';");
            Integer numberOfColumns = selectedRows.getMetaData().getColumnCount();
            while(selectedRows.next())
            {
                for(int counter = 1; counter <= numberOfColumns; counter++)
                    columnTitles.add(selectedRows.getString(counter));
            }
            selectedRows.close();
            currentStatement.close();
            connection.close();
            return columnTitles;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
    /*
    This method is very easy to utilise. This method is utilised to insert a new row into the specified table of the database. It takes just 2 parameters.
    Both parameters are necessary. The first parameter is the name of the table and the second parameter is an ArrayList containing the values of the row to be inserted.

    Sample Usage
    Database aDatabase = new Database();
    ArrayList<String> newRowValues = new ArrayList<>(Arrays.asList("5", "Shane", "Collins"));
    aDatabase.insertTableRow("drivers", newRowValues);
    */
    public void insertTableRow(String tableName, List<String> tableRowValues)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("insert into " + tableName + " values (");
            tableRowValues.forEach(x -> sqlStatement.append("?, "));
            sqlStatement.setLength(sqlStatement.length() - 2);
            sqlStatement.append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            Integer counter = 0;
            for(counter = 1; counter <= tableRowValues.size(); counter++)
            {
                if(Utilities.isWholeNumber(tableRowValues.get(counter - 1)))
                    preparedStatement.setInt(counter, Integer.parseInt(tableRowValues.get(counter - 1)));
                else if(Utilities.isDecimal(tableRowValues.get(counter - 1)))
                    preparedStatement.setDouble(counter, Double.parseDouble(tableRowValues.get(counter - 1)));
                else
                    preparedStatement.setString(counter, tableRowValues.get(counter - 1));
            }
         //   System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    /*
    This method is very easy to utilise. This method is used to delete rows from the specified table in the database. It takes 2 parameters both of which are necessary.
    The first parameter is the name of the table. This parameter is required. The second parameter is required to filter the rows of the table.
    If no value is supplied to the second parameter all the rows in the table will be deleted. Therefore it is extremely important to add a value to the
    selectedParameters HashMap.

    Sample Usage
    Database aDatabase = new Database();
    HashMap<String, String> selectedParameters = new HashMap<>();
    selectedParameters.put("code", "2");
    aDatabase.removeTableRow("drivers", selectedParameters);
    */
    public void removeTableRow(String tableName, HashMap<String, String> selectedParameters)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("delete from " + tableName);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error, "Error Message", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*
    This method is a little harder to utilise. It is used to update the selected attributes of a selected row in a selected table in the database.
    This method takes 3 parameters which are all necessary. The first parameter is the name of the table. The second parameter is a HashMap which is used to specify the
    name of the attribute to be updated and the updated value of that attribute. The third parameter is a HashMap which is used to specify the row that is to be updated.
    If the third parameter does not contain a value, all the rows in the selected table will be updated to the updated values.

    Sample Usage
    Database aDatabase = new Database();
    HashMap<String, String> updatedParameters = new HashMap<>();
    updatedParameters.put("firstname", "Shane");
    HashMap<String, String> selectedParameters = new HashMap<>();
    selectedParameters.put("code", "2");
    aDatabase.updateTableRow("drivers", updatedParameters, selectedParameters);
    */
    public void updateTableRow(String tableName, HashMap<String, String> updatedParameters, HashMap<String, String> selectedParameters)
    {
        try
        {
            Connection connection = DriverManager.getConnection(databaseUrl, user, password);
            StringBuilder sqlStatement = new StringBuilder("update " + tableName + " set ");
            updatedParameters.forEach((x, y) ->
            {
                if(Utilities.isDecimal(y) || Utilities.isWholeNumber(y) || Utilities.isDate(y, "yyyy/MM/dd"))
                    sqlStatement.append(x + " = " + y + ", ");
                else
                    sqlStatement.append(x + " = '" + y + "', ");
            });

            sqlStatement.setLength(sqlStatement.length() - 2);
            addParametersToSQLStatement(selectedParameters, sqlStatement);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    private void addParametersToSQLStatement(HashMap<String, String> selectedParameters, StringBuilder sqlStatement)
    {
        if(selectedParameters.size() > 0)
        {
            sqlStatement.append(" where ");
            selectedParameters.forEach((x, y) ->
            {
                if(Utilities.isDecimal(y) || Utilities.isWholeNumber(y) || Utilities.isDate(y, "yyyyMMdd"))
                    sqlStatement.append(x + " = " + y + " or ");
                else
                    sqlStatement.append(x + " like '%" + y + "%' or ");
            });
            sqlStatement.setLength(sqlStatement.length() - 4);
        }
    }
    private List<List<String>> retrieveResults(String sqlStatement, Statement currentStatement)
    {
        try
        {
            ResultSet selectedRows = currentStatement.executeQuery(sqlStatement);
            List<List<String>> retrievedRows = new ArrayList<>();
            Integer numberOfColumns = selectedRows.getMetaData().getColumnCount();
            while (selectedRows.next())
            {
                List<String> aRetrievedRow = new ArrayList<>();
                for (Integer k = 1; k <= numberOfColumns; k++)
                    aRetrievedRow.add(selectedRows.getString(k));
                retrievedRows.add(aRetrievedRow);
            }
            selectedRows.close();
            return retrievedRows;
        }
        catch(Exception error)
        {
            JOptionPane.showMessageDialog(null, error);
            return new ArrayList<>();
        }
    }
}