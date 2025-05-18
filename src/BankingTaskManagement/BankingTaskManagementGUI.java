package BankingTaskManagement;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BankingTaskManagementGUI extends JFrame{
    public JButton processTransactionButton;
    public JButton createAccountButton;

    protected JFrame frame;
    protected BankingTaskManager taskManager;

    protected JTextField accountNumberField, initialBalanceField, interestRateField;
    protected JTextField transactionAccountField, transactionAmountField;
    protected JComboBox<String> transactionTypeCombo;

    protected DefaultListModel<String> highPriorityModel, lowPriorityModel;
    protected JList<String> highPriorityList, lowPriorityList;
    protected JTextField taskInputField;

    protected DecimalFormat currencyFormat;

    public BankingTaskManagementGUI() {
        taskManager = new BankingTaskManager();
        currencyFormat = new DecimalFormat("RMB #,##0.00");
        initializeFrame();
    }

    protected void initializeFrame() {
        setTitle("Banking Task Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(new BorderLayout(10, 10));

        add(createAccountCreationPanel(), BorderLayout.NORTH);
        add(createTransactionPanel(), BorderLayout.CENTER);
        add(createTaskManagementPanel(), BorderLayout.SOUTH);
    }

    protected JPanel createAccountCreationPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Create Bank Account"));

        panel.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField(10);
        panel.add(accountNumberField);

        panel.add(new JLabel("Initial Balance:"));
        initialBalanceField = new JTextField(10);
        panel.add(initialBalanceField);

        panel.add(new JLabel("Annual Interest Rate (%):"));
        interestRateField = new JTextField(5);
        panel.add(interestRateField);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> createAccount());
        panel.add(createAccountButton);

        return panel;
    }

    protected void createAccount() {
        try {
            String accountNumber = accountNumberField.getText().trim();
            double initialBalance = Double.parseDouble(initialBalanceField.getText().trim());
            double interestRate = Double.parseDouble(interestRateField.getText().trim()) / 100;

            taskManager.createAccount(accountNumber, initialBalance, interestRate);

            JOptionPane.showMessageDialog(frame,
                "Account Created:\n" +
                "Number: " + accountNumber + "\n" +
                "Initial Balance: " + currencyFormat.format(initialBalance) + "\n" +
                "Interest Rate: " + (interestRate * 100) + "%",
                "Account Creation Successful",
                JOptionPane.INFORMATION_MESSAGE);

            accountNumberField.setText("");
            initialBalanceField.setText("");
            interestRateField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                "Please enter valid numbers for balance and interest rate.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                "Error creating account: " + ex.getMessage(),
                "Account Creation Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    protected JPanel createTransactionPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Banking Transactions"));

        panel.add(new JLabel("Account Number:"));
        transactionAccountField = new JTextField(10);
        panel.add(transactionAccountField);

        panel.add(new JLabel("Amount:"));
        transactionAmountField = new JTextField(10);
        panel.add(transactionAmountField);

        String[] transactionTypes = {"Deposit", "Withdraw"};
        transactionTypeCombo = new JComboBox<>(transactionTypes);
        panel.add(transactionTypeCombo);

        JButton transactionButton = new JButton("Process Transaction");
        transactionButton.addActionListener(e -> processTransaction());
        panel.add(transactionButton);

        return panel;
    }

    protected void processTransaction() {
        try {
            String accountNumber = transactionAccountField.getText().trim();
            double amount = Double.parseDouble(transactionAmountField.getText().trim());
            String transactionType = (String) transactionTypeCombo.getSelectedItem();

            if ("Deposit".equals(transactionType)) {
                taskManager.deposit(accountNumber, amount);
                updateTaskLists();
            } else {
                taskManager.withdraw(accountNumber, amount);
                updateTaskLists();
            }

            JOptionPane.showMessageDialog(frame,
                transactionType + " of " + currencyFormat.format(amount) +
                " to Account " + accountNumber + " successful!",
                "Transaction Completed",
                JOptionPane.INFORMATION_MESSAGE);

            transactionAccountField.setText("");
            transactionAmountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                "Please enter a valid transaction amount.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                "Transaction failed: " + ex.getMessage(),
                "Transaction Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    protected JPanel createTaskManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Task Management"));

        JPanel inputPanel = new JPanel(new FlowLayout());
        taskInputField = new JTextField(20);
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(e -> addTask());
        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskInputField);
        inputPanel.add(addTaskButton);

        highPriorityModel = new DefaultListModel<>();
        lowPriorityModel = new DefaultListModel<>();
        highPriorityList = new JList<>(highPriorityModel);
        lowPriorityList = new JList<>(lowPriorityModel);

        JPanel listPanel = new JPanel(new GridLayout(1, 2));
        listPanel.add(new JScrollPane(highPriorityList));
        listPanel.add(new JScrollPane(lowPriorityList));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton removeTaskButton = new JButton("Remove Task");
        JButton changeTaskPriorityButton = new JButton("Change Priority");
        removeTaskButton.addActionListener(e -> removeTask());
        changeTaskPriorityButton.addActionListener(e -> changePriority());
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(changeTaskPriorityButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(listPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    protected void addTask() {
        String task = taskInputField.getText().trim();
        if (!task.isEmpty()) {
            taskManager.addTask(task);
            updateTaskLists();
            taskInputField.setText("");
        }
    }

    protected void removeTask() {
        if (!highPriorityList.isSelectionEmpty()) {
            int index = highPriorityList.getSelectedIndex();
            taskManager.removeTask("high", index);
        } else if (!lowPriorityList.isSelectionEmpty()) {
            int index = lowPriorityList.getSelectedIndex();
            taskManager.removeTask("low", index);
        }
        updateTaskLists();
    }

    protected void changePriority() {
        if (!highPriorityList.isSelectionEmpty()) {
            int index = highPriorityList.getSelectedIndex();
            taskManager.changePriority("high", index);
        } else if (!lowPriorityList.isSelectionEmpty()) {
            int index = lowPriorityList.getSelectedIndex();
            taskManager.changePriority("low", index);
        }
        updateTaskLists();
    }

    protected void updateTaskLists() {
        highPriorityModel.clear();
        lowPriorityModel.clear();

        for (String task : taskManager.getHighPriorityTasks()) {
            highPriorityModel.addElement(task);
        }

        for (String task : taskManager.getLowPriorityTasks()) {
            lowPriorityModel.addElement(task);
        }
    }
}
