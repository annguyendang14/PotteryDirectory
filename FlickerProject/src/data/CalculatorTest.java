package data;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testCalculatorFunction() {
		assertEquals(8.500, Calculator.StringCalculator("8.5"), 0.001);
		assertEquals(3881.355, Calculator.StringCalculator("8.5*456.63"), 0.001);
		assertEquals(68, Calculator.StringCalculator("8.5*4+8.5*4"), 0.001);
		assertEquals(9, Calculator.StringCalculator("4+5"), 0.1);
		assertEquals(0, Calculator.StringCalculator("0"), 0.1);
		assertEquals(4.25, Calculator.StringCalculator("8.5/4+8.5/4"), 0.01);
		assertEquals(4.25, Calculator.StringCalculator("8.5 / 4 + 8.5 / 4"), 0.01);
		assertEquals(46.5, Calculator.StringCalculator("8.5 + 4 * 8.5 + 4"), 0.01);
	}

}
