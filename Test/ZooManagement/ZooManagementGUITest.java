package ZooManagement;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class ZooManagementGUITest {

    private ZooManagementGUI gui;

    @BeforeEach
    void setUp() {

        gui = new ZooManagementGUI();
        gui.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        gui.dispose();
    }

    @Test
    void testAddAnimalDialog_addsAnimalSuccessfully() {
        SwingUtilities.invokeLater(() -> {
            Zoo southern = ZooManagementGUI.southernZone;
            int initialCount = southern.getCounter();


            JButton addAnimalButton = getAddAnimalButton();
            assertNotNull(addAnimalButton);


            for (ActionListener listener : addAnimalButton.getActionListeners()) {
                listener.actionPerformed(new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, ""));
            }


            Component dialog = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            assertTrue(dialog instanceof JDialog);

            JDialog addDialog = (JDialog) dialog;
            Container contentPane = addDialog.getContentPane();

            JComboBox<String> zooSelector = findComponentInContainer(contentPane, JComboBox.class);
            JTextField nameField = findComponentInContainer(contentPane, JTextField.class, 0);
            JTextField speciesField = findComponentInContainer(contentPane, JTextField.class, 1);
            JTextField ageField = findComponentInContainer(contentPane, JTextField.class, 2);
            JButton submitBtn = findComponentInContainer(contentPane, JButton.class, 0);


            nameField.setText("Leo");
            speciesField.setText("Lion");
            ageField.setText("5");

            zooSelector.setSelectedIndex(0); // Southern-Zone


            submitBtn.doClick();


            assertEquals(initialCount + 1, southern.getCounter());
            Animal added = southern.getAnimal(southern.getCounter() - 1);
            assertEquals("Leo", added.getName());
            assertEquals("Lion", added.getSpecies());
            assertEquals(5, added.getAge());

            addDialog.dispose();
        });
    }

    @Test
    void testRemoveAnimalDialog_removesAnimalSuccessfully() {
        SwingUtilities.invokeLater(() -> {
            Zoo southern = ZooManagementGUI.southernZone;
            int initialCount = southern.getCounter();


            JButton removeAnimalButton = getRemoveAnimalButton();
            assertNotNull(removeAnimalButton);

            for (ActionListener listener : removeAnimalButton.getActionListeners()) {
                listener.actionPerformed(new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, ""));
            }

            Component dialog = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            assertTrue(dialog instanceof JDialog);

            JDialog removeDialog = (JDialog) dialog;
            Container contentPane = removeDialog.getContentPane();

            JComboBox<String> animalSelector = findComponentInContainer(contentPane, JComboBox.class, 1);

            String animalToRemove = (String) animalSelector.getSelectedItem();
            assertNotNull(animalToRemove);

            JButton removeBtn = findComponentInContainer(contentPane, JButton.class, 0);
            removeBtn.doClick();


            assertEquals(initialCount - 1, southern.getCounter());
            assertEquals(-1, southern.findAnimal(animalToRemove));

            removeDialog.dispose();
        });
    }

    @Test
    void testMoveAnimalDialog_movesAnimalSuccessfully() {
        SwingUtilities.invokeLater(() -> {
            Zoo fromZoo = ZooManagementGUI.southernZone;
            Zoo toZoo = ZooManagementGUI.northernZone;
            int initialFromCount = fromZoo.getCounter();
            int initialToCount = toZoo.getCounter();


            JButton moveAnimalButton = getMoveAnimalButton();
            assertNotNull(moveAnimalButton);

            for (ActionListener listener : moveAnimalButton.getActionListeners()) {
                listener.actionPerformed(new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, ""));
            }

            Component dialog = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            assertTrue(dialog instanceof JDialog);

            JDialog moveDialog = (JDialog) dialog;
            Container contentPane = moveDialog.getContentPane();

            JComboBox<String> fromZooSelector = findComponentInContainer(contentPane, JComboBox.class, 0);
            JComboBox<String> animalSelector = findComponentInContainer(contentPane, JComboBox.class, 1);

            fromZooSelector.setSelectedIndex(0); // Southern-Zone
            String animalName = (String) animalSelector.getSelectedItem();
            assertNotNull(animalName);


            JTextField vehicleCost = findComponentInContainer(contentPane, JTextField.class, 0);
            JTextField fuelCost = findComponentInContainer(contentPane, JTextField.class, 1);
            JSpinner caretakerCount = findComponentInContainer(contentPane, JSpinner.class);

            vehicleCost.setText("1000");
            fuelCost.setText("200");
            caretakerCount.setValue(2);


            JButton confirmBtn = findComponentInContainer(contentPane, JButton.class, 0);
            confirmBtn.doClick();


            assertEquals(initialFromCount - 1, fromZoo.getCounter());
            assertEquals(initialToCount + 1, toZoo.getCounter());
            assertEquals(-1, fromZoo.findAnimal(animalName));
            assertTrue(toZoo.findAnimal(animalName) >= 0);

            moveDialog.dispose();
        });
    }

    @Test
    void testFindAnimalDialog_findsExistingAnimal() {
        SwingUtilities.invokeLater(() -> {

            JButton findAnimalButton = getFindAnimalButton();
            assertNotNull(findAnimalButton);

            for (ActionListener listener : findAnimalButton.getActionListeners()) {
                listener.actionPerformed(new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, ""));
            }

            Component dialog = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            assertTrue(dialog instanceof JDialog);

            JDialog findDialog = (JDialog) dialog;
            Container contentPane = findDialog.getContentPane();

            JTextField searchField = findComponentInContainer(contentPane, JTextField.class);
            JButton searchBtn = findComponentInContainer(contentPane, JButton.class);


            String existingName = ZooManagementGUI.southernZone.getAnimal(0).getName();
            searchField.setText(existingName);
            searchBtn.doClick();


            assertEquals(5, ZooManagementGUI.southernZone.getCounter());
            assertEquals(5, ZooManagementGUI.northernZone.getCounter());

            findDialog.dispose();
        });
    }

    @Test
    void testDisplayAnimalsDialog_displaysAllAnimals() {
        SwingUtilities.invokeLater(() -> {

            JButton displaySouthernBtn = getDisplaySouthernButton();
            assertNotNull(displaySouthernBtn);

            for (ActionListener listener : displaySouthernBtn.getActionListeners()) {
                listener.actionPerformed(new ActionEvent(gui, ActionEvent.ACTION_PERFORMED, ""));
            }

            Component dialog = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
            assertTrue(dialog instanceof JDialog);

            JDialog displayDialog = (JDialog) dialog;
            JTextArea textArea = (JTextArea) ((JScrollPane) ((JPanel) displayDialog.getContentPane().getComponent(0)).getComponent(0)).getViewport().getView();
            assertNotNull(textArea.getText());
            assertTrue(textArea.getText().contains("List of animals in Southern-Zone Zoo"));

            displayDialog.dispose();
        });
    }

    // === Helper Methods ===

    private <T extends Component> T findComponentInContainer(Container container, Class<T> componentClass) {
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp)) {
                return componentClass.cast(comp);
            }
        }
        fail("No component found of type: " + componentClass.getSimpleName());
        return null;
    }

    private <T extends Component> T findComponentInContainer(Container container, Class<T> componentClass, int index) {
        int count = 0;
        for (Component comp : container.getComponents()) {
            if (componentClass.isInstance(comp)) {
                if (count == index) {
                    return componentClass.cast(comp);
                }
                count++;
            }
        }
        fail("No component found at index: " + index + " of type: " + componentClass.getSimpleName());
        return null;
    }

    private JButton getDisplaySouthernButton() {
        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        return (JButton) panel.getComponent(0);
    }

    private JButton getMoveAnimalButton() {
        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        return (JButton) panel.getComponent(2);
    }

    private JButton getAddAnimalButton() {
        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        return (JButton) panel.getComponent(3);
    }

    private JButton getRemoveAnimalButton() {
        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        return (JButton) panel.getComponent(4);
    }

    private JButton getFindAnimalButton() {
        JPanel panel = (JPanel) gui.getContentPane().getComponent(0);
        return (JButton) panel.getComponent(5);
    }
}
