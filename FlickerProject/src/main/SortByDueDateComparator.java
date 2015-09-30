package main;

import java.util.Comparator;

public class SortByDueDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order arg0, Order arg1) {

		if (arg1.getDueDate().equals(arg0.getDueDate())) {
			return arg0.getStage() - arg1.getStage();
		} else {
			return -arg0.getDueDate().compareTo(arg1.getDueDate());

		}
	}

}
