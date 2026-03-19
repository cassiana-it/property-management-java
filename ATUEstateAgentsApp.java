/*
ATU Estate Agents Application
Author: Cassiana de Oliveira

JavaFX application developed to manage property listings.
Users can add, view, update, and analyse property data.

This application uses the Property class provided by lecturer.
*/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ATUEstateAgentsApp extends Application 
{
    //creating an ArrayList to store Property objects
    private final ArrayList<Property> properties = new ArrayList<>();

    //creating text fields and text area for user inputs and displaying information
    private TextField tfStreet = new TextField();
    private TextField tfTown = new TextField();
    private TextField tfCounty = new TextField();
    private TextField tfBeds = new TextField();
    private TextField tfReceptions = new TextField();
    private TextField tfBath = new TextField();
    private TextField tfType = new TextField();
    private TextField tfPrice = new TextField();
    private TextArea taDisplay = new TextArea(); //text area to display results and feedback to the user

    @Override
    public void start(Stage stage) 
    {
        //creating a VBox to arrange elements vertically with space between them
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER); //aligning everything in the centre

         //creating a label for the title and centre it
        Label titleLabel = new Label("**** Enter Property Details ****");
        titleLabel.setAlignment(Pos.CENTER);

        //creating HBox for the first row of input fields
        HBox row1 = new HBox(10); //horizontal box with 10px spacing
        row1.setAlignment(Pos.CENTER);
        row1.getChildren().addAll(
            new Label("Street:"), tfStreet,
            new Label("Town:"), tfTown,
            new Label("County:"), tfCounty
        );
        
        //creating HBox for the second row of input fields
        HBox row2 = new HBox(10); 
        row2.setAlignment(Pos.CENTER);
        row2.getChildren().addAll(
            new Label("Beds:"), tfBeds,
            new Label("Receptions:"), tfReceptions,
            new Label("Bath:"), tfBath,
            new Label("Type:"), tfType,
            new Label("Price:"), tfPrice
        );

         //setting preferred width for text fields with numeric values
        tfBeds.setPrefWidth(40);
        tfReceptions.setPrefWidth(40);
        tfBath.setPrefWidth(40);

        //label for the section with property functions
        Label propFuncLabel = new Label("**** Property Functions ****");
        propFuncLabel.setAlignment(Pos.CENTER);
        
        //creating HBox for buttons to perform property actions
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button btnAdd = new Button("Add Property");
        Button btnViewAll = new Button("View All");
        Button btnRemove = new Button("Remove Last");
        Button btnUpdate = new Button("Update Price");
        buttonBox.getChildren().addAll(btnAdd, btnViewAll, btnRemove, btnUpdate); //adding buttons to the button container

        //setting text area to display properties
        taDisplay.setEditable(false); //don't allow user to edit
        taDisplay.setPrefRowCount(10); //setting number of rows
        
        //label for the custom functions section      
        Label customFuncLabel = new Label("**** Custom Functions ****");
        customFuncLabel.setAlignment(Pos.CENTER);
        
        //creating HBox for custom function buttons
        HBox customButtons = new HBox(10);
        customButtons.setAlignment(Pos.CENTER);
        Button btnCheapest = new Button("Find Cheapest"); //find the cheapest property
        Button btnAveragePrice = new Button("Average Price"); //calculate average price
        Button btnCountType = new Button("Count by Type"); //count properties by type
        customButtons.getChildren().addAll(btnCheapest, btnAveragePrice, btnCountType);

        //adding everything to the main layout (root)
        root.getChildren().addAll(
            titleLabel,
            row1,
            row2,
            propFuncLabel,  
            buttonBox,
            taDisplay,
            customFuncLabel,  
            customButtons
        );

       //event handlers for button clicks
        btnAdd.setOnAction(e -> addProperty());                  //call addProperty when Add button is clicked
        btnViewAll.setOnAction(e -> viewAllProperties());        //call viewAllProperties when View All button is clicked
        btnRemove.setOnAction(e -> removeProperty());            //call removeProperty when Remove Last button is clicked
        btnUpdate.setOnAction(e -> updatePropertyPrice());       //call updatePropertyPrice when Update Price button is clicked
        btnCheapest.setOnAction(e -> findCheapestProperty());    //call findCheapestProperty when Find Cheapest button is clicked
        btnAveragePrice.setOnAction(e -> calculateAveragePrice()); //call calculateAveragePrice when Average Price button is clicked
        btnCountType.setOnAction(e -> countPropertiesByType());  //call countPropertiesByType when Count by Type button is clicked

        //creating a scene and display the stage
        Scene scene = new Scene(root, 800, 650);  
        stage.setTitle("ATU Estate Agents"); //setting window title
        stage.setScene(scene); //setting the scene
        stage.show(); //displaying the window
    }

   //finding the cheapest property in the list and display it
    public void findCheapestProperty()
    {
        if (properties.isEmpty()) //if properties list is empty
        {
            taDisplay.setText("No properties available"); //display error message
            return;
        }

        Property cheapest = properties.get(0); //assume that first property is the cheapest
        for (Property p : properties) 
        {
            if (p.viewPrice() < cheapest.viewPrice()) //checking if the current property is cheaper
            {
                cheapest = p; //update to cheapest property
            }
        }
        taDisplay.setText("Cheapest property:\n" + cheapest); //display the cheapest property
    }

   //calculate and display the average price of all properties
    public void calculateAveragePrice() 
    {
        if (properties.isEmpty()) //if properties list is empty
        {
            taDisplay.setText("No properties available"); //display error message
            return;
        }

        double total = 0; //initializing sum of prices
        
        for (Property p : properties) //calculating sum of all property prices
        {
            total += p.viewPrice(); //adding price of each property to total
        }
        double average = total / properties.size(); //calculate average price
        taDisplay.setText(String.format("Average price: %.2f", average)); //display average price with 2 decimal places
    }

   //counting properties by type and display the count
    public void countPropertiesByType() 
    {
        if (properties.isEmpty()) //if properties list is empty
        {
            taDisplay.setText("No properties available"); //display message
            return;
        }

        String searchType = tfType.getText().trim(); //getting the property type to search for from the input field
        
        if (searchType.isEmpty()) //checking if type field is not empty
        {
            taDisplay.setText("Please enter a property type first"); //display message if type field is blank
            return;
        }

        int count = 0;
        for (Property p : properties) 
        {
            if (p.viewPropertyType().equalsIgnoreCase(searchType)) //case-insensitive checking if the property matches the search type
            {
                count++;
            }
        }
        taDisplay.setText(String.format("Found %d properties of type: %s", count, searchType));
    }

   //adding new property
   public void addProperty()
   {
      //checking for empty fields
    if (tfStreet.getText().isEmpty() || tfTown.getText().isEmpty() || 
        tfCounty.getText().isEmpty() || tfBeds.getText().isEmpty() || 
        tfBath.getText().isEmpty() || tfReceptions.getText().isEmpty() || 
        tfPrice.getText().isEmpty() || tfType.getText().isEmpty()) 
     {
        taDisplay.setText("Error: All fields must be filled!"); //display error message
        return;
     }
    
    try
    {
      int beds = Integer.parseInt(tfBeds.getText()); //convert
      int baths = Integer.parseInt(tfBath.getText());
      int receptions = Integer.parseInt(tfReceptions.getText());
      double price = Double.parseDouble(tfPrice.getText());
      
      //additional validation for price - must be positive
      if (price <= 0) 
      {
        taDisplay.setText("Error: Price must be positive!"); //display error message
        return;
      }

      Property p = new Property(
        tfStreet.getText(), //get street from text field
        tfTown.getText(), //get town from text field
        tfCounty.getText(), //get county from text field
        beds, 
        baths,
        receptions,
        price,
        tfType.getText() //get property type from text field
        );
    
      properties.add(p); //adding property
      taDisplay.setText("Added: " + p); //confirmation message
      clearInputFields();
     }
   
      catch (NumberFormatException e) //catching if the input is a number
      {
       taDisplay.setText("Error: Invalid number in fields!"); //display error message
       }
   }
      
   //helper method to clear all input fields
   public void clearInputFields() 
   {
    tfStreet.clear();
    tfTown.clear();
    tfCounty.clear();
    tfBeds.clear();
    tfBath.clear();
    tfReceptions.clear();
    tfPrice.clear();
    tfType.clear();
    }

    //view all properties in the list
    public void viewAllProperties() 
    {
       if (properties.isEmpty()) //if properties list is empty
       {
        taDisplay.setText("No properties available."); //display error message
        return;
       }
       
        StringBuilder sb = new StringBuilder(); //using StringBuilder for efficient string concatenation
        for (Property p : properties) 
        {
          sb.append(p.toString()) //adding property string representation
          .append("\n"); //adding a new line after each property
        }
        
         taDisplay.setText(sb.toString()); //display all properties
    
    }

    //remove the last property added to the list
    public void removeProperty() 
    {
        if (!properties.isEmpty()) //if there are properties
        {
            Property removed = properties.remove(properties.size() - 1); //removing
            taDisplay.setText("Removed: " + removed); //display message
        } 
        
        else //if property list is empty
        {
            taDisplay.setText("No properties to remove");  //display message
        }
    }

    //update the price of the last property in the list
    public void updatePropertyPrice()
    {
      if (!properties.isEmpty()) //if there are properties
      {
         try 
         {
            double price = Double.parseDouble(tfPrice.getText()); //convert first

            if (price <= 0) //validate
            {
                taDisplay.setText("Error: Price must be positive!");
                return;
            }

            Property p = properties.get(properties.size() - 1); //get last property
            p.setPrice(price); //update

            taDisplay.setText("Updated price: " + p); //display updated price
         } 
        
         catch (NumberFormatException e) //if there's an error converting input to number
         {
            taDisplay.setText("Error: Invalid price!"); //display error message
         }
     } 
    
     else //if property list is empty
     {
        taDisplay.setText("No properties to update"); //display message
     }
   }

    public static void main(String[] args) 
    {
        launch(args); //lauching JavaFX application
    }
}