package control;

import static inventory.util.OrderConstants.ORDER_ID;
import static inventory.util.ProductConstants.PRODUCT_ID;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import database.Database;
import inventory.Order;
import util.ISearchKey;
import util.SearchKey;

public class Scrap {

	private Database db;
	private ISearchKey orderForScrapping = new SearchKey(ORDER_ID, "ProductID", "LocationID").on("ordertable").orderBy(ORDER_ID);

	public Scrap(Database db) {
		this.db = db;
	}

	public void scrapChain(Integer orderID, String shipTo) {
		addToScrapLog(new Order(orderID, shipTo));
		deleteOrder(orderForScrapping.addCriterion(ORDER_ID, orderID));
	}

	public static List<String> orderColumnsToTable() {
		return Arrays.asList(ORDER_ID, PRODUCT_ID, "LocationID", "Select Order");
	}

	public List<List<String>> ordervaluesToTable() {
		return db.getTableRows(orderForScrapping);
	}

	protected void addToScrapLog(Order order) {
		db.insertTableRow("scraplog", 0, order.getId(), new Timestamp(new Date().getTime()));
	}

	protected void deleteOrder(ISearchKey searchKey) {
		db.removeTableRow("ordertable", searchKey.getCriteria());
	}
}
