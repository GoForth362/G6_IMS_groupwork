package BankingTaskManagement;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class BankingTaskManagementGUITest {

    private BankingTaskManagementGUI gui;

    @BeforeEach
    void setUp() {
        gui = new BankingTaskManagementGUI();
        gui.setVisible(true);

        // 验证关键组件是否非空
        assertNotNull(gui.accountNumberField);
        assertNotNull(gui.initialBalanceField);
        assertNotNull(gui.interestRateField);
    }

    @AfterEach
    void tearDown() {
        gui.dispose();
    }

    @Test
    void testCreateAccount_directCall_success() {
        gui.taskManager.createAccount("ACC001", 1000, 5.0);
        assertNotNull(gui.taskManager.accounts.get("ACC001"));
        assertEquals(1000.0, gui.taskManager.accounts.get("ACC001").getBalance(), 0.01);
    }


    @Test
    void testDeposit_directCall_success() {
        gui.taskManager.createAccount("ACC002", 500, 3.0);
        gui.taskManager.deposit("ACC002", 200);

        assertEquals(700.0, gui.taskManager.accounts.get("ACC002").getBalance(), 0.01);
    }


    @Test
    void testWithdraw_directCall_success() {
        gui.taskManager.createAccount("ACC003", 1000, 3.0);
        gui.taskManager.withdraw("ACC003", 300);

        assertEquals(700.0, gui.taskManager.accounts.get("ACC003").getBalance(), 0.01);
    }


    @Test
    void testAddAndRemoveTask_success() {
        gui.taskInputField.setText("Pay Electricity Bill");
        JButton addTaskButton = getAddTaskButton();
        triggerActionEvent(addTaskButton);

        assertTrue(gui.taskManager.getLowPriorityTasks().contains("Pay Electricity Bill"));

        int index = gui.lowPriorityList.getModel().getSize() - 1;
        gui.lowPriorityList.setSelectedIndex(index);
        JButton removeTaskButton = getRemoveTaskButton();
        triggerActionEvent(removeTaskButton);

        assertFalse(gui.taskManager.getLowPriorityTasks().contains("Pay Electricity Bill"));
    }

    @Test
    void testChangeTaskPriority_success() {
        gui.taskInputField.setText("Renew Subscription");
        JButton addTaskButton = getAddTaskButton();
        triggerActionEvent(addTaskButton);

        gui.lowPriorityList.setSelectedIndex(0);
        JButton changePriorityButton = getChangePriorityButton();
        triggerActionEvent(changePriorityButton);

        assertTrue(gui.taskManager.getHighPriorityTasks().contains("Renew Subscription"));
    }

    // === Helper Methods ===

    private JButton getCreateAccountButton() {
        JPanel panel = (JPanel) ((JPanel) gui.getContentPane().getComponent(0)).getComponent(0);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Create Account")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JButton getProcessTransactionButton() {
        JPanel panel = (JPanel) ((JPanel) gui.getContentPane().getComponent(1)).getComponent(0);
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Process Transaction")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JButton getAddTaskButton() {
        JPanel inputPanel = (JPanel) ((JPanel) gui.getContentPane().getComponent(2)).getComponent(0);
        for (Component comp : inputPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Add Task")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JButton getRemoveTaskButton() {
        JPanel buttonPanel = (JPanel) ((JPanel) gui.getContentPane().getComponent(2)).getComponent(2);
        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Remove Task")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private JButton getChangePriorityButton() {
        JPanel buttonPanel = (JPanel) ((JPanel) gui.getContentPane().getComponent(2)).getComponent(2);
        for (Component comp : buttonPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Change Priority")) {
                return (JButton) comp;
            }
        }
        return null;
    }

    private void triggerActionEvent(JButton button) {
        for (ActionListener listener : button.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, ""));
        }
    }
}
