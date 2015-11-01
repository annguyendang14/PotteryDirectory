package searcherAndSorter;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import data.Customer;
import data.Order;

public class SearcherTest {


	@Test
	public void testIsInteger() {
		assertEquals(true, Searcher.isInteger("1"));
		assertEquals(false, Searcher.isInteger("Moose"));
		assertEquals(false, Searcher.isInteger(""));
		assertEquals(false, Searcher.isInteger("One"));
	}
	
	
}
