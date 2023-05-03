/**
 * Class that represents a given date.
 * 
 * @author Matthew Wackerfuss
 *
 */
public class Date implements Comparable<Date>{
    /**
     * Integer that represents the month.
     */
    private int month;
    /**
     * Integer that represents the day.
     */
    private int day;
    /**
     * Integer that represents the year.
     */
    private int year;
    
    /**
     * Constructor that takes in a year, month, and day.
     * 
     * @param year The year of the date.
     * @param month The month of the date.
     * @param day The day of the date.
     */
    public Date(int year,int month,int day) {
        this.month = month;
        this.day = day;
        this.year = year;
        
    }
    /**
     * Static factory method that creates a date from a YYYYMMDD String.
     * 
     * @param YYYYMMDD A String with the form of YYYYMMDD.
     * @return Returns a new Date object with the year, month, and date from the YYYYMMDD String.
     */
    public static Date fromYYYYMMDDString(String YYYYMMDD) {
        int year;
        int day;
        int month;
        double dateAsInt;
        if(YYYYMMDD.length() != 8) {
            throw new IllegalArgumentException();
        }
        try {
            dateAsInt = Integer.parseInt(YYYYMMDD);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        year = (int) dateAsInt / 10000;
        month = (int) (dateAsInt - (year * 10000)) / 100;
        day = (int) dateAsInt - (year *10000 ) - (month * 100);
        
        Date date = new Date(year,month,day);
        if(!date.isValidDate(date)) {
            throw new NumberFormatException();
        }
        return date;
    }
    /**
     * Static factory method that creates a date from a YYYY-MM-DD String.
     * 
     * @param YYYYMMDD A String with the form of YYYYMMDD.
     * @return Returns a new Date object with the year, month, and date from the YYYY-MM-DD String.
     */
    public static Date fromYYYYMMDDDashString(String YYYYMMDD) { 
        YYYYMMDD = YYYYMMDD.replace("-", "");
        return Date.fromYYYYMMDDString(YYYYMMDD);
    }
    
    /**
     * Gets the date and returns it as a String in the from of YYYYMMDD.
     * 
     * @return A String in the form of YYYYMMDD.
     */
    public int getAsYYYYMMDD() {
        return year*10000 + month*100 + day;
    }
    /**
     * Method to checks if two dates are equal.
     * 
     * @param Another object to check equality.
     * @return A boolean that returns true if the two dates are equal, and false otherwise.
     */
    @Override 
    public boolean equals(Object other) {
        if(other==null) {
            return false;
        } else if(other.getClass() != this.getClass()) {
            return false;
        }
        Date otherAsDate = (Date)other;
        if(this.day == otherAsDate.day && this.month == otherAsDate.month && this.year == otherAsDate.year) {
            return true;
        }
        return false;
    }
    /**
     * Method that compares a dates with another.
     * 
     * @param other Another date to be compared.
     * @return An integer that equals -1 if the date is smaller than the other, 1 if the date is larger, and 0 if they are equal.
     */
    @Override
    public int compareTo(Date other) {
        if(this.equals(other)) {
            return 0;
        }else if(this.year < other.year) {
            return -1;
        } else if(this.year > other.year) {
            return 1;
        } else {
            if(this.month < other.month) {
                return -1;
            } else if(this.month > other.month) {
                return 1;
            } else {
                if(this.day < other.day) {
                    return -1;
                } else if(this.day > other.day) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    /**
     * This method checks to see if a given year is a leap year.
     * 
     * @param inputYear The year that is to be checked.
     * @return True if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear(int inputYear) {
        if(inputYear%100 == 0 && inputYear%400 != 0) {
            return false;
        }
        else if(inputYear%4 == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * This method checks to see if an inputed date is valid.
     * 
     * @param date The date with a month, date, year to be checked if valid.
     * @return True if the date is valid and false if not. 
     */
    private boolean isValidDate(Date date) {
        int inputDay = date.day;
        int inputMonth = date.month;
        // A list of all the end days of each month, where the 1st element is Jan, and so on. 
        int[] validEndDays = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (isLeapYear(date.year) == true) {
            validEndDays[1] = 29;
        }
        // This if Statement checks to see if the month is less than or equal to 12.
        // and it checks to see if the inputed day is less than or equal to the last day of the month.
        if(inputMonth > 0 && inputMonth <= 12 && inputDay <= validEndDays[inputMonth -1]) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Method that returns a String for a Date object.
     * 
     * @return A String in the from of Month/Day/Year.
     */
    @Override
    public String toString() {
        return month+"/"+day+"/"+year;
    }
    
}
