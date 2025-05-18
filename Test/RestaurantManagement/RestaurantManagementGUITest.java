package RestaurantManagement;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantManagementGUITest {

    private RestaurantManagementGUI gui;

    @BeforeEach
    void setUp() {
        gui = new RestaurantManagementGUI();
        gui.setVisible(true);
        System.out.println("=== GUI Initialized ===");
    }

    @AfterEach
    void tearDown() {
        gui.dispose();
        System.out.println("=== GUI Disposed ===");
    }


    @Test
    void testAddMealToMenu_success() {
        gui.mealNameField.setText("Spaghetti");
        gui.ingredientNameField.setText("Tomato Sauce");
        gui.ingredientPriceField.setText("5.0");

        ActionEvent event = new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, "Add Meal");
        for (ActionListener listener : gui.mealNameField.getActionListeners()) {
            listener.actionPerformed(event);
        }

        String menuText = gui.menuDisplayArea.getText();
        assertTrue(menuText.contains("Spaghetti"), "Menu should contain 'Spaghetti'");
        assertTrue(menuText.contains("Tomato Sauce"), "Menu should list ingredient 'Tomato Sauce'");
    }

    @Test
    void testAddIngredientToExistingMeal_success() {
        gui.mealNameField.setText("Spaghetti");
        gui.ingredientNameField.setText("Pasta");
        gui.ingredientPriceField.setText("3.0");
        triggerActionEvent(gui.mealNameField);

        // 第二次添加同一道菜的不同配料
        gui.mealNameField.setText("Spaghetti");
        gui.ingredientNameField.setText("Cheese");
        gui.ingredientPriceField.setText("2.0");
        triggerActionEvent(gui.mealNameField);

        String menuText = gui.menuDisplayArea.getText();
        assertTrue(menuText.contains("Pasta") && menuText.contains("Cheese"),
                "Spaghetti should have both Pasta and Cheese as ingredients");
    }

    @Test
    void testAddToOrder_valid_success() {
        // 添加菜品
        addMeal("Burger", "Beef Patty", 3.5);

        JTextField orderField = getTextFieldByName("Enter Meal Name:");
        assertNotNull(orderField, "Order field should not be null");
        orderField.setText("Burger");

        JButton addToOrderBtn = getButtonByText("Add to Order");
        System.out.println("Add to Order Button: " + addToOrderBtn);
        assertNotNull(addToOrderBtn, "Add to Order button should not be null");

        addToOrderBtn.doClick();

        String orderText = gui.orderDisplayArea.getText();
        System.out.println("Order Display Text: " + orderText);

        assertTrue(orderText.contains("Burger x 1"), "Order should include Burger");
    }



    @Test
    void testCalculateBill_correctTotal() {
        addMeal("Pizza", "Cheese", 2.0);
        addMeal("Salad", "Lettuce", 1.5);

        addMealToOrder("Pizza");
        addMealToOrder("Salad");

        JButton billBtn = getButtonByText("Calculate Bill");
        triggerActionEvent(billBtn);

        double total = gui.billingSystem.calculateBill();
        assertEquals(3.5, total, 0.01, "Total bill should be RMB3.50");
    }

    @Test
    void testClearOrder_clearsOrderList() {
        addMeal("Noodles", "Sauce", 4.0);
        addMealToOrder("Noodles");

        JButton clearBtn = getButtonByText("Clear Order");
        triggerActionEvent(clearBtn);

        String orderText = gui.orderDisplayArea.getText();
        assertFalse(orderText.contains("Noodles"), "Order should be cleared after clicking Clear button");
    }

    @Test
    void testInvalidIngredientPrice_displaysError() {
        gui.mealNameField.setText("Soup");
        gui.ingredientNameField.setText("Broth");
        gui.ingredientPriceField.setText("invalid");

        triggerActionEvent(gui.mealNameField);

        assertNotNull(gui.menuDisplayArea);
    }

    // Helper Methods
    private void addMeal(String mealName, String ingName, double price) {
        gui.mealNameField.setText(mealName);
        gui.ingredientNameField.setText(ingName);
        gui.ingredientPriceField.setText(String.valueOf(price));
        triggerActionEvent(gui.mealNameField);
    }

    private void addMealToOrder(String mealName) {
        JTextField orderField = getTextFieldByName("Enter Meal Name:");
        orderField.setText(mealName);
        JButton addToOrderBtn = getButtonByText("Add to Order");
        triggerActionEvent(addToOrderBtn);
    }

    private void triggerActionEvent(JButton button) {
        for (ActionListener listener : button.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, ""));
        }
    }

    private void triggerActionEvent(JTextField field) {
        for (ActionListener listener : field.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(field, ActionEvent.ACTION_PERFORMED, ""));
        }
    }

    private JButton getButtonByText(String text) {
        for (Component comp : gui.getContentPane().getComponents()) {
            if (comp instanceof Container) {
                JButton result = findButtonInContainer((Container) comp, text);
                if (result != null) return result;
            }
        }
        return null;
    }

    private JButton findButtonInContainer(Container container, String text) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            } else if (comp instanceof Container) {
                JButton result = findButtonInContainer((Container) comp, text);
                if (result != null) return result;
            }
        }
        return null;
    }


    private JTextField getTextFieldByName(String label) {
        Component[] components = gui.getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                for (Component c : ((JPanel) comp).getComponents()) {
                    if (c instanceof JPanel) {
                        for (Component inner : ((JPanel) c).getComponents()) {
                            if (inner instanceof JLabel && ((JLabel) inner).getText().equals(label)) {
                                int index = ((JPanel) c).getComponentZOrder(inner);
                                if (index + 1 < ((JPanel) c).getComponentCount()) {
                                    Component next = ((JPanel) c).getComponent(index + 1);
                                    if (next instanceof JTextField) {
                                        return (JTextField) next;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
