package data;

import java.util.ArrayList;
import java.util.Date;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Calculator {
	public static double StringCalculator(String expression) {
		DoubleEvaluator evaluator = new DoubleEvaluator();
		Double result = evaluator.evaluate(expression);
		return result;
	}

}
