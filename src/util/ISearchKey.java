package util;

import java.util.List;
import java.util.Map;

public interface ISearchKey {

	ISearchKey on(String table);

	ISearchKey orderBy(String orderBy);

	ISearchKey addCriterion(String column, Object value);

	String getTable();

	String getOrderBy();

	List<String> getColumns();

	Map<String, String> getCriteria();
}
