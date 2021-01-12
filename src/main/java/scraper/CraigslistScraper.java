/*
Program: Craigslist Scraper
This: CraigslistScraper.java
Date: 01/06/2020
Purpose: 
This program is meant to allow the user to to do any search on Craigslist
given the zipcode. Currently the user would need to do a preliminary search with 
a search engine or search on the Craigslist global list of regions. This will 
take the search term as well as the home zipcode of the user, and the apx mileage
of the users vehicle. This will then provide the user with distance to the item 
and the apoximate fuel cost to retieve the item. 

 */
package scraper;

import java.util.Scanner;

public class CraigslistScraper {
    
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        mainLoop();
    }
    
    //==========================================================================
    //==========================================================================
    //                          main functions
    public static void mainLoop(){
        /*
        The below code is the set up. This is where most of the heavy lifting 
        is being done in building the reference objects and arrays to be parsed
        later. The only other scraping that is done later is for the specific 
        searches. 
        */
        System.out.println("Fetching craigslist data...");
        System.out.println("Succesful retrieval for the State:");
        String[][] gasTable = Web.createGasTable();
        State[] states = Web.createClStateArray(gasTable);
        openMsg();
        mainDoWhileSwitch(states);
        
    }
    /*
    ============================== char MainMenu() =============================
    Prints the main menu with the options and retrieves an input from the 
    user. THis takes the first char in the user input and passes 
    */
    private static char mainMenu(){
        System.out.println("----------------------------------------------------");
        System.out.println("                      Main Menu");
        System.out.println("----------------------------------------------------");
        System.out.println("Select an option from below");
        System.out.println("\t(1)Search\n"
                            + "\t(2)TEST\n"
                            + "\t(0)Quit");
        
        System.out.println("Enter Below: ");
        return input.next().charAt(0);
        
        
    }
    /*
    =============================== void openMsg() =============================
    This prints the opening message for the program to the user. 
    */
    public static void openMsg(){
        System.out.println("====================================================");
        System.out.println("---------------- Craigslist Scraper ----------------");
        System.out.println("====================================================");
        System.out.println("You will be able to search Craigslist in any zip \n"
                         + "code in the continental United States easily and\n"
                         + "without having to find the region for the specific\n"
                         + "zip code.");
        System.out.println("====================================================");
        System.out.println();
    }
    /*
    ======================= void mainDoWhileSwitch() ===========================
    This is the main menu switch that allows a user to perform ther own 
    search, run a TEST that provides test search criteria to show the user what
    the output will look like, or to quit the program by exiting the do while loop
    */
    public static void mainDoWhileSwitch(State[] states){
        char selection; 
        do{
            selection = mainMenu();
            switch(selection){
                case '1':{
                  searchDoWhileSwitch(states);
                    
                    break;
                }
                case '2':{
                    String[] searchData = new String[5];
                    System.out.println("The search term is 'Kayak'");
                    searchData[0] = "kayak";
                    System.out.println("The Search zip is '60007'");
                    searchData[1] = "60007";
                    System.out.println("The home zip is '61523'");
                    searchData[2] = "61523";
                    System.out.println("The search radius is '25' miles");
                    searchData[3] = "25";
                    System.out.println("The mpg is '18' mpg");
                    searchData[4] = "18";
                    craigslistSearch(states,searchData);

                    break;
                }
                case '0':{
                    exitMsg();
                    break;
                }
                default:
                {
                System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
                System.out.println("+ Try again. That is not a valid input. +");
                System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
                }
            }
        }while(selection != '0');
    }
    /*
    ============================ void exitMsg() ================================
    message that will be printed upon user exiting the program. 
    */
    public static void exitMsg(){
        System.out.println("====================================================");
        System.out.println("       Thank you for using the Nation-wide\n"
                         + "               Craigslist scraper");
        System.out.println("====================================================");
    }
   
    /*
    ======================= void searchDoWhileSwitch() =========================
    This do while loop containing the below switch allows the user to select 
    to instantiate a new search with all new criteria or, after supplying criteria
    prior will all the user to change just one of the criteria to save time
    between possible subsequent searches. 
    */
    public static void searchDoWhileSwitch(State[] states){
        char selection;
        String[] searchData = new String[5];
        int firstTime = 0;
        do{
            if(firstTime != 0)
            {
                selection = continueResponse();
            }
            else
                selection = '1';
            switch(selection){
                case '1':{
                    searchData = Validate.getSearchComponents();
                    craigslistSearch(states,searchData);
                    firstTime++;
                    break;
                }
                case '2':{
                    searchData = Validate.changeSearch(searchData, 1);
                    craigslistSearch(states,searchData);
                    break;
                }
                case '3':{
                    searchData = Validate.changeSearch(searchData, 2);
                    craigslistSearch(states,searchData);
                    break;
                    // change only searchTerm 
                }
                case '4':{
                    searchData = Validate.changeSearch(searchData, 3);
                    craigslistSearch(states,searchData);
                    break;
                }
                case '0':{
                    break;
                }
                default:
                {
                System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
                System.out.println("+ Try again. That is not a valid input. +");
                System.out.println("+ + + + + + + + + + + + + + + + + + + + + ");
                
                }
            }
            
        }while(selection != '0');
        
    }
    /*
    ============================ char continueResponse() =======================
    provides a menu to retrieve a char input from the user to be applied to the 
    searchDoWhileSwitch() method. 
    */
    private static char continueResponse(){
        System.out.println("");
        System.out.println("What would you like to do: \n"
                + "(1)Entirely new search\n"
                + "(2)Change zipcode to search\n"
                + "(3)Change search term\n"
                + "(4)Change search radius\n"
                + "(0)Return to Main Menu");
        System.out.println("Enter Below: ");
        return input.next().charAt(0);
 
    }
    /*
    ========================= String getSearchUrl() ============================
    retrieves the appropriate region url for the given search zip and 
    concatenates the url needed to perform the given search with the collected 
    criteria.
    */
    private static String getSearchUrl(State state, String[] searchData){
        String query = searchData[0];
        String searchZip = searchData[1];
        String radius = searchData[3];
    
        String baseUrl = state.getRegionUrl(searchZip);
        String searchPageUrl = baseUrl + "search/sss?query=" + query + 
                "&sort=rel&search_distance=" + radius + "&postal=" + searchZip;
        return searchPageUrl;
        
    }
    /*
    ======================== void craigsllistSearch() ==========================
    Combines several other methods in the Web class to retrieve the appropriate 
    data to supply the method Web.printSearchOutput() so it can be called. This
    prints the output for any specific search. 
    */
    private static void craigslistSearch(State[] states, String[] searchData){
        double toZipDist;
        State searchStateObject = Web.getStateObject(
                            states, Web.getState(searchData[1]));
        State homeStateObject = Web.getStateObject(states,
                Web.getState(searchData[2]));
        String searchUrl = getSearchUrl(searchStateObject,searchData);
            if(searchData[1].equals(searchData[2]))
                toZipDist = 0;
            else
                toZipDist = Web.zipToZipDist(searchData[1], searchData[2]);
            
        Web.printSearchOutput(searchUrl,homeStateObject, toZipDist,searchData[4]);
    }
    
}

