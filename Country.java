import java.util.HashMap;
import java.util.ArrayList;

/**
 * class Country represents a country
 * holds and returns data about the country
 */
public class Country{   

    /**
     * name of Country object
     */
    private String countryName;
    /**
     * holds name of country as key and ArrayList of country info as value
     */
    HashMap<String, ArrayList<String>> countryData = new HashMap<String, ArrayList<String>>();
    
    /**
     * creates a new Country object with given name and HashMap, as defined above
     * @param countryName String name of Country
     * @param countryInfo HashMap holding country's name and info
     */
    public Country(String countryName, HashMap<String, ArrayList<String>> countryInfo){
        this.countryName = countryName;
        this.countryData = countryInfo;
    }

    /**
     * gets the country's name
     * @return String of country's name
     */
    public String getName(){
        return countryName;
    }

    /**
     * tests to see if a string the user enters is a number
     * @param userString string to be tested that user enters
     * @return (boolean) returns true if string is number, false if not
     */
    public boolean isInteger(String userString){
        try{
            Integer.parseInt(userString);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /**
     * gets a certain piece of info about the country at user request
     * tells user if category they requested is not in the data
     * @param countryName (String) name of country user would like info about
     * @param category (String) what user would like to know about the country
     * @return
     */
    public String getCountryInfo(String countryName, String category){
        if(isInteger(category)!=true){
            System.out.println("Sorry, that indicator is not in the list."); 
            System.out.println("Please run the program again and enter a number.");
            return "";
        }
        int indicatorInt = Integer.parseInt(category);
        if(indicatorInt<1 || indicatorInt>7){
            System.out.println("Sorry, that indicator is not in the list. Please run the program again.");
            return "";
        }
        return this.countryData.get(countryName).get(Integer.parseInt(category));
    }

    public static void main(String[] args) {
    }
}