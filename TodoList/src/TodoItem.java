/**
 * The class that represents each individual task.
 * 
 * @author Matthew Wackerfuss
 * 
 */
public class TodoItem{
    
    /**
     * The date of the task.
     */
    private Date date;
    /**
     * The description of the task.
     */
    private String description;
    /**
     * The importance of the task.
     */
    private Importance importanceLevel;
    
    /**
     * Constructor of the todoItem
     * 
     * @param date The date of the new todoItem.
     * @param description The description of the new todoItem.
     * @param importance The importance of the new todoItem.
     */
    public TodoItem(Date date, String description, Importance importance) {
        this.date = date;
        this.description = description;
        importanceLevel = importance;
    }
    /**
     * Method that creates a todoItem from a CSV line of the 
     * form YYYYMMDD,description,importance.
     * 
     * @param csv The csv line that a todoItem will be created from.
     * @return A new todoItem.
     */
    public static TodoItem buildFromCSV(String csv){
        String[] csvList = csv.split(",");
        Date date;
        String description;
        Importance importance;
        if(csvList.length != 3) {
            throw new IllegalArgumentException();
        }
        
        date = Date.fromYYYYMMDDString(csvList[0]);
        description = csvList[1];
        if(csvList[2].equals("HIGH")) {
            importance = Importance.HIGH;
        } else if (csvList[2].equals("MEDIUM")) {
                importance = Importance.MEDIUM;
        } else if (csvList[2].equals("LOW")) {
            importance = Importance.LOW;
        } else {
            throw new IllegalArgumentException();
        }
        return new TodoItem(date, description, importance);
    }
    
    /**
     * Method that gets the todoItem and creates a csv.
     * 
     * @return A csv line of the from of YYYYMMDD,description,importance.
     */
    public String getAsCSV() {
        return date.getAsYYYYMMDD() + "," + description + "," + importanceLevel;
    }
    
    /**
     * Method to get the date of the todoItem.
     * @return The date as a date object.
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Method to get the importance of the todoItem.
     * 
     * @return The importance as an importance enum.
     */
    public Importance getImportanceLevel() {
        return importanceLevel;
    }
    /**
     * Method to get the description of the todoItem.
     * 
     * @return The description as a String.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Method to return the todoItem as a String.
     * 
     * @return The todoItem as a String with it's date, importance level, and description.
     */
    @Override
    public String toString() {
        return "\n* \n Date: "+ date.toString() +"\n Importance:"+ importanceLevel +"\n "+description +"\n";
    }
}
