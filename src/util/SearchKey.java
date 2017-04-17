package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchKey implements ISearchKey {

	private String table;
	private String orderBy;
	private List<String> columns = new ArrayList<>();
	private Map<String, String> criteria = new HashMap<>();

	public SearchKey(String... parameters) {
		this(Arrays.asList(parameters));
	}

	public SearchKey(List<String> parameters) {
		this.columns.addAll(parameters);
	}

	@Override
	public ISearchKey on(String table) {
		this.table = table;
		return this;
	}

	@Override
	public String getTable() {
		return table;
	}

	@Override
	public SearchKey addCriterion(String column, Object value) {
		criteria.put(column, value.toString());
		return this;
	}

	@Override
	public Map<String, String> getCriteria() {
		return criteria;
	}

	@Override
	public SearchKey orderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}

	@Override
	public String getOrderBy() {
		return orderBy;
	}

	@Override
	public List<String> getColumns() {
		return columns;
	}
}
