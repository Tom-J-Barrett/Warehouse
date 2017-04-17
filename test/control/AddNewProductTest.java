package control;

import static inventory.util.OrderConstants.ORDER_ID;
import static inventory.util.ProductConstants.PRODUCT_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class AddNewProductTest {

	private static final AddNewProduct ADDED_PRODUCT = new AddNewProduct();

	@Test
	public void testSetID_okCase() {
		assertEquals(5, ADDED_PRODUCT.setID(new Scanner("5")));
	}
	
	@Test
	public void testSetID_koCase() {
		assertNotEquals(6, ADDED_PRODUCT.setID(new Scanner("5")));
	}
	
	@Test
	public void testSetName_okCase() {
		assertEquals("Product 1", ADDED_PRODUCT.setName(new Scanner("Product 1")));
	}
	
	@Test
	public void testSetName_koCase() {
		assertNotEquals("Product 2", ADDED_PRODUCT.setName(new Scanner("Product 1")));
	}
	
//	@Test
//	public void testGetItems_okCase(){
//		
//	}
	
//	@Test
//	public void testGetItemColumnTitles_okCase(){
//		assertEquals("item", AddNewProduct.getItemColumnTitles());
//	}
	
}
