# ATU Estate Agents App

A JavaFX-based desktop application developed as part of a programming assignment.

---

## Overview

This JavaFX application allows users to input, manage, and analyse property data through an interactive graphical user interface. It simulates a simple estate agency system for managing property listings.

---

## Features

* Add new properties
* View all properties
* Remove the last property added
* Update property prices
* Find the cheapest property
* Calculate the average price
* Count properties by type

---

## Technologies Used

* Java
* JavaFX
* Object-Oriented Programming (OOP)
* ArrayList

---

## Project Structure

* `ATUEstateAgentsApp.java` → Main JavaFX application (UI + logic)
* `Property.java` → Class representing a property (provided by lecturer)

---

## How to Run

1. Make sure Java and JavaFX are installed
2. Clone this repository:

   ```bash
   git clone https://github.com/cassiana-it/atu-estate-agents.git
   ```
3. Open the project in your IDE (e.g. IntelliJ, Eclipse, jGRASP)
4. Run `ATUEstateAgentsApp.java`

---

## User Interface

The application uses JavaFX layouts:

* `VBox` for main structure
* `HBox` for grouped inputs and buttons

---

## OOP Concepts Demonstrated

* Encapsulation (private fields + getters/setters)
* Constructors (multiple constructors in Property class)
* Method separation
* Object interaction (App ↔ Property class)

---

## Validation

* Input validation for empty fields
* Numeric validation (beds, baths, price)
* Price must be positive

---

## Author

Cassiana de Oliveira

---

## Notes

* The `Property` class was provided by the lecturer
* This project focuses on JavaFX and core Java concepts

---

## Future Improvements

* Add search functionality (by town or price range)
* Improve UI styling (CSS for JavaFX)
* Save/load data from file or database
* Allow editing of all property fields

---
