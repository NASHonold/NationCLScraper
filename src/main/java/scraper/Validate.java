/*
Program: Craigslist Scraper
This: Validate.java
Date: 01/06/2020
Purpose: 
This class contains methods that are used to check and verify data. It can be used
to also parse data to be utilized by other methods in the Web class. 
*/
package scraper;

import java.util.Scanner;


public class Validate {
public static Scanner input = new Scanner(System.in);
    
    /*
    ============================== Class Methods ===============================
    */
    
    /*
    ============================ String cleanRegion() ==========================
    Takes craigslist region and converts it to a respective zipcode. This 
    will provide the State object with zipcodes for each region. This zip will be
    used to determine what region is the closest to the search zip so the 
    appropriate craigslist url can be accessed for search results. 
    */
    public static String cleanRegion(String region){
        String regionAfter = noDirections(region);
        if(regionAfter.contains("/")){
            int index = regionAfter.indexOf("/");
            regionAfter = regionAfter.substring(0, index);        
        }
        if(regionAfter.contains("-")){
            int index = regionAfter.indexOf('-');
            regionAfter = regionAfter.substring(0, index);
        }
        return regionAfter;
    }   
    
    /*
    =========================== String noDirections() =========================
    Returns NA for any string that contains a cardinal direction as a description
    of the craigslist region. This is done as the methods that return a zip code
    for the region are unable to determine a zip code with general type of 
    description such as "Southeast Nebraska".
    */
    public static String noDirections(String region){
        String[] noGo = {"northwest", "northeast", "southwest", "southeast",
            "northern","southern", "eastern", "western"};
        String cleanRegion = "";
    for (String noGo1 : noGo) {
        if (region.contains(noGo1)) {
            cleanRegion = "NA";
            break;
        } else {
            cleanRegion = region;
        }
        return cleanRegion;
    }
        return cleanRegion;
    }
    /*
    =========================== String[] getSearchComponents() =================
    THis method is responsible for capturing user input, validating it, and 
    creating a string array from it that will be passed to other methods
    for specific searches. this allows each string in the array to be manipulated
    singularly later without having to create an entirely new list of search criteria. 
    
    */
    public static String[] getSearchComponents(){
        /*
        indices:
        0 = search query
        1 = search zipcode
        2 = home zipcode
        3 = radius
        4 = mileage
        */
        String [] components = new String[5];
        String query = Validate.getQuery();
        String searchZip = Validate.getUserSearchZip();
        String homeZip = Validate.getUserHomeZip();
        String radius = Validate.getSearchRadius();
        String mileage = Validate.getMileage();
        components[0] = query;
        components[1] =  searchZip;
        components[2] =  homeZip;
        components[3] =  radius;
        components[4] =  mileage;
        return components;
        
        
    }
    /*
    ======================== String[] changeSearch() ===========================
    Takes as argument the searchData string which contains, query, searchZip, 
    homeZip, search radius, and mileage. Uses the second int argument as a flag 
    for what string object to change. Calls the appropriate Validate class method
    to take user input for the specific String data. 
    */
    public static String[] changeSearch(String[] searchData, int termIndicator){
    switch (termIndicator) {
        case 1:
            searchData[1] = Validate.getUserSearchZip();
            return searchData;
        case 2:
            searchData[0] = Validate.getQuery();
            return searchData;
        default:
            searchData[3] = Validate.getSearchRadius();
            return searchData;
    }
        
    }
     
    /*
    =========================== String getUserHomeZip() ========================
    Gets the users zipcode to be referenced as the homeZip in the String[] 
    searchData. Validates the input before allowing the user to progress to 
    insure proper fuctionality. 
    */
    public static String getUserHomeZip(){
        boolean run = true;
        String zip= "";
        
        do{
            System.out.println("What is the zipcode you are\n"
                    + "currently located in?");
            System.out.println("Enter Below:");
            zip = input.next().trim();
            run = !Validate.zipCheck1(zip);
            if(run == true){
                System.out.println("You must only enter a 5 digit zipcode.\n"
                                 + "            Try Again.");
                System.out.println("----------------------------------------------------");
        }
        }while(run);
        System.out.println("----------------------------------------------------");
        return zip;
    }
    
