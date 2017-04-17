package database;

import java.util.Arrays;
import java.util.List;

import util.ISearchKey;

public class ScrapDatabaseTest extends Database {

	public ScrapDatabaseTest() {}
	
	@Override
	public List<List<String>> getTableRows(ISearchKey searchKey) {
		return Arrays.asList(Arrays.asList("1", "10", "2"), Arrays.asList("2", "3", "3"));
	}

	@Override
	public void insertTableRow(String tableName, Object... values) {
		super.insertTableRow(tableName, values);
	}

	@Override
	public void removeTableRow(ISearchKey searchKey) {
		super.removeTableRow(searchKey);
	}
}
