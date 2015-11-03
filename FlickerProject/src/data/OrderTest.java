package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderTest {

	@Test
	public void testConvertStageNameToStageNumber() {
		assertEquals(0, Order.convertStageNameToStageNumber("Undone"));
		assertEquals(1, Order.convertStageNameToStageNumber("Done"));
		assertEquals(2, Order.convertStageNameToStageNumber("Shipped"));
		assertEquals(3, Order.convertStageNameToStageNumber("Completed"));
		assertEquals(4, Order.convertStageNameToStageNumber("Canceled"));
	}

	@Test
	public void testConvertStageNumberToStageName() {
		assertEquals("Undone", Order.convertStageNumberToStageName(0));
		assertEquals("Done", Order.convertStageNumberToStageName(1));
		assertEquals("Shipped", Order.convertStageNumberToStageName(2));
		assertEquals("Completed", Order.convertStageNumberToStageName(3));
		assertEquals("Canceled", Order.convertStageNumberToStageName(4));
		
	}

}
