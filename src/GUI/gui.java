package GUI;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class gui{
    public static void main(String[] args) throws Exception {
        //Theme
        UIManager.setLookAndFeel(new MetalLookAndFeel());
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












    }
}

