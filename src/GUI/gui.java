package GUI;

import javax.swing.*;

import BankingTaskManagement.BankingTaskListGUI;
import RestaurantManagement.RestaurantManagementGUI;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class gui{
    public static void main(String[] args) throws Exception {
        //Theme
        FlatMacLightLaf.setup();
        //Initialization window
        JFrame frame = new JFrame("Integrated Management System (IMS)");
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null); // Centered display
        //Set the mouse
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //layout
        frame.setLayout(new GridLayout(4, 1));


        //Exit prompt
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int value = JOptionPane.showConfirmDialog(frame,"Do you want to exit?","Hint",JOptionPane.YES_NO_OPTION);
                if (value == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });

        frame.setVisible(true);

        //Add button
        JButton button1 = new JButton("Zoo Management");
        frame.add(button1);
        JButton button2 = new JButton("Banking Task Management");
        frame.add(button2);
        JButton button3 = new JButton("Restaurant Management");
        frame.add(button3);
        JButton button4 = new JButton("Shape Parsing & Analysis");
        frame.add(button4);

        button3.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                BankingTaskListGUI bankGUI = new BankingTaskListGUI();
                bankGUI.setVisible(true);

                //退出提示
                bankGUI.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                bankGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        int value = JOptionPane.showConfirmDialog(bankGUI,"Do you want to exit?","Hint",JOptionPane.YES_NO_OPTION);
                        if (value == JOptionPane.OK_OPTION){
                            bankGUI.dispose();
                        }
                    }
                });

            });
        });
        frame.setVisible(true);

    }
}

