import java.util.Comparator;

/**
 * 
 * Class that compares two TodoItems by their importance as a comparator.
 * 
 * @author Matthew Wackerfuss
 *
 */
public class ImportanceComparator implements Comparator<TodoItem>{
    /**
     * Compares two separate TodoItems by their importance. 
     * 
     * @param left TodoItem to be compared.
     * @param right TodoItem to be compared to the left item.
     * @return An integer that equals -1 if the importance is higher than compared to the right, 1 if the date is lower, and 0 if they are equal.
     */
    @Override
    public int compare(TodoItem left, TodoItem right) {
       if(left.getImportanceLevel().equals(right.getImportanceLevel())) {
           return 0;
       }
       if(left.getImportanceLevel().equals(Importance.HIGH)) {
           return -1;
       } else if(left.getImportanceLevel().equals(Importance.LOW)){
           return 1;
       } else if(right.getImportanceLevel().equals(Importance.HIGH)) {
           return 1;
       } else if(right.getImportanceLevel().equals(Importance.LOW)){
           return -1;
       }
       return 0;
    }
}
