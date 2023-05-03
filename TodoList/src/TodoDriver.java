import java.util.Scanner;

/**
 * @author Matthew Wackerfuss
 * 
 * Class that asks the user for their username, and pulls up their todo list if 
 * it exists. If not it prompts the user to make a new todo list. You can add a 
 * new task, sort by date, sort by importance, and save the todo list.
 */
public class TodoDriver {

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        String username;
        
        System.out.print("Welcome to the TodoList application.\n" + "Enter your username: ");
        username = keyboard.nextLine();
        TodoList todo;
        try {
            todo = TodoList.buildFromUsername(username);
        } catch(IllegalArgumentException e) {
            System.out.println("An error occurred trying to read the file for your username.");
            System.out.print("Create new Todo List? (Y/N) ");
            if(!keyboard.nextLine().equals("Y")) {
                return;
            }
            todo = new TodoList(username);
        }
        while(true) {
            System.out.println("Options:\n"
                    + "1) Show tasks sorted by date\n"
                    + "2) Show tasks sorted by importance\n"
                    + "3) Add new task\n"
                    + "4) Save and exit");
            System.out.print("Your choice: ");
            String choice = keyboard.nextLine();
            if(choice.equals("1")) {
                System.out.println(todo.getAsDateSortedString());
            } else if(choice.equals("2")) {
                System.out.println(todo.getAsImportanceSortedString());
            } else if(choice.equals("3")) {
                boolean nextPrompt = true;
                Date enteredDate = new Date(1,1,2000);
                String enteredImportance;
                Importance importance = Importance.LOW;
                String enteredDescription;
                try {
                    System.out.print("Enter a date (YYYY-MM-DD): ");
                    enteredDate = Date.fromYYYYMMDDDashString(keyboard.nextLine());
                } catch(NumberFormatException e) {
                    System.out.println("Invalid date");
                    nextPrompt = false;
                } catch(IllegalArgumentException e) {
                    System.out.println("Dates must be entered in YYYY-MM-DD format");
                    nextPrompt = false;
                }
                if(nextPrompt) {
                    System.out.print("Enter an importance (HIGH, MEDIUM, LOW): ");
                    enteredImportance = keyboard.nextLine();
                    if(enteredImportance.equals("HIGH")) {
                        importance = Importance.HIGH;
                    } else if (enteredImportance.equals("MEDIUM")) {
                        importance = Importance.MEDIUM;
                    } else if (enteredImportance.equals("LOW")) {
                        importance = Importance.LOW;
                    } else {
                        System.out.println("Bad importance choice");
                        nextPrompt = false;
                    }
                } 
                if (nextPrompt) {
                    System.out.print("Enter a short description (no commas): ");
                    enteredDescription = keyboard.nextLine();
                    if(enteredDescription.contains(",")) {
                        System.out.println("No commas allowed");
                        nextPrompt = false;
                    }
                    TodoItem newItem = new TodoItem(enteredDate,enteredDescription,importance);
                    todo.addTask(newItem);
                }
            } else if(choice.equals("4")){
                todo.finalize();
                return;
            } else {
                System.out.println("Invalid input, please try again.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n");
            }
        }
        
    }

}