    /*
    ========================== String getUserSearchZip() =======================
    Takes the user input for search zipcode and validates it before allowing the
    user to proceed to insure proper fuctionality
    */
    public static String getUserSearchZip(){
        
        boolean run = true;
        String zip= "";
        
        do{
            System.out.println("What is the zipcode of the area\n"
                + "area you would like to search in? ");
            System.out.println("Enter Below:");
            zip = input.next().trim();
            run = !Validate.zipCheck1(zip);
            if(run == true){
                System.out.println("You must only enter a 5 digit zipcode.\n"
                                 + "            Try Again.");
                System.out.println("----------------------------------------------------");
            }
        }while(run);
        System.out.println("----------------------------------------------------");
        return zip;
    } 
    
    
    /*
    ========================== String getSearchRadius() ========================
    Takes the user input for search radius and validates it before allowing the 
    user to proceed to insure funcionality. 
    */
    public static String getSearchRadius(){
        
        boolean run = true;
        String radiu= "";
        
        do{
            System.out.println("What is the radius you would like to search?");
            System.out.println("Enter Below:");
            radiu = input.next().trim();
            run = !Validate.distCheck1(radiu);
            if(run == true){
                System.out.println("You must only enter a value \n"
                        + "between 10 and 999.\n"
                        + "            Try Again.");
                System.out.println("----------------------------------------------------");
            }
        }while(run);
        System.out.println("----------------------------------------------------");
        return radiu;
    }
    
    
    /*
    ============================ String getQuery() =============================
    Takes the user input for search query and validates it before allowing the 
    user to proceed to insure funcionality. 
    */
    public static String getQuery(){
        
        boolean run = true;
        String query= "";
        
        do{
        System.out.println("----------------------------------------------------");
            System.out.println("What do you want to search?");
            System.out.println("Enter Below:");
            query = input.next().trim();
            run = !lengthCheck1(query,0,30);
            if(run == true){
                System.out.println("You must only enter a number value \n"
                        + "between 10 and 999.\n"
                                 + "            Try Again.");
                System.out.println("----------------------------------------------------");
            }
        }while(run);
        System.out.println("----------------------------------------------------");
        return query;
    } 
    /*
    ============================ String getMileage() =============================
    Takes the user input for search mileage and validates it before allowing the 
    user to proceed to insure funcionality. 
    */
    public static String getMileage(){
        boolean run = true;
        String mileage = "";
        do{System.out.println("What is the miles per gallon of your vehicle?\n"
                + "*enter a number value between 0 and 100 \n"
                + "**If you own an electric vehicle enter \"0\"**");
            System.out.println("Enter Below:");
            mileage = input.next().trim();
            run = !mileageCheck1(mileage);
            if(run){
                System.out.println("Only enter a number value for mileage.\n"
                                 + "             Try Again.");
                System.out.println("----------------------------------------------------");
            }
        }while(run);
        System.out.println("----------------------------------------------------");
        return mileage;
    }
    
   /*
    ============================ boolean zipCheck1() ==========================
    Takes a string argument and ensures it meets the criteria for a 5 digit
    zipcode in the US.
    *security measure
    */
    public static boolean zipCheck1(String zip){
        String workingZip = zip.trim();
        if(workingZip.matches("\\d{5}"))
            return true;
        else
            return false;
        
    }
    /*
    ============================ boolean distCheck1() ==========================
    Takes a string argument and validates that is between 10-999. There should be 
    no other type of input from the user in this field. 
    *security measure
    */
    public static boolean distCheck1(String dist){
        String workingDist = dist.trim();
        if(workingDist.matches("\\d{2,3}"))
            return true;
        else
            return false;
        
    }
    /*
    =========================== boolean lengthCheck() ==========================
    Takes the searchterm String and validates for minimum and maximum length.
    *security measure
    */
    public static boolean lengthCheck1(String searchTerm, int minimum, int maximum){
        String term = searchTerm.trim();
        if(term.length() > maximum)
            return false;
        else if(term.length() == minimum)
            return false;
        else 
            return true;
    }
    
    /*
    =========================== boolean mileageCheck1() ========================
    validates the string is an appropiate value given the String data's specific
    use as vehicle mileage.
    */
    public static boolean mileageCheck1(String mileage){
        try{
            double  doubleMileage = Double.parseDouble(mileage);
            if(doubleMileage >= 0 && doubleMileage < 100){
                return true;
            }
            else{
                return false;
            }
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    
}
