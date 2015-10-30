package data;
import java.util.Date;

import com.fathzer.soft.javaluator.DoubleEvaluator;
public class StringtoMathCalculator {
	public static void main(String[] args) {
		DoubleEvaluator evaluator = new DoubleEvaluator();
	    Order tempOrder = new Order("Kelsey", new Date(2,3,2015), new Date(2,5,2015), 12,  "4.95*12" , 25.5, 5);
		String expression = tempOrder.getDescription();
	    // Evaluate an expression
	    Double result = evaluator.evaluate(expression);
	    // Ouput the result
	    System.out.println(expression + " = " + result);

	}

}
