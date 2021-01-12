/*
Program: Craigslist Scraper
This: State.java
Date: 01/06/2020
Purpose: 
An object class of a US state. Contains the attributes of the state name, state
abbreviation, the current average fuel price for regular unleaded. Also included 
a 2d array that contains the names of all the craigslist regions for the state,
the associated zipcode for the region and the craigslist url that would be 
used for a search given a specific zip.  
 */
package scraper;
import java.util.ArrayList;
public class State {
    
    private String name;
    private String abbr;
    private double fuelPrice;// no set information
    private String[][] regions;
    
    
    State(){
        this.name = "NA";
    }
    
    State(String name){
        this.name = name;
    }
    //========================= State class functions ==========================
    
    
    //======================= Start of getters/setters =========================
    
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
    
    public String[][] getRegions() {
        return regions;
    }

    public void setRegions(String[][] regions) {
        this.regions = regions;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }
    
    //======================== End Setters/Getters =============================
    
    
    /*
    ========================== String getRegionUrl() ===========================
    Returns the url given a zip code as an argument. This finds the closest
    region based on zip code and will return the associated scraped url for 
    the correlating region. 
    Dependencies:
    getNearestZip()
    */
    public String getRegionUrl(String zip){
        ArrayList<String> list = new ArrayList<>();
        
        for (String[] region : regions) {
            if (!region[2].equals("NA")) {
                list.add(region[2]);
            } else {
                list.add("100000");
            }
        }
        
        String[] zipCodes = list.toArray(new String[list.size()]);
        String returnZip = getNearestZip(zipCodes, zip);
        String returnUrl = "NA";
        for (int count = 0; count < zipCodes.length; count++) {
            if(returnZip.equals(regions[count][2])){
               returnUrl = regions[count][1];
            }
        }
            return returnUrl;
    }
    
    /*
    ============================ String nearestZip() ==========================
    Returns the 5 digit zipcode in the String[] zipCodes that is closest to the 
    reference zipCode. 
    */
    private static String getNearestZip(String[] zipCodes, String comparingZip){
        Integer searchNum = Integer.parseInt(comparingZip);
        int indexOfCloseZip = 0;
        int seperation = 100000;
        for (int count = 0; count < zipCodes.length; count++){
            Integer testZip = Integer.parseInt(zipCodes[count]);
            if(testZip >= searchNum){
                if((testZip - searchNum) < seperation){
                    seperation = testZip - searchNum;
                    indexOfCloseZip = count;
                }
            }
            else{
                if((searchNum - testZip) < seperation){
                    seperation = searchNum - testZip;
                    indexOfCloseZip = count;
                }
            }
        }
        return zipCodes[indexOfCloseZip];
    }
    
    /*
    ============================ void printState() =============================
    Method prints out the states attributes. Mainly for testing purposes
    */
    public void printState(){
        System.out.println("\n\n------------ STATE ------------");
        System.out.println("State: " + this.getName());
        System.out.println("State Abbr: " + this.getAbbr());
        System.out.println("Average fuel price:" + this.getFuelPrice());
        System.out.println("------------ Regions ------------");
        for (int count = 0; count < this.regions.length; count++) {
            System.out.println("Region name: " + this.regions[count][0]);
            System.out.println("Url for above region: " + this.regions[count][1]);
            System.out.println("zip for region: " + this.regions[count][2]);
            System.out.println("--------------------\n");
            
        }
    }
    
    
}