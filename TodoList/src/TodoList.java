import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Matthew Wackerfuss
 *
 * Class that represents the todolist.
 */
public class TodoList {
    /**
     * An arrayList the holds all the tasks in the todoList.
     */
    private ArrayList<TodoItem> theTasks;
    /**
     * The username of the user.
     */
    private String username;

    /**
     * Constructor that takes in the username.
     * 
     * @param username The chosen username for the user.
     */
    public TodoList(String username) {    
        this.username = username;
        theTasks = new ArrayList<TodoItem>();
    }
    /**
     * Builds the todoList from a csv file under the named username.txt
     * 
     * @param name The name of the file that the todoList will be built from.
     * @return A new todoList from built name.txt.
     */
    public static TodoList buildFromUsername(String name){
        List<String> theLines;
        TodoList builtFromUser = new TodoList(name);
        try {
            theLines = Files.readAllLines(Paths.get(name+".txt"));
            for(String currentLine : theLines) {
                builtFromUser.addTask(TodoItem.buildFromCSV(currentLine));
            }
        } catch(IOException e) {
            throw new IllegalArgumentException();
        }
        return builtFromUser;
    }
    
    /**
     * Adds a new task to the todoList.
     * 
     * @param item The TodoItem to be added.
     */
    public void addTask(TodoItem item) {
        theTasks.add(item);
    }
    /**
     * Sorts the todoList from lowest to largest date and 
     * returns a list of that sorted todoList.
     * 
     * @return A string representing the ordered todoList.
     */
    public String getAsDateSortedString() {
        if(theTasks.size() == 0) {
            return "No tasks in list";
        }
        Comparator<TodoItem> dateComparator = new DateComparator(); 
        theTasks.sort(dateComparator);
        String returnString = "";
        for(TodoItem currentItem: theTasks) {
            returnString = returnString + currentItem.toString();
        }
        return returnString;
        
    }
    /**
     * Sorts the todoList that sorts from most important to least important.
     * 
     * @return A string representing the ordered todoList.
     */
    public String getAsImportanceSortedString() {
        if(theTasks.size() == 0) {
            return "No tasks in list";
        }
        Comparator<TodoItem> importanceComparator = new ImportanceComparator(); 
        theTasks.sort(importanceComparator);
        String returnString = "";
        for(TodoItem currentItem: theTasks) {
            returnString = returnString + currentItem.toString();
        }
        return returnString;
    }
    /**
     * Takes the todoList and creates a text file with each 
     * tasks having it's own line. Each line is in the form
     * of YYYYMMDD,description,importance.
     */
    public void finalize() {
        
        String toWrite = "";
        for (TodoItem currentLine: theTasks) {
            toWrite = toWrite + currentLine.getAsCSV() +"\n";
        }
        try {
            Files.write(Paths.get(username+".txt"),toWrite.getBytes(StandardCharsets.UTF_8));
        } catch(IOException e) {
            System.out.println("Problem Writing");
        }
    }
}

