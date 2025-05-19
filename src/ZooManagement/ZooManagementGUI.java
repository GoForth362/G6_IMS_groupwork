package ZooManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Main window class (the only public class)
public class ZooManagementGUI extends JFrame{
    // Initialize two zoo instances
    protected static Zoo southernZone = new Zoo("Southern-Zone Zoo");
    protected static Zoo northernZone = new Zoo("Northern-Zone Zoo");

    public ZooManagementGUI() {
        setTitle("Zoo Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centered display
        //Set the mouse
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(new BorderLayout());

        add(new MainMenuPanel(), BorderLayout.CENTER);

        // Initialize the animal data
        initializeZoos();
    }

    private static void initializeZoos() {
        southernZone.addAnimal(new Animal("Simba", "African Lion", 6));
        southernZone.addAnimal(new Animal("Dumbo", "African Elephant", 12));
        southernZone.addAnimal(new Animal("Luna", "Gray Wolf", 4));
        southernZone.addAnimal(new Animal("Poe", "Raven", 3));
        southernZone.addAnimal(new Animal("Benny", "Grizzly Bear", 8));

        northernZone.addAnimal(new Animal("Arctic", "Polar Bear", 7));
        northernZone.addAnimal(new Animal("Blizzard", "Snow Leopard", 5));
        northernZone.addAnimal(new Animal("Frost", "Arctic Fox", 3));
        northernZone.addAnimal(new Animal("Penguin", "Emperor Penguin", 4));
        northernZone.addAnimal(new Animal("Aurora", "Caribou", 6));
    }

    // Main menu panel
    class MainMenuPanel extends JPanel {
        public MainMenuPanel() {
            setLayout(new GridLayout(7, 1));

            JButton displaySouthern = new JButton("Display Southern-Zone Zoo Animals");
            JButton displayNorthern = new JButton("Display Northern-Zone Zoo Animals");
            JButton moveAnimal = new JButton("Move Animal Between Zoos");
            JButton addAnimal = new JButton("Add New Animal to a Zoo");
            JButton removeAnimal = new JButton("Remove Animal from a Zoo");
            JButton findAnimal = new JButton("Find an Animal");
            JButton displayChart = new JButton("Display Animal Counts as Bar Chart");

            add(displaySouthern);
            add(displayNorthern);
            add(displayChart);
            add(moveAnimal);
            add(addAnimal);
            add(removeAnimal);
            add(findAnimal);

            displaySouthern.addActionListener(e -> new DisplayAnimalsDialog(southernZone));
            displayNorthern.addActionListener(e -> new DisplayAnimalsDialog(northernZone));
            displayChart.addActionListener(e -> new AnimalCountChartDialog());
            moveAnimal.addActionListener(e -> new MoveAnimalDialog());
            addAnimal.addActionListener(e -> new AddAnimalDialog());
            removeAnimal.addActionListener(e -> new RemoveAnimalDialog());
            findAnimal.addActionListener(e -> new FindAnimalDialog());
        }
    }

    // Display Animal List Dialog Box (Modified Version)
    class DisplayAnimalsDialog extends JDialog {
        public DisplayAnimalsDialog(Zoo zoo) {
            super((Frame) null, "Animals in " + zoo.getName(), true);
            setLayout(new BorderLayout());
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            textArea.setForeground(Color.DARK_GRAY);

            if (zoo.getCounter() == 0) {
                textArea.append("There are no animals in this zoo.");
            } else {
                textArea.append("List of animals in " + zoo.getName() + ":\n\n");
                for (int i = 0; i < zoo.getCounter(); i++) {
                    Animal animal = zoo.getAnimal(i);
                    textArea.append(animal.toString() + "\n----------------------------------------\n");
                }
            }

            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);

            // Bottom button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(e -> dispose());
            buttonPanel.add(closeBtn);

            add(buttonPanel, BorderLayout.SOUTH);
            pack(); // Automatically adjust the window size
            setVisible(true);
        }
    }

    class AnimalCountChartDialog extends JDialog {
        public AnimalCountChartDialog() {
            super((Frame) null, "Animal Count Chart", true);
            setSize(500, 400);
            setLocationRelativeTo(null);
            add(new AnimalCountChartPanel());
            setVisible(true);
        }

