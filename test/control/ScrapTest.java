package control;

import static inventory.util.OrderConstants.ORDER_ID;
import static inventory.util.ProductConstants.PRODUCT_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

import database.ScrapDatabaseTest;

public class ScrapTest {

	private static final Scrap GET_ORDER_TEST = new Scrap(new ScrapDatabaseTest());

	@Test
	public void testOrderColumnsToTable_okCase() {
		assertEquals(Arrays.asList(ORDER_ID, PRODUCT_ID, "LocationID", "Select Order"), Scrap.orderColumnsToTable());
	}

	@Test
	public void testOrderColumnsToTable_koCase() {
		assertNotEquals(Arrays.asList(ORDER_ID, PRODUCT_ID, "LocationID", "test"), Scrap.orderColumnsToTable());
	}

	@Test
	public void testOrdervaluesToTable_okCase() {
		assertEquals(Arrays.asList(Arrays.asList("1", "10", "2"), Arrays.asList("2", "3", "3")), GET_ORDER_TEST.ordervaluesToTable());
	}
	
	@Test
	public void testOrdervaluesToTable_koCase() {
		assertNotEquals(Arrays.asList(Arrays.asList("1", "10", "3"), Arrays.asList("2", "3", "4")), GET_ORDER_TEST.ordervaluesToTable());
	}
}
