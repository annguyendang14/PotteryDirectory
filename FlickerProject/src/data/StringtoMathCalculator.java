package data;
import com.fathzer.soft.javaluator.DoubleEvaluator;
public class StringtoMathCalculator {
	public static void main(String[] args) {
		DoubleEvaluator evaluator = new DoubleEvaluator();
	    String expression = "(2^3-1)*sin(pi/4)/ln(pi^2)";
	    // Evaluate an expression
	    Double result = evaluator.evaluate(expression);
	    // Ouput the result
	    System.out.println(expression + " = " + result);

	}

}
