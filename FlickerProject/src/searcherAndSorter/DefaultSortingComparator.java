package searcherAndSorter;
import java.util.Comparator;

import data.Order;

public class DefaultSortingComparator implements Comparator<Order> {

	@Override
	public int compare(Order arg0, Order arg1) {

		if (arg1.getOrderDate().equals(arg0.getOrderDate())) {
			return arg0.getStage() - arg1.getStage();
		} else {
			return -arg0.getOrderDate().compareTo(arg1.getOrderDate());

		}
	}
}
