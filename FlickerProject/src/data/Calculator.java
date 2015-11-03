package data;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Calculator {
	public static double StringCalculator(String expression) {
		DoubleEvaluator evaluator = new DoubleEvaluator();
		return evaluator.evaluate(expression);
	}

}
