# Integrated Management System (IMS)

This Group project is a multi-module Integrated Management System (IMS) developed in Java, featuring a graphical user interface (GUI) for managing various operational aspects.

## IDE

This Java project can be developed and managed using any standard Java IDE, such as:

* IntelliJ IDEA
* Eclipse
* Visual Studio Code with Java extensions

## Project Structure & Modules

The IMS is composed of several distinct modules, each handling a specific domain, all accessible via a central dashboard:

* **`GUI`**: The main entry point of the application, providing a dashboard to launch and interact with the different management modules.

* **Banking Task Management (`BankingTaskManagement`)**:
    * Manages bank accounts, including creation, deposits, withdrawals, and interest processing.
    * Features a task management system for banking-related activities, categorizing tasks by priority.
    * **Core Classes**: `BankAccount.java`, `BankingTaskManager.java`, `TaskManager.java`, `BankingTaskManagementGUI.java`.

* **Restaurant Management (`RestaurantManagement`)**:
    * Manages restaurant operations, including ingredient inventory, meal creation, customer orders, and billing.
    * Integrates with the Banking module for payment processing.
    * **Core Classes**: `Ingredient.java`, `Meal.java`, `Priceable.java`, `RestaurantBilling.java`, `RestaurantBillingSystem.java`, `RestaurantManagementGUI.java`.

* **Shape Parsing and Analysis (`ShapeManagement`)**:
    * Parses shape definitions (e.g., circle, square, rectangle, triangle) from text files.
    * Performs analysis on parsed shapes, such as counting by type and calculating total area.
    * Includes a `BounceBox` framework for visualizing shapes.
    * Features functionality to generate and visualize bar charts, for example, to display animal counts from the Zoo Management module.
    * **Core Classes**: `ShapesParsingAndAnalysisGUI.java`, and classes within the `bouncebox` and `bounceboxframework` subpackages.

* **Zoo Management (`ZooManagement`)**:
    * Manages zoo operations, including registration of zoos, adding and moving animals, and listing animals.
    * Handles logistics aspects like transport costs.
    * Integrates with the Banking module for depositing ticket sales.
    * **Core Classes**: `Animal.java`, `Item.java`, `Logistics.java`, `Zoo.java`, `ZooManagementGUI.java`.

## Usage

To run the Integrated Management System:

1.  Ensure you have a Java Development Kit (JDK) installed and configured on your system.
2.  Compile all `.java` files within the `src` directory.
3.  Run the main dashboard class: `gui`.

This will launch the main IMS dashboard, from which you can navigate to the various management modules.

## Class/Function Introduction (Project Modules)

This section is adapted from the provided template to describe the Java modules in this project.

### Banking Task Management

> Handles banking operations and task management.

| Class/Component            | Functionality                                                                 |
|:---------------------------| :---------------------------------------------------------------------------- |
| `BankingTaskManagementGUI` | Provides the user interface for creating accounts, making transactions, and managing tasks. |
| `BankingTaskManager`       | Manages bank accounts and tasks, interfacing with `BankAccount` and `TaskManager`. |
| `BankAccount`              | Represents a bank account with balance, interest rate, and transaction history. |
| `TaskManager`              | Manages lists of high and low priority tasks.                                 |
| **Key Operations**         | Create accounts, deposit/withdraw funds, process monthly interest, add/remove/prioritize tasks. |

### Restaurant Management

> Manages restaurant inventory, menu, orders, and billing.

| Class/Component             | Functionality                                                               |
| :-------------------------- | :-------------------------------------------------------------------------- |
| `RestaurantManagementGUI`   | Provides the UI for managing ingredients, creating meals, placing orders, and processing payments. |
| `RestaurantBilling`         | Handles order processing and bill calculation.                              |
| `Ingredient`                | Represents a food ingredient with a name and price.                         |
| `Meal`                      | Represents a menu item composed of ingredients, with a calculated price.    |
| **Key Operations** | Add ingredients, create meals, add meals to menu, place customer orders, calculate bills, process payments (via BankingService). |

### Shape Parsing and Analysis

> Parses shape data from files, performs analysis, and provides visualization.

| Class/Component                 | Functionality                                                                 |
| :------------------------------ | :---------------------------------------------------------------------------- |
| `ShapeManagementGUI`   | UI for loading shape files, initiating parsing, displaying analysis results, and launching visualizations. |
| `bouncebox` & `bounceboxframework` | A collection of classes (e.g., `Circle`, `Square`, `Rectangle`, `Triangle`, `BounceBox`) for defining and visualizing shapes in a simple physics environment. |
| **Key Operations** | Parse shape data from `.txt` files, count shapes, calculate total area/mass, visualize parsed shapes, visualize data (e.g., animal counts) as bar charts. |

### Zoo Management

> Manages zoo facilities, animals, and related logistics.

| Class/Component             | Functionality                                                               |
| :-------------------------- | :-------------------------------------------------------------------------- |
| `ZooManagementGUI`          | UI for managing zoos, animals (add, remove, move, find), and depositing ticket sales. |
| `Zoo`                       | Represents an individual zoo facility holding a collection of animals.      |
| `Animal`                    | Represents an animal with a name, species, and age.                         |
| `Item` & `Logistics`        | Used for managing items like transport vehicles and fuel for logistics.       |
| **Key Operations** | Register new zoos, add animals to zoos, move animals between zoos, list animals, find animals, calculate transport costs, deposit ticket sales (via BankingService). |

## Testing

The project includes a suite of JUnit tests to verify the functionality of the core service/manager classes within each module. These can be found in the `test` directory, under their respective module packages:

* `BankingTaskManagement.BankingServiceTest`
* `RestaurantManagement.RestaurantServiceTest`
* `ShapeParsingAndAnalysis.ShapeServiceTest`
* `ZooManagement.ZooManagerTest`

These tests cover various scenarios, including valid operations, edge cases, and error handling, ensuring the robustness of the business logic.