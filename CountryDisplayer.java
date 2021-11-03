import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * takes spreadsheet of info about countries and displays it in different ways
 */
public class CountryDisplayer {
    
    /**
     * ArrayList holding country objects
     */
    ArrayList<Country> countries;
     
    /**
     * takes info from a specific spreadsheet and organizes it
     * reads the spreadsheet and makes a new Country object for each line
     * adds each Country object to an ArrayList of all the countries
     * @param fileName (String) the spreadsheet of info the user wants to use
     */
    public CountryDisplayer(String fileName){
        // Read the country file and load the countries into this instance of CountryDisplayer.
        String inputFilePath = fileName;
        File inputFile = new File(inputFilePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        scanner.nextLine();
        countries = new ArrayList<Country>();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineList = line.split(",");
            ArrayList<String> lineArrayList = new ArrayList<String>();
            for(int i=0; i<8; i++){
                lineArrayList.add(lineList[i]);
            }
            HashMap <String, ArrayList<String>> countryInfo = new HashMap<String, ArrayList<String>>();
            countryInfo.put(lineArrayList.get(0), lineArrayList);
            Country countryTemp = new Country(lineArrayList.get(0), countryInfo);
            countries.add(countryTemp);
        }
        scanner.close();
    }

    /**
     * tests to see if a string the user entered is a number
     * @param userString the string the user entered
     * @return (boolean) true if entry is a number, false if not
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
     * gets a Country object based on user's request of name
     * tells user if country they requested is not in the data
     * @param name (String) name of the country that the user wants to get
     * @return a Country object with name matching user request
     */
    public Country getCountryByName(String name){
        for(Country temp: countries){
            if(temp.getName().equals(name)){
                System.out.println("Country found!");
                return temp;
            }
        }
        System.out.println("Sorry, that country is not in the list. Please run the program again.");
        System.exit(0);
        return null;
    }

    /**
     * gets country with the maximum data value in a specific category
     * tells user if category they requested is not in the data
     * @param indicator (String) category of data that the user wants to know about
     * @return the Country object with max value in the requested  data category
     */
    public Country getCountryMaxIndicator(String indicator){
        Country max = countries.get(0);
       
        if(isInteger(indicator)!=true){
            System.out.println("Sorry, that indicator is not in the list.");
            System.out.println("Please run the program again and enter a number 1-7.");
            return null;
        }
        int indicatorInt = Integer.parseInt(indicator);
        if(indicatorInt<1 || indicatorInt>7){
            System.out.println("Sorry, that indicator is not in the list. Please run the program again.");
            return null;
        }
        for(Country temp: countries){
            String stringTemp = temp.getCountryInfo(temp.getName(), indicator);
            double doubleTemp = Double.parseDouble(stringTemp);
            String maxName = max.getName();
            String maxInfo = max.getCountryInfo(maxName, indicator); 
            double maxDouble = Double.parseDouble(maxInfo);
            if(doubleTemp > maxDouble){
                max = temp;
            }
        }
        return max;
    }

    /**
     * runs several methods from CountryDisplayer
     * asks the user which spreadsheet they want to use
     * creates instance of CountryDisplayer using requested spreadsheet
     * asks the user which country they want to know about, gets Country object
     * asks user what information they want to know about said country
     * prints info, or prints that that info is not available
     * asks user which category they want to know the country with the max of
     * prints country name, or prints that the info is not available
     * @param args allows for user input
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which file would you like to use? (Maybe: CountryDataset.csv)");
        String fileName = scanner.nextLine();
        CountryDisplayer file1 = new CountryDisplayer(fileName);
        System.out.println("Which Country would you like to get information about?");
        String countryRequested = scanner.nextLine();
        Country userCountry = file1.getCountryByName(countryRequested);
        System.out.println("\nWhat would you like to know about the country?");
        System.out.println("Enter the number corresponding to the info you want:");
        System.out.println("1 - total population, 2 - carbon emissions");
        System.out.println("3 - access to electricity, 4 - renewable energy consumption");
        System.out.println("5 - terrestrial protected areas, 6 - population growth");
        System.out.println("7 - urban population growth");
        String infoRequested = scanner.nextLine();
        String info = userCountry.getCountryInfo(countryRequested,infoRequested);
        if(!info.equals("")){
            System.out.println(info);
        }
        System.out.println("\nWhich indicator would you like to find the country with the max value of?");
        System.out.println("Enter the number corresponding to the info you want:");
        System.out.println("1 - total population, 2 - carbon emissions");
        System.out.println("3 - access to electricity, 4 - renewable energy consumption");
        System.out.println("5 - terrestrial protected areas, 6 - population growth");
        System.out.println("7 - urban population growth");
        String maxIndRequested = scanner.nextLine();
        Country maxCountry = file1.getCountryMaxIndicator(maxIndRequested);
        if(maxCountry != null){
            System.out.println(maxCountry.getName());
        }
        scanner.close();
        }
}