        class AnimalCountChartPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Set background color
                setBackground(Color.WHITE);

                // Zoo data
                int southernCount = southernZone.getCounter();
                int northernCount = northernZone.getCounter();

                // Bar chart parameters
                int barWidth = 80;
                int maxBarHeight = 200;
                int spacing = 100;
                int startX = 100;
                int startY = getHeight() - 50;

                // Draw Southern-Zone bar
                g2d.setColor(Color.BLUE);
                int southernHeight = (int) ((double) southernCount / Math.max(southernCount, northernCount) * maxBarHeight);
                g2d.fillRect(startX, startY - southernHeight, barWidth, southernHeight);
                g2d.drawString("Southern-Zone: " + southernCount, startX, startY - southernHeight - 10);

                // Draw Northern-Zone bar
                g2d.setColor(Color.GREEN);
                int northernHeight = (int) ((double) northernCount / Math.max(southernCount, northernCount) * maxBarHeight);
                g2d.fillRect(startX + spacing, startY - northernHeight, barWidth, northernHeight);
                g2d.drawString("Northern-Zone: " + northernCount, startX + spacing, startY - northernHeight - 10);
            }
        }
    }

    //Add the animal dialog box
    class AddAnimalDialog extends JDialog {
        protected JTextField nameField, speciesField, ageField;
        protected JComboBox<String> zooSelector;
        public AddAnimalDialog() {
            super((Frame) null, "Add New Animal", true);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(5, 2));

            zooSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});
            add(new JLabel("Zoo:"));
            add(zooSelector);

            nameField = new JTextField();
            speciesField = new JTextField();
            ageField = new JTextField();

            add(new JLabel("Name:"));
            add(nameField);
            add(new JLabel("Species:"));
            add(speciesField);
            add(new JLabel("Age:"));
            add(ageField);

            JButton submit = new JButton("Add Animal");
            JButton cancel = new JButton("Cancel");

            submit.addActionListener(e -> {
                try {
                    String name = nameField.getText();
                    String species = speciesField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    Zoo selectedZoo = (zooSelector.getSelectedIndex() == 0) ? southernZone : northernZone;
                    selectedZoo.addAnimal(new Animal(name, species, age));
                    JOptionPane.showMessageDialog(this, "Animal added successfully.");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            cancel.addActionListener(e -> dispose());

            add(submit);
            add(cancel);
            setVisible(true);
        }
    }

    // Move the animal dialog box
    class MoveAnimalDialog extends JDialog {
        protected JComboBox<String> fromZooSelector, animalSelector;
        protected JTextField vehicleCost, fuelCost;
        protected JSpinner caretakerCount;

        public MoveAnimalDialog() {
            super((Frame) null, "Move Animal", true);
            setSize(500, 400);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(6, 2));

            fromZooSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});
            fromZooSelector.addActionListener(this::updateAnimalList);
            add(new JLabel("From Zoo:"));
            add(fromZooSelector);

            animalSelector = new JComboBox<>();
            add(new JLabel("Select Animal:"));
            add(animalSelector);

            vehicleCost = new JTextField();
            fuelCost = new JTextField();
            caretakerCount = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));

            add(new JLabel("Vehicle Cost:"));
            add(vehicleCost);
            add(new JLabel("Fuel Cost:"));
            add(fuelCost);
            add(new JLabel("Caretakers (1-3):"));
            add(caretakerCount);

            JButton confirm = new JButton("Confirm Move");
            JButton cancel = new JButton("Cancel");

            confirm.addActionListener(e -> performMove());
            cancel.addActionListener(e -> dispose());

            add(confirm);
            add(cancel);
            updateAnimalList(null);
            setVisible(true);
        }

        private void updateAnimalList(ActionEvent e) {
            Zoo zoo = (fromZooSelector.getSelectedIndex() == 0) ? southernZone : northernZone;
            animalSelector.removeAllItems();
            for (int i = 0; i < zoo.getCounter(); i++) {
                animalSelector.addItem(zoo.getAnimal(i).getName());
            }
        }

        private void performMove() {
            try {
                Zoo fromZoo = (fromZooSelector.getSelectedIndex() == 0) ? southernZone : northernZone;
                Zoo toZoo = (fromZooSelector.getSelectedIndex() == 0) ? northernZone : southernZone;
                String animalName = (String) animalSelector.getSelectedItem();

                double vehiclePrice = Double.parseDouble(vehicleCost.getText());
                double fuelPrice = Double.parseDouble(fuelCost.getText());
                int caretakersCount = (Integer) caretakerCount.getValue();

                Item vehicle = new Item("Truck", "V1");
                vehicle.setPrice(vehiclePrice);

                Item fuel = new Item("Diesel", "F1");
                fuel.setPrice(fuelPrice);

                String[] caretakers = new String[caretakersCount];
                for (int i = 0; i < caretakersCount; i++) {
                    caretakers[i] = JOptionPane.showInputDialog(this, "Enter caretaker " + (i+1) + " name:");
                }

                Logistics logistics = new Logistics(fuel, vehicle, caretakers);
                fromZoo.moveAnimal(animalName, toZoo, logistics);
                JOptionPane.showMessageDialog(this, "Animal moved successfully.");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid costs.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Delete the animal dialog box
    class RemoveAnimalDialog extends JDialog {
        protected JComboBox<String> zooSelector, animalSelector;

        public RemoveAnimalDialog() {
            super((Frame) null, "Remove Animal", true);
            setSize(400, 200);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(3, 2));

            zooSelector = new JComboBox<>(new String[]{"Southern-Zone", "Northern-Zone"});
            zooSelector.addActionListener(this::updateAnimalList);
            add(new JLabel("Zoo:"));
            add(zooSelector);

            animalSelector = new JComboBox<>();
            add(new JLabel("Animal:"));
            add(animalSelector);

            JButton removeBtn = new JButton("Remove");
            JButton cancelBtn = new JButton("Cancel");

            removeBtn.addActionListener(e -> {
                Zoo zoo = (zooSelector.getSelectedIndex() == 0) ? southernZone : northernZone;
                String animalName = (String) animalSelector.getSelectedItem();
                if (animalName != null) {
                    zoo.deleteAnimal(animalName);
                    JOptionPane.showMessageDialog(this, "Animal removed.");
                }
                dispose();
            });

            cancelBtn.addActionListener(e -> dispose());

            add(removeBtn);
            add(cancelBtn);
            updateAnimalList(null);
            setVisible(true);
        }

        private void updateAnimalList(ActionEvent e) {
            Zoo zoo = (zooSelector.getSelectedIndex() == 0) ? southernZone : northernZone;
            animalSelector.removeAllItems();
            for (int i = 0; i < zoo.getCounter(); i++) {
                animalSelector.addItem(zoo.getAnimal(i).getName());
            }
        }
    }

    // Search for the animal dialog box
    class FindAnimalDialog extends JDialog {
        protected JTextField searchField;

        public FindAnimalDialog() {
            super((Frame) null, "Find Animal", true);
            setSize(400, 150);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JPanel inputPanel = new JPanel(new FlowLayout());
            inputPanel.add(new JLabel("Enter Animal Name:"));
            searchField = new JTextField(20);
            inputPanel.add(searchField);

            JButton searchBtn = new JButton("Search");
            searchBtn.addActionListener(this::searchAnimal);

            add(inputPanel, BorderLayout.CENTER);
            add(searchBtn, BorderLayout.SOUTH);
            setVisible(true);
        }

        private void searchAnimal(ActionEvent e) {
            String name = searchField.getText();
            int indexSouthern = southernZone.findAnimal(name);
            int indexNorthern = northernZone.findAnimal(name);

            if (indexSouthern != -1) {
                JOptionPane.showMessageDialog(this, "Found in Southern-Zone:\n" + southernZone.getAnimal(indexSouthern).toString());
            } else if (indexNorthern != -1) {
                JOptionPane.showMessageDialog(this, "Found in Northern-Zone:\n" + northernZone.getAnimal(indexNorthern).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Not found.");
            }
            dispose();
        }
    }
}

