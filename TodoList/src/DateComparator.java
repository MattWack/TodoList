import java.util.Comparator;

/**
 * Class that compares two TodoItems by their dates as a comparator.
 * 
 * @author Matthew Wackerfuss
 *
 */
public class DateComparator implements Comparator<TodoItem>{
    /**
     * Compares two separate TodoItems by there dates. 
     * 
     * @param left TodoItem to be compared.
     * @param right TodoItem to be compared to the left item.
     * @return An integer that equals -1 if the date is smaller than the right, 1 if the date is larger, and 0 if they are equal.
     */
    @Override
    public int  compare(TodoItem left, TodoItem right) {
        return left.getDate().compareTo(right.getDate());
    }
}
