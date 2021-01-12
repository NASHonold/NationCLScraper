/*
Program: Craigslist Scraper
This: Item.java
Date: 01/06/2020
Purpose: 
An object class of a craislist item. This object contains the attributes:
item url,listed price, description(craigslist title), miles away from the zip code
used in the search, the general location of the item if available, state the item is in. 

 */

package scraper;

public class Item {
    
    private String url;
    private double price;
    private String description; 
    private double milesAwayMax;
    private double milesAwayMin;
    private double fuelCostMin;
    private double fuelCostMax;
    private String Location;
    private String homeState;

    
    //========================= getters/ setters ===============================
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMilesAwayMax() {
        return milesAwayMax;
    }

    public void setMilesAwayMax(double milesAwayMax) {
        this.milesAwayMax = milesAwayMax;
    }

    public double getMilesAwayMin() {
        return milesAwayMin;
    }

    public void setMilesAwayMin(double mileAwayMin) {
        this.milesAwayMin = mileAwayMin;
    }

    public double getFuelCostMin() {
        return fuelCostMin;
    }

    public void setFuelCostMin(double fuelCostMin) {
        this.fuelCostMin = fuelCostMin;
    }

    public double getFuelCostMax() {
        return fuelCostMax;
    }

    public void setFuelCostMax(double fuelCostMax) {
        this.fuelCostMax = fuelCostMax;
    }
    

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    /*
    =========================== void printItem() ===============================
    outputs the attributes of the Item in a user friendly manner. 
    */
    public void printItem(){
        System.out.println("Title: " + this.getDescription());
        System.out.println("Price: $" + this.getPrice());
        System.out.println("Location: " + this.getLocation());
        System.out.println("Distance from home between: " +
                this.getMilesAwayMin() + " and " + this.getMilesAwayMax() );
        System.out.println("Fuel cost for retrieval(round trip):\n\t"
                + "$" + this.getFuelCostMin() + " to $" + this.getFuelCostMax());
        System.out.println("URL: \n" + this.getUrl() + "\n");
        System.out.println("****************************************************\n");
    }
    
}
