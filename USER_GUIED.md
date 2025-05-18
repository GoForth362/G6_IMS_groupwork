# Integrated Management System - User Guide

## 1. Introduction
Welcome to the Integrated Management System (IMS). This system integrates four core modules: Banking Task Management, Restaurant Operations Management, Shape Parsing & Visualization, and Zoo Management. This guide will walk you through the basic operations of each module.

## 2. Starting the Application
1.  Please ensure you have successfully built the project according to the "Build Instructions" in the `README.md` file.
2.  In your Java IDE (e.g., IntelliJ IDEA, Eclipse), locate and run the main class: `gui`.
3.  Once the system starts, the main dashboard interface will appear.

## 3. Main Dashboard Navigation
The application window features a column of buttons corresponding to the different management modules:
* **Zoo Management**: Accesses the Zoo Management module.
* **Restaurant Management**: Accesses the Restaurant Management module.
* **Banking Task Management**: Accesses the Banking and Task Management module.
* **Shape Parsing & Analysis**: Accesses the Shape Parsing and Visualization module.

Click the respective button to switch to the desired functional panel.

---

## 4. Zoo Management Module

Access this module via the "Zoo Management" button. The interface displays a list of registered zoos, a list of animals in the selected zoo, and an actions panel on the right.

#### 1. Launch the Application
After running the `ZooManagementGUI` class, the main window of the Zoo Management System will appear. The window contains a menu with several buttons for different operations.

#### 2. Display Animals
1. **Display Southern-Zone Zoo Animals**: Click this button to view a list of animals in the Southern-Zone Zoo.
2. **Display Northern-Zone Zoo Animals**: Click this button to view a list of animals in the Northern-Zone Zoo.

#### 3. Move Animal Between Zoos
1. Click the "Move Animal Between Zoos" button.
2. Select the zoo from which you want to move the animal.
3. Select the animal you want to move.
4. Enter the vehicle cost, fuel cost, and the number of caretakers (1 - 3).
5. Enter the names of the caretakers when prompted.
6. Click the "Confirm Move" button to move the animal.

#### 4. Add New Animal to a Zoo
1. Click the "Add New Animal to a Zoo" button.
2. Select the zoo where you want to add the animal.
3. Enter the name, species, and age of the animal.
4. Click the "Add Animal" button to add the animal to the selected zoo.

#### 5. Remove Animal from a Zoo
1. Click the "Remove Animal from a Zoo" button.
2. Select the zoo from which you want to remove the animal.
3. Select the animal you want to remove.
4. Click the "Remove" button to remove the animal from the selected zoo.

#### 6. Find an Animal
1. Click the "Find an Animal" button.
2. Enter the name of the animal you want to search for.
3. Click the "Search" button. The system will display the location and details of the animal if found, or a "Not found" message if the animal is not in either zoo.

---

## 5. Restaurant Management Module

Access this module via the "Restaurant Management" button. It includes tabs for managing ingredients, creating meals, and handling orders and billing.

#### 1. Starting the Application
Launch the application by following the run instructions mentioned above. The main window of the restaurant management system will appear.

#### 2. Managing the Menu
1. **Adding a Meal**:
    - Enter the meal name in the `Meal Name` field.
    - Enter the ingredient name in the `Ingredient Name` field.
    - Enter the ingredient price in the `Ingredient Price` field.
    - Click the `Add Meal` button. If the meal already exists, the ingredient will be added to it; otherwise, a new meal will be created.
2. **Viewing the Menu**:
    - The current menu will be displayed in the right panel under `Information Display`.

#### 3. Taking Orders
1. **Adding a Meal to the Order**:
    - Enter the name of the meal you want to order in the `Enter Meal Name` field.
    - Click the `Add to Order` button. If the meal exists in the menu, it will be added to the order; otherwise, an error message will be displayed.
2. **Viewing the Order**:
    - The current order will be displayed in the right panel under `Information Display`, along with the total bill amount.

#### 4. Calculating the Bill
Click the `Calculate Bill` button to view the total bill amount for the current order.

#### 5. Clearing the Order
Click the `Clear Order` button to clear the current order. All items in the order will be removed, and the total bill will be reset to zero.

#### 6. Exiting the Application
Click the `Exit` option in the `File` menu to close the application.

---

## 6. Banking Task Management Module

Access this module by clicking the "Banking Task Management" button on the main dashboard. This module has three sub-tabs: "Account Operations," "Fund Transactions," and "Task Management."

#### 1. Create a Bank Account
1. **Open the Application**: Launch the `BankingTaskManagementGUI` application.
2. **Enter Account Information**: In the "Create Bank Account" section, enter the account number, initial balance, and annual interest rate (in percentage).
3. **Click "Create Account"**: The system will validate the input. If the input is valid, the account will be created, and a confirmation message will be displayed. The account details will also be printed in the console.

#### 2. Perform a Transaction
1. **Select Transaction Type**: In the "Banking Transactions" section, select either "Deposit" or "Withdraw" from the dropdown menu.
2. **Enter Account Number and Amount**: Enter the account number and the transaction amount.
3. **Click "Process Transaction"**: The system will perform the transaction and update the account balance. A confirmation message will be displayed, and the task will be added to the task list.

#### 3. Manage Tasks
1. **Add a Task**: In the "Task Management" section, enter a task in the text field and click "Add Task". If the task contains an asterisk (`*`), it will be added to the high - priority list; otherwise, it will be added to the low - priority list.
2. **Remove a Task**: Select a task from either the high - priority or low - priority list and click "Remove Task". The selected task will be removed from the list.
3. **Change Task Priority**: Select a task from either the high - priority or low - priority list and click "Change Priority". The task will be moved to the other priority list.

---

## 7. Shapes Parsing and Analysis Module

Access this module via the "Shape Parsing & Analysis" button on the dashboard.

#### 1. Starting the Application
When you start the application, a window titled "Shape Parsing & Analysis" will appear. This window contains a panel in the center where shapes will be displayed and two buttons at the bottom.

#### 2. Loading Shapes from a File
1. **Click the "Load File" Button**: This will open a file chooser dialog.
2. **Select a File**: Navigate to the file containing shape information and select it. The file should have each shape's information on a separate line, with the shape type (e.g., "Circle", "Square") followed by its parameters (such as position, size, velocity, and color).
3. **View the Shapes**: After selecting the file, the shapes will be loaded and displayed in the panel. They will start bouncing within the boundaries of the panel.

#### 3. Calculating the Total Area
1. **Click the "Calculate Total Area" Button**: This will calculate the total area of all the shapes currently loaded in the application.
2. **View the Result**: A dialog box will appear showing the total area of all shapes, rounded to two decimal places.

---
(End of User Guide)