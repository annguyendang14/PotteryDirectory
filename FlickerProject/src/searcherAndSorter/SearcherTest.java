package searcherAndSorter;

import static org.junit.Assert.*;



import org.junit.Test;

public class SearcherTest {


	@Test
	public void testIsInteger() {
		assertEquals(true, Searcher.isInteger("1"));
		assertEquals(false, Searcher.isInteger("Moose"));
		assertEquals(false, Searcher.isInteger(""));
		assertEquals(false, Searcher.isInteger("One"));
	}
	
	
}
