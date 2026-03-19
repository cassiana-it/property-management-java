/*
Property class provided by lecturer for Assignment 1.
Used as part of ATU Estate Agents JavaFX Application.

Author: CD
*/

public class Property
{
   // Declare instance variables
   private int propertyNo;
   private String propertyStreet;
   private String propertyTown;
   private String propertyCounty;
   private String propertyType;
   private int noBeds;
   private int noBaths;
   private int noReception;
   private double price;
   private static int propNum = 100;

   // Default constructor
   public Property()
   {
      propertyNo = propNum++; // Creates different property numbers
      propertyStreet = "";
      propertyTown = "";
      propertyCounty = "";
      propertyType = "";
      noBeds = 0;
      noBaths = 0;
      noReception = 0;
      price = 0.00;
   }

   // Constructor 2: address only
   public Property(String street, String town, String county)
   {
      propertyNo = propNum++;
      propertyStreet = street;
      propertyTown = town;
      propertyCounty = county;
      propertyType = "";
      noBeds = 0;
      noBaths = 0;
      noReception = 0;
      price = 0.00;
   }

   // Constructor 3: full details
   public Property(String street, String town, String county, int numBeds, int numBaths, int numReception, double propPrice, String type)
   {
      propertyNo = propNum++;
      propertyStreet = street;
      propertyTown = town;
      propertyCounty = county;
      propertyType = type;
      noBeds = numBeds;
      noBaths = numBaths;
      noReception = numReception;
      price = propPrice;
   }

   // View property details
   @Override
   public String toString() 
   {
    return "------------------------\n" +
           "Property No: " + propertyNo + "\n" +
           "Street: " + propertyStreet + "\n" +
           "Town: " + propertyTown + " | County: " + propertyCounty + "\n" +
           "Beds: " + noBeds + " | Baths: " + noBaths + " | Receptions: " + noReception + "\n" +
           "Type: " + propertyType + "\n" +
           "Price: €" + String.format("%,.2f", price) + "\n" +
           "------------------------\n";
   }
       
   // Accessor methods
   public int viewPropNo() { return propertyNo; }
   public String viewStreet() { return propertyStreet; }
   public String viewTown() { return propertyTown; }
   public String viewCounty() { return propertyCounty; }
   public int viewBeds() { return noBeds; }
   public int viewBaths() { return noBaths; }
   public int viewReceptions() { return noReception; }
   public double viewPrice() { return price; }
   public String viewPropertyType() { return propertyType; }

   // Mutator methods
   public void setStreet(String street) { propertyStreet = street; }
   public void setTown(String town) { propertyTown = town; }
   public void setCounty(String county) { propertyCounty = county; }
   public void setNoBeds(int numBeds) { noBeds = numBeds; }
   public void setNoBaths(int numBaths) { noBaths = numBaths; }
   public void setNoReceptions(int numReceptions) { noReception = numReceptions; }
   public void setPrice(double propPrice) { price = propPrice; }
   public void setType(String type) { propertyType = type; }

} // End of class