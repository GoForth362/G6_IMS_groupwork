package RestaurantManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class RestaurantManagementGUI extends JFrame{
    protected RestaurantBilling billingSystem;

    protected JTextField mealNameField, ingredientNameField, ingredientPriceField;
    protected JTextArea menuDisplayArea, orderDisplayArea;

    public RestaurantManagementGUI() {
        billingSystem = new RestaurantBilling();

        setTitle("Restaurant Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Set up the mouse
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(new BorderLayout());

        // Create a menu area
        add(createTopMenu(), BorderLayout.NORTH);

        // Left panel - Add dishes and order
        JPanel leftPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Manage Menu and Order"));

        leftPanel.add(createAddMealPanel());
        leftPanel.add(createAddToOrderPanel());
        leftPanel.add(createFindMealPanel());
        leftPanel.add(createBillPanel());
        leftPanel.add(createClearOrderPanel());

        // Right panel - Displays menus and orders
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Information Display"));

        menuDisplayArea = new JTextArea();
        menuDisplayArea.setEditable(false);
        orderDisplayArea = new JTextArea();
        orderDisplayArea.setEditable(false);

        rightPanel.add(new JScrollPane(menuDisplayArea));
        rightPanel.add(new JScrollPane(orderDisplayArea));

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        refreshMenuDisplay();
        refreshOrderDisplay();
    }

    private JMenuBar createTopMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    private JPanel createAddMealPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Meal Name:");
        mealNameField = new JTextField();
        JLabel ingLabel = new JLabel("Ingredient Name:");
        ingredientNameField = new JTextField();
        JLabel priceLabel = new JLabel("Ingredient Price:");
        ingredientPriceField = new JTextField();
        JButton addButton = new JButton("Add Meal");

        panel.add(nameLabel);
        panel.add(mealNameField);
        panel.add(ingLabel);
        panel.add(ingredientNameField);
        panel.add(priceLabel);
        panel.add(ingredientPriceField);
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String mealName = mealNameField.getText().trim();
                String ingName = ingredientNameField.getText().trim();
                double price = Double.parseDouble(ingredientPriceField.getText().trim());

                Ingredient ingredient = new Ingredient(ingName, price);
                Meal meal = null;

                boolean found = false;
                for (Meal m : billingSystem.menu) {
                    if (m.getName().equals(mealName)) {
                        meal = m;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    meal = new Meal(mealName);
                    billingSystem.addMeal(meal);
                }

                meal.addIngredient(ingredient);
                JOptionPane.showMessageDialog(this, "Added ingredient to meal: " + mealName);
                refreshMenuDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createAddToOrderPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField orderField = new JTextField();
        JButton addToOrderBtn = new JButton("Add to Order");

        panel.add(new JLabel("Enter Meal Name:"));
        panel.add(orderField);
        panel.add(new JLabel());
        panel.add(addToOrderBtn);

        addToOrderBtn.addActionListener(e -> {
            String name = orderField.getText().trim();
            if (billingSystem.addMealToOrder(name)) {
                JOptionPane.showMessageDialog(this, "Added '" + name + "' to order.");
            } else {
                JOptionPane.showMessageDialog(this, "Meal not found in menu.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            refreshOrderDisplay();
        });

        return panel;
    }

    private JPanel createFindMealPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField findField = new JTextField();
        JButton findBtn = new JButton("Find Meal");

        panel.add(new JLabel("Meal Name:"));
        panel.add(findField);
        panel.add(new JLabel());
        panel.add(findBtn);

        findBtn.addActionListener(e -> {
            String name = findField.getText().trim();
            boolean found = false;
            for (Meal meal : billingSystem.menu) {
                if (meal.getName().equals(name)) {
                    JOptionPane.showMessageDialog(this, "Found meal:\n" + meal.toString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Meal not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return panel;
    }

    private JButton createBillPanel() {
        JButton billBtn = new JButton("Calculate Bill");
        billBtn.addActionListener(e -> {
            double total = billingSystem.calculateBill();
            JOptionPane.showMessageDialog(this, "Total Bill: RMB" + total);
        });
        return billBtn;
    }

    private JButton createClearOrderPanel() {
        JButton clearBtn = new JButton("Clear Order");
        clearBtn.addActionListener(e -> {
            billingSystem.clearOrder();
            refreshOrderDisplay();
            JOptionPane.showMessageDialog(this, "Order cleared.");
        });
        return clearBtn;
    }

    private void refreshMenuDisplay() {
        menuDisplayArea.setText("");
        if (billingSystem.menu.isEmpty()) {
            menuDisplayArea.append("The menu is currently empty.\n");
        } else {
            menuDisplayArea.append("=== Current Menu ===\n");
            for (Meal meal : billingSystem.menu) {
                menuDisplayArea.append(meal.toString() + "\n-------------------\n");
            }
        }
    }

    private void refreshOrderDisplay() {
        orderDisplayArea.setText("");
        orderDisplayArea.append("=== Current Order ===\n");
        for (Map.Entry<Meal, Integer> entry : billingSystem.mealQuantities.entrySet()) {
            orderDisplayArea.append(entry.getKey().getName() + " x " + entry.getValue() + "\n");
        }
        double total = billingSystem.calculateBill();
        orderDisplayArea.append("\nTotal: RMB" + total);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new RestaurantManagementGUI().setVisible(true);
//        });
//    }
}

